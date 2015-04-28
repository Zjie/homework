package game;
import java.util.Arrays;

/**
*
* @file OthelloBoard.java
* @author Adam Yaziji
* @date 30 Jan 2014
* 
*
* @brief This class creates the board for the game of Othello. 
* it is extended from the abstract class Board.
* 
* This class creates a constuctor for OthelloBoard.
* It sets the board, checks if the players chosen move is valid or not,
* checks if either one of the players had won.
*/


public class OthelloBoard extends Board <Piece.OthelloPieceColour,OthelloPiece> {

	 
	
	// creates an object of Piece which contains a white piece
	 private final OthelloPiece WHITE_PIECE = new OthelloPiece(Piece.OthelloPieceColour.WHITE);
       // creates an object of Piece which contains a black piece 
	 private final OthelloPiece BLACK_PIECE = new OthelloPiece(Piece.OthelloPieceColour.BLACK);
       // creates an object of Piece which contains 
	 private final OthelloPiece NONE_PIECE = new OthelloPiece(Piece.OthelloPieceColour.NONE);
	 
	 //symbolic constants denoting options of possible valid moves
	 private final int VALID_MOVE_1 = -1;
	 private final int VALID_MOVE_2 = 0;
	 private final int VALID_MOVE_3 = 1;
	 
	 //symbolic constants for test cases
	 private static final int BOARD_SIZE = 8; //sets size of board to 8 wide and 8 high
	 
	 private static final int TEST_MOVE_X1 = 2; //sets x and y coorindates of a possible move
	 private static final int TEST_MOVE_Y1 = 3; 
	 
	 private static final int TEST_PIECE_X = 5; //sets x and y coordinates of a piece in othello
	 private static final int TEST_PIECE_Y = 3;
	 
     private static final int OUTPUT_SETPIECE_TEST_X = 3; //expected x and y coordinates from output in setPiece test
     private static final int OUTPUT_SETPIECE_TEST_Y = 5;
    
     private static final int OUTPUT_MOVE_TEST_X = 1; //expected x and y coordinates from output move test
     private static final int OUTPUT_MOVE_TEST_Y = 2;
     
     private static final int OUTPUT_ANYMOVE_TEST_X = 1; //expected x and y coordinates from output in anymove test
     private static final int OUTPUT_ANYMOVE_TEST_Y = 2;
     
     private static final int OUTPUT_DECPIECE_TEST_X = 0; //expected x and y coordinates from output in decPiece test
     private static final int OUTPUT_DECPIECE_TEST_Y = 3;
     
     

/**
* This is the constructor to make the Othello Board
*
* @param OthelloPieces - an array which stores the pieces on the board
*/	

	private OthelloPiece[][] piecesToSwap = new OthelloPiece[HEIGHT][WIDTH];
	
	
/**	
* This is the constructor for the OthelloBoard class.
*
* @param height - this is the height of the board
* @param width - this is the width of the board
*/	
	public OthelloBoard (int height, int width){
            super(height, width);
            m_Pieces = new OthelloPiece[WIDTH][HEIGHT];
            m_PieceCount+=4;
            
       }


 /**
*
* This sets the position of the piece the player has chosen to move to 
* but only if its a valid move.
*
* @param x - the x co-ordinate of the players chosen move
* @param y - the y co-ordinate of the player chosen move
* @param setPiece - boolean variable which returns true if 
* 		      the poisition the players wants to move to is valid.
* @param colour -  a variable which stores which colour piece the player has, enum type.
*
* @return true -It returns true if the method returns successfully. 
**/
	
public boolean setPiece (int x, int y, OthelloPiece colour) {
        m_Pieces[x][y]=colour;
        
        return true;
        
    }
        

/** 
* This method is a boolean which returns true if the players chooses to place the piece in any 
* available positions out of the eight positions around the desired piece.
* 
* @param x - the x co-ordinate of the players chosen move
* @param y - the y co-ordinate of the player chosen move
* @param colour -  a variable which stores which colour piece the player has, enum type.
*
**/   
        
	public boolean move (int x,int y, OthelloPiece colour){
            boolean valid = false;
            if(move(x,y,VALID_MOVE_3,VALID_MOVE_2,colour)) valid=true;
            if(move(x,y,VALID_MOVE_3,VALID_MOVE_3,colour)) valid=true;
            if(move(x,y,VALID_MOVE_3,VALID_MOVE_1,colour)) valid=true;
            if(move(x,y,VALID_MOVE_2,VALID_MOVE_3,colour)) valid=true;
            if(move(x,y,VALID_MOVE_2,VALID_MOVE_1,colour)) valid=true;
            if(move(x,y,VALID_MOVE_1,VALID_MOVE_2,colour)) valid=true;
            if(move(x,y,VALID_MOVE_1,VALID_MOVE_3,colour)) valid=true;
            if(move(x,y,VALID_MOVE_1,VALID_MOVE_1,colour)) valid=true;
            setThePieces();
            if(valid){
                m_PieceCount++;
            }
            return (valid);
          }


/** 
* This method is a boolean which returns true if the players chooses to place the piece in any 
* available positions out of the eight positions around the desired piece.
*
* 
* @ param x - the x co-ordinate of the players chosen move
* @ param y - the y co-ordinate of the player chosen move
* @ param colour -  a variable which stores which colour piece the player has, enum type.
*
**/   

        private boolean anyMove (int x,int y, OthelloPiece colour){
            boolean valid = false;
            if(move(x,y,VALID_MOVE_3,VALID_MOVE_2,colour)) valid=true;
            if(move(x,y,VALID_MOVE_3,VALID_MOVE_3,colour)) valid=true;
            if(move(x,y,VALID_MOVE_3,VALID_MOVE_1,colour)) valid=true;
            if(move(x,y,VALID_MOVE_2,VALID_MOVE_3,colour)) valid=true;
            if(move(x,y,VALID_MOVE_2,VALID_MOVE_1,colour)) valid=true;
            if(move(x,y,VALID_MOVE_1,VALID_MOVE_2,colour)) valid=true;
            if(move(x,y,VALID_MOVE_1,VALID_MOVE_3,colour)) valid=true;
            if(move(x,y,VALID_MOVE_1,VALID_MOVE_1,colour)) valid=true;
            return (valid);
          }

/**
* This method clears the array with the pieces in.
*/

        public void clearPieces(){
            for(int i =0; i<WIDTH;i++){
                for(int j = 0 ; j<HEIGHT;j++){
                    piecesToSwap[i][j]=null;
                }
            }
        }
          
/**
* This method returns the pieces to swap from the array, which ultimately places the 
* pieces on the board.
* 
* @return piecesToSwap - this returns the pieces to swap
*/
         
    	public OthelloPiece[][] setPieces(){
        return piecesToSwap;
         
    }

/**
* This method sets the pieces on the board. 
*/

	private void setThePieces(){
            for(int i = 0; i<HEIGHT;i++){
              for(int j = 0; j<WIDTH;j++){
                  if(piecesToSwap[i][j]==null){
                      
                  }else{
                      m_Pieces[i][j]=piecesToSwap[i][j];
                      
                  }
              }
            }
        }

	
/**
* This method checks if there is a valid move anywhere on the board.
*
* @param boolean- anyValid returns true if there is a valid move anywhere on the board
* and false if there is no valid move.
*
*/

        public boolean anyValid(OthelloPiece colour){
            boolean valid = false;
            for(int i=0; i<WIDTH;i++){
                for(int j=0;j<HEIGHT;j++){
                    if(anyMove(i,j,colour)){
                        valid = true;
                    }
                }    
            }
                clearPieces();
            return valid;
        }
	
/**
*
* This is the method to decide whether the move the player wishes to make is valid or not.
*
* @param colour
* @param x - the x co-ordinate of the players chosen move
* @param y - the y co-ordinate of the player chosen move
* @param xdirection - the direction along the x-axis the chainswap has to follow
* @param ydirection - the direction along the x-axis the chainswap has to follow
* @param colour -  a variable which stores which colour piece the player has, enum type
*/


	private boolean move (int x, int y, int xdirection, int ydirection, OthelloPiece colour){
            
            OthelloPiece otherColour;
            int xsearch = x + xdirection;
            int ysearch = y + ydirection;


            if (colour.getPieceColour() == Piece.OthelloPieceColour.WHITE){
                otherColour = BLACK_PIECE;
               
            }else{
                otherColour = WHITE_PIECE;
               
            }
            if(m_Pieces[x][y].getPieceColour()==Piece.OthelloPieceColour.NONE){
               
                if(onBoard(xsearch,ysearch)){
                    if(m_Pieces[xsearch][ysearch].getPieceColour()==otherColour.getPieceColour()){
                    while(onBoard(xsearch,ysearch)&& 
                    	m_Pieces[xsearch][ysearch].getPieceColour()==otherColour.getPieceColour()){
                        xsearch+=xdirection;
                        ysearch+=ydirection;
                    }
                   
                    if(onBoard(xsearch,ysearch)&&
                    	m_Pieces[xsearch][ysearch].getPieceColour()== colour.getPieceColour()){
                        xsearch-=xdirection;
                        ysearch-=ydirection;
                        piecesToSwap[xsearch][ysearch]=colour;
                        while(m_Pieces[xsearch][ysearch].getPieceColour()==otherColour.getPieceColour()){
                            
                            xsearch-=xdirection; 
                            ysearch-=ydirection;
                            piecesToSwap[xsearch][ysearch]=colour;                          
                        }
                         return true;
                    }
                }
                }
            }else{
                return false;
            }
            return false;
	}
		
	
/**
*
* This class is a method which returns true if the chain of pieces is still on the board
* or returns false if the chain of pieces has hit the edge of the board.
*
* @param x - the x co-ordinate of the players chosen move
* @param y - the y co-ordinate of the player chosen move
*
*
*/	
	private boolean onBoard(int x, int y){
            if((x<WIDTH&&y<HEIGHT)){
                if(0<=x&&0<=y){
                    return true;
                }
                    return false;
            }else{
                    return false;
            }
	}
        
/**
* This is a boolean method which returns false 
*
* @return false - This returns false is the method is not run successfully
*/

	public boolean CheckWin(){
	            return false;
	        }

/**
* This is a boolean method which returns isFull() method.
*
* @param int - totalPieces - this is the number of total pieces on the board
* @return isFull - tells checkWin if the board is full or not
*/	        
  	 public boolean checkWin (int totalPieces){
		return isFull();
	}
		
	@Override
	boolean checkWin() {
		return false;
	}
			
/**
* This method is used to get the pieces on the board.
*
*@return m_Pieces - this is an array holding all the pieces on the board
*/

    	public OthelloPiece[][] getPieces() {
       	 return m_Pieces;
    	}


        /**
        * This method is to see if there are no pieces to set.
        *
        *@return false- This will return if the method fails to run successfully 
        */

        boolean setPiece(int x, int y, Piece.OthelloPieceColour colour) {
                return false;
        }
	

/**
* This method initiates the board with two white pieces and two black pieces in 
* the middle of the board. In the poistions (3,3),(4,4),(3,4),(4,3).
*/
	     public void setBoard(){
                
	        m_Pieces[3][3]= WHITE_PIECE;
            m_Pieces[4][4]= WHITE_PIECE;
            m_Pieces[3][4]= BLACK_PIECE;
            m_Pieces[4][3]= BLACK_PIECE;
            for(int x=0;x<WIDTH;x++){
                for(int y=0;y<HEIGHT;y++){
                    if(m_Pieces[x][y]==null){
                        m_Pieces[x][y]=NONE_PIECE;
                    }
                }
            }
        }
 

/**
* This method increments the piece count.
*/

	public  void incPieceCount () {
            m_PieceCount++;
	}
 

       
/**
* This method decrements the piece count.
*/	
	public void decPieceCount () {
		m_PieceCount --;
	}		
	
	
	
/**
* This method returns the piece count.
*
*@return m_PieceCount - This returns the piece count on the board
*/
        
	public int getPieceCount () {
            return m_PieceCount;
	}
	
/**
* This is a test for incPiececount and checkWin
*/

	public static void incPieceCountTest() {
       	OthelloBoard Board = new OthelloBoard(BOARD_SIZE,BOARD_SIZE);
       	Board.setBoard();
       	Board.incPieceCount();
       	System.out.println(Board.getPieceCount());
       	System.out.println(Board.move(
       			OUTPUT_DECPIECE_TEST_X, OUTPUT_DECPIECE_TEST_Y, Board.WHITE_PIECE));
       	Board.m_Pieces[TEST_PIECE_X][TEST_PIECE_Y]=Board.WHITE_PIECE;
       	System.out.println("");
       	System.out.println("");
		Board.checkWin();
		System.out.println("Valid inputs");
		System.out.println("OthelloBoard.checkWin() - Begin");
		System.out.println("Expected output: false");
		System.out.println("");
		System.out.println("Actual output: " + Board.checkWin());
       	System.out.println("");
    	}
	
	
 /**
* This is a test for decPiececount and CheckWin
*/	
	public static void decPieceCountTest() {
	       OthelloBoard Board = new OthelloBoard(BOARD_SIZE,BOARD_SIZE);
	       Board.setBoard();
	       Board.decPieceCount();
	       System.out.println(Board.getPieceCount());
	       System.out.println(Board.move(
	    		   TEST_MOVE_X1, TEST_MOVE_Y1, Board.WHITE_PIECE));
	       Board.m_Pieces[TEST_PIECE_X][TEST_PIECE_Y]=Board.WHITE_PIECE;
	       System.out.println("");
	       System.out.println("");
		Board.checkWin();
		System.out.println("Valid inputs");
		System.out.println("OthelloBoard.CheckWin() - Begin");
		System.out.println("Expected output: false");
		System.out.println("");
		System.out.println("Actual output: " + Board.CheckWin());
	       System.out.println("");
	    }
	

/**
* This is a test for setPiece
*/	

	public static void setPieceTest() {
		OthelloBoard Board = new OthelloBoard(BOARD_SIZE,BOARD_SIZE);
		Board.setBoard();
		Board.decPieceCount();
		System.out.println(Board.getPieceCount());
		System.out.println(Board.move(
				TEST_MOVE_X1, TEST_MOVE_Y1, Board.WHITE_PIECE));
		Board.m_Pieces[TEST_PIECE_X][TEST_PIECE_Y]=Board.WHITE_PIECE;
		System.out.println("");
		System.out.println("");
		Board.checkWin();
		System.out.println("Valid inputs");
		System.out.println("OthelloBoard.setPiece() - Begin");
		System.out.println("Expected output: true");
		System.out.println("");
		System.out.println("Actual output: " + 
		Board.setPiece(
				OUTPUT_SETPIECE_TEST_X,OUTPUT_SETPIECE_TEST_Y,Board.BLACK_PIECE));
		System.out.println("");
	}
	
	
/**	
* This is a test for move
*/
	
	public static void moveTest() {
		OthelloBoard Board = new OthelloBoard(BOARD_SIZE,BOARD_SIZE);
	       Board.setBoard();
	       Board.decPieceCount();
	       System.out.println(Board.getPieceCount());
	       System.out.println(Board.move(
	       TEST_MOVE_X1, TEST_MOVE_Y1, Board.WHITE_PIECE));
	       Board.m_Pieces[TEST_PIECE_X][TEST_PIECE_Y]=Board.WHITE_PIECE;
	       System.out.println("");
	       System.out.println("");
		Board.checkWin();
		System.out.println("Valid inputs");
		System.out.println("OthelloBoard.move() - Begin");
		System.out.println("Expected output: false");
		System.out.println("");
		System.out.println("Actual output: " + 
		Board.move(OUTPUT_MOVE_TEST_X,OUTPUT_MOVE_TEST_Y,Board.BLACK_PIECE));
	       System.out.println("");
	}
	
	

/**	
* This is a test for anyMove
*/	
	public static void anyMoveTest() {
		OthelloBoard Board = new OthelloBoard(BOARD_SIZE,BOARD_SIZE);
       	Board.setBoard();
		Board.decPieceCount();
       	System.out.println(Board.getPieceCount());	
		System.out.println(Board.move(
		TEST_MOVE_X1, TEST_MOVE_Y1, Board.WHITE_PIECE));
		Board.m_Pieces[TEST_PIECE_X][TEST_PIECE_Y]=Board.WHITE_PIECE;
		System.out.println("");
		System.out.println("");
		Board.checkWin();
		System.out.println("Valid inputs");
		System.out.println("OthelloBoard.anyMove() - Begin");
		System.out.println("Expected output: false");
		System.out.println("");
		System.out.println("Actual output: " 
		+ Board.anyMove(
				OUTPUT_ANYMOVE_TEST_X,OUTPUT_ANYMOVE_TEST_Y,Board.BLACK_PIECE));
		System.out.println("");
	}
		
/**	
* This is a test for getPieces
*/		
	public static void getPiecesTest() {
			OthelloBoard Board = new OthelloBoard(BOARD_SIZE,BOARD_SIZE);
		      	Board.setBoard();
			Board.decPieceCount();
		      	System.out.println(Board.getPieceCount());	
			System.out.println(Board.move(
					TEST_MOVE_X1, TEST_MOVE_Y1, Board.WHITE_PIECE));
			Board.m_Pieces[TEST_PIECE_X][TEST_PIECE_Y]=Board.WHITE_PIECE;
			System.out.println("");
			System.out.println("");
			Board.checkWin();
			System.out.println("Valid inputs");
			System.out.println("OthelloBoard.clearPieces() - Begin");
			System.out.println("Expected output: 0");
			System.out.println("");
			Board.clearPieces();
			System.out.println("Actual output: " + Board.getPieces());
			System.out.println("");
	}
	

/**	
* This is a test for setPiece
*/
	
	public static void setPieceTest2() {
				OthelloBoard Board = new OthelloBoard(BOARD_SIZE,BOARD_SIZE);
			      	Board.setBoard();
				Board.decPieceCount();
			      	System.out.println(Board.getPieceCount());	
				System.out.println(Board.move(
						TEST_MOVE_X1, TEST_MOVE_Y1, Board.WHITE_PIECE));
				Board.m_Pieces[TEST_PIECE_X][TEST_PIECE_Y]=Board.WHITE_PIECE;
				System.out.println("");
				System.out.println("");
				Board.checkWin();
				System.out.println("Valid inputs");
				System.out.println("OthelloBoard.clearPieces() - Begin");
				System.out.println("Expected output: Throws exception");
				System.out.println("");
				try{
					
				}catch(UnsupportedOperationException e){

				}
				System.out.println("");
		}
				
/** 		
* This is the main method of the OthelloBoard class. This method is used to test the 
* methods within the class with both valid and invalid parameters.
*/		
	public static void main (String[] args){
		incPieceCountTest();
		decPieceCountTest();
		setPieceTest();
		moveTest();
		anyMoveTest();
		getPiecesTest();
		setPieceTest2();
	}
}