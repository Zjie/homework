package game;
import java.awt.Point;
class ConnectFourHardComputerPlayer <C> extends HardComputerPlayer {

    public ConnectFourHardComputerPlayer(String newName, Object newPlayerColour) {
		super(newName, newPlayerColour);
		// TODO Auto-generated constructor stub
	}

	private int numPieces;
    private static final int maxPieces = 70, Empty = 2;
    public static boolean[][][] map;
    static int w = 10;
    static int h =7;
    static int n =4;
    //Equation for checking the winning place on Connect4board
    //winPlace=(4 * w *h) - (3*w*n) - (3*h*n) + (3*w) + (3*h) - (4*n) + (2*n*n);
    private static final int winPlaces =143;
    public static void main(String[] args) {
        // Tests go here
    }
    
    
    // this method checks the state of the board and calls computermove method
    //Just an idea ...sort of pseodo
    //Find a winning computer move first, or if that fails, find a move that blocks the human;
    //if that fails, too, then just choose a random move.
    //Look for vertical runs of 2 or 3 length;  if they are the human player's(opponent) vertical runs stop it, 
    //or continue them if they are the computer player's vertical runs
    //same for horizontal and diagonal.
      public void Connect4State() {
    	 /* if(totalpiece on board <= 6) { 
    			drop piece on any of my longest chain if it is a valid move
    		  }

    		}else if(my longest chain >= longest chain of opponent) {
    		  if(my longest chain piece== 3 && my move is a winning move{ make move}
    		  else{add piece to any of the longest chain of opponent}
    		  
    		 else if(there are valid moves) {drop piece on my previous move if it is valid}
    		 else if(there are valid moves) {drop piece beside any of my piece if it is a valid move)
    		 else(make a random move)
    		 */
     
      }
      public void Move(int x, int y) {
    	  
    	  
    	  
    	}
      
      public Point computerMove(int i, int j) {
		return null;
      }
}
      