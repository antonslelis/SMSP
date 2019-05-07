public class Teacher extends Pupil {

    private PupilList pupilList;

    public Activity createActivity(Activity newActivity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PupilList getPupilList() {
        return pupilList;
    }

    public void setPupilList(PupilList pupilList) {
        this.pupilList = pupilList;
    }

    public Activity modifyActivity(Activity upActivity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Pupil createPupil(Pupil newPupil) {
        pupilList.insertPupil(newPupil); 
        return newPupil; 
    }

    public Pupil modifyPupil(Pupil updPupil) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Pupil linkToParent(Pupil updPupil) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
