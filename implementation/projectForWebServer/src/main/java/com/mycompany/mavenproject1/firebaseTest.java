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
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;


/**
 *
 * @author anton
 */
//link: https://smspdata.firebaseio.com/
public class firebaseTest {
     

    public firebaseTest() throws MalformedURLException, IOException {
        
        
    }
    public static void main(String[] args) throws MalformedURLException, IOException {
//        // INSERT NEW USER TO DATABASE
//        String username="test";
//        URL url = new URL("https://smspdata.firebaseio.com/users/"+username+".json");
//        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
//        conn.setRequestMethod("PUT");
//        String name="Tony";
//        String postMessage="{"
//                + "\"password\": \"password1\","
//                + " \"data\": {"
//                    + "\"first_name\": \""+name+"\","
//                    + "\"last_name\": \"Last\","
//                    + "\"type\": \"ADMIN\","
//                    + "\"manages\": \"~all\""
//                    + "}"
//                + "}";
//        conn.setDoOutput(true);
//        
//        OutputStream out=conn.getOutputStream();
//        out.write(postMessage.getBytes());
//        System.out.println("OutputStream response code:"+conn.getResponseCode());
////        if (conn.getResponseCode() == 200) {
////            InputStream is=conn.getInputStream();
////            BufferedReader br = new BufferedReader(new InputStreamReader(is));
////            String all = "", line;
////            while ((line = br.readLine()) != null) {
////                all += line;
////            }    
////            System.out.println(all);
////        }    
//        conn.disconnect();
//        
//         // UPDATE USER IN DATABASE
//        username="usr001";
//        url = new URL("https://smspdata.firebaseio.com/users/"+username+".json");
//        conn=(HttpsURLConnection) url.openConnection();
//        conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
//        conn.setRequestMethod("PUT");
//        name="Test2";
//        postMessage="{\"name\": \""+name+"\", \"data\": {\"password\": \"notadmin\"}}";
//        conn.setDoOutput(true);
//        out=conn.getOutputStream();
//        out.write(postMessage.getBytes());
//        System.out.println("OutputStream response code:"+conn.getResponseCode());
//        if (conn.getResponseCode() == 200) {
//            InputStream is=conn.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String all = "", line;
//            while ((line = br.readLine()) != null) {
//                all += line;
//            }    
//            System.out.println(all);
//        }    
//        conn.disconnect();
//        
//         // VIEW USER IN DATABASE
//        URL url2 = new URL("https://smspdata.firebaseio.com/user_act/admin/.json?print=pretty");
//        HttpsURLConnection conn2=(HttpsURLConnection) url2.openConnection();
//        InputStream is2=conn2.getInputStream();
//        System.out.println("InputStream response code:"+conn2.getResponseCode());
//        if (conn2.getResponseCode() == 200) {
//            BufferedReader br = new BufferedReader(new InputStreamReader(is2));
//            String all = "", line;
//            while ((line = br.readLine()) != null) {
//                all += line;
//            }    
//            System.out.println(all);
//        } 
//        
//       // DELETE USER IN DATABASE
//        url2 = new URL("https://smspdata.firebaseio.com/users/usr001/name.json?print=pretty");
//        conn2=(HttpsURLConnection) url2.openConnection();
//        conn2.setDoOutput(true);
//        conn2.setRequestProperty(
//                "Content-Type", "application/x-www-form-urlencoded" );
//        conn2.setRequestMethod("DELETE");
//        conn2.connect();
//        System.out.println("DELET response:"+conn2.getResponseCode());
//        
//        
//        //////adding stuff 
//        username="usr001";
//        url = new URL("https://smspdata.firebaseio.com/managment/~all/.json");
//        conn=(HttpsURLConnection) url.openConnection();
//        //conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
//        conn.setRequestMethod("PUT");
//        name="Test2";
//        postMessage="{\"schools\": [{\"school\":\"_sch0\"}]}";
//        conn.setDoOutput(true);
//        out=conn.getOutputStream();
//        out.write(postMessage.getBytes());
//        System.out.println("OutputStream response code:"+conn.getResponseCode());
//        if (conn.getResponseCode() == 200) {
//            InputStream is=conn.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String all = "", line;
//            while ((line = br.readLine()) != null) {
//                all += line;
//            }    
//            System.out.println(all);
//        }    
//        conn.disconnect();
//                
//        url = new URL("https://smspdata.firebaseio.com/managment/_sch0/.json");
//        conn=(HttpsURLConnection) url.openConnection();
//        //conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
//        conn.setRequestMethod("PUT");
//        name="Test2";
//        postMessage="{\"classes\": [{\"class\":\"-classA\"},{\"class\":\"-classB\"}], \"board\":\"user321\"}";
//        conn.setDoOutput(true);
//        out=conn.getOutputStream();
//        out.write(postMessage.getBytes());
//        System.out.println("OutputStream response code:"+conn.getResponseCode());
//        if (conn.getResponseCode() == 200) {
//            InputStream is=conn.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String all = "", line;
//            while ((line = br.readLine()) != null) {
//                all += line;
//            }    
//            System.out.println(all);
//        }    
//        conn.disconnect();
//     
//        url = new URL("https://smspdata.firebaseio.com/managment/-classA/.json");
//        conn=(HttpsURLConnection) url.openConnection();
//        //conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
//        conn.setRequestMethod("PUT");
//        name="Test2";
//        postMessage="{\"pupils\": [{\"pupil\":\"user100\"},{\"class\":\"user201\"}],\"teacher\":\"user000\"}";
//        conn.setDoOutput(true);
//        out=conn.getOutputStream();
//        out.write(postMessage.getBytes());
//        System.out.println("OutputStream response code:"+conn.getResponseCode());
//        if (conn.getResponseCode() == 200) {
//            InputStream is=conn.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String all = "", line;
//            while ((line = br.readLine()) != null) {
//                all += line;
//            }    
//            System.out.println(all);
//        }    
//        conn.disconnect();
//        
//        
//        url = new URL("https://smspdata.firebaseio.com/activity/0/.json");
//        conn=(HttpsURLConnection) url.openConnection();
//        //conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
//        conn.setRequestMethod("PUT");
//        name="Test2";
//        postMessage="{\"author\":\"admin\",\"task\":\"Create Activity!\",\"paid\":\"true\",\"price\":\"10\"}";
//        conn.setDoOutput(true);
//        out=conn.getOutputStream();
//        out.write(postMessage.getBytes());
//        System.out.println("OutputStream response code:"+conn.getResponseCode());
//        if (conn.getResponseCode() == 200) {
//            InputStream is=conn.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String all = "", line;
//            while ((line = br.readLine()) != null) {
//                all += line;
//            }    
//            System.out.println(all);
//        }    
//        conn.disconnect();
//        
//        url = new URL("https://smspdata.firebaseio.com/user_act/admin/0/.json");
//        conn=(HttpsURLConnection) url.openConnection();
//        //conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
//        conn.setRequestMethod("PUT");
//        name="Test2";
//        postMessage="{\"ID\":\"0\",\"comment\":\"I did it!\",\"paid\":\"true\",\"image\":\"false\"}";
//        conn.setDoOutput(true);
//        out=conn.getOutputStream();
//        out.write(postMessage.getBytes());
//        System.out.println("OutputStream response code:"+conn.getResponseCode());
//        if (conn.getResponseCode() == 200) {
//            InputStream is=conn.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String all = "", line;
//            while ((line = br.readLine()) != null) {
//                all += line;
//            }    
//            System.out.println(all);
//        }    
//        conn.disconnect();
//    }
        String firstName="test";
        String lastName="test";
        String password="test";
        String username="bot";
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
            System.out.println("OutputStream response code:"+conn.getResponseCode());
            if (conn.getResponseCode() == 200) {
                InputStream is=conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String all = "", line;
                while ((line = br.readLine()) != null) {
                    all += line;
                }    
                System.out.println(all);
            }    
            //get size of Board list from creator
            int size=1;
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
            System.out.println("OutputStream response code:"+conn.getResponseCode());
            conn.disconnect();
        }
    
}
