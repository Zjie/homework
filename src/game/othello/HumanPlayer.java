package game.othello;

import javax.swing.ImageIcon;

import game.OthelloGameGUI;
import game.OthelloPiece;

public class HumanPlayer extends Player {

	public HumanPlayer(ImageIcon piece, OthelloPiece pc, OthelloGameGUI ogg,
			int playerNo) {
		super(piece, pc, ogg, playerNo);
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean move(int x, int y) {
		if (ogg.gameCheck.board.anyValid(this.playerColour)) {
			if (ogg.gameCheck.move(x, y, playerColour)) {
				ogg.gridButtons[x][y].setIcon(piece);
				OthelloPiece piecesToSwap[][] = ogg.gameCheck.board.setPieces();

				swapPieces(piecesToSwap);
				if (this.playerNo == 1)
					ogg.gameCheck.setPlayer1Score(1);
				else if (this.playerNo == 2)
					ogg.gameCheck.setPlayer2Score(1);
				ogg.gameCheck.board.clearPieces();
				// gridButtons[x][y].setIcon(whitePiece);
				System.out.print("player 1 score: "
						+ ogg.gameCheck.getPlayer1Score());
				System.out.println(" player 2 score: "
						+ ogg.gameCheck.getPlayer2Score());

				return true;// made a valid move
			}

			return false;// invalid move
		}
		return true; // no possible move
	}

	@Override
	public boolean isHuman() {
		return true;
	}

}
