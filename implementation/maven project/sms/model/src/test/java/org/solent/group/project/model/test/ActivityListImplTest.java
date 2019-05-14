/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.group.project.model.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.solent.group.project.model.Activity;
import org.solent.group.project.model.ActivityList;
/**
 *
 * @author Andre
 */
public class ActivityListImplTest {
    
    
    
    @Test
    public void testInsertActivity(){
        
        ActivityList ac = new ActivityList(); 
        
        Activity activity = new Activity();

        activity.setActId(1);
        activity.setAuthorId(1);
        activity.setPupilId(2);
        activity.setTask("Example task");
        
        ac.insertActivity(activity);
        
        Activity testAc = ac.getActivitybyId(1); 
        
        assertEquals(1, testAc.getAuthorId());
        assertEquals(1, testAc.getActId());
        assertEquals(2, testAc.getPupilId());
        
        System.out.print("############################## " + testAc.toString());
        
    }
    
    @Test
    public void testGetActivityById(){
        
        int ACTIVITY_ID = 23; 
        
        ActivityList ac = new ActivityList(); 
        Activity activity = new Activity();

        activity.setActId(ACTIVITY_ID);
        activity.setAuthorId(1);
        activity.setPupilId(2);
        activity.setTask("Example task");
        
        ac.insertActivity(activity);
        
        Activity testAct = ac.getActivitybyId(ACTIVITY_ID); 
       
        //both objects must be identical
        assertEquals(activity, testAct); 
    }
    
    @Test
    public void testDeleteActivity(){
        int ACTIVITY_ID = 30; 
        
        ActivityList ac = new ActivityList(); 
        
        Activity activityToDelete = new Activity();
        //activity to delete 
        activityToDelete.setActId(ACTIVITY_ID);
        activityToDelete.setAuthorId(3);
        activityToDelete.setTask("Example task 1"); 
        
        ac.insertActivity(activityToDelete); 
        
        //create random activity objects
        
        //ac1 
        Activity ac1 = new Activity(); 
        
        ac1.setActId(2);
        ac1.setAuthorId(5);
        ac1.setTask("Example task 2");
        
        ac.insertActivity(ac1);
        
        //ac2
        Activity ac2 = new Activity(); 
        
        ac2.setActId(2);
        ac2.setAuthorId(5);
        ac2.setTask("Example task 2");
        
        ac.insertActivity(ac2); 
        
        
        //check list size
        assertEquals(3, ac.getSize());
        
        //delete activity
        ac.deleteActivity(ACTIVITY_ID); 
        
        //check list size which should have decreased by 1
        assertEquals(2, ac.getSize()); 
    }
}
