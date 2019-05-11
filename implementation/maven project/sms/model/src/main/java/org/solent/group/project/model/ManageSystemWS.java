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
    //Function logIn is used to check if the input data existin the database
    //It takes User as a parameter with password and username
    //It returns User with same username and password but with type of the User
    //If input data is wrong returns null
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
            //substring method is called as we get password from the database in ""
            String comparePass=all.substring(1,all.length()-1);
            conn.disconnect();
            //compares input password with the one from the database
            if(comparePass.equals(newUser.getPassword())){
                //if the password is correct, we set up new User and get type from the database
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
    //Function logPupil is used to generate Pupil account
    //It takes User as a parameter, where User's password is optional
    //It return generated account in the Pupil class
    public Pupil logPupil(User newUser) throws IOException, ParseException{
        Pupil newPupil=new Pupil();
        newPupil.setUsername(newUser.getUsername());
        if(newUser.getPassword()!=null){
            newPupil.setPassword(newUser.getPassword());
        }
        newPupil.setType(newUser.getType());
        //connects to the database to get all the required data about Pupil from the database
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
            //getActivities function is called to get Pupil's activities
            newPupil.setPersonalActivities(getActivities(newPupil.getUsername())); 
        }
        conn.disconnect();
        return newPupil;
    }
    //Function logParent is used to generate Parent account
    //It takes User as a parameter, where User's password is optional
    //It return generated account in the Parent class
    public Parent logParent(User newUser) throws IOException, ParseException{
        Parent newParent=new Parent();
        newParent.setUsername(newUser.getUsername());
        if(newUser.getPassword()!=null){
            newParent.setPassword(newUser.getPassword());
        }
        newParent.setType(newUser.getType());
        //connects to the database to get all the required data about Parent from the database
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
            //connects to the database second time to get the list of Pupils which belong to the Parent
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
                //If pupilArray exist, for each object in the pupilArray we create new User
                //Each User generates new Pupil account and ads it to the list, which is stored in Parent class
                if(pupilArray!=null){
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
    //Function logParent is used to generate Techer account
    //It takes User as a parameter, where User's password is optional
    //It return generated account in the Techer class
    public Teacher logTeacher(User newUser) throws IOException, ParseException{
        Teacher newTeacher=new Teacher();
        newTeacher.setUsername(newUser.getUsername());
        if(newUser.getPassword()!=null){
            newTeacher.setPassword(newUser.getPassword());
        }
        newTeacher.setType(newUser.getType());
        //connects to the database to get all the required data about Teacher from the database
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
            //Function getActivities is used to get all Teacher's activities
            newTeacher.setPersonalActivities(getActivities(newTeacher.getUsername()));
            String manages=(String)jsonUser.get("manages");
            //connects to the database second time to get the list of Pupils which belong to the Teacher
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
                //If pupilArray exist, for each object in the pupilArray we create new User
                //Each User generates new Pupil account and ads it to the list, which is stored in Teacher class
                if(pupilArray!=null){
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
    //Function logBoard is used to generate Board account
    //It takes User as a parameter, where User's password is optional
    //It return generated account in the Board class
    public Board logBoard(User newUser) throws IOException, ParseException{
        Board newBoard=new Board();
        newBoard.setUsername(newUser.getUsername());
        if(newUser.getPassword()!=null){
            newBoard.setPassword(newUser.getPassword());
        }
        newBoard.setType(newUser.getType());
        //connects to the database to get all the required data about Board from the database
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
            //connects to the database second time to get the list of Teachers which belong to the Board
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
                //If teacherArray exist, for each object in the teacherArray we create new User
                //Each User generates new Teacher account and ads it to the list, which is stored in Board class
                if(teacherArray!=null){
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
            //connects to the database to get the list of Parents which belong to the Board
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
                //If parentArray exist, for each object in the parentArray we create new User
                //Each User generates new Parent account and ads it to the list, which is stored in Board class
                if(parentArray!=null){
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
    //Function logAdmin is used to generate Admin account
    //It takes User as a parameter, where User's password is optional
    //It return generated account in the Admin class
    public Admin logAdmin(User newUser) throws IOException, ParseException{
        Admin newAdmin=new Admin();
        newAdmin.setUsername(newUser.getUsername());
        if(newUser.getPassword()!=null){
            newAdmin.setPassword(newUser.getPassword());
        }
        newAdmin.setType(newUser.getType());
        //connects to the database to get all the required data about Admin from the database
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
            //connects to the database to get the list of Boards which belong to the Admin
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
                //If boardArray exist, for each object in the boardArray we create new User
                //Each User generates new Board account and ads it to the list, which is stored in Admin class
                if(boardArray!=null){
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
    //function getActivities is used to get all the activities of the user from the database
    //it takes username as a parameter
    //and returns ActivityList
    private ActivityList getActivities(String username) throws MalformedURLException, IOException, ParseException {
        //All activites are stored in 2 parts
        //First we get the user part, which has user comment, information if user paid, and the ID
        //Second we get the author part, which has author username, task, price, and if activity is free
        //Each ActivityList is generated with two calls to the database
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
            if(manageArray!=null){
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
        } 
        return newList;
    }
    //Function createBoard is used to to create new Board account
    //It takes Board and Admin as a parameters
    //And returns Admin with updated Board list
    public Admin createBoard(Board newUser, Admin creator) throws MalformedURLException, ProtocolException, IOException{
        String firstName=newUser.getFirst_name();
        String lastName=newUser.getLast_name();
        String password=newUser.getPassword();
        String username=newUser.getUsername();
        String type="BOARD";
        String manages="_"+username;
        //the jsonobject for users table is generated
        String postMessage="{"
                    + "\"password\": \""+password+"\","
                    + " \"data\": {"
                    + "\"first_name\": \""+firstName+"\","
                    + "\"last_name\": \""+lastName+"\","
                    + "\"type\": \""+type+"\","
                    + "\"manages\": \""+manages+"\""
                    + "}"
                    + "}";
        //connects  to the database and uploads new Board
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
        //connects to the database to create new school in admin managment tree
        url = new URL("https://smspdata.firebaseio.com/managment/~all/schools/"+size+"/.json");
        conn=(HttpsURLConnection) url.openConnection();
        postMessage="{\"school\":\""+manages+"\"}";
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        out=conn.getOutputStream();
        out.write(postMessage.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        //connects to the database to create new school in the managment tree
        url = new URL("https://smspdata.firebaseio.com/managment/"+manages+".json");
        conn=(HttpsURLConnection) url.openConnection();
        postMessage="{\"board\":\""+username+"\"}";
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        out=conn.getOutputStream();
        out.write(postMessage.getBytes());
        conn.disconnect();
        //add new Board to the BoardList of creator(Admin)
        creator.createBoard(newUser);
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        return creator;
    }
    //Function createTeacher is used to to create new Teacher account
    //It takes Teacher and Board as a parameters
    //And returns Board with updated Teacher list
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
        //connects  to the database and uploads new Teacher
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
        //connects  to the database to add new class in the creator's managment tree
        url = new URL("https://smspdata.firebaseio.com/managment/_"+creator.getUsername()+"/classes/"+size+".json");
        conn=(HttpsURLConnection) url.openConnection();
        postMessage="{\"class\":\""+manages+"\"}";
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        out=conn.getOutputStream();
        out.write(postMessage.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        //connects  to the database to create new class in managment tree
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
    //Function createParent is used to to create new Parent account
    //It takes Parent and Board as a parameters
    //And returns Board with updated Parent list
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
        //connects  to the database and uploads new Parent
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
        //connects  to the database to add new parent in the creator's managment tree
        url = new URL("https://smspdata.firebaseio.com/managment/_"+creator.getUsername()+"/parents/"+size+".json");
        conn=(HttpsURLConnection) url.openConnection();
        postMessage="{\"parent\":\""+manages+"\"}";
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        out=conn.getOutputStream();
        out.write(postMessage.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        //connects  to the database to create new parent in managment tree
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
    //Function createPupil is used to to create new Pupil account
    //It takes Pupil, Teacher and Parent as a parameters
    //And returns Teacher with updated Parent list
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
        //connects  to the database and uploads new Pupil
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
        //connects to the database and adds pupil to creator's managment tree
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
            //connects to the database and adds pupil to parent's managment tree
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
  
    //Function updateUser is used to to update information abouth the user
    //It takes 5 parameters
    //and returns boolean
    public boolean updateUser (String username, String password, String first, String last, String type) throws MalformedURLException, IOException{
        URL url = new URL("https://smspdata.firebaseio.com/users/"+username+"/.json");
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
        conn.setRequestMethod("PUT");
        String updString="";
        if(type.equals("ADMIN")){
            updString="{"
                         + "\"password\": \""+password+"\","
                         + " \"data\": {"
                         + "\"first_name\": \""+first+"\","
                         + "\"last_name\": \""+last+"\","
                         + "\"manages\": \"~all\","
                         + "\"type\": \"ADMIN\""
                         + "}"
                         + "}";;
        }else if(type.equals("BOARD")){
            updString="{"
                         + "\"password\": \""+password+"\","
                         + " \"data\": {"
                         + "\"first_name\": \""+first+"\","
                         + "\"last_name\": \""+last+"\","
                         + "\"manages\": \"_"+username+"\","
                         + "\"type\": \"BOARD\""
                         + "}"
                         + "}";;
            
        }else if(type.equals("TEACHER")){
            updString="{"
                         + "\"password\": \""+password+"\","
                         + " \"data\": {"
                         + "\"first_name\": \""+first+"\","
                         + "\"last_name\": \""+last+"\","
                         + "\"manages\": \"-"+username+"\","
                         + "\"type\": \"TEACHER\""
                         + "}"
                         + "}";;
            
        }else if(type.equals("PARENT")){
            updString="{"
                         + "\"password\": \""+password+"\","
                         + " \"data\": {"
                         + "\"first_name\": \""+first+"\","
                         + "\"last_name\": \""+last+"\","
                         + "\"manages\": \"+"+username+"\","
                         + "\"type\": \"PARENT\""
                         + "}"
                         + "}";;
            
        }else if(type.equals("PUPIL")){
            updString="{"
                         + "\"password\": \""+password+"\","
                         + " \"data\": {"
                         + "\"first_name\": \""+first+"\","
                         + "\"last_name\": \""+last+"\","
                         + "\"type\": \"PUPIL\""
                         + "}"
                         + "}";;
            
        }
        conn.setDoOutput(true);
        OutputStream out=conn.getOutputStream();
        out.write(updString.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        if(conn.getResponseCode()==200){
            return true;
        }
        return false;
    }
    //Function createActivity is used to create Activity in the database
    //It takes Activity as parameter
    //and returns Activity back
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
    //Function updateActivity is used to update Activity or make payments
    //It takes Activity and username as parameter
    //and returns Activity back
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
                newActivities.add(jsonActivity);
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
        out.write(postMessage.getBytes());
        System.out.println("OutputStream response code:"+conn.getResponseCode());
        conn.disconnect();
        return updActivity;
    }
    //Function getChildrenArray is used to get all the pupils from the managment tree
    //it takes String as parameter
    //And return JSONArray which consist of pupil usernames
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
    //Function getUserActivities is used to get all user activities
    //it takes String as parameter
    //And return JSONArray which consist of user's part activities
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
