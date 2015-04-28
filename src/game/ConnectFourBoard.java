package game;
/**
* @file ConnectFourBoard.java
* @author Daniel Kelleher & Ieuan Skinner
* @date 30 Jan 2014
* @see 'JAVA for everyone' - Cay Horstmann, Second edition, page 420 for 
*	   implementing subclasses.
*
* @brief This class creates the board for the game of Connect Four 
* 		 it is extended from the abstract class Board.
* 
* This class creates the board for the Connect Four game, 
* it stores the number of pieces in plays, 
* sets all pieces on board, 
* takes user's input to place a new piece on the board, 
* checks if there is a winning set of pieces on the board, 
* if the board is full so no other pieces can be played and 
* can return all the piece positions on the board.
*/

public class ConnectFourBoard extends Board<Piece.ConnectFourPieceColour,
															ConnectFourPiece> {
	
	/**< constant for the size of the board in x direction */ 
	public static final int WIDTH = 10; 
	
	/**< constant for the size of the board in y direction */ 
	public static final int HEIGHT = 7; 
	
	/**< array of piece variables storing all pieces on the board */ 
	private static ConnectFourPiece[][] m_Pieces = 
		new ConnectFourPiece[WIDTH][HEIGHT]; 

	/**< constant for column one on the board */ 					
	private final int COLUMN_ONE = 0;

	/**< constant for column two on the board */ 
	private final int COLUMN_TWO = 1;

	/**< constant for column three on the board */ 
	private final int COLUMN_THREE = 2;
	
	/**< constant for column four on the board */ 
	private final int COLUMN_FOUR = 3;
	
	/**< constant for column five on the board */ 
	private final int COLUMN_FIVE = 4;
	
	/**< constant for column six on the board */ 
	private final int COLUMN_SIX = 5;
	
	/**< constant for column seven on the board */ 
	private final int COLUMN_SEVEN = 6;

	/**< constant for row one on the board */ 
	private final int ROW_ONE = 0;
	
	/**< constant for row two on the board */ 
	private final int ROW_TWO = 1;

	/**< constant for row three on the board */ 
	private final int ROW_THREE = 2;

	/**< constant for row four on the board */ 
	private final int ROW_FOUR = 3;
	
	/**< constant for row five on the board */ 
	private final int ROW_FIVE = 4;

	/**< constant for row six on the board */ 
	private final int ROW_SIX = 5;
	
	/**< constant for row seven on the board */ 
	private final int ROW_SEVEN = 6;

	/**< constant for row eight on the board */ 
	private final int ROW_EIGHT = 7;

	/**< constant for row nine on the board */ 
	private final int ROW_NINE = 8;
	
	/**< constant for row ten on the board */ 
	private final int ROW_TEN = 9;

	/**< create an object of Piece with colour NONE so to give the colour to the 
	EMPTY_PIECE constant*/ 
	private ConnectFourPiece m_EmptyPiece = new
							ConnectFourPiece(Piece.ConnectFourPieceColour.NONE);

	/**< constant for storing the empty piece colour for comparing with other 
	pieces,for setting a piece and checking for win scenario*/ 
	private final Piece.ConnectFourPieceColour	EMPTY_PIECE =
												m_EmptyPiece.getPieceColour();				

	/**< create an object of Piece with colour RED so to give the colour to the 
	RED_PIECE constant*/ 
	private static ConnectFourPiece m_RedPiece = new
							ConnectFourPiece(Piece.ConnectFourPieceColour.RED);

	/**< constant for storing the red piece colour for comparing with other
	pieces,	for setting a piece and checking for win scenario*/  							
	private final static Piece.ConnectFourPieceColour	RED_PIECE = 
													m_RedPiece.getPieceColour();

	/**< create an object of Piece with colour YELLOW so to give the colour to 
	the YELLOW_PIECE constant*/  
	private static ConnectFourPiece m_YellowPiece = new
						ConnectFourPiece(Piece.ConnectFourPieceColour.YELLOW);

	/**< constant for storing the yellow piece colour for comparing with other 
	pieces,	for setting a piece and checking for win scenario*/ 					
	private final static Piece.ConnectFourPieceColour	YELLOW_PIECE = 
												m_YellowPiece.getPieceColour();

	/**< create an object of Piece with colour RED_STAR so to give the colour 
	to the RED_STAR_PIECE constant*/ 
	private ConnectFourPiece m_RedStarPiece = new
						ConnectFourPiece(Piece.ConnectFourPieceColour.RED_STAR);

	/**< constant for storing the red star piece colour for setting the winning 
	red pieces to this colour to display the winning pieces*/ 					
	private final Piece.ConnectFourPieceColour	RED_STAR_PIECE = 
												m_RedStarPiece.getPieceColour();

	/**< create an object of Piece with colour YELLOW_STAR so to give the colour 
	to the YELLOW_STAR__PIECE constant*/ 
	private ConnectFourPiece m_YellowStarPiece = new
					ConnectFourPiece(Piece.ConnectFourPieceColour.YELLOW_STAR);

	/**< constant for storing the yellow star piece colour for setting the 
	winning	yellow pieces to this colour to display the winning pieces*/ 					
	private final Piece.ConnectFourPieceColour	YELLOW_STAR_PIECE = 
											m_YellowStarPiece.getPieceColour();

	/** 
	* This is the constructor for the ConnectFourBoard class.
	*
	* @param width -the width of the board, therefore width of the array storing 
    *               all the pieces on the board.
    * @param height -the height of the board, therefore width of the array  
    *               storing all the pieces on the board. 
	*/
	public ConnectFourBoard(int width, int height) {
		super(height, width);
        for (int j=0; j<WIDTH; j++) {
			for (int i=0; i<HEIGHT; i++) {
				m_Pieces[j][i] = new ConnectFourPiece(EMPTY_PIECE);
			}
		}
	}
	
	public static Piece.ConnectFourPieceColour getRedPiece(){
		return RED_PIECE;
	}
	
	public static Piece.ConnectFourPieceColour getYellowPiece(){
		return YELLOW_PIECE;
	}
	
	/** 
	* This is the method for setting a piece on the board from the users input.
	*
	* @param x -the variable storing the x-coordinate of the pieces position.
	* @param colour -the variable storing the colour of the piece, enum type.
	* @return piecesSet -a boolean variable returned as true if the piece
	*					 was set or false if it was not.
	*/
	public boolean setPiece(int x, int y, Piece.ConnectFourPieceColour colour) {
		boolean pieceSet = false;
		for (int i=0; i<HEIGHT; i++) {
			if (m_Pieces[x][i].getPieceColour() == EMPTY_PIECE) {
				m_Pieces[x][i].setPieceColour(colour);
				m_PieceCount++;
				pieceSet = true;
				return pieceSet;
			}
		}
		return pieceSet;
	}
	
	/** 
	* This is the method for getting the next available empty slot for a new 
	* piece that is to be placed in a specific column.
	*
	* @param x -the variable storing the column in which a piece is being set.
	* @return i -the variable storing the row in which the next available slot 
	* 			 the new piece can be placed.
	*/
	public int getLowestEmptySlot(int x){
		for (int i=0; i<HEIGHT; i++) {
			if (m_Pieces[x][i].getPieceColour() == EMPTY_PIECE) {
				return i;
			}
		}	
		return 0;	
	}

	/** 
	* This is the method for getting all of the pieces on the board.
	* 
	* @return Piece[][] -the array of pieces is returned to get all
	*				     the pieces on the board. 
	*/
	public static ConnectFourPiece[][] getPieces() {
		return m_Pieces;
	}

	/** 
	* This is the method for checking if there are a winning set of 
	* pieces on the board and therefore if the game has been won.
	*
	* @return gameWon -a boolean variable returned as true if the game
	*				   has been won or false if it has not. 		 
	*/
	public boolean checkWin() {
		boolean gameWon = false;
		boolean gameWonBackDiagonal = false;
		boolean gameWonVertical = false;
		boolean gameWonForwardDiagonal = false;
		boolean gameWonHorizontal = false;
		for (int j=0; j<WIDTH ; j++) {
			for (int i=0; i<HEIGHT ; i++) {
				if (m_Pieces[j][i].getPieceColour() != EMPTY_PIECE) {
					gameWonBackDiagonal = checkBackDiagonal(j,i,m_Pieces);
					if (gameWonBackDiagonal) {
						gameWon = true;
						return gameWon;
					}
					gameWonVertical = checkVertical(j,i,m_Pieces);
					if (gameWonVertical) {
						gameWon = true;
						return gameWon;
					}
					gameWonForwardDiagonal = checkForwardDiagonal(j,i,m_Pieces);
					if (gameWonForwardDiagonal) {
						gameWon = true;
						return gameWon;
					}
					gameWonHorizontal = checkHorizontal(j,i,m_Pieces);
					if (gameWonHorizontal) {
						gameWon = true;
						return gameWon;
					}
				}
			}
		}
		return gameWon;
	}
	
	/** 
	* This is the method called in checkWin() for checking if there are 
	* a winning set of pieces on the board in a downward diagonal row.
	*
	* @param j -the variable storing column position of the piece.
	* @param i -the variable storing row position of the piece.
	* @param m_Pieces -the variable storing the array of pieces.
	* @return gameWon -a boolean variable returned as true if there are 
	*				   a set of four identical pieces in a downward diagonal
	*				   row or false if it has not. 
	*/
	private boolean checkBackDiagonal(int j, int i, 
										ConnectFourPiece[][] m_Pieces) {
		boolean gameWon = false;
		int secondPieceDDiagonalColoumn = i-1;
		int thirdPieceDDiagonalColoumn = i-2;
		int fourthPieceDDiagonalColoumn = i-3;
		int secondPieceDDiagonalRow = j+1;
		int thirdPieceDDiagonalRow = j+2;
		int fourthPieceDDiagonalRow = j+3;

		if (j<ROW_EIGHT && i>COLUMN_THREE && 
			m_Pieces[j][i].getPieceColour() == 
			m_Pieces[secondPieceDDiagonalRow]
			[secondPieceDDiagonalColoumn].getPieceColour()) {

			if (j<ROW_EIGHT && i>COLUMN_THREE && 
				m_Pieces[j][i].getPieceColour() == 
				m_Pieces[thirdPieceDDiagonalRow]
				[thirdPieceDDiagonalColoumn].getPieceColour()) {

				if (j<ROW_EIGHT && i>COLUMN_THREE 
					&& m_Pieces[j][i].getPieceColour() == 
					m_Pieces[fourthPieceDDiagonalRow]
					[fourthPieceDDiagonalColoumn].getPieceColour()) {

					if (m_Pieces[j][i].getPieceColour() == RED_PIECE) {

						m_Pieces[j][i].setPieceColour(RED_STAR_PIECE);

						m_Pieces[secondPieceDDiagonalRow]
						[secondPieceDDiagonalColoumn]
						.setPieceColour(RED_STAR_PIECE);

						m_Pieces[thirdPieceDDiagonalRow]
						[thirdPieceDDiagonalColoumn]
						.setPieceColour(RED_STAR_PIECE);

						m_Pieces[fourthPieceDDiagonalRow]
						[fourthPieceDDiagonalColoumn]
						.setPieceColour(RED_STAR_PIECE);

					} else if (m_Pieces[j][i].getPieceColour() == 
						YELLOW_PIECE) {

						m_Pieces[j][i].setPieceColour(YELLOW_STAR_PIECE);

						m_Pieces[secondPieceDDiagonalRow]
						[secondPieceDDiagonalColoumn]
						.setPieceColour(YELLOW_STAR_PIECE);

						m_Pieces[thirdPieceDDiagonalRow]
						[thirdPieceDDiagonalColoumn]
						.setPieceColour(YELLOW_STAR_PIECE);

						m_Pieces[fourthPieceDDiagonalRow]
						[fourthPieceDDiagonalColoumn]
						.setPieceColour(YELLOW_STAR_PIECE);
					}
					gameWon = true;
				}
			}
		} 
		return gameWon;
	}	
	
	/** 
	* This is the method called in checkWin() for checking if there are 
	* a winning set of pieces on the board in a vertical row.
	*
	* @param j -the variable storing column position of the piece.
	* @param i -the variable storing row position of the piece.
	* @param m_Pieces -the variable storing the array of pieces.
	* @return gameWon -a boolean variable returned as true if there are 
	*				   a set of four identical pieces in a vertical row 
	*				   or false if it has not. 
	*/
	private boolean checkVertical(int j, int i, ConnectFourPiece[][] m_Pieces) {	
		boolean gameWon = false;
		int secondPieceUp = i+1;
		int thirdPieceUp = i+2;
		int fourthPieceUp = i+3;
		if (i<COLUMN_FIVE && m_Pieces[j][i].getPieceColour() == 
			m_Pieces[j][secondPieceUp].getPieceColour()) {

			if (i<COLUMN_FIVE && m_Pieces[j][i].getPieceColour() == 
				m_Pieces[j][thirdPieceUp].getPieceColour()) {

				if (i<=COLUMN_FIVE && m_Pieces[j][i].getPieceColour() == 
					m_Pieces[j][fourthPieceUp].getPieceColour()) {

                    if (m_Pieces[j][i].getPieceColour() == RED_PIECE) {

						m_Pieces[j][i].setPieceColour(RED_STAR_PIECE);

						m_Pieces[j][secondPieceUp]
						.setPieceColour(RED_STAR_PIECE);
						
						m_Pieces[j][thirdPieceUp]
						.setPieceColour(RED_STAR_PIECE);
						
						m_Pieces[j][fourthPieceUp]
						.setPieceColour(RED_STAR_PIECE);

					} else if (m_Pieces[j][i].getPieceColour() == 
						YELLOW_PIECE) {

						m_Pieces[j][i].setPieceColour(YELLOW_STAR_PIECE);

						m_Pieces[j][secondPieceUp]
						.setPieceColour(YELLOW_STAR_PIECE);

						m_Pieces[j][thirdPieceUp]
						.setPieceColour(YELLOW_STAR_PIECE);

						m_Pieces[j][fourthPieceUp]
						.setPieceColour(YELLOW_STAR_PIECE);
					}
					gameWon = true;
				}
			}
		} 
		return gameWon;
	}
	
	/** 
	* This is the method called in checkWin() for checking if there are 
	* a winning set of pieces on the board in a upward diagonal row.
	*
	* @param j -the variable storing column position of the piece.
	* @param i -the variable storing row position of the piece.
	* @param m_Pieces -the variable storing the array of pieces.
	* @return gameWon -a boolean variable returned as true if there are 
	*				   a set of four identical pieces in a upward diagonal row 
	*				   or false if it has not. 
	*/
	private boolean checkForwardDiagonal(int j, int i, 
												ConnectFourPiece[][] m_Pieces) {
		boolean gameWon = false;
		int secondPieceFDiagonalColoumn = i+1;
		int thirdPieceFDiagonalColoumn = i+2;
		int fourthPieceFDiagonalColoumn = i+3;
		int secondPieceFDiagonalRow = j+1;
		int thirdPieceFDiagonalRow = j+2;
		int fourthPieceFDiagonalRow = j+3;

		if (j<ROW_EIGHT && i<COLUMN_FIVE && 
			m_Pieces[j][i].getPieceColour() == 
			m_Pieces[secondPieceFDiagonalRow][secondPieceFDiagonalColoumn]
			.getPieceColour()) {

			if (j<ROW_EIGHT && i<COLUMN_FIVE 
				&& m_Pieces[j][i].getPieceColour() == 
				m_Pieces[thirdPieceFDiagonalRow][thirdPieceFDiagonalColoumn]
				.getPieceColour()) {

				if (j<ROW_EIGHT && i<COLUMN_FIVE && 
					m_Pieces[j][i].getPieceColour() == 
					m_Pieces[fourthPieceFDiagonalRow]
					[fourthPieceFDiagonalColoumn]
					.getPieceColour()) {

                    if (m_Pieces[j][i].getPieceColour() == RED_PIECE) {

						m_Pieces[j][i].setPieceColour(RED_STAR_PIECE);

						m_Pieces[secondPieceFDiagonalRow]
							[secondPieceFDiagonalColoumn]
							.setPieceColour(RED_STAR_PIECE);

						m_Pieces[thirdPieceFDiagonalRow]
							[thirdPieceFDiagonalColoumn]
							.setPieceColour(RED_STAR_PIECE);

						m_Pieces[fourthPieceFDiagonalRow]
							[fourthPieceFDiagonalColoumn]
							.setPieceColour(RED_STAR_PIECE);

					} else if (m_Pieces[j][i].getPieceColour() == 
						YELLOW_PIECE) {

						m_Pieces[j][i].setPieceColour(YELLOW_STAR_PIECE);

						m_Pieces[secondPieceFDiagonalRow]	
							[secondPieceFDiagonalColoumn]
							.setPieceColour(YELLOW_STAR_PIECE);

						m_Pieces[thirdPieceFDiagonalRow]
							[thirdPieceFDiagonalColoumn]
							.setPieceColour(YELLOW_STAR_PIECE);

						m_Pieces[fourthPieceFDiagonalRow]
							[fourthPieceFDiagonalColoumn]
							.setPieceColour(YELLOW_STAR_PIECE);
					}
					gameWon = true;
				}
			}
		} 
		return gameWon;
	}
	
	/** 
	* This is the method called in checkWin() for checking if there are 
	* a winning set of pieces on the board in a horizontal row.
	*
	* @param j -the variable storing column position of the piece.
	* @param i -the variable storing row position of the piece.
	* @param m_Pieces -the variable storing the array of pieces.
	* @return gameWon -a boolean variable returned as true if there are 
	*				   a set of four identical pieces in a horizontal row 
	*				   or false if it has not. 
	*/
	private boolean checkHorizontal(int j, int i, ConnectFourPiece[][] m_Pieces) {	
		boolean gameWon = false;
		int secondPieceForwardRow = j+1;
		int thirdPieceForwardRow = j+2;
		int fourthPieceForwardRow = j+3;

		if (j<ROW_EIGHT && m_Pieces[j][i].getPieceColour() == 
			m_Pieces[secondPieceForwardRow][i].getPieceColour()) {

			if (j<ROW_EIGHT && m_Pieces[j][i].getPieceColour() == 
				m_Pieces[thirdPieceForwardRow][i].getPieceColour()) {
				
				if (j<ROW_EIGHT && m_Pieces[j][i].getPieceColour() == 
					m_Pieces[fourthPieceForwardRow][i].getPieceColour()) {
                    
                    if (m_Pieces[j][i].getPieceColour() == RED_PIECE) {
						
						m_Pieces[j][i].setPieceColour(RED_STAR_PIECE);
						
						m_Pieces[secondPieceForwardRow][i]
							.setPieceColour(RED_STAR_PIECE);
						
						m_Pieces[thirdPieceForwardRow][i]
							.setPieceColour(RED_STAR_PIECE);
						
						m_Pieces[fourthPieceForwardRow][i]
							.setPieceColour(RED_STAR_PIECE);

					} else if (m_Pieces[j][i].getPieceColour() == 
						YELLOW_PIECE) {
						
						m_Pieces[j][i].setPieceColour(YELLOW_STAR_PIECE);
						
						m_Pieces[secondPieceForwardRow][i]
							.setPieceColour(YELLOW_STAR_PIECE);
						
						m_Pieces[thirdPieceForwardRow][i]
							.setPieceColour(YELLOW_STAR_PIECE);
						
						m_Pieces[fourthPieceForwardRow][i]
							.setPieceColour(YELLOW_STAR_PIECE);
					}
					gameWon = true;
				}
			}
		}
		return gameWon;
	}

	/** 
	* This method is for testing purposes to check the methods of the class 
	* ConnectFourBoard, it sets a number of pieces to postions on the board, 
	* this board set up will give a non-full board with a win senerio, and
	* allow a piece to be set.
	*
	* @param c4Board -the object of the class.
	* @return boardSet -a boolean variable returned as true if the peices  
	*					 are all set or false if they are not not.
	*/
	private boolean testSetPiecesOne(ConnectFourBoard c4Board) {
		boolean boardSet = false;
		c4Board.setPiece(ROW_ONE,COLUMN_ONE,YELLOW_PIECE);
		c4Board.setPiece(ROW_ONE,COLUMN_TWO,YELLOW_PIECE);
		c4Board.setPiece(ROW_ONE,COLUMN_THREE,YELLOW_PIECE);
		c4Board.setPiece(ROW_ONE,COLUMN_FOUR,RED_PIECE);
		
		c4Board.setPiece(ROW_TWO,COLUMN_ONE,YELLOW_PIECE);
		c4Board.setPiece(ROW_TWO,COLUMN_TWO,RED_PIECE);
		c4Board.setPiece(ROW_TWO,COLUMN_THREE,RED_PIECE);
			
		c4Board.setPiece(ROW_THREE,COLUMN_ONE,RED_PIECE);
		c4Board.setPiece(ROW_THREE,COLUMN_TWO,RED_PIECE);
		c4Board.setPiece(ROW_THREE,COLUMN_THREE,RED_PIECE);
		
		c4Board.setPiece(ROW_THREE,COLUMN_ONE,RED_PIECE);
		c4Board.setPiece(ROW_THREE,COLUMN_TWO,RED_PIECE);
		c4Board.setPiece(ROW_THREE,COLUMN_THREE,YELLOW_PIECE);
		c4Board.setPiece(ROW_THREE,COLUMN_FOUR,RED_PIECE);
		
		c4Board.setPiece(ROW_FOUR,COLUMN_ONE,RED_PIECE);
		
		c4Board.setPiece(ROW_FIVE,COLUMN_ONE,YELLOW_PIECE);
		boardSet = true;
		return boardSet;
	}
	
	/** 
	* This method is for testing purposes to check the methods of the class 
	* ConnectFourBoard, it sets a number of pieces to positions on the board, 
	* this board set up will give a full board with a non-win scenario, and not
	* allow a piece to be set.
	*
	* @param c4Board -the object of the class.
	* @return boardSet -a boolean variable returned as true if the pieces  
	*					 are all set or false if they are not not.
	*/
	private boolean testSetPiecesTwo(ConnectFourBoard c4Board) {
		boolean boardSet = false;
		c4Board.setPiece(ROW_ONE,COLUMN_ONE,YELLOW_PIECE);
		c4Board.setPiece(ROW_ONE,COLUMN_TWO,YELLOW_PIECE);
		c4Board.setPiece(ROW_ONE,COLUMN_THREE,RED_PIECE);
		c4Board.setPiece(ROW_ONE,COLUMN_FOUR,YELLOW_PIECE);
		c4Board.setPiece(ROW_ONE,COLUMN_FIVE,RED_PIECE);
		c4Board.setPiece(ROW_ONE,COLUMN_SIX,RED_PIECE);
		c4Board.setPiece(ROW_ONE,COLUMN_SEVEN,YELLOW_PIECE);
		c4Board.setPiece(ROW_TWO,COLUMN_ONE,RED_PIECE);
		c4Board.setPiece(ROW_TWO,COLUMN_TWO,YELLOW_PIECE);
		c4Board.setPiece(ROW_TWO,COLUMN_THREE,RED_PIECE);
		c4Board.setPiece(ROW_TWO,COLUMN_FOUR,YELLOW_PIECE);
		c4Board.setPiece(ROW_TWO,COLUMN_FIVE,RED_PIECE);
		c4Board.setPiece(ROW_TWO,COLUMN_SIX,YELLOW_PIECE);
		c4Board.setPiece(ROW_TWO,COLUMN_SEVEN,RED_PIECE);
		c4Board.setPiece(ROW_THREE,COLUMN_ONE,YELLOW_PIECE);
		c4Board.setPiece(ROW_THREE,COLUMN_TWO,RED_PIECE);
		c4Board.setPiece(ROW_THREE,COLUMN_THREE,YELLOW_PIECE);
		c4Board.setPiece(ROW_THREE,COLUMN_FOUR,YELLOW_PIECE);
		c4Board.setPiece(ROW_THREE,COLUMN_FIVE,RED_PIECE);
		c4Board.setPiece(ROW_THREE,COLUMN_SIX,YELLOW_PIECE);
		c4Board.setPiece(ROW_THREE,COLUMN_SEVEN,YELLOW_PIECE);
		c4Board.setPiece(ROW_FOUR,COLUMN_ONE,YELLOW_PIECE);
		c4Board.setPiece(ROW_FOUR,COLUMN_TWO,RED_PIECE);
		c4Board.setPiece(ROW_FOUR,COLUMN_THREE,RED_PIECE);
		c4Board.setPiece(ROW_FOUR,COLUMN_FOUR,RED_PIECE);
		c4Board.setPiece(ROW_FOUR,COLUMN_FIVE,YELLOW_PIECE);
		c4Board.setPiece(ROW_FOUR,COLUMN_SIX,RED_PIECE);
		c4Board.setPiece(ROW_FOUR,COLUMN_SEVEN,YELLOW_PIECE);
		c4Board.setPiece(ROW_FIVE,COLUMN_ONE,RED_PIECE);
		c4Board.setPiece(ROW_FIVE,COLUMN_TWO,YELLOW_PIECE);
		c4Board.setPiece(ROW_FIVE,COLUMN_THREE,RED_PIECE);
		c4Board.setPiece(ROW_FIVE,COLUMN_FOUR,YELLOW_PIECE);
		c4Board.setPiece(ROW_FIVE,COLUMN_FIVE,RED_PIECE);
		c4Board.setPiece(ROW_FIVE,COLUMN_SIX,YELLOW_PIECE);
		c4Board.setPiece(ROW_FIVE,COLUMN_SEVEN,YELLOW_PIECE);
		c4Board.setPiece(ROW_SIX,COLUMN_ONE,RED_PIECE);
		c4Board.setPiece(ROW_SIX,COLUMN_TWO,YELLOW_PIECE);
		c4Board.setPiece(ROW_SIX,COLUMN_THREE,RED_PIECE);
		c4Board.setPiece(ROW_SIX,COLUMN_FOUR,RED_PIECE);
		c4Board.setPiece(ROW_SIX,COLUMN_FIVE,RED_PIECE);
		c4Board.setPiece(ROW_SIX,COLUMN_SIX,YELLOW_PIECE);
		c4Board.setPiece(ROW_SIX,COLUMN_SEVEN,RED_PIECE);
		c4Board.setPiece(ROW_SEVEN,COLUMN_ONE,RED_PIECE);
		c4Board.setPiece(ROW_SEVEN,COLUMN_TWO,RED_PIECE);
		c4Board.setPiece(ROW_SEVEN,COLUMN_THREE,YELLOW_PIECE);
		c4Board.setPiece(ROW_SEVEN,COLUMN_FOUR,YELLOW_PIECE);
		c4Board.setPiece(ROW_SEVEN,COLUMN_FIVE,YELLOW_PIECE);
		c4Board.setPiece(ROW_SEVEN,COLUMN_SIX,RED_PIECE);
		c4Board.setPiece(ROW_SEVEN,COLUMN_SEVEN,YELLOW_PIECE);
		c4Board.setPiece(ROW_EIGHT,COLUMN_ONE,YELLOW_PIECE);
		c4Board.setPiece(ROW_EIGHT,COLUMN_TWO,YELLOW_PIECE);
		c4Board.setPiece(ROW_EIGHT,COLUMN_THREE,RED_PIECE);
		c4Board.setPiece(ROW_EIGHT,COLUMN_FOUR,RED_PIECE);
		c4Board.setPiece(ROW_EIGHT,COLUMN_FIVE,YELLOW_PIECE);
		c4Board.setPiece(ROW_EIGHT,COLUMN_SIX,RED_PIECE);
		c4Board.setPiece(ROW_EIGHT,COLUMN_SEVEN,YELLOW_PIECE);
		c4Board.setPiece(ROW_NINE,COLUMN_ONE,YELLOW_PIECE);
		c4Board.setPiece(ROW_NINE,COLUMN_TWO,RED_PIECE);
		c4Board.setPiece(ROW_NINE,COLUMN_THREE,YELLOW_PIECE);
		c4Board.setPiece(ROW_NINE,COLUMN_FOUR,YELLOW_PIECE);
		c4Board.setPiece(ROW_NINE,COLUMN_FIVE,RED_PIECE);
		c4Board.setPiece(ROW_NINE,COLUMN_SIX,RED_PIECE);
		c4Board.setPiece(ROW_NINE,COLUMN_SEVEN,RED_PIECE);
		c4Board.setPiece(ROW_TEN,COLUMN_ONE,YELLOW_PIECE);
		c4Board.setPiece(ROW_TEN,COLUMN_TWO,RED_PIECE);
		c4Board.setPiece(ROW_TEN,COLUMN_THREE,YELLOW_PIECE);
		c4Board.setPiece(ROW_TEN,COLUMN_FOUR,RED_PIECE);
		c4Board.setPiece(ROW_TEN,COLUMN_FIVE,YELLOW_PIECE);
		c4Board.setPiece(ROW_TEN,COLUMN_SIX,YELLOW_PIECE);
		c4Board.setPiece(ROW_TEN,COLUMN_SEVEN,YELLOW_PIECE);
		boardSet = true;
		return boardSet;
	}

	/** 
	* This method is for testing purposes to check the methods of the class 
	* ConnectFourBoard, it prints out the board layout as it is when the 
	* getPieces method is called, it prints out all the piece colours at their 
	* position on the board.
	*
	* @param Pieces -this is an array holding all the pieces on the board with 
	*				 thier colour values.
	*/
	private void testPrintPieceColours(ConnectFourPiece[][] Pieces){
		for (int i=0; i<WIDTH; i++) {
			System.out.println("");
			for (int j=0; j<HEIGHT; j++) {
					System.out.print(Pieces[i][j].getPieceColour()+", ");
			}
		}
		System.out.println("");
	}
	
	/** 
	* This is the main method of the ConnectFourBoard class, it is used to test
	* all the methods in this class with valid and invalid parameters to show 
	* how the methods hold up against different inputs.
	*
	*/
	public static void main(String[] args) {
		int WIDTH = 10;
		int HEIGHT = 7;
		ConnectFourBoard c4Board1 = new ConnectFourBoard(WIDTH,HEIGHT);
		ConnectFourPiece[][] c4Pieces1 = new ConnectFourPiece[WIDTH][HEIGHT]; 
		for (int i=0; i<WIDTH; i++) {
			for (int j=0; j<HEIGHT; j++) {
				c4Pieces1[i][j] = c4Board1.m_Pieces[i][j];
			}
		}

		int columnValue = 4;
		int rowValue = 1;

		if (c4Board1.testSetPiecesOne(c4Board1)) {
			System.out.println("Valid inputs");
			System.out.println("ConnectFourBoard.setPiece() - Begin");
			System.out.println("Expected output: true");
			System.out.println("");
			System.out.println("Actual output: " + 
								c4Board1.setPiece(columnValue,rowValue,c4Board1.RED_PIECE));
			System.out.println("ConnectFourBoard.setPiece() - End"); 
			System.out.println("");
			System.out.println("ConnectFourBoard.getPieceCount() - Begin");
			System.out.println("Expected output: 17");
			System.out.println("");
			System.out.println("Actual output: " + c4Board1.getPieceCount());
			System.out.println("ConnectFourBoard.getPieceCount() - End");
			System.out.println("");
			System.out.println("ConnectFourBoard.isFull() - Begin");
			System.out.println("Expected output: false");
			System.out.println("");
			System.out.println("Actual output: " + 
									c4Board1.isFull());
			System.out.println("ConnectFourBoard.isFull() - End");
			System.out.println("");
			System.out.println("ConnectFourBoard.checkWin() - Begin");
			System.out.println("Expected output: true");
			System.out.println("");
			System.out.println("Actual output: " + c4Board1.checkWin());
			System.out.println("ConnectFourBoard.checkWin() - End");
			System.out.println("");
			System.out.println("ConnectFourBoard.getPieces() - Begin");
			System.out.println("Expected output: ");
			c4Board1.testPrintPieceColours(c4Pieces1);
			System.out.println("");
			System.out.println("Actual output: ");
			c4Board1.testPrintPieceColours(c4Board1.getPieces());
			System.out.println("ConnectFourBoard.getPieces() - End");
			System.out.println("");
		}

		ConnectFourBoard c4Board2 = new ConnectFourBoard(WIDTH,HEIGHT);
		ConnectFourPiece[][] c4Pieces2 = new ConnectFourPiece[WIDTH][HEIGHT]; 
		for (int i=0; i<WIDTH; i++) {
			for (int j=0; j<HEIGHT; j++) {
				c4Pieces2[i][j] = c4Board2.m_Pieces[i][j];
			}
		}

		if (c4Board2.testSetPiecesTwo(c4Board2)) {
			System.out.println("Invalid inputs");
			System.out.println("ConnectFourBoard.setPiece() - Begin");
			System.out.println("Expected output: false");
			System.out.println("");
			System.out.println("Actual output: " + 
								c4Board2.setPiece(columnValue,rowValue,c4Board2.YELLOW_PIECE));
			System.out.println("ConnectFourBoard.setPiece() - End"); 
			System.out.println("");
			System.out.println("ConnectFourBoard.getPieceCount() - Begin");
			System.out.println("Expected output: 70");
			System.out.println("");
			System.out.println("Actual output: " + c4Board2.getPieceCount());
			System.out.println("ConnectFourBoard.getPieceCount() - End");
			System.out.println("");
			System.out.println("ConnectFourBoard.isFull() - Begin");
			System.out.println("Expected output: true");
			System.out.println("");
			System.out.println("Actual output: " + 
									c4Board2.isFull());
			System.out.println("ConnectFourBoard.isFull() - End");
			System.out.println("");
			System.out.println("ConnectFourBoard.checkWin() - Begin");
			System.out.println("Expected output: false");
			System.out.println("");
			System.out.println("Actual output: " + c4Board2.checkWin());
			System.out.println("ConnectFourBoard.checkWin() - End");
			System.out.println("");
			System.out.println("ConnectFourBoard.getPieces() - Begin");
			System.out.println("Expected output: ");
			c4Board2.testPrintPieceColours(c4Pieces2);
			System.out.println("");
			System.out.println("Actual output: ");
			c4Board2.testPrintPieceColours(c4Board2.getPieces());
			System.out.println("ConnectFourBoard.getPieces() - End");
		}
		
	}
}