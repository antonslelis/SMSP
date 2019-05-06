public class Board extends Teacher {

    private TeacherList teacherList;

    private ParentList parentList;

    public Activity createActivity(Activity newAct) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Activity modifyActivity(Activity updActivity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Teacher createTeacher(Teacher newTeacher) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Teacher updateTeacher(Teacher updTeacher) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Parent createParent(Parent newParent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Parent updateParent(Parent updParent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deletePupil(int pupilId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deleteParent(int parentId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deleteTeacher(int teacherId) {
        throw new UnsupportedOperationException("Not supported yet.");
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
