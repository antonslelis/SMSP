public class ManageSystemWS {
    
    private ActivityList activityList; 

    public User logIn(User newUser) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public User createUser(User newUser) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public User updateUser(User updUser) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean deleteUser(int userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Activity createActivity(Activity newActivity) {
        Activity activity = newActivity; 
        
        activityList.insertActivity(activity); 
        
        return activity; 
    }

    public Activity updateActivity(int id, String newComment, boolean isPaid) {
        
        Activity newActivity = activityList.updateActivity(id, newComment, isPaid); 
        
        return newActivity; 
        
    }

    public boolean deleteActivity(int actId) {
       
        return activityList.deleteActivity(actId);
    }

    public boolean makePayment(int activityId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
