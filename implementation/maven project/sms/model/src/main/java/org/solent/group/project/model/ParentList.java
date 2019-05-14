package org.solent.group.project.model;
import java.util.ArrayList;
import java.util.List;

public class ParentList {

    private List<Parent> parentList=new ArrayList();
    int parentSize=0;

    public List<Parent> getParentList() {
        return parentList;
    }

    public void setParentList(List<Parent> parentList) {
        this.parentList = parentList;
    }
    
    public int getListSize(){
        
        return parentSize; 
        
    }   
    
    
    public boolean insertParent (Parent parent){
        parentList.add(parent); 
        parentSize++;
        return true;       
    }

    
    public Parent updateParent(Parent newParent){

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
