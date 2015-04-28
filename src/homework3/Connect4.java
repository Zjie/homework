package homework3;

/**
 * @file -Connect4.java
 * @author -I.C. Skinner
 * @date -18 Feb '14
 * 
 *       \brief This class controls the flow of the game, specific to Connect 4,
 *       such as checking if there are 4 of the same coloured pieces in a
 *       vertical, horizontal or diagonal line.
 * 
 */

public class Connect4 extends GameImplementation {

	public Connect4() {
		this.setBoard(BOARD_WIDTH, BOARD_HEIGHT);
		getPlayer(PLAYER_ONE).setColour("Red");
		getPlayer(PLAYER_ONE).setName("Player 1");

		getPlayer(PLAYER_TWO).setColour("Yellow");
		getPlayer(PLAYER_TWO).setName("Player 2");
	}

	public boolean checkWin() {
		int[][] boardArray = (getBoard()).getBoard();
		int j;
		int i;

		for (j = 0; j < (BOARD_HEIGHT); j++) {
			for (i = 0; i < (BOARD_WIDTH - 3); i++) {
				if (boardArray[i][j] == boardArray[i + 1][j]
						&& boardArray[i][j] == boardArray[i + 2][j]
						&& boardArray[i][j] == boardArray[i + 3][j]
						&& boardArray[i][j] != EMPTY_SQUARE) {
					setWinner(boardArray[i][j]);
					m_Winning_i = i;
					m_Winning_j = j;
					m_Winning_Move = 0;
					return true;
				}
			}
		}

		for (i = 0; i < (BOARD_WIDTH); i++) {
			for (j = 0; j < (BOARD_HEIGHT - 3); j++) {
				if (boardArray[i][j] == boardArray[i][j + 1]
						&& boardArray[i][j] == boardArray[i][j + 2]
						&& boardArray[i][j] == boardArray[i][j + 3]
						&& boardArray[i][j] != EMPTY_SQUARE) {
					setWinner(boardArray[i][j]);
					m_Winning_i = i;
					m_Winning_j = j;
					m_Winning_Move = 1;
					return true;
				}
			}
		}

		for (j = 0; j < (BOARD_HEIGHT - 3); j++) {
			for (i = 0; i < (BOARD_WIDTH - 3); i++) {
				if (boardArray[i][j] == boardArray[i + 1][j + 1]
						&& boardArray[i][j] == boardArray[i + 2][j + 2]
						&& boardArray[i][j] == boardArray[i + 3][j + 3]
						&& boardArray[i][j] != EMPTY_SQUARE) {
					setWinner(boardArray[i][j]);
					m_Winning_i = i;
					m_Winning_j = j;
					m_Winning_Move = 2;
					return true;
				}
			}
		}

		for (j = 0; j < (BOARD_HEIGHT - 3); j++) {
			for (i = 3; i < (BOARD_WIDTH); i++) {
				if (boardArray[i][j] == boardArray[i - 1][j + 1]
						&& boardArray[i][j] == boardArray[i - 2][j + 2]
						&& boardArray[i][j] == boardArray[i - 3][j + 3]
						&& boardArray[i][j] != EMPTY_SQUARE) {
					setWinner(boardArray[i][j]);
					m_Winning_i = i;
					m_Winning_j = j;
					m_Winning_Move = 3;
					return true;
				}
			}
		}

		boolean boardFull = isBoardFull(BOARD_WIDTH, BOARD_HEIGHT);
		if (boardFull == true) {
			setWinner(DRAW);
		} else {
		} // draw
		return boardFull;
	}

	public boolean checkValid(int x, int y, Player player) {
		System.out.println("Connect4::checkValid()");
		if ((getBoard()).getBoard()[x][0] != EMPTY_SQUARE) {
			System.out.println("Invalid move");
			return false;
		} else {

			return true;
		}
	}

	public void setPiece(int x, int m, Player player) {
		System.out.println("Connect4::setPiece()");
		if ((getBoard()).getBoard()[x][m] == EMPTY_SQUARE) {
			int piece;
			if (player.getColour().equals("Red")) {
				piece = PLAYER_ONE_PIECE;
				m_Red_Pieces++;
			} else {
				piece = PLAYER_TWO_PIECE;
				m_Yellow_Pieces++;
			}
			(getBoard()).setPiece(piece, x, m);
		}
		/*
		int columnIndex = BOARD_HEIGHT - 1;
		boolean columnNotFull = true;
		while (columnNotFull) {
			if (columnIndex < 0) {
				columnNotFull = false;
			} else if ((getBoard()).getBoard()[x][columnIndex] == EMPTY_SQUARE) {
				int piece;
				if (player.getColour().equals("Red")) {
					piece = PLAYER_ONE_PIECE;
					m_Red_Pieces++;
				} else {
					piece = PLAYER_TWO_PIECE;
					m_Yellow_Pieces++;
				}
				(getBoard()).setPiece(piece, x, columnIndex);
				columnNotFull = false;
			} else {
			}
			columnIndex--;
		}
		*/
	}

	public int getPlayerPieceTotal(Player player) {
		if (player.getColour() == "Red") {
			return m_Red_Pieces;
		} else {
			return m_Yellow_Pieces;
		}
	}

	/**
	 * public static void main(String[] args){ Connect4 C4 = new Connect4();
	 * 
	 * C4.gameLoop(); }
	 */

	public int getWinningMove() {
		return m_Winning_Move;
	}

	public int getWinningi() {
		return m_Winning_i;
	}

	public int getWinningj() {
		return m_Winning_j;
	}

	int m_Winning_Move;
	int m_Winning_i;
	int m_Winning_j;

	private int m_Red_Pieces;
	private int m_Yellow_Pieces;

	private final int DRAW = 3;

	private final int PLAYER_ONE_PIECE = 1;
	private final int PLAYER_TWO_PIECE = 2;

	private static final int BOARD_HEIGHT = 7;
	private static final int BOARD_WIDTH = 10;
}
