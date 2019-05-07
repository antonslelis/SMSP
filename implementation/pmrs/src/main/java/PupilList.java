
import java.util.List;

public class PupilList {

    private List<Pupil> pupilList;

    public List<Pupil> getPupilList() {
        return pupilList;
    }

    public void setPupilList(List<Pupil> pupilList) {
        this.pupilList = pupilList;
    }
    
    public int getListSize(){
        
        return pupilList.size(); 
        
    }    
    
    
}
