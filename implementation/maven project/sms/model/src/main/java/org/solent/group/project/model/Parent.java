package org.solent.group.project.model;

/**
 *
 * @author Andre
 */

public class Parent extends User {

    private String first_name;

    private String last_name;

    private PupilList pupilList=new PupilList();

    public boolean makePayment(int activityId) {
        throw new UnsupportedOperationException("Not supported yet.");
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

    public PupilList getPupilList() {
        return pupilList;
    }

    public void setPupilList(PupilList pupilList) {
        this.pupilList = pupilList;
    }
    
    public void addPupil(Pupil pupil){
        pupilList.insertPupil(pupil); 
    }
}
