package game;
/**
* @file ConnectFourPiece.java
* @author Jones lo
* @date 12 FEB 2014
* @see 'JAVA for everyone' - Cay Horstmann, Second edition, page 420 for 
*	   implementing subclasses.
*
* @brief This is the subclass of piece which get the pieces colour for Connect 
*		 Four.
*
* 
* This class handles the connect four piece type and it uses the implemented 
* methods in the super class Piece to set and get the piece colour of a piece 
* object.
*
*/	
public class ConnectFourPiece extends Piece<Piece.ConnectFourPieceColour>{
	
	/** 
	* This is the constructor for the ConnectFourPiece class.
	*
	* @param pieceColour -the variable storing the colour of the new piece.
	*					  
	*/	
	public ConnectFourPiece(Piece.ConnectFourPieceColour pieceColour){
		super(pieceColour);
	}

	public static void main(String[] args) {
		ConnectFourPiece piece = 
			new ConnectFourPiece(Piece.ConnectFourPieceColour.RED);
		
		System.out.println("Valid inputs");
		System.out.println("OthelloPiece.getPieceColour() - Begin");
		System.out.println("Expected output: RED");
		System.out.println("");
		System.out.println("Actual output: " + piece.getPieceColour());
		System.out.println("OthelloPiece.getPieceColour() - End");
		System.out.println("");
		System.out.println("OthelloPiece.setPieceColour() - Begin");
		System.out.println("Expected output: true");
		System.out.println("");
		System.out.println("Actual output: " + 
			piece.setPieceColour(Piece.ConnectFourPieceColour.YELLOW));
		System.out.println("OthelloPiece.setPieceColour() - End");
		System.out.println("");
		System.out.println("OthelloPiece.getPieceColour() - Begin");
		System.out.println("Expected output: YELLOW");
		System.out.println("");
		System.out.println("Actual output: " + piece.getPieceColour());
		System.out.println("OthelloPiece.getPieceColour() - End");
		System.out.println("");
		
		System.out.println("Invalid inputs");
		System.out.println("OthelloPiece.setPieceColour() - Begin");
		System.out.println("Expected output: false");
		System.out.println("");
		System.out.println("Actual output: " + 
			piece.setPieceColour(Piece.ConnectFourPieceColour.NONE));
		System.out.println("OthelloPiece.setPieceColour() - End");
		System.out.println("");
		System.out.println("OthelloPiece.getPieceColour() - Begin");
		System.out.println("Expected output: YELLOW");
		System.out.println("");
		System.out.println("Actual output: " + piece.getPieceColour());
		System.out.println("OthelloPiece.getPieceColour() - End");
	}
}
	
	