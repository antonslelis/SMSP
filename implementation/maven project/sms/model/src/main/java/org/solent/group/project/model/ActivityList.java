package org.solent.group.project.model;
import java.util.List;

public class ActivityList {

    private List<Activity> activityList;

    public Activity getActivitybyId(int actId) {
        
        int length = activityList.size(); 
        int i = 0; 
        Activity act = new Activity(); 
        
        while (i < length){
            act = activityList.get(i); 
             
            
            if (act.getActId() == actId){
                break; 
            }

            i++;
        }
        
        return act;
    }
    
    public boolean insertActivity (Activity activity){
        activityList.add(activity); 
        
        return true;       
    }
    
    public boolean deleteActivity(int id){
        
        for(int i = 0; i < activityList.size(); i++){
            
            Activity activity = activityList.get(i); 
            
            if(activity.getActId() == id){ 
                activityList.remove(i); 
            }         
            
        }
        
        return true; 
    }
    
    //string comment boolean paid
    public Activity updateActivity(int id, String newComment, boolean isPaid){

        for (int i = 0; i < activityList.size();  i++) {
            Activity ac = activityList.get(i);
            
            if(ac.getActId() == id){
                activityList.get(i).setPupilComment(newComment);
                activityList.get(i).setFree(isPaid);
                
                
                return ac;
            }
                
            
        }
      
        return null; 
    }
    
    public int getListSize(){
        
        return activityList.size(); 
        
    }   
    
}
