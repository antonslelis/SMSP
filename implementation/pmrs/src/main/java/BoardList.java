
import java.util.List;

public class BoardList {
    private List<Board> boardList;

    public List<Board> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<Board> boardList) {
        this.boardList = boardList;
    }
    
    public int getListSize(){
        
        return boardList.size(); 
        
    }    
    
   
}
