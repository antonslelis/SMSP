package org.solent.group.project.model;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andre
 */

public class BoardList {
    private List<Board> boardList = new ArrayList<Board>();
    int listsize=0;

    public BoardList() {
        this.boardList = new ArrayList<Board>();
    }

    public List<Board> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<Board> boardList) {
        this.boardList = boardList;
    }
    
    public int getListSize(){
           return listsize;
        
    }

    public boolean insertBoard (Board board){
        boardList.add(board); 
        listsize++;
        return true;       
    }

    
    public Board updateBoard(Board newBoard){

        for (int i = 0; i < boardList.size();  i++) {
            Board b = boardList.get(i);
            
            //checks for equal usernames, ignores uppercase letters
            if(b.getUsername().equalsIgnoreCase(newBoard.getUsername())){
                boardList.remove(i); 
                boardList.add(newBoard); 
            }
                
            
        }
     
        return newBoard; 
     
    }
    
   
}
