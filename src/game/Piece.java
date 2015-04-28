package game;
/**
* @file Piece.java
* @author Jones lo ,Yiwei Li
* @date 21 FEB 2014
* @see 'JAVA for everyone' - Cay Horstmann, Second edition, page 420 for 
*	   implementing subclasses.
* @brief This is the super class of ConnectFourPiece and OthelloPiece 
*         and holds the variables for them.
*
* 
* This class is the abstract Piece type for the use in both connect four and 
* othello, the two subclasses will extend this class and use the accessors 
* methods to control the colour of a piece.
*
*/
public abstract class Piece<P> {
	
    /**< variable storing the colour of a piece */ 
	protected P m_PieceColour;
	private boolean m_IsSet = false;
	
    public boolean setPieceColour(P pieceColour) {
        m_IsSet = false;
        if (pieceColour != OthelloPieceColour.NONE || 
            pieceColour != ConnectFourPieceColour.NONE) {
            
            m_PieceColour = pieceColour;
            m_IsSet = true;
        }
        return m_IsSet;
    }

    /** 
    * This is the method for getting the colour of a piece.
    *
    * @param pieces -the array variable storing all the pieces on the board.
    * @return m_PieceColour -the colour of the piece.
    */
    public P getPieceColour() {
        return m_PieceColour;
    }

	/** 
	* This is the enum of ConnectFourPieceColour, it defines all the colours 
	* that a piece can be that is used in the Connect Four game.
	*/
	public enum ConnectFourPieceColour {
		NONE, RED, YELLOW, RED_STAR, YELLOW_STAR
	}
		
	/** 
	* This is the enum of OthelloPieceColour, it defines all the colours 
	* that a piece can be that is used in the Othello game.
	*/
	public enum OthelloPieceColour {
		NONE, BLACK, WHITE, BLACK_STAR, WHITE_STAR
	}
    

	/** 
	* This is the constructor for the Piece class.
	*
	* @param pieceColour -the variable storing the 
	*					  colour of the new piece.
	*/
	public Piece(P pieceColour) {
		m_PieceColour = pieceColour;
	}
	
	/** 
	* This is the method for setting the colour of a piece.
	*
	* @param pieceColour -the variable storing the colour of the new piece.
	* @return isSet -a boolean variable returned as true if the piece
	*			     was set or false if it was not.
	*/
}
