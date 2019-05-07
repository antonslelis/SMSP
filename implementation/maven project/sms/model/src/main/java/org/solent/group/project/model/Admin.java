package org.solent.group.project.model;
public class Admin extends Board {

    private BoardList boardList;

    public Board createBoard(Board newBoard) {
        boardList.insertBoard(newBoard); 
        
        return newBoard; 
    }

    public Board updateBoard(Board updBoard) {
        boardList.updateBoard(updBoard); 
        
        return updBoard; 
    }
}
