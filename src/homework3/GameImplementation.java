package homework3;
/**
 * @file    -GameImplementation.java
 * @author  -I.C. Skinner
 * @date    -18 Feb '14 
 * 
 * \brief This class controls the flow of the game such as creating the board 
 * and deciding whose turn it is.
 * 
*/

abstract public class GameImplementation {
	public GameImplementation() {
		setPlayer(0, new Human());
		setPlayer(1, new Human());
	}
	
    public boolean checkWin(){
        return false;
    }

    public boolean isBoardFull(int boardWidth, int boardHeight) {
        boolean boardFull = true;
            for (int i = 0; i < boardWidth; i++) {
                for (int j = 0; j < boardHeight; j++) {
                    if (m_board.getBoard()[i][j] == 0) {
                        boardFull = false;
                    }
		}
            }
        return boardFull;
    }
	
    // Returns true if a position passed in is a valid place for the user to place a piece
    public boolean checkValid(int x, int y, Player player){
        return false;
    }

	public boolean checkTakeableTurn(Player player) {
		return true;		
	}
    
    public void setPiece(int x, int y, Player player){
    }    
    
    public void setBoard(int boardWidth, int boardHeight) {
        System.out.println("GameImplementation::setBoard()");
        int board[][] = new int[boardWidth][boardHeight];
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                board[i][j] = 0;
            }
        }
        m_board = new Board(); 
        m_board.setBoard(boardWidth, boardHeight); 
	
    }
    
    public Board getBoard(){
        return m_board;
    }
        
    public Player getPlayer(int playerNumber){
        return m_player[playerNumber];
    }

	private void setPlayer(int playerNumber, Player player) {
		m_player[playerNumber] = player;
	}
    
    public int getWinner() {
        return winner;
    }

	public int setWinner(int playerNumber) {
		winner = playerNumber;
		return winner;
	}

		
	public int getWinningMove(){
		return 0;
	}
    
	public int getWinningi(){
		return 0;
	}
	
	public int getWinningj(){
		return 0;
	}
	
    private final Player[] m_player  = new Player[2]; 
    private Board m_board = new Board();
    private int winner;
	
	final int EMPTY_SQUARE = 0;
	final int PLAYER_ONE = 0;
	final int PLAYER_TWO = 1;
}
