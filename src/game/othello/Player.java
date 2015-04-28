package game.othello;

import game.OthelloGameGUI;
import game.OthelloPiece;

import javax.swing.ImageIcon;

public abstract class Player {
	public OthelloGameGUI ogg;
	public ImageIcon piece;
	public OthelloPiece playerColour;
	public int playerNo;

	public abstract boolean move(int x, int y);

	public Player(ImageIcon piece, OthelloPiece pc, OthelloGameGUI ogg,
			int playerNo) {
		this.piece = piece;
		this.playerColour = pc;
		this.ogg = ogg;
		this.playerNo = playerNo;
	}

	/**
	 * @param OthelloPiece
	 *            [][] piecesToSwap - array containing the pieces that need
	 *            swapping This method will flip all pieces that need to be
	 *            flipped
	 */
	protected void swapPieces(OthelloPiece piecesToSwap[][]) {
		for (int i = 0; i < ogg.TOTALWIDTH; i++) {
			for (int j = 0; j < ogg.TOTALHEIGHT; j++) {
				if (piecesToSwap[i][j] == null) {
					// do nothing
				} else if (this.playerNo == 1) {
					player1SwapPieces(i, j);
				} else if (this.playerNo == 2) {
					player2SwapPieces(i, j);
				}
			}
		}
	}

	/**
	 * @param int i - x coord of point on board
	 * @param int j - y coord of point on board
	 * 
	 *        used to swap player one's pieces
	 */
	@SuppressWarnings("static-access")
	private void player1SwapPieces(int i, int j) {
		if (ogg.gridButtons[i][j].getIcon() == ogg.blackPiece
				|| ogg.gridButtons[i][j].getIcon() == ogg.whiteToBlackPiece) {

			ogg.gridButtons[i][j].setIcon(ogg.blackToWhitePiece);
			ogg.gameCheck.setPlayer1Score(1);
			ogg.gameCheck.setPlayer2Score(-1);
			ogg.blackToWhitePiece.getImage().flush();
			// gridButtons[i][j].setIcon(whitePiece);
		}
	}

	@SuppressWarnings("static-access")
	private void player2SwapPieces(int i, int j) {
		if (ogg.gridButtons[i][j].getIcon() == ogg.whitePiece
				|| ogg.gridButtons[i][j].getIcon() == ogg.blackToWhitePiece) {
			ogg.gridButtons[i][j].setIcon(ogg.whiteToBlackPiece);
			ogg.gameCheck.setPlayer2Score(1);
			ogg.gameCheck.setPlayer1Score(-1);

			ogg.whiteToBlackPiece.getImage().flush();
		}
	}
	public abstract boolean isHuman();
}
