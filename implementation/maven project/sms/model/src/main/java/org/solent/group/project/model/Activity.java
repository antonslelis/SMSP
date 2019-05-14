package org.solent.group.project.model;
import java.awt.Image;

/**
 *
 * @author Andre
 */

public class Activity {

    private int actId;

    private String author; 

    private String task;

    private Image uploadedImage;

    private String pupilComment;

    private boolean free;

    private Invoice payment=new Invoice();
   

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Image getUploadedImage() {
        return uploadedImage;
    }

    public void setUploadedImage(Image uploadedImage) {
        this.uploadedImage = uploadedImage;
    }

    public String getPupilComment() {
        return pupilComment;
    }

    public void setPupilComment(String pupilComment) {
        this.pupilComment = pupilComment;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Invoice getPayment() {
        return payment;
    }

    public void setPayment(Invoice payment) {
        this.payment = payment;
    }

    //activity should have a function to create an invoice 
    @Override
    public String toString(){
        
        return "Activity ID: " + String.valueOf(actId) + " Author ID: " + author + " Task: " + task; 
    
    }

    
   
}
