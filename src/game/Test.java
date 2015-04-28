package game;

public class Test{

	public static OthelloPiece WHITE_PIECE = new OthelloPiece(Piece.OthelloPieceColour.WHITE);
       // creates an object of Piece which contains a black piece 
	public static OthelloPiece BLACK_PIECE = new OthelloPiece(Piece.OthelloPieceColour.BLACK);
       // creates an object of Piece which contains 
	public static OthelloPiece NONE_PIECE = new OthelloPiece(Piece.OthelloPieceColour.NONE);

	/**< create an object of Piece with colour RED so to give the colour to the 
	RED_PIECE constant*/ 
	private ConnectFourPiece m_RedPiece = new ConnectFourPiece(Piece.ConnectFourPieceColour.RED);

	/**< constant for storing the red piece colour for comparing with other
	pieces,	for setting a piece and checking for win scenario*/  							
	private final Piece.ConnectFourPieceColour RED_PIECE = m_RedPiece.getPieceColour();

	/**< create an object of Piece with colour YELLOW so to give the colour to 
	the YELLOW_PIECE constant*/  
	private ConnectFourPiece m_YellowPiece = new ConnectFourPiece(Piece.ConnectFourPieceColour.YELLOW);

	/**< constant for storing the yellow piece colour for comparing with other 
	pieces,	for setting a piece and checking for win scenario*/ 					
	private final Piece.ConnectFourPieceColour YELLOW_PIECE = m_YellowPiece.getPieceColour();
	public static final int WIDTH = 10; 
	
	/**< constant for the size of the board in y direction */ 
	public static final int HEIGHT = 7; 
	
	/**< array of piece variables storing all pieces on the board */ 
	private ConnectFourPiece[][] m_Pieces = new ConnectFourPiece[WIDTH][HEIGHT]; 

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







	private static void testOthelloGame(){
		
		final int SCORE_TWO = 2;
		final int SCORE_TEN = 10;
		final int TEST_PLAYER_NAME_0 = 0;
		final int TEST_PLAYER_NAME_100 = 100;
		
		
		OthelloPiece testPiece = new OthelloPiece(OthelloPiece.OthelloPieceColour.WHITE);

                OthelloGame testGame = new OthelloGame("Tom","Human",OthelloPiece.OthelloPieceColour.BLACK,
                                            "Harry","Human",OthelloPiece.OthelloPieceColour.WHITE);
        testGame.board.setBoard();
        System.out.println("testing implemented methods:");
        System.out.println("testing placing a piece in a valid square (5,3) WHITE");
        System.out.println("expected output: true");
        System.out.println(testGame.move(COLUMN_SIX,ROW_FOUR,testPiece));
        System.out.println();
        System.out.println("testing placing a piece in an invalid square (0,3) WHITE");
        System.out.println("expected output: false");
        System.out.println(testGame.move(COLUMN_ONE,ROW_FOUR,testPiece));
        System.out.println();
        System.out.println("testing updating player1 score by 2");
        System.out.println("expected output: player score 4");
        testGame.setPlayer1Score(SCORE_TWO);
        System.out.println("player score: "+testGame.getPlayer1Score());
        System.out.println();
        System.out.println("testing updating player2 score by 10");
        System.out.println("expected output: player score 12");
        testGame.setPlayer2Score(SCORE_TEN);
        System.out.println("player score: "+testGame.getPlayer2Score());
        System.out.println();
        System.out.println("testing inherited methods:");
        String[] s = testGame.getPlayerNames();
        System.out.println("testing getPlayerNames()");
        System.out.println("expected output: Tom, Harry");
        System.out.println(s[0]+" , "+s[1]);
        System.out.println();
        System.out.println("testing getPlayerName(0)");
        System.out.println("expected output: Tom");
        System.out.println(testGame.getPlayerName(TEST_PLAYER_NAME_0));
        System.out.println();
        System.out.println("testing getPlayerName(100)");
        System.out.println("expected output: Harry");
        System.out.println(testGame.getPlayerName(TEST_PLAYER_NAME_100));
    }

    private static void testOthelloPiece(){
    	OthelloPiece piece = new OthelloPiece(Piece.OthelloPieceColour.BLACK);
		
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

	private static void testMenuGUI(){
		int VALID_WIDTH = 370;		
		int VALID_HEIGHT = 300;
	
		MenuGUI menu = new MenuGUI("Main Menu", VALID_WIDTH, VALID_HEIGHT);
		
	}
	private static void testConnectFourPiece(){
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

	private static void testConnectFourBoard(){
		//could not test as everything is private
	}

	private static void testOthelloGameGUI(){
		final int WIDTH = 600, HEIGHT = 600;
		String[] s = new String[2];
		s[0]="Tom";
		s[1]="Harry";
		OthelloGameGUI displayExample = new OthelloGameGUI("Othello",WIDTH,HEIGHT);
		displayExample.setPlayers(s);
		displayExample.creatingGui();
	}

	private static void testHumanPlayer(){
		
	final int TEST_SCORE = 2;
	ConnectFourPiece player1Piece;
        player1Piece = new ConnectFourPiece(ConnectFourPiece.ConnectFourPieceColour.YELLOW);
        HumanPlayer player1 = new HumanPlayer("Tom",player1Piece.getPieceColour());
        System.out.println("Player1: " +player1.getName());
        System.out.println("Colour: " +player1.getColour());
        System.out.println("score: " +player1.getScore());
        player1.setScore(TEST_SCORE);
        System.out.println("Change score: " +player1.getScore());
        OthelloPiece player2Piece;
        player2Piece = new OthelloPiece(OthelloPiece.OthelloPieceColour.BLACK);
        HumanPlayer player2 = new HumanPlayer("Tom",player2Piece.getPieceColour());
        System.out.println("Player1: " +player2.getName());
        System.out.println("Colour: " +player2.getColour());
        System.out.println("score: " +player2.getScore());
        player1.setScore(TEST_SCORE);
        System.out.println("Change score: " +player2.getScore());
	}
	public static void test2() {
	final int WIDTH = 8, HEIGHT = 8;
       	OthelloBoard Board = new OthelloBoard(WIDTH,HEIGHT);
       	Board.setBoard();
       	Board.incPieceCount();
       	System.out.println(Board.getPieceCount());
       	System.out.println(Board.move(COLUMN_ONE,ROW_FOUR, WHITE_PIECE));
       	Board.m_Pieces[COLUMN_SIX][ROW_FOUR]=WHITE_PIECE;
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
	public static void test3() {
	       final int WIDTH = 8, HEIGHT = 8;
	       OthelloBoard Board = new OthelloBoard(WIDTH,HEIGHT);
	       Board.setBoard();
	       Board.decPieceCount();
	       System.out.println(Board.getPieceCount());
	       System.out.println(Board.move(COLUMN_THREE,ROW_FOUR, WHITE_PIECE));
	       Board.m_Pieces[COLUMN_SIX][ROW_FOUR]=WHITE_PIECE;
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

	public static void test4() {
		final int WIDTH = 8, HEIGHT = 8;
	        OthelloBoard Board = new OthelloBoard(WIDTH,HEIGHT);
		Board.setBoard();
		Board.decPieceCount();
		System.out.println(Board.getPieceCount());
		System.out.println(Board.move(COLUMN_THREE,ROW_FOUR, WHITE_PIECE));
		Board.m_Pieces[COLUMN_SIX][ROW_FOUR]=WHITE_PIECE;
		System.out.println("");
		System.out.println("");
		Board.checkWin();
		System.out.println("Valid inputs");
		System.out.println("OthelloBoard.setPiece() - Begin");
		System.out.println("Expected output: true");
		System.out.println("");
		System.out.println("Actual output: " + Board.setPiece(COLUMN_FOUR,ROW_SIX,BLACK_PIECE));
		System.out.println("");
	}
	
	
/**	
* This is a test for move
*/
	
	public static void test5() {
	       final int WIDTH = 8, HEIGHT = 8;
	       OthelloBoard Board = new OthelloBoard(WIDTH,HEIGHT);
	       Board.setBoard();
	       Board.decPieceCount();
	       System.out.println(Board.getPieceCount());
	       System.out.println(Board.move(COLUMN_THREE,ROW_FOUR, WHITE_PIECE));
	       Board.m_Pieces[COLUMN_SIX][ROW_FOUR]=WHITE_PIECE;
	       System.out.println("");
	       System.out.println("");
		Board.checkWin();
		System.out.println("Valid inputs");
		System.out.println("OthelloBoard.move() - Begin");
		System.out.println("Expected output: false");
		System.out.println("");
		System.out.println("Actual output: " + Board.move(COLUMN_TWO,ROW_THREE,BLACK_PIECE));
	       System.out.println("");
	}
	
		
/**	
* This is a test for getPieces
*/		
	public static void test7() {
			final int WIDTH = 8, HEIGHT = 8;
	                OthelloBoard Board = new OthelloBoard(WIDTH,HEIGHT);
		      	Board.setBoard();
			Board.decPieceCount();
		      	System.out.println(Board.getPieceCount());	
			System.out.println(Board.move(COLUMN_THREE,ROW_FOUR, WHITE_PIECE));
			Board.m_Pieces[COLUMN_SIX][ROW_FOUR]=WHITE_PIECE;
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
	
	public static void test8() {
				final int WIDTH = 8, HEIGHT = 8;
	                	OthelloBoard Board = new OthelloBoard(WIDTH,HEIGHT);
			      	Board.setBoard();
				Board.decPieceCount();
			      	System.out.println(Board.getPieceCount());	
				System.out.println(Board.move(COLUMN_THREE,ROW_FOUR, WHITE_PIECE));
				Board.m_Pieces[COLUMN_SIX][ROW_FOUR]=WHITE_PIECE;
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

		private static void testOthelloBoard(){
			test2();
	     	test3();
	     	test4();
	     	test5();
		test7();
		test8();
		}

		private static void testConnectFour(){
			ConnectFourGameGUI connectFour = 
					    new ConnectFourGameGUI("tom", "harry");
		}


	public static void main (String[] args){
		System.out.println("testing OthelloGame");
		testOthelloGame();
		System.out.println("testing OthelloPiece");
		testOthelloPiece();
		System.out.println("testing ConnectFourPiece");
		testConnectFourPiece();
		System.out.println("testing menuGUI");
		testMenuGUI();
		System.out.println("testing ConnectFourBoard");
		testConnectFourBoard();
		System.out.println("testing OthelloGameGUI");
		testOthelloGameGUI();
		System.out.println("testing HumanPlayer");
		testHumanPlayer();
		System.out.println("testing OthelloBoard");
		testOthelloBoard();
		System.out.println("testing connectFourGUI");

	}
	
}
