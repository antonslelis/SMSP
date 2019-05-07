
import java.util.List;

public class TeacherList {

    private List<Teacher> teacherList;

    public List<Teacher> getTeacherList() {
        return teacherList;
    }
    
    public int getListSize(){
        
        return teacherList.size(); 
        
    }    
    
    public boolean insertTeacher (Teacher teacher){
        teacherList.add(teacher); 
        
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
}
