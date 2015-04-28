package game;
/**
* @file Board.java
* @author Callum Dicker
* @date 01 Feb 2014
* @see Abstract Oracle 
*      (http://docs.oracle.com/javase/tutorial/java/IandI/abstract.html)
* 
* @brief This superclass contains all the methods that will be
*	     inherited from the subclasses Connect4Board and OthelloBoard
*		
* This class will lay down the foundations for the subclasses which creates
* the board behind the GUI, this will check if the board is full or places a 
* piece on the board or increases the players piece count or returns the players
* piece count. In this class, they are abstract methods which means that the 
* subclasses implement the methods shown.
*
*/

public abstract class Board<C,P> {
	
     /**< This is the array of pieces on the board */
    protected P m_Pieces[][];
    
    /**< This is the amount of pieces on the board */
    protected int m_PieceCount;
    
    /**< This is the constant for the height of the board*/
    protected final int HEIGHT;
    
    /**< This is the constant for the Width of the board*/
    protected final int WIDTH;
    
	/**
	* This is the constructor for the class, gets the height and width of the
	* current game board
	*
	* @param height -The variable passed in storing the height of the board.
	* @param width -The variable passed in storing the width of the board.
	*/
	public Board(int width, int height) {
		HEIGHT = height;
		WIDTH = width;
	}
	
	/**
	* This method returns the amount of pieces on the board at the time of the
    * method call.
	*
	* @return m_PieceCount -The number of pieces on the board.
	*/
	public int getPieceCount() {
	
		return m_PieceCount;
	}
	
    /**
	* This method tests to see if the current game board is full or not.
	*
	* @return isFull -This will return true if the board is full, calculated 
    *                 through the number of spaces on the board, hight 
    *                 multiplied by width, compared with thee piece count on 
    *                 the board.
	*/
		
	/**
	* This method will set a pieces on the board at the designated place using
	* the x and y coordinates
	*
	* @param x -Used for the coordinates
	* @param y -Used for the coordinates
	* @param colour -Used to set the players piece on the board
	* @return boolean -If the method is successfully executed it returns true 
	*                  else false means it had failed to place the piece at the 
	*                  desired location. This potions could be invalid if it 
	*                  returns false.
	*
    */
	abstract boolean setPiece(int x, int y, C colour);
	    
	
	public boolean isFull() {
        boolean isFull = false;
		int totalValue =  HEIGHT * WIDTH;
        
		if (totalValue == m_PieceCount) {
			isFull = true;
            return isFull;
		}
        
		return isFull;
	}
	
    /**
    * This method checks to see if a player is in a winning position.
    *
    * @return boolean -This will return true if a player has won and false 
    *                  otherwise.
    *
    */
	abstract boolean checkWin();
}

