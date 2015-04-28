package game;
import java.util.Arrays;
import java.util.Random;
import java.awt.Point;

class ConnectFourEasyComputerPlayer <C> extends EasyComputerPlayer {

	
    public ConnectFourEasyComputerPlayer(String newName, C newPlayerColour) {
		super(newName, newPlayerColour);
	}


	public static void main(String[] args) {
        // Tests go here
    	
    }
  
    public static Point move(ConnectFourBoard board){
        Random r = new Random( );
        int Position;
        i = r.nextInt(WIDTH);
        // I didn't realise Piece.ConnectFourPiece EMPTY_PIECE was private - Jon
        // The new while condition is what the ConnectFourGameGUI uses to check for full rows.
        //while (!(board.getPieces()[i][HEIGHT] == Piece.ConnectFourPieceColour EMPTY_PIECE)) {
        while(board.getLowestEmptySlot(i) < HEIGHT) {
            i = r.nextInt(WIDTH);
        }
        System.out.println(i);
        Point movePosition = new Point(i,7);
       return movePosition;
    }
    
	/**< constant for the size of the board in x direction */ 
	private static final int WIDTH = 10; 
	
	/**< constant for the size of the board in y direction */ 
	private static final int HEIGHT = 7; 
	
	private static int i;
	
}