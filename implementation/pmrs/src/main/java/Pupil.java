public class Pupil extends User {

    private ActivityList personalActivities;

    private String first_name;

    private String last_name;

    private int seniorId;

    private int parentId;

    public Activity createActivity(Activity newAct) {
        
        personalActivities.insertActivity(newAct); 
        
        return personalActivities.getActivitybyId(newAct.getActId()); 
    }

    public Activity modifyActivity(Activity updAct) {
        personalActivities.updateActity(updAct); 
        
        return personalActivities.getActivitybyId(updAct.getActId()); 
    }


    public ActivityList getPersonalActivities() {
        return personalActivities;
    }

    public void setPersonalActivities(ActivityList personalActivities) {
        this.personalActivities = personalActivities;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getSeniorId() {
        return seniorId;
    }

    public void setSeniorId(int seniorId) {
        this.seniorId = seniorId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
