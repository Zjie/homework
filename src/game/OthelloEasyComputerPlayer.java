package game;
import java.awt.Point;
import java.util.Random;

class OthelloEasyComputerPlayer <C> extends EasyComputerPlayer{
    
    public OthelloEasyComputerPlayer(String newName, C newPlayerColour) {
		super(newName, newPlayerColour);
		
	}

	public static void main(String[] args) {
        // Tests go here
    }
    
    public Point move(OthelloBoard board){
        Random r = new Random( );
        int Position;
        i = r.nextInt(WIDTH);
        j = r.nextInt(HEIGHT);
        // Assuming their piece is white...
        // Othello isn't using the HumanPlayer class at the moment...
        // That fact may need to change...
        while (!board.anyValid(new OthelloPiece(Piece.OthelloPieceColour.WHITE))) {
            i = r.nextInt(WIDTH);
            j = r.nextInt(HEIGHT);
        }
         System.out.println(i);
        Point movePosition = new Point(i,j);
       return movePosition;
    }
	
	    /**< constant for the size of the board in x direction */ 
    private static final int WIDTH = 10; 
    
    /**< constant for the size of the board in y direction */ 
    private static final int HEIGHT = 7; 
    
    private static int i;
    private static int j;
	
	//method to generate ramdom move
}