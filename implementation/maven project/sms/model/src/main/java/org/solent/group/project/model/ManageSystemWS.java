/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.group.project.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author anton
 */
public class ManageSystemWS {
    //downloading all the data about the user from database and putting it into java classes
    public User logIn(User newUser) throws MalformedURLException, IOException, ParseException{
        //we are getting username and password from the log in page
        User userFromData=new User();
        if(newUser==null){
            return null;
        }
        String username=newUser.getUsername();
        //connects to database and gets the password of the user with matching username
        URL url = new URL("https://smspdata.firebaseio.com/users/"+username+"/password.json");
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        InputStream is=conn.getInputStream();
        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String all = "", line;
            while ((line = br.readLine()) != null) {
                all += line;
            }
            //compares passwords and calls getUser function if passwords match
            //otherwise returns null
            String comparePass=all.substring(1,all.length()-1);
            conn.disconnect();
            if(comparePass.equals(newUser.getPassword())){
                userFromData.setPassword(newUser.getPassword());
                userFromData.setUsername(username);
                url = new URL("https://smspdata.firebaseio.com/users/"+username+"/data/type.json");
                conn=(HttpsURLConnection) url.openConnection();
                is=conn.getInputStream();
                if (conn.getResponseCode() == 200) {
                    br = new BufferedReader(new InputStreamReader(is));
                    line = br.readLine();
                    String userType=line.substring(1,line.length()-1);
                    userFromData.setType(userType);
                }
                conn.disconnect();
                return userFromData;
            }
        }
        return null;
    }
    public Pupil logPupil(User newUser) throws IOException, ParseException{
        Pupil newPupil=new Pupil();
        newPupil.setUsername(newUser.getUsername());
        if(newUser.getPassword()!=null){
            newPupil.setPassword(newUser.getPassword());
        }
        newPupil.setType(newUser.getType());
        URL url = new URL("https://smspdata.firebaseio.com/users/"+newPupil.getUsername()+"/data.json");
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        InputStream is=conn.getInputStream();
        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String all = "", line;
            while ((line = br.readLine()) != null) {
                all += line;
            }
            JSONParser parser=new JSONParser();
            Object obj=parser.parse(all);
            JSONObject jsonUser=(JSONObject) obj;
            newPupil.setFirst_name((String)jsonUser.get("first_name"));
            newPupil.setLast_name((String)jsonUser.get("last_name"));
            newPupil.setPersonalActivities(getActivities(newPupil.getUsername())); 
        }
        conn.disconnect();
        return newPupil;
    }
    public Parent logParent(User newUser) throws IOException, ParseException{
        Parent newParent=new Parent();
        newParent.setUsername(newUser.getUsername());
        if(newUser.getPassword()!=null){
            newParent.setPassword(newUser.getPassword());
        }
        newParent.setType(newUser.getType());
        URL url = new URL("https://smspdata.firebaseio.com/users/"+newParent.getUsername()+"/data.json");
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        InputStream is=conn.getInputStream();
        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String all = "", line;
            while ((line = br.readLine()) != null) {
                all += line;
            }
            conn.disconnect();
            JSONParser parser=new JSONParser();
            Object obj=parser.parse(all);
            JSONObject jsonUser=(JSONObject) obj;
            newParent.setFirst_name((String)jsonUser.get("first_name"));
            newParent.setLast_name((String)jsonUser.get("last_name"));
            String manages=(String)jsonUser.get("manages");
            url = new URL("https://smspdata.firebaseio.com/managment/"+manages+"/pupils.json");
            conn=(HttpsURLConnection) url.openConnection();
            is=conn.getInputStream();
            if(conn.getResponseCode()==200){
                br = new BufferedReader(new InputStreamReader(is));
                all = "";
                while ((line = br.readLine()) != null) {
                    all += line;
                }
                JSONParser parser2=new JSONParser();
                Object obj2=parser.parse(all);
                JSONArray pupilArray=(JSONArray) obj2;
                if(!pupilArray.isEmpty()){
                    Iterator i=pupilArray.iterator();
                    while (i.hasNext()){
                        JSONObject loopObject=(JSONObject) i.next();
                        String pupilUsername=(String) loopObject.get("pupil");
                        User newPupil=new User();
                        newPupil.setUsername(pupilUsername);
                        newPupil.setType("PUPIL");
                        newParent.addPupil(logPupil(newPupil));
                    }
                }
            }
            conn.disconnect();
        }
        return newParent;
    }
    
    public Teacher logTeacher(User newUser) throws IOException, ParseException{
        Teacher newTeacher=new Teacher();
        newTeacher.setUsername(newUser.getUsername());
        if(newUser.getPassword()!=null){
            newTeacher.setPassword(newUser.getPassword());
        }
        newTeacher.setType(newUser.getType());
        URL url = new URL("https://smspdata.firebaseio.com/users/"+newTeacher.getUsername()+"/data.json");
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        InputStream is=conn.getInputStream();
        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String all = "", line;
            while ((line = br.readLine()) != null) {
                all += line;
            }
            conn.disconnect();
            JSONParser parser=new JSONParser();
            Object obj=parser.parse(all);
            JSONObject jsonUser=(JSONObject) obj;
            newTeacher.setFirst_name((String)jsonUser.get("first_name"));
            newTeacher.setLast_name((String)jsonUser.get("last_name"));
            newTeacher.setPersonalActivities(getActivities(newTeacher.getUsername()));
            String manages=(String)jsonUser.get("manages");
            url = new URL("https://smspdata.firebaseio.com/managment/"+manages+"/pupils.json");
            conn=(HttpsURLConnection) url.openConnection();
            is=conn.getInputStream();
            if(conn.getResponseCode()==200){
                br = new BufferedReader(new InputStreamReader(is));
                all = "";
                while ((line = br.readLine()) != null) {
                    all += line;
                }
                JSONParser parser2=new JSONParser();
                Object obj2=parser.parse(all);
                JSONArray pupilArray=(JSONArray) obj2;
                if(!pupilArray.isEmpty()){
                    Iterator i=pupilArray.iterator();
                    while (i.hasNext()){
                        JSONObject loopObject=(JSONObject) i.next();
                        String pupilUsername=(String) loopObject.get("pupil");
                        User newPupil=new User();
                        newPupil.setUsername(pupilUsername);
                        newPupil.setType("PUPIL");
                        newTeacher.createPupil(logPupil(newPupil));
                    }
                }
            }
            conn.disconnect();
        }
        return newTeacher;
    }
    public Board logBoard(User newUser) throws IOException, ParseException{
        Board newBoard=new Board();
        newBoard.setUsername(newUser.getUsername());
        if(newUser.getPassword()!=null){
            newBoard.setPassword(newUser.getPassword());
        }
        newBoard.setType(newUser.getType());
        URL url = new URL("https://smspdata.firebaseio.com/users/"+newBoard.getUsername()+"/data.json");
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        InputStream is=conn.getInputStream();
        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String all = "", line;
            while ((line = br.readLine()) != null) {
                all += line;
            }
            conn.disconnect();
            JSONParser parser=new JSONParser();
            Object obj=parser.parse(all);
            JSONObject jsonUser=(JSONObject) obj;
            newBoard.setFirst_name((String)jsonUser.get("first_name"));
            newBoard.setLast_name((String)jsonUser.get("last_name"));
            newBoard.setPersonalActivities(getActivities(newBoard.getUsername()));
            String manages=(String)jsonUser.get("manages");
            url = new URL("https://smspdata.firebaseio.com/managment/"+manages+"/classes.json");
            conn=(HttpsURLConnection) url.openConnection();
            is=conn.getInputStream();
            if(conn.getResponseCode()==200){
                br = new BufferedReader(new InputStreamReader(is));
                all = "";
                while ((line = br.readLine()) != null) {
                    all += line;
                }
                JSONParser parser2=new JSONParser();
                Object obj2=parser.parse(all);
                JSONArray teacherArray=(JSONArray) obj2;
                if(!teacherArray.isEmpty()){
                    Iterator i=teacherArray.iterator();
                    while (i.hasNext()){
                        JSONObject loopObject=(JSONObject) i.next();
                        String teacherUsername=(String) loopObject.get("class");
                        teacherUsername=teacherUsername.substring(1);
                        User newTeacher=new User();
                        newTeacher.setUsername(teacherUsername);
                        newTeacher.setType("TEACHER");
                        newBoard.createTeacher(logTeacher(newTeacher));
                    }
                }
            }
            conn.disconnect();
            url = new URL("https://smspdata.firebaseio.com/managment/"+manages+"/parents.json");
            conn=(HttpsURLConnection) url.openConnection();
            is=conn.getInputStream();
            if(conn.getResponseCode()==200){
                br = new BufferedReader(new InputStreamReader(is));
                all = "";
                while ((line = br.readLine()) != null) {
                    all += line;
                }
                JSONParser parser2=new JSONParser();
                Object obj2=parser.parse(all);
                JSONArray parentArray=(JSONArray) obj2;
                if(!parentArray.isEmpty()){
                    Iterator i=parentArray.iterator();
                    while (i.hasNext()){
                        JSONObject loopObject=(JSONObject) i.next();
                        String parentUsername=(String) loopObject.get("parent");
                        parentUsername=parentUsername.substring(1);
                        User newParent=new User();
                        newParent.setUsername(parentUsername);
                        newParent.setType("PARENT");
                        newBoard.createParent(logParent(newParent));
                    }
                }
            }
            conn.disconnect();
        }
        return newBoard;
        
    }
    public Admin logAdmin(User newUser) throws IOException, ParseException{
        Admin newAdmin=new Admin();
        newAdmin.setUsername(newUser.getUsername());
        if(newUser.getPassword()!=null){
            newAdmin.setPassword(newUser.getPassword());
        }
        newAdmin.setType(newUser.getType());
        URL url = new URL("https://smspdata.firebaseio.com/users/"+newAdmin.getUsername()+"/data.json");
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        InputStream is=conn.getInputStream();
        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String all = "", line;
            while ((line = br.readLine()) != null) {
                all += line;
            }
            conn.disconnect();
            JSONParser parser=new JSONParser();
            Object obj=parser.parse(all);
            JSONObject jsonUser=(JSONObject) obj;
            newAdmin.setFirst_name((String)jsonUser.get("first_name"));
            newAdmin.setLast_name((String)jsonUser.get("last_name"));
            String manages=(String)jsonUser.get("manages");
            url = new URL("https://smspdata.firebaseio.com/managment/~all/schools.json");
            conn=(HttpsURLConnection) url.openConnection();
            is=conn.getInputStream();
            if(conn.getResponseCode()==200){
                br = new BufferedReader(new InputStreamReader(is));
                all = "";
                while ((line = br.readLine()) != null) {
                    all += line;
                }
                JSONParser parser2=new JSONParser();
                Object obj2=parser.parse(all);
                JSONArray boardArray=(JSONArray) obj2;
                if(!boardArray.isEmpty()){
                    Iterator i=boardArray.iterator();
                    while (i.hasNext()){
                        JSONObject loopObject=(JSONObject) i.next();
                        String boardUsername=(String) loopObject.get("school");
                        boardUsername=boardUsername.substring(1);
                        User newBoard=new User();
                        newBoard.setUsername(boardUsername);
                        newBoard.setType("BOARD");
                        newAdmin.createBoard(logBoard(newBoard));
                    }
                }
            }
            conn.disconnect();
        }
        return newAdmin;
    }

    private ActivityList getActivities(String username) throws MalformedURLException, IOException, ParseException {
        URL url = new URL("https://smspdata.firebaseio.com/user_act/"+username+"/.json");
        ActivityList newList=new ActivityList();
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        InputStream is=conn.getInputStream();
        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String all = "", line;
            while ((line = br.readLine()) != null) {
                all += line;
            }
            conn.disconnect();
            JSONParser parser=new JSONParser();
            Object obj=parser.parse(all);
            JSONArray manageArray=(JSONArray) obj;
            Iterator i=manageArray.iterator();
            while(i.hasNext()){
                JSONObject activity=(JSONObject) i.next();
                Activity loopAct=new Activity();
                loopAct.setPupilComment((String) activity.get("comment"));
                loopAct.getPayment().setPaid((boolean) activity.get("paid"));
                long idForAct=(long) activity.get("ID");
                int actId=(int)idForAct;
                loopAct.setActId(actId);
                url = new URL("https://smspdata.firebaseio.com/activity/"+actId+"/.json");
                conn=(HttpsURLConnection) url.openConnection();
                is=conn.getInputStream();
                if (conn.getResponseCode() == 200) {
                    br = new BufferedReader(new InputStreamReader(is));
                    all = "";
                    while ((line = br.readLine()) != null) {
                        all += line;
                    }
                    JSONParser parser2=new JSONParser();
                    Object obj2=parser.parse(all);
                    JSONObject actObj=(JSONObject) obj2;
                    loopAct.setAuthor((String) actObj.get("author"));
                    loopAct.setTask((String) actObj.get("task"));
                    String freeCheck=(String) actObj.get("free");
                    if(freeCheck.equals("true")){
                        loopAct.setFree(true);
                        loopAct.getPayment().setPrice(0);
                    }else{
                        loopAct.setFree(false);
                        String price=(String) actObj.get("price");
                        loopAct.getPayment().setPrice(Double.parseDouble(price));
                    }
                }
                newList.insertActivity(loopAct);
            }
        } 
        return newList;
    }
    public Admin createBoard(Board newUser, Admin creator) throws MalformedURLException, ProtocolException, IOException{
        String firstName=newUser.getFirst_name();
        String lastName=newUser.getLast_name();
        String password=newUser.getPassword();
        String username=newUser.getUsername();
        String type="BOARD";
        String manages="_"+username;
        String postMessage="{"
                    + "\"password\": \""+password+"\","
                    + " \"data\": {"
                    + "\"first_name\": \""+firstName+"\","
                    + "\"last_name\": \""+lastName+"\","
                    + "\"type\": \""+type+"\","
                    + "\"manages\": \""+manages+"\""
                    + "}"
                    + "}";
        URL url = new URL("https://smspdata.firebaseio.com/users/"+username+".json");
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        OutputStream out=conn.getOutputStream();
        out.write(postMessage.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        //get size of Board list from creator
        int size=creator.getBoardList().getListSize();
        url = new URL("https://smspdata.firebaseio.com/managment/~all/schools/"+size+"/.json");
        conn=(HttpsURLConnection) url.openConnection();
        postMessage="{\"school\":\""+manages+"\"}";
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        out=conn.getOutputStream();
        out.write(postMessage.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        url = new URL("https://smspdata.firebaseio.com/managment/"+manages+".json");
        conn=(HttpsURLConnection) url.openConnection();
        postMessage="{\"board\":\""+username+"\"}";
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        out=conn.getOutputStream();
        out.write(postMessage.getBytes());
        conn.disconnect();
        creator.createBoard(newUser);
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        return creator;
    }
    public Board createTeacher(Teacher newUser,Board creator) throws MalformedURLException, ProtocolException, IOException{
        String firstName=newUser.getFirst_name();
        String lastName=newUser.getLast_name();
        String password=newUser.getPassword();
        String username=newUser.getUsername();
        String type="TEACHER";
        String manages="-"+username;
        String postMessage="{"
                    + "\"password\": \""+password+"\","
                    + " \"data\": {"
                    + "\"first_name\": \""+firstName+"\","
                    + "\"last_name\": \""+lastName+"\","
                    + "\"type\": \""+type+"\","
                    + "\"manages\": \""+manages+"\""
                    + "}"
                    + "}";
        URL url = new URL("https://smspdata.firebaseio.com/users/"+username+".json");
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        OutputStream out=conn.getOutputStream();
        out.write(postMessage.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        //get size of Teacher list from creator
        int size=creator.getTeacherList().getListSize();
        url = new URL("https://smspdata.firebaseio.com/managment/_"+creator.getUsername()+"/classes/"+size+".json");
        conn=(HttpsURLConnection) url.openConnection();
        postMessage="{\"class\":\""+manages+"\"}";
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        out=conn.getOutputStream();
        out.write(postMessage.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        url = new URL("https://smspdata.firebaseio.com/managment/"+manages+".json");
        conn=(HttpsURLConnection) url.openConnection();
        postMessage="{\"teacher\":\""+username+"\"}";
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        out=conn.getOutputStream();
        out.write(postMessage.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        creator.createTeacher(newUser);
        return creator;
    }
    public Board createParent(Parent newUser,Board creator) throws MalformedURLException, IOException{
        String firstName=newUser.getFirst_name();
        String lastName=newUser.getLast_name();
        String password=newUser.getPassword();
        String username=newUser.getUsername();
        String type="PARENT";
        String manages="+"+username;
        String postMessage="{"
                    + "\"password\": \""+password+"\","
                    + " \"data\": {"
                    + "\"first_name\": \""+firstName+"\","
                    + "\"last_name\": \""+lastName+"\","
                    + "\"type\": \""+type+"\","
                    + "\"manages\": \""+manages+"\""
                    + "}"
                    + "}";
        URL url = new URL("https://smspdata.firebaseio.com/users/"+username+".json");
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        OutputStream out=conn.getOutputStream();
        out.write(postMessage.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        //get size of Parent list from creator
        int size=creator.getParentList().getListSize();
        url = new URL("https://smspdata.firebaseio.com/managment/_"+creator.getUsername()+"/parents/"+size+".json");
        conn=(HttpsURLConnection) url.openConnection();
        postMessage="{\"parent\":\""+manages+"\"}";
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        out=conn.getOutputStream();
        out.write(postMessage.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        url = new URL("https://smspdata.firebaseio.com/managment/"+manages+".json");
        conn=(HttpsURLConnection) url.openConnection();
        postMessage="{\"parent\":\""+username+"\"}";
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        out=conn.getOutputStream();
        out.write(postMessage.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        creator.createParent(newUser);
        return creator;
    }
    
    public Teacher createPupil(Pupil newUser, Teacher creator, Parent parent) throws MalformedURLException, IOException{
        String firstName=newUser.getFirst_name();
        String lastName=newUser.getLast_name();
        String password=newUser.getPassword();
        String username=newUser.getUsername();
        String type="PUPIL";
        String postMessage="{"
                    + "\"password\": \""+password+"\","
                    + " \"data\": {"
                    + "\"first_name\": \""+firstName+"\","
                    + "\"last_name\": \""+lastName+"\","
                    + "\"type\": \""+type+"\""
                    + "}"
                    + "}";
        URL url = new URL("https://smspdata.firebaseio.com/users/"+username+".json");
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        OutputStream out=conn.getOutputStream();
        out.write(postMessage.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        //get size of Pupil list from creator
        int size=creator.getPupilList().getListSize();
        url = new URL("https://smspdata.firebaseio.com/managment/-"+creator.getUsername()+"/pupils/"+size+".json");
        conn=(HttpsURLConnection) url.openConnection();
        postMessage="{\"pupil\":\""+username+"\"}";
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        out=conn.getOutputStream();
        out.write(postMessage.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        creator.createPupil(newUser);
        if(parent!=null){
            //get size of Pupil list from parent
            int parentsize=parent.getPupilList().getListSize();
            url = new URL("https://smspdata.firebaseio.com/managment/+"+parent.getUsername()+"/pupils/"+size+".json");
            conn=(HttpsURLConnection) url.openConnection();
            postMessage="{\"pupil\":\""+username+"\"}";
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            out=conn.getOutputStream();
            out.write(postMessage.getBytes());
            System.out.println("OutputStream response code:"+conn.getResponseCode());
            conn.disconnect();
        }
        return creator;
    }
  

    public User updateUser (User updUser) throws MalformedURLException, IOException{
       Admin updUser1=(Admin) updUser; 
       String username=updUser1.getUsername();
       String password=updUser1.getPassword(); 
       String firstName=updUser1.getFirst_name(); 
       String lastName=updUser1.getLast_name();
       URL url = new URL("https://smspdata.firebaseio.com/users/"+username+"/.json");
       HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
       conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
       conn.setRequestMethod("PUT");
       String updString="{"
                    + "\"password\": \""+password+"\","
                    + " \"data\": {"
                    + "\"first_name\": \""+firstName+"\","
                    + "\"last_name\": \""+lastName+"\","
                    + "}"
                    + "}";;
        conn.setDoOutput(true);
        OutputStream out=conn.getOutputStream();
        out.write(updString.getBytes());
        return updUser;
    }
    public Activity createActivity(Activity newActivity) throws MalformedURLException, IOException, ParseException{
        //program connects to database to get last activity ID
        URL url = new URL("https://smspdata.firebaseio.com/activity_last/last_id/.json");
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        int last_id=-1;
        if (conn.getResponseCode() == 200) {
            InputStream is=conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            if(line.equals("null")){
                last_id=-1;
            }else{
                last_id=Integer.parseInt(line);
            }
        }
        conn.disconnect();
        //After getting last ID we set all new variables and prepare to add new activity to database
        Integer new_id=last_id+1;
        newActivity.setActId(new_id);
        String author=newActivity.getAuthor();
        String task=newActivity.getTask();
        boolean free=newActivity.isFree();
        double price=0;
        if(!free){
            price=newActivity.getPayment().getPrice();
        }
        String postMessage="{\"author\":\""+author+"\",\"task\":\""+task+"\",\"free\":\""+free+"\",\"price\":\""+price+"\"}";
        //connecting to database to add new activity
        URL uploadUrl=new URL("https://smspdata.firebaseio.com/activity/"+new_id+"/.json");
        conn=(HttpsURLConnection) uploadUrl.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("PUT");
        OutputStream out=conn.getOutputStream();
        out.write(postMessage.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        //increment last id for activity list
        uploadUrl=new URL("https://smspdata.firebaseio.com/activity_last/last_id/.json");
        conn=(HttpsURLConnection) uploadUrl.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("PUT");
        out=conn.getOutputStream();
        postMessage=new_id.toString();
        out.write(postMessage.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        //create variables to insert into user_act table
        JSONArray userActivities=getUserActivities(author);
        String comment=" ";
        boolean paid=true;
        if(!free){
            paid=false;
        }
        JSONObject newUserAct=new JSONObject();
        newUserAct.put("ID", new_id);
        newUserAct.put("comment", comment);
        newActivity.setPupilComment(comment);
        newActivity.getPayment().setPaid(paid);
        newUserAct.put("paid", paid);
        userActivities.add(newUserAct);
        //insert data into user_act table
        uploadUrl=new URL("https://smspdata.firebaseio.com/user_act/"+author+"/.json");
        conn=(HttpsURLConnection) uploadUrl.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("PUT");
        out=conn.getOutputStream();
        out.write(userActivities.toString().getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        //creates user_activities for all child classes
        //get data from database of what user manages
        url = new URL("https://smspdata.firebaseio.com/users/"+author+"/data/manages/.json");
        conn=(HttpsURLConnection) url.openConnection();
        InputStream is=conn.getInputStream();
        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String all = "", line;
            while ((line = br.readLine()) != null) {
                all += line;
            }
            conn.disconnect();
            if(!all.equals("null")){       
                JSONArray childArray=getChildrenArray(all);
                Iterator i=childArray.iterator();
                while (i.hasNext()){
                    JSONObject loopObject=(JSONObject) i.next();
                    String username=(String)loopObject.get("pupil");
                    JSONArray pupilActivities=getUserActivities(username);
                    pupilActivities.add(newUserAct);
                    uploadUrl=new URL("https://smspdata.firebaseio.com/user_act/"+username+"/.json");
                    conn=(HttpsURLConnection) uploadUrl.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("PUT");
                    out=conn.getOutputStream();
                    out.write(pupilActivities.toString().getBytes());
                    System.out.println("OutputStream response code:"+conn.getResponseCode());
                    conn.disconnect();
                }
            }
        }
        conn.disconnect();
        return newActivity;
        
    }
    public Activity updateActivity(Activity updActivity, String username) throws IOException, MalformedURLException, ParseException{
        int actId=updActivity.getActId();
        JSONArray userActivities=getUserActivities(username);
        Iterator i=userActivities.iterator();
        JSONArray newActivities=new JSONArray();
        while (i.hasNext()){
            JSONObject loopObject=(JSONObject) i.next();
            long forId=(long)loopObject.get("ID");
            int loopId=(int) forId;
            if(actId==loopId){
                JSONObject jsonActivity=new JSONObject();
                jsonActivity.put("paid", updActivity.getPayment().isPaid());
                jsonActivity.put("comment", updActivity.getPupilComment());
                jsonActivity.put("ID",actId);
                System.out.println(jsonActivity);
                newActivities.add(updActivity);
            }else{
                newActivities.add(loopObject);
            }
        }
        URL uploadUrl=new URL("https://smspdata.firebaseio.com/user_act/"+username+"/.json");
        HttpsURLConnection conn=(HttpsURLConnection) uploadUrl.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("PUT");
        OutputStream out=conn.getOutputStream();
        String postMessage=newActivities.toString();
        System.out.println(postMessage);
        out.write(postMessage.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        return updActivity;
    }
    private JSONArray getChildrenArray(String managment) throws IOException, ParseException{
        char first=managment.charAt(0);
        if(first=='\"'){
            managment=managment.substring(1,managment.length()-1);
            first=managment.charAt(0);
        }
        JSONArray childArray=new JSONArray();
        if(first=='_'){
            URL url=new URL("https://smspdata.firebaseio.com/managment/"+managment+"/classes.json");
            HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
            InputStream is=conn.getInputStream();
            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String all = "",line;
                while ((line = br.readLine()) != null) {
                    all += line;
                }
                JSONParser parser=new JSONParser();
                Object obj=parser.parse(all);
                JSONArray classesArray=(JSONArray) obj;
                Iterator i=classesArray.iterator();
                while (i.hasNext()){
                    JSONObject loopObject=(JSONObject) i.next();
                    String classManage=(String)loopObject.get("class");
                    JSONArray subArray= getChildrenArray(classManage);
                    Iterator i2=subArray.iterator();
                    while (i2.hasNext()){
                        JSONObject loopObject2=(JSONObject) i2.next();
                        childArray.add(loopObject2);
                    }
                }
            }
        }else if ((first=='+')||(first=='-')){//if first letter is + or - we connect to database, grab list of pupils
            //and parse it to childArray
            URL url=new URL("https://smspdata.firebaseio.com/managment/"+managment+"/pupils.json");
            HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
            InputStream is=conn.getInputStream();
            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String all = "",line;
                while ((line = br.readLine()) != null) {
                    all += line;
                }
                JSONParser parser=new JSONParser();
                Object obj=parser.parse(all);
                JSONArray classesArray=(JSONArray) obj;
                Iterator i=classesArray.iterator();
                while (i.hasNext()){
                    JSONObject loopObject=(JSONObject) i.next();
                    childArray.add(loopObject);
                      
                }
            }
        }
        return childArray;
        
    }
    private JSONArray getUserActivities(String username) throws MalformedURLException, IOException, ParseException{
        //get JsonArray of activities from a user
        URL url = new URL("https://smspdata.firebaseio.com/user_act/"+username+"/.json");
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        InputStream is=conn.getInputStream();
        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String all = "", line;
            while ((line = br.readLine()) != null) {
                all += line;
            }
            conn.disconnect();
            JSONArray jsonArray=null;
            if (all.equals("null")){
                jsonArray=new JSONArray();
            }else{
                JSONParser parser=new JSONParser();
                Object obj=parser.parse(all);
                jsonArray=(JSONArray) obj;
            }
            return jsonArray;
            
        }
        return null;
        
    }
}
