/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

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
        User userFromData=null;
        if(newUser==null){
            return userFromData;
        }
        String username=newUser.getUsername();
        //connects to database and gets the password of the user with matching username
        URL url = new URL("https://smspdata.firebaseio.com/users/"+username+"/data/password.json?print=pretty");
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        InputStream is=conn.getInputStream();
        System.out.println("InputStream response code:"+conn.getResponseCode());
        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String all = "", line;
            while ((line = br.readLine()) != null) {
                all += line;
            }
            //compares passwords and calls getUser function if passwords match
            //otherwise returns null
            if(all.equals(newUser.getPassword())){
                userFromData.setPassword(newUser.getPassword());
                userFromData.setPassword(username);
                userFromData=getUser(userFromData);
            }
        }
        return userFromData;
    }

    
    private User getUser(User user) throws IOException, ParseException {
        //this function gets all the data about the user from the database
        //first we get all information about the user himself from the database
        URL url = new URL("https://smspdata.firebaseio.com/users/"+user.getUsername()+"/data/.json?print=pretty");
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        InputStream is=conn.getInputStream();
        System.out.println("InputStream response code:"+conn.getResponseCode());
        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String all = "", line;
            while ((line = br.readLine()) != null) {
                all += line;
            }
            JSONParser parser=new JSONParser();
            Object obj=parser.parse(all);
            JSONObject jsonUser=(JSONObject) obj;
            //program checks what type of user we are dealing with
            //and depending on the result a proper script will be run
            String accountType=(String)jsonUser.get("type");
            User result=null;
            String newurl=new String("https://smspdata.firebaseio.com/managment/");
            if(accountType.equals("ADMIN")){
                Admin result1=new Admin();
                result1.setFirst_name((String)jsonUser.get("first_name"));
                result1.setLast_name((String)jsonUser.get("last_name"));
                result1.setUsername(user.getUsername());
                result1.setPassword(user.getPassword());
                //we cast Admin class to User to access getActivities and getChild function
                result=(User)result1;
                result=getActivities(result);
                String managment=(String)jsonUser.get("manages");
                result=getChild(managment,result,newurl);
            }else if(accountType.equals("BOARD")){
                Board result1=new Board();
                result1.setFirst_name((String)jsonUser.get("first_name"));
                result1.setLast_name((String)jsonUser.get("last_name"));
                result1.setUsername(user.getUsername());
                result1.setPassword(user.getPassword());
                //we cast Board class to User to access getActivities and getChild function
                result=(User)result1;
                result=getActivities(result);
                String managment=(String)jsonUser.get("manages");
                result=getChild(managment,result,newurl);
            }else if(accountType.equals("TEACHER")){
                Teacher result1=new Teacher();
                result1.setFirst_name((String)jsonUser.get("first_name"));
                result1.setLast_name((String)jsonUser.get("last_name"));
                result1.setUsername(user.getUsername());
                result1.setPassword(user.getPassword());
                //we cast Teacher class to User to access getActivities and getChild function
                result=(User)result1;
                result=getActivities(result);
                String managment=(String)jsonUser.get("manages");
                result=getChild(managment,result,newurl);
            }else if(accountType.equals("PARENT")){
                Parent result1=new Parent();
                result1.setFirst_name((String)jsonUser.get("first_name"));
                result1.setLast_name((String)jsonUser.get("last_name"));
                result1.setUsername(user.getUsername());
                result1.setPassword(user.getPassword());
                //we cast Parent class to User to access getActivities and getChild function
                result=(User)result1;
                result=getActivities(result);
                String managment=(String)jsonUser.get("manages");
                result=getChild(managment,result,newurl);
            }else if(accountType.equals("PUPIL")){
                Pupil result1=new Pupil();
                result1.setFirst_name((String)jsonUser.get("first_name"));
                result1.setLast_name((String)jsonUser.get("last_name"));
                result1.setUsername(user.getUsername());
                result1.setPassword(user.getPassword());
                //we cast Pupil class to User to access getActivities function
                result=(User)result1;
                result=getActivities(result);
            }
            //After 
            return result;
        }
        return null;
    }
//START SYMBOLS    
//~ means everything
//_ means school
//- means class  
//+ means parent's children      
    private User getChild(String managment, User parent, String urlString) throws MalformedURLException, IOException, ParseException {
        char first=managment.charAt(0);
        User result=null;
        if(first=='~'){
            URL url=new URL(urlString+managment+"/.json");
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
                JSONObject jsonManagment=(JSONObject) obj;
                JSONArray manageArray=(JSONArray) jsonManagment.get("schools");
                Iterator i=manageArray.iterator();
                while (i.hasNext()){
                    JSONObject loopObject=(JSONObject) i.next();
                    String schoolName=(String) loopObject.get("school");
                    parent=getChild(schoolName,parent,urlString);
                }
                result=parent;
                return result;
            }
        } else if(first=='_'){
            URL url=new URL(urlString+managment+"/.json");
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
                JSONObject jsonManagment=(JSONObject) obj;
                String boardName=(String) jsonManagment.get("board");
                User board=new User();
                board.setUsername(boardName);
                JSONArray manageArray=(JSONArray) jsonManagment.get("classes");
                Iterator i=manageArray.iterator();
                                    
                    //ADD board TO THE BOARD LIST IN parent IF PARENT IS ADMIN AND RETURN parent
                    //OR RETURN board
                   
                if(parent.getClass().getName().equals("Admin")){
                    Admin parent1=(Admin)parent;
                    board=getUser(board);
                    Board board1=(Board)board;
                    parent1.createBoard(board1);//add board to admin's BoardList
                    return parent1;

                }else{
                    while (i.hasNext()){
                        JSONObject loopObject=(JSONObject) i.next();
                        String className=(String) loopObject.get("class");
                        parent=getChild(className,parent,urlString);
                        
                    }
                    return parent;
                }
            }
            
        } else if(first=='-'){
            URL url=new URL(urlString+managment+"/.json");
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
                JSONObject jsonManagment=(JSONObject) obj;
                String teacherName=(String) jsonManagment.get("teacher");
                User teacher=new User();
                teacher.setUsername(teacherName);
                JSONArray manageArray=(JSONArray) jsonManagment.get("pupils");
                Iterator i=manageArray.iterator();
                if(parent.getClass().getName().equals("Board")){
                    teacher=getUser(teacher);
                    Board parent1=(Board)parent;
                    Teacher teacher1=(Teacher)teacher;
                    parent1.createTeacher(teacher1);
                    return parent1;

                }else{
                    while (i.hasNext()){
                        JSONObject loopObject=(JSONObject) i.next();
                        String pupilName=(String) loopObject.get("pupil");
                        parent=getChild(pupilName,parent,urlString);
                        
                    }
                    return parent;
                }
            }
        } else if(first=='+'){
            URL url=new URL(urlString+managment+"/.json");
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
                JSONObject jsonManagment=(JSONObject) obj;
                String parentName=(String) jsonManagment.get("parent");
                User userParent=new User();
                userParent.setUsername(parentName);
                JSONArray manageArray=(JSONArray) jsonManagment.get("pupils");
                Iterator i=manageArray.iterator();
                if(parent.getClass().getName().equals("Board")){
                    userParent=getUser(userParent);
                    Board parent1=(Board)parent;
                    Parent userParent1=(Parent)userParent;
                    
                    parent1.createParent(userParent1);//add parent to board's ParentList
                    return parent1;

                }else{
                    while (i.hasNext()){
                        JSONObject loopObject=(JSONObject) i.next();
                        String pupilName=(String) loopObject.get("pupil");
                        parent=getChild(pupilName,parent,urlString);
                        
                    }
                    return parent;
                }
            }
        } else{
            User child=new User();
            child.setUsername(managment);
            child=getUser(child);
            if(parent.getClass().getName().equals("Parent")){
                Parent userParent=(Parent)parent;
                Pupil child1=(Pupil)child;
                userParent.addPupil(child1);
                return userParent;
            } else if(parent.getClass().getName().equals("Teacher")){
                Teacher teacher=(Teacher) parent;
                Pupil child1=(Pupil)child;
                teacher.createPupil(child1);
                return teacher;
            }
           
                    
        }
        return null;
    }  
    ///JSONArray.size();

    private User getActivities(User user) throws MalformedURLException, IOException, ParseException {
        String username=user.getUsername();
        URL url = new URL("https://smspdata.firebaseio.com/user_act/"+username+"/.json");
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
            JSONArray manageArray=(JSONArray) obj;
            Iterator i=manageArray.iterator();
            while(i.hasNext()){
                JSONObject activity=(JSONObject) i.next();
                Activity loopAct=new Activity();
                loopAct.setPupilComment((String) activity.get("comment"));
                //configure Invoice
                //
                //
                //And add image somehow
                int actId=(Integer) activity.get("ID");
                loopAct.setActId(actId);
                url = new URL("https://smspdata.firebaseio.com/activity/"+actId+"/.json");
                HttpsURLConnection conn2=(HttpsURLConnection) url.openConnection();
                InputStream is2=conn.getInputStream();
                if (conn.getResponseCode() == 200) {
                    BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
                    all = "";
                    line="";
                    while ((line = br.readLine()) != null) {
                        all += line;
                    }
                    JSONParser parser2=new JSONParser();
                    Object obj2=parser.parse(all);
                    JSONObject actObj=(JSONObject) obj;
                    loopAct.setAuthor((String) actObj.get("author"));
                    loopAct.setTask((String) actObj.get("task"));
                    //Complete invoice
                    //
                    //
                }
                if(user.getClass().getName().equals("Admin")){
                    Admin user1=(Admin) user;
                    user1.createActivity(loopAct);
                    return user1;
                } else if(user.getClass().getName().equals("Board")){
                    Board user1=(Board) user;
                    user1.createActivity(loopAct);
                    return user1;
                } else if(user.getClass().getName().equals("Teacher")){
                    Teacher user1=(Teacher) user;
                    user1.createActivity(loopAct);
                    return user1;
                } else if(user.getClass().getName().equals("Pupil")){
                    Pupil user1=(Pupil) user;
                    user1.createActivity(loopAct);
                    return user1;
                }
                       
            }
            
            
        } 
        return user;
    }

    private User createUser(User newUser, User creator, User parent) throws MalformedURLException, ProtocolException, IOException{
        
        if(newUser.getClass().getName().equals("Board")){
            Board newUser1=(Board)newUser;
            String firstName=newUser1.getFirst_name();
            String lastName=newUser1.getLast_name();
            String password=newUser1.getPassword();
            String username=newUser1.getUsername();
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
            conn.disconnect();
            //get size of Board list from creator
            int size=1;
            url = new URL("https://smspdata.firebaseio.com/managment/~all/schools/"+size+".json");
            conn=(HttpsURLConnection) url.openConnection();
            postMessage="{\"school\":\""+manages+"\"}";
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            out=conn.getOutputStream();
            conn.disconnect();
            url = new URL("https://smspdata.firebaseio.com/managment/"+manages+".json");
            conn=(HttpsURLConnection) url.openConnection();
            postMessage="{\"board\":\""+username+"\"}";
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            out=conn.getOutputStream();
            conn.disconnect();
            //Add new user to BoardList
            //
        
        } else if(newUser.getClass().getName().equals("Teacher")){
            Teacher newUser1=(Teacher)newUser;
            String firstName=newUser1.getFirst_name();
            String lastName=newUser1.getLast_name();
            String password=newUser1.getPassword();
            String username=newUser1.getUsername();
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
            conn.disconnect();
            //get size of Teacher list from creator
            int size=1;
            url = new URL("https://smspdata.firebaseio.com/managment/-"+creator.getUsername()+"/classes/"+size+".json");
            conn=(HttpsURLConnection) url.openConnection();
            postMessage="{\"class\":\""+manages+"\"}";
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            out=conn.getOutputStream();
            conn.disconnect();
            url = new URL("https://smspdata.firebaseio.com/managment/"+manages+".json");
            conn=(HttpsURLConnection) url.openConnection();
            postMessage="{\"teacher\":\""+username+"\"}";
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            out=conn.getOutputStream();
            conn.disconnect();
            //Add new user to TeacherList
            //

        } else if(newUser.getClass().getName().equals("Parent")){
            Parent newUser1=(Parent)newUser;
            String firstName=newUser1.getFirst_name();
            String lastName=newUser1.getLast_name();
            String password=newUser1.getPassword();
            String username=newUser1.getUsername();
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
            conn.disconnect();
            //get size of Parent list from creator
            int size=1;
            url = new URL("https://smspdata.firebaseio.com/managment/_"+creator.getUsername()+"/parents/"+size+".json");
            conn=(HttpsURLConnection) url.openConnection();
            postMessage="{\"class\":\""+manages+"\"}";
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            out=conn.getOutputStream();
            conn.disconnect();
            url = new URL("https://smspdata.firebaseio.com/managment/"+manages+".json");
            conn=(HttpsURLConnection) url.openConnection();
            postMessage="{\"parent\":\""+username+"\"}";
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            out=conn.getOutputStream();
            conn.disconnect();
            //Add new user to TeacherList
            //

        } else if(newUser.getClass().getName().equals("Pupil")){
            Pupil newUser1=(Pupil)newUser;
            String firstName=newUser1.getFirst_name();
            String lastName=newUser1.getLast_name();
            String password=newUser1.getPassword();
            String username=newUser1.getUsername();
            
            String type="PUPIL";
           
            String postMessage="{"
                    + "\"password\": \""+password+"\","
                    + " \"data\": {"
                    + "\"first_name\": \""+firstName+"\","
                    + "\"last_name\": \""+lastName+"\","
                    + "\"type\": \""+type+"\","
                    + "}"
                    + "}";
            URL url = new URL("https://smspdata.firebaseio.com/users/"+username+".json");
            HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            OutputStream out=conn.getOutputStream();
            out.write(postMessage.getBytes());
            conn.disconnect();
            //get size of Pupil list from creator
            int size=1;
            url = new URL("https://smspdata.firebaseio.com/managment/-"+creator.getUsername()+"/pupils/"+size+".json");
            conn=(HttpsURLConnection) url.openConnection();
            postMessage="{\"pupil\":\""+username+"\"}";
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            out=conn.getOutputStream();
            conn.disconnect();
            //Add new user to PupilList
            //
            //
            if(parent!=null){
                //get size of Pupil list from parent
                int parentsize=1;
                url = new URL("https://smspdata.firebaseio.com/managment/+"+parent.getUsername()+"/pupils/"+size+".json");
                conn=(HttpsURLConnection) url.openConnection();
                postMessage="{\"pupil\":\""+username+"\"}";
                conn.setRequestMethod("PUT");
                conn.setDoOutput(true);
                out=conn.getOutputStream();
                conn.disconnect();
            }
        }
        return creator;
    }
    private User updateUser (User updUser) throws MalformedURLException, IOException{
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
    private Activity createActivity(Activity newActivity) throws MalformedURLException, IOException, ParseException{
        //program connects to database to get last activity ID
        URL url = new URL("https://smspdata.firebaseio.com/activity_last/last_id/.json");
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        int last_id=-1;
        if (conn.getResponseCode() == 200) {
            InputStream is=conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            last_id=Integer.parseInt(line);
        }
        conn.disconnect();
        //After getting last ID we set all new variables and prepare to add new activity to database
        int new_id=last_id+1;
        String author=newActivity.getAuthor();
        String task=newActivity.getTask();
        boolean free=newActivity.isFree();
        double price=0;
        if(!free){
            price=newActivity.getPayment().getPrice();
        }
        String postMessage="{\"author\":\""+author+"\",\"task\":\""+task+"\",\"paid\":\""+free+"\",\"price\":\""+price+"\"}";
        //connecting to database to add new activity
        URL uploadUrl=new URL("https://smspdata.firebaseio.com/activity/"+new_id+"/.json");
        conn=(HttpsURLConnection) uploadUrl.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("PUT");
        OutputStream out=conn.getOutputStream();
        out.write(postMessage.getBytes());
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
        newUserAct.put("paid", paid);
        userActivities.add(newUserAct);
        //insert data into user_act table
        uploadUrl=new URL("https://smspdata.firebaseio.com/user_act/"+author+"/.json");
        conn=(HttpsURLConnection) uploadUrl.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("PUT");
        out=conn.getOutputStream();
        out.write(userActivities.toString().getBytes());
        conn.disconnect();
        //creates user_activities for all child classes
        //get data from database of what user manages
        url = new URL("https://smspdata.firebaseio.com/users/"+author+"/manages/.json");
        conn=(HttpsURLConnection) url.openConnection();
        InputStream is=conn.getInputStream();
        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String all = "", line;
            while ((line = br.readLine()) != null) {
                all += line;
            }
            conn.disconnect();
            if(line!=null){
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
                    conn.disconnect();
                }
            }
        }
        conn.disconnect();
        return newActivity;
        
    }
    private Activity updateActivity(Activity updActivity, String username) throws IOException, MalformedURLException, ParseException{
        int actId=updActivity.getActId();
        JSONArray userActivities=getUserActivities(username);
        Iterator i=userActivities.iterator();
        JSONArray newActivities=new JSONArray();
        while (i.hasNext()){
            JSONObject loopObject=(JSONObject) i.next();
            int loopId=(Integer)loopObject.get("ID");
            if(actId==loopId){
                JSONObject jsonActivity=new JSONObject();
                jsonActivity.put("ID",actId);
                jsonActivity.put("comment", updActivity.getPupilComment());
                jsonActivity.put("paid", updActivity.getPayment().isPaid());
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
        out.write(newActivities.toString().getBytes());
        conn.disconnect();
        return updActivity;
    }
    private JSONArray getChildrenArray(String managment) throws IOException, ParseException{
        char first=managment.charAt(0);
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
                        JSONObject loopObject2=(JSONObject) i.next();
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
            JSONParser parser=new JSONParser();
            Object obj=parser.parse(all);
            JSONArray jsonArray=(JSONArray) obj;
            return jsonArray;
            
        }
        return null;
        
    }
}
