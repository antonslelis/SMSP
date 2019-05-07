package org.solent.group.project.model;
public class Teacher extends Pupil {

    private PupilList pupilList;

    public Activity createActivity(Activity newActivity) {
           return null;    
    }

    public PupilList getPupilList() {
        return pupilList;
    }

    public void setPupilList(PupilList pupilList) {
        this.pupilList = pupilList;
    }

    public void modifyActivities(int id, String newComment, boolean isPaid) {
        
      pupilList.updatePupilsActivity(id, newComment, isPaid);
     
    }

    public Pupil createPupil(Pupil newPupil) {
        pupilList.insertPupil(newPupil); 
        return newPupil; 
    }

    public Pupil modifyPupil(Pupil updPupil) {
        pupilList.updatePupil(updPupil); 
        
        //checks if pupil was added to list
        return pupilList.getPupilByUsername(updPupil.getUsername()); 
    }

    public Pupil linkToParent(Pupil updPupil) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void addActivityToPupils(Activity newActivity){
        pupilList.addActivityToPupils(newActivity);
    }
}
