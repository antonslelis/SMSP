package org.solent.group.project.model;
import java.util.ArrayList;
import java.util.List;

public class PupilList {

    private List<Pupil> pupilList=new ArrayList();
    private int listSize=0;

    public List<Pupil> getPupilList() {
        return pupilList;
    }

    public void setPupilList(List<Pupil> pupilList) {
        this.pupilList = pupilList;
    }
    
    public int getListSize(){
        
        return listSize; 
        
    }    
   
    
    public boolean insertPupil (Pupil pupil){
        pupilList.add(pupil); 
        listSize++;
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
    
    public Pupil getPupilByUsername(String username){
        
        int length = pupilList.size(); 
        int i = 0; 
        Pupil pp = new Pupil(); 
        
        while (i < length){
            pp = pupilList.get(i); 
             
            
            if (pp.getUsername().equalsIgnoreCase(username)){
                break; 
            }

            i++;
        }
        
        return pp;
        
    }
    
    public void addActivityToPupils(Activity newActivity){
        
        int size = pupilList.size(); 
        int i = 0; 
        
        while (i < size){
            pupilList.get(i).createActivity(newActivity); 
            i++;
        }
    }
    
        public void updatePupilsActivity(int id, String newComment, boolean isPaid){
        
        int size = pupilList.size(); 
        int i = 0; 
        
        while (i < size){
            pupilList.get(i).modifyActivity(id, newComment, isPaid); 
            i++;
        }

    }
}
