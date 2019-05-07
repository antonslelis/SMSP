
import java.util.List;

public class ParentList {

    private List<Parent> parentList;

    public List<Parent> getParentList() {
        return parentList;
    }

    public void setParentList(List<Parent> parentList) {
        this.parentList = parentList;
    }
    
    public int getListSize(){
        
        return parentList.size(); 
        
    }   
    
    
    public boolean insertParent (Parent parent){
        parentList.add(parent); 
        
        return true;       
    }

    
    public Parent updatePupil(Parent newParent){

        for (int i = 0; i < parentList.size();  i++) {
            Parent pa = parentList.get(i);
            
            //checks for equal usernames, ignores uppercase letters
            if(pa.getUsername().equalsIgnoreCase(newParent.getUsername())){
                parentList.remove(i); 
                parentList.add(newParent); 
            }
                
            
        }
     
        return newParent; 
     
    }
}
