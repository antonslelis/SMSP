
import java.awt.Image;

public class Activity {

    private int actId;

    private int authorId;

    private String task;

    private Image uploadedImage;

    private String pupilComment;

    private int pupilId;

    private boolean free;

    private Invoice payment;
   

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
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

    public int getPupilId() {
        return pupilId;
    }

    public void setPupilId(int pupilId) {
        this.pupilId = pupilId;
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
    
}
