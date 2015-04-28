package game.othello;

import game.OthelloGameGUI;
import game.OthelloPiece;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

public class ComputerHardPlayer extends Player {
	public ComputerHardPlayer(ImageIcon piece, OthelloPiece pc,
			OthelloGameGUI ogg, int playerNo) {
		super(piece, pc, ogg, playerNo);
	}

	@Override
	public boolean move(int x, int y) {
		//ignore x and y
		Random r = new Random();
		int i = r.nextInt(ogg.WIDTH);
        int j = r.nextInt(ogg.HEIGHT);
        Map<Integer, Integer[]> temp = new HashMap<Integer, Integer[]>();
        for (int xi = 0; xi < ogg.WIDTH; xi++) {
        	for (int yj = 0; yj < ogg.HEIGHT; yj++) {
        		
        	}
        }
        // Assuming their piece is white...
        // Othello isn't using the HumanPlayer class at the moment...
        // That fact may need to change...
        while (!ogg.gameCheck.board.anyValid(this.playerColour)) {
            i = r.nextInt(ogg.WIDTH);
            j = r.nextInt(ogg.HEIGHT);
        }
        if (ogg.gameCheck.board.anyValid(this.playerColour)) {
			if (ogg.gameCheck.move(i, j, playerColour)) {
				ogg.gridButtons[i][j].setIcon(piece);
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
		return false;
	}
	
}
