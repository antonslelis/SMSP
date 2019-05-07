
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
   
    
    public boolean insertPupil (Pupil pupil){
        pupilList.add(pupil); 
        
        return true;       
    }

    
    public Pupil updatePupil(Pupil pupil){

        for (int i = 0; i < pupilList.size();  i++) {
            Pupil pp = pupilList.get(i);
            
            //checks for equal usernames, ignores uppercase letters
            if(pp.getUsername().equalsIgnoreCase(pupil.getUsername())){
                pupilList.remove(i); 
                pupilList.add(pupil); 
            }
                
            
        }
     
        return pupil; 
     
    }
    
}
