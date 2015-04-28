package game;
/**
* @file OthelloPiece.java
* @author Yiwei Li 
* @date 21 Feb 2014
* @see 'JAVA for everyone' - Cay Horstmann, Second edition, page 420 for 
*	   	implementing subclasses.
*
* @brief This is the subclass of piece which get the pieces colour for Othello.
*
* 
* This class handles the othello piece type and it uses the implemented methods 
* in the super class Piece to set and get the piece colour of a piece object.
*
*/
public class OthelloPiece extends Piece<Piece.OthelloPieceColour> {
    
	/** 
	* This is the constructor for the OthelloPiece class.
	*
	* @param pieceColour -the variable storing the colour of the new piece.
	*					  
	*/
	public OthelloPiece(OthelloPieceColour pieceColour) {
		super(pieceColour);
	}
	
	/**
	* This is the main method and it is used for unit testing purposes, it calls 
	* every method in this class for testing valid and invalid input parameters.
	*
	*/
	public static void main(String[] args) {
		OthelloPiece piece = 
			new OthelloPiece(Piece.OthelloPieceColour.BLACK);
		
		System.out.println("Valid inputs");
		System.out.println("OthelloPiece.getPieceColour() - Begin");
		System.out.println("Expected output: BLACK");
		System.out.println("");
		System.out.println("Actual output: " + piece.getPieceColour());
		System.out.println("OthelloPiece.getPieceColour() - End");
		System.out.println("");
		System.out.println("OthelloPiece.setPieceColour() - Begin");
		System.out.println("Expected output: true");
		System.out.println("");
		System.out.println("Actual output: " + 
			piece.setPieceColour(Piece.OthelloPieceColour.WHITE));
		System.out.println("OthelloPiece.setPieceColour() - End");
		System.out.println("");
		System.out.println("OthelloPiece.getPieceColour() - Begin");
		System.out.println("Expected output: WHITE");
		System.out.println("");
		System.out.println("Actual output: " + piece.getPieceColour());
		System.out.println("OthelloPiece.getPieceColour() - End");
		System.out.println("");
		
		System.out.println("Invalid inputs");
		System.out.println("OthelloPiece.setPieceColour() - Begin");
		System.out.println("Expected output: false");
		System.out.println("");
		System.out.println("Actual output: " + 
			piece.setPieceColour(Piece.OthelloPieceColour.NONE));
		System.out.println("OthelloPiece.setPieceColour() - End");
		System.out.println("");
		System.out.println("OthelloPiece.getPieceColour() - Begin");
		System.out.println("Expected output: WHITE");
		System.out.println("");
		System.out.println("Actual output: " + piece.getPieceColour());
		System.out.println("OthelloPiece.getPieceColour() - End");
	}
}