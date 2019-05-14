/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.group.project.web;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.json.simple.parser.ParseException;
import org.solent.group.project.model.Activity;
import org.solent.group.project.model.Admin;
import org.solent.group.project.model.Board;
import org.solent.group.project.model.User;
import org.solent.group.project.model.ManageSystemWS;
import org.solent.group.project.model.Parent;
import org.solent.group.project.model.Pupil;
import org.solent.group.project.model.Teacher;

/**
 *
 * @author anton
 */
@WebService(serviceName = "ManagmentWebServer")
public class ManagmentWebServer {
    ManageSystemWS ms=new ManageSystemWS();
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    @WebMethod(operationName ="logIn")
    public User logIn(@WebParam(name="user") User newUser){
        try {
            newUser=ms.logIn(newUser);
            return newUser;
        } catch (IOException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    
    @WebMethod(operationName ="logPupil")
    public Pupil logPupil (@WebParam(name="user") User newUser){
        try {
            Pupil newPupil=ms.logPupil(newUser);
            return newPupil;
        } catch (IOException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @WebMethod(operationName ="logParent")
    public Parent logParent (@WebParam(name="user") User newUser){
        try {
            Parent newParent=ms.logParent(newUser);
            return newParent;
        } catch (IOException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @WebMethod(operationName ="logTeacher")
    public Teacher logTeacher (@WebParam(name="user")User newUser){
        try {
            Teacher newTeacher=ms.logTeacher(newUser);
            return newTeacher;
        } catch (IOException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @WebMethod(operationName ="logBoard")
    public Board logBoard (@WebParam(name="user") User newUser){
        try {
            Board newBoard=ms.logBoard(newUser);
            return newBoard;
        } catch (IOException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @WebMethod(operationName ="logAdmin")
    public Admin logAdmin (@WebParam(name="user") User newUser){
        try {
            Admin newAdmin=ms.logAdmin(newUser);
            return newAdmin;
        } catch (IOException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @WebMethod(operationName ="createBoard")
    public Admin createBoard(@WebParam(name="board") Board newUser, @WebParam(name="admin") Admin creator){
        try {
            creator=ms.createBoard(newUser, creator);
        } catch (ProtocolException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return creator;
    }
    @WebMethod(operationName ="createTeacher")
    public Board createTeacher(@WebParam(name="teacher") Teacher newUser, @WebParam(name="board") Board creator){
        try {
            creator=ms.createTeacher(newUser, creator);
        } catch (ProtocolException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return creator;
    }
    @WebMethod(operationName ="createParent")
    public Board createParent(@WebParam(name="parent") Parent newUser, @WebParam(name="board") Board creator){
        try {
            creator=ms.createParent(newUser, creator);
        } catch (ProtocolException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return creator;
    }
    @WebMethod(operationName ="createPupil")
    public Teacher createPupil(@WebParam(name="pupil") Pupil newUser, @WebParam(name="teacher") Teacher creator, @WebParam(name="parent") Parent parent){
        try {
            creator=ms.createPupil(newUser, creator,parent);
        } catch (ProtocolException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return creator;
    }
    @WebMethod(operationName="updateUser")
    public boolean updateUser(@WebParam(name="username")String username, @WebParam(name="password")String password, @WebParam(name="first")String first, 
                                            @WebParam(name="last")String last, @WebParam(name="type")String type){
        try {
            return ms.updateUser(username,password,first,last,type);
        } catch (IOException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    @WebMethod(operationName="createActivity")
    public Activity createActivity(@WebParam(name="activity") Activity newActivity){
        try {
            newActivity=ms.createActivity(newActivity);
            return newActivity;
        } catch (IOException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @WebMethod(operationName="updateActivity")
    public Activity updateActivity(@WebParam(name="activity") Activity updActivity,@WebParam(name="username") String username){
        try {
            updActivity=ms.updateActivity(updActivity, username);
            return updActivity;
        } catch (IOException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManagmentWebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
