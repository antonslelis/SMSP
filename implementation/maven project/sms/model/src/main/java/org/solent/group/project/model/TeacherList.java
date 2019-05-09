package org.solent.group.project.model;
import java.util.ArrayList;
import java.util.List;

public class TeacherList {

    private List<Teacher> teacherList=new ArrayList();
    int teacherSize=0;

    public List<Teacher> getTeacherList() {
        return teacherList;
    }
    
    public int getListSize(){
        
        return teacherSize; 
        
    }    
    
    public boolean insertTeacher (Teacher teacher){
        teacherList.add(teacher); 
        teacherSize++;
        return true;       
    }

    
    public Teacher updateTeacher(Teacher newTeacher){

        for (int i = 0; i < teacherList.size();  i++) {
            Teacher t = teacherList.get(i);
            
            //checks for equal usernames, ignores uppercase letters
            if(t.getUsername().equalsIgnoreCase(newTeacher.getUsername())){
                teacherList.remove(i); 
                teacherList.add(newTeacher); 
            }
                
            
        }
     
        return newTeacher; 
     
    }
    
    public void addActivityToPupils(Activity newActivity){
        
        int size = teacherList.size(); 
        int i = 0; 
        
        while (i < size){
            teacherList.get(i).addActivityToPupils(newActivity);
            i++;
        }
    }
    
    public void updatePupilsActivity(int id, String newComment, boolean isPaid){
        int size = teacherList.size(); 
        int i = 0; 
        
        while (i < size){
            teacherList.get(i).modifyActivities(id, newComment, isPaid);
            i++;
        }
    }
}
