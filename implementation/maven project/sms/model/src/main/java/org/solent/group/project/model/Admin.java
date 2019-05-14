package org.solent.group.project.model;

/**
 *
 * @author Andre
 */

public class Admin extends Board {

    private BoardList boardList=new BoardList();
    
    public BoardList getBoardList() {
        return boardList;
    }

    public Board createBoard(Board newBoard) {
        boardList.insertBoard(newBoard); 
        
        return newBoard; 
    }

    public Board updateBoard(Board updBoard) {
        boardList.updateBoard(updBoard); 
        
        return updBoard; 
    }
}
