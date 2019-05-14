/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.group.project.model.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;
import static org.junit.Assert.*;
import org.junit.Test;
import org.solent.group.project.model.Activity;
import org.solent.group.project.model.Admin;
import org.solent.group.project.model.Board;
import org.solent.group.project.model.ManageSystemWS;
import org.solent.group.project.model.Parent;
import org.solent.group.project.model.Pupil;
import org.solent.group.project.model.Teacher;
import org.solent.group.project.model.User;

/**
 *
 * @author anton
 */
public class ManageSystemWStest {
    @Test
    public void testLoggingIn() throws IOException, MalformedURLException, ParseException{        
        try {
            ManageSystemWS ms=new ManageSystemWS();
            //logging in with wrong username or password
            User user=new User();
            user.setUsername("t");
            user.setPassword("----");
            user=ms.logIn(user);
            assertNull(user);
            
            //logging in as Admin
            user=new User();
            user.setUsername("admin");
            user.setPassword("123");
            user=ms.logIn(user);
            Admin adminFromData=null;
            if(user.getType().equals("ADMIN")){
                System.out.println("If ADMIN");
                adminFromData=ms.logAdmin(user);
            }
            assertNotNull(adminFromData);
            
            //logging in as Board
            user=new User();
            user.setUsername("board_test");
            user.setPassword("11");
            user=ms.logIn(user);
            Board boardFromData=null;
            if(user.getType().equals("BOARD")){
                System.out.println("If BOARD");
                boardFromData=ms.logBoard(user);
            }
            assertNotNull(boardFromData);
            
            //logging in as Teacher
            user=new User();
            user.setUsername("teacher_test");
            user.setPassword("11");
            user=ms.logIn(user);
            Teacher teacherFromData=null;
            if(user.getType().equals("TEACHER")){
                System.out.println("If TEACHER");
                teacherFromData=ms.logTeacher(user);
            }
            assertNotNull(teacherFromData);
            
            //logging in as Parent
            user=new User();
            user.setUsername("parent_test1");
            user.setPassword("11");
            user=ms.logIn(user);
            Parent parentFromData=null;
            if(user.getType().equals("PARENT")){
                System.out.println("If PARENT");
                parentFromData=ms.logParent(user);
            }
            assertNotNull(parentFromData);
            
            //logging in as Pupil 
            user=new User();
            user.setUsername("pupil_test1");
            user.setPassword("11");
            user=ms.logIn(user);
            Pupil pupilFromData=null;
            if(user.getType().equals("PUPIL")){
                System.out.println("If PUPIL");
                pupilFromData=ms.logPupil(user);
            }
            assertNotNull(pupilFromData);
            
        } catch (ParseException ex) {
            Logger.getLogger(ManageSystemWStest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @Test
    public void testUserCreation(){
        //To see changes on the database access https://smspdata.firebaseio.com/users/*username*.json?print=pretty 
        ManageSystemWS ms=new ManageSystemWS();
        try {
            User user=new User();
            user.setUsername("admin");
            user.setPassword("123");
            user=ms.logIn(user);
            Admin adminFromData=ms.logAdmin(user);
            
            //Create new Board
            int firstListSize=adminFromData.getBoardList().getListSize();
            Board newBoard=new Board();
            newBoard.setFirst_name("name");
            newBoard.setLast_name("last");
            newBoard.setUsername("newBoard");
            newBoard.setPassword("123");
            adminFromData=ms.createBoard(newBoard,adminFromData);
            int secondListSize=adminFromData.getBoardList().getListSize();
            assertNotEquals(firstListSize,secondListSize);
            
            //create new Teacher
            firstListSize=newBoard.getTeacherList().getListSize();
            Teacher newTeacher=new Teacher();
            newTeacher.setFirst_name("name");
            newTeacher.setLast_name("last");
            newTeacher.setUsername("newTeacher");
            newTeacher.setPassword("123");
            newBoard=ms.createTeacher(newTeacher, newBoard);
            secondListSize=newBoard.getTeacherList().getListSize();
            assertNotEquals(firstListSize,secondListSize);
            
            //create new Parent
            firstListSize=newBoard.getParentList().getListSize();
            Parent newParent=new Parent();
            newParent.setFirst_name("name");
            newParent.setLast_name("last");
            newParent.setUsername("newParent");
            newParent.setPassword("123");
            newBoard=ms.createParent(newParent, newBoard);
            secondListSize=newBoard.getParentList().getListSize();
            assertNotEquals(firstListSize,secondListSize);
            
            //create new Pupil
            firstListSize=newTeacher.getPupilList().getListSize();
            Pupil newPupil=new Pupil();
            newPupil.setFirst_name("name");
            newPupil.setLast_name("last");
            newPupil.setUsername("newPupil");
            newPupil.setPassword("123");
            newTeacher=ms.createPupil(newPupil, newTeacher, newParent);
            secondListSize=newTeacher.getPupilList().getListSize();
            assertNotEquals(firstListSize,secondListSize);

        } catch (IOException ex) {
            Logger.getLogger(ManageSystemWStest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManageSystemWStest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testActivityCreation(){
        //to see result of the test check the database https://smspdata.firebaseio.com/activity/.json?print=pretty
        //and https://smspdata.firebaseio.com/user_act/board_test.json?print=pretty
        //and personal activity is also created for Pupil who belong to board_test
        //https://smspdata.firebaseio.com/user_act/pupil_test1.json?print=pretty
        ManageSystemWS ms=new ManageSystemWS();
        
        try {
            Activity act=new Activity();
            act.setAuthor("board_test");
            act.setTask("Complex task");
            act.setFree(false);
            act.getPayment().setPrice(10);        
            ms.createActivity(act);
        } catch (IOException ex) {
            Logger.getLogger(ManageSystemWStest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManageSystemWStest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testAccountUpdate(){
        //to see result of the test check the database https://smspdata.firebaseio.com/users/board_test/.json?print=pretty
        ManageSystemWS ms=new ManageSystemWS();
        try {
            assertTrue(ms.updateUser("board_test", "11", "Jerry", "The Board","BOARD"));
        } catch (IOException ex) {
            Logger.getLogger(ManageSystemWStest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testActivityUpdate(){
        ManageSystemWS ms=new ManageSystemWS();
        //https://smspdata.firebaseio.com/user_act/pupil_test1.json?print=pretty
        try {
            Activity act=new Activity();
            act.setActId(3);
            act.setPupilComment("Paid for activity");
            act.getPayment().setPaid(true);
            ms.updateActivity(act,"pupil_test1");
        } catch (IOException ex) {
            Logger.getLogger(ManageSystemWStest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManageSystemWStest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
