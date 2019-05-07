package org.solent.group.project.model;
public class Board extends Teacher {

    private TeacherList teacherList;

    private ParentList parentList;

    public Activity createActivity(Activity newAct) {
        
        teacherList.addActivityToPupils(newAct);
        
        return newAct;
    }

    public Activity modifyActivity(int id, String newComment, boolean isPaid) {
        teacherList.updatePupilsActivity(id, newComment, isPaid);
        
        return null; 
    }

    public Teacher createTeacher(Teacher newTeacher) {
        teacherList.insertTeacher(newTeacher); 
        
        return newTeacher; 
    }

    public Teacher updateTeacher(Teacher updTeacher) {
        teacherList.updateTeacher(updTeacher);
        
        return updTeacher;
    }

    public Parent createParent(Parent newParent) {
        parentList.insertParent(newParent); 
        
        return newParent; 
    }

    public Parent updateParent(Parent updParent) {
        parentList.updateParent(updParent);
        
        return updParent; 
    }

    
    public TeacherList getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(TeacherList teacherList) {
        this.teacherList = teacherList;
    }

    public ParentList getParentList() {
        return parentList;
    }

    public void setParentList(ParentList parentList) {
        this.parentList = parentList;
    }
}
