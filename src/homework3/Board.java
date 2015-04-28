package homework3;
/**
 * @file    -Board.java
 * @author  -X. Chen
 * @date    - ...
 * 
 * \brief Stores information about if there is a piece object in a specific 
 * board position.
 * 
 */

public class Board {
	private int[][] board;
        private Piece[][] pieces;
	private int BOARD_WIDTH;
        private int BOARD_HEIGHT;
        
        
        public int[][] getBoard() {
		return board;
	}
        
        public int getBoardHeight(){
            return BOARD_HEIGHT;
        }
        
        public int getBoardWidth(){
            return BOARD_WIDTH;
        }
        
	public void setBoard(int m_boardWidth, int m_boardHeight) {
		board = new int[m_boardWidth][m_boardHeight];
                BOARD_HEIGHT = m_boardHeight;
                BOARD_WIDTH = m_boardWidth;
                
	}
        
	public void setPiece(int colour, int row, int column) {
		/**
                  marking the <row, column> as 1 means this position has a 
                  piece object.
		*/
                board[row][column] = colour;
		//pieces[row][column] = p;
	}

	public boolean isEmpty(int row, int column) {
		return board[row][column] == 1;
	}
}