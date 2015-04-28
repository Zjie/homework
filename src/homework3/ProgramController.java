package homework3;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// For animate classes, we can update board to show there is a piece in neither position, the have a draw(int x, int y, BufferedImage image) method, which will draw any image
// at custom coordinates (not board position) on the board.

public class ProgramController extends JFrame implements MouseListener,
		ActionListener {
	private static final long serialVersionUID = 8560415728005943968L;
	private final static String BASE_DIR = "src\\homework3";

	void ProgramController() throws IOException {
		Object[] options = { "Connect 4", "Othello" };

		int n = JOptionPane.showOptionDialog(this,
				"Would you like to play Connect 4 " + "or Othello",
				"Pick a game", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

		if (n == 0) {
			m_IsC4 = true;
		} else if (n == 1) {
			m_IsC4 = false;
		} else {
			System.out.println("Too-da-loo");
			System.exit(1);
		}

		if (m_IsC4 == true) {
			m_Game = new Connect4();
			m_Background_File = new File(BASE_DIR
					+ "\\Images\\Connect4Background.png");
		} else if (m_IsC4 == false) {
			m_Game = new Othello();
			m_Background_File = new File(BASE_DIR
					+ "\\Images\\OthelloBackground.png");
		} else {
			System.out.println("ERROR");
		}

		BOARD = m_Game.getBoard();
		m_Square_Labels = new JLabel[BOARD.getBoardWidth()][BOARD
				.getBoardHeight()];

		try {
			m_Background_Image = ImageIO.read(m_Background_File);
		} catch (IOException e) {
			System.out.println("IOException Error");
		}

		for (int y = 0; y < BOARD.getBoardHeight(); y++) {
			for (int x = 0; x < BOARD.getBoardWidth(); x++) {
				m_Square_Labels[x][y] = new JLabel(new ImageIcon(
						m_Background_Image));
				m_Square_Labels[x][y].addMouseListener(this);
			}
		}

		container = getContentPane();
		container.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		this.setMinimumSize(new Dimension(64 * BOARD.getBoardWidth(),
				64 * BOARD.getBoardHeight() + 22)); // Magic Numbers

		newGameButton = new JButton("New Game");
		c.gridy = BOARD.getBoardHeight() + 2;
		c.gridx = BOARD.getBoardWidth() / 2 - 1;
		c.gridwidth = 2;
		container.add(newGameButton, c);
		newGameButton.addActionListener(this);

		turnLabel = new JLabel("Player 1's turn");
		c.gridy = BOARD.getBoardHeight() + 1;
		c.gridx = (BOARD.getBoardWidth() / 2) - 4;
		c.gridwidth = 8;
		container.add(turnLabel, c);
		newGameButton.addActionListener(this);

		for (int y = 0; y < BOARD.getBoardHeight(); y++) {
			for (int x = 0; x < BOARD.getBoardWidth(); x++) {
				c.gridx = x;
				c.gridy = y;
				c.gridwidth = 1;
				container.add(m_Square_Labels[x][y], c);
			}
		}

		update((m_Game.getBoard()).getBoard(), "Black", "White");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	void update(int[][] board, String colour1, String colour2)
			throws IOException {
		System.out.println("ProgramController::update()");

		int t = board[0].length;
		int s = board.length;

		turnLabel.setText("Player " + (TURN + 1) + "'s turn");

		for (int y = 0; y < t; y++) {
			for (int x = 0; x < s; x++) {
				if (board[x][y] == 0) {
					m_Square_Labels[x][y].setIcon(new ImageIcon(
							m_Background_Image));
				} else if (board[x][y] == 1) {
					BufferedImage piece_Image = ImageIO.read(new File(BASE_DIR
							+ "\\Images\\" + colour1 + "Piece.png"));
					m_Square_Labels[x][y].setIcon(new ImageIcon(piece_Image));
				} else if (board[x][y] == 2) {
					BufferedImage piece_Image = ImageIO.read(new File(BASE_DIR
							+ "\\Images\\" + colour2 + "Piece.png"));
					m_Square_Labels[x][y].setIcon(new ImageIcon(piece_Image));
				} else {
					System.out.println("What colour piece are you using?");
				}
			}
		}
	}

	public GameImplementation getGame() {
		return m_Game;
	}

	public void actionPerformed(ActionEvent event) {
		if (m_IsC4 == true) {
			m_Game = new Connect4();
			try {
				update((m_Game.getBoard()).getBoard(), "Red", "Yellow");
			} catch (IOException e) {
			}
		} else {
			m_Game = new Othello();
			try {
				update((m_Game.getBoard()).getBoard(), "Black", "White");
			} catch (IOException e) {
			}
		}
		TURN = 0; // Reset turn to 0.
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("Clicked");
		if (m_Game.checkWin() == false) {
			for (int y = 0; y < BOARD.getBoardHeight(); y++) {
				for (int x = 0; x < BOARD.getBoardWidth(); x++) {
					if (e.getSource() == m_Square_Labels[x][y])
						attemptMove(x, y);
				}
			}
		} else {
			try {
				displayWinner();
			} catch (IOException exception) {
			}

		}

	}

	private void attemptMove(int x, int y) {
		Player[] players = new Player[2];
		players[PLAYER_ONE] = m_Game.getPlayer(PLAYER_ONE);
		players[PLAYER_TWO] = m_Game.getPlayer(PLAYER_TWO);

		boolean checkMoveIsValid = false;
		checkMoveIsValid = players[TURN].move(x, y, this);
		if (checkMoveIsValid == true) {
			if (m_Game.checkTakeableTurn(players[PLAYER_TWO]) == true) {
				/*
				 * If it's 0 (player 1) turn this will change it to 1 (player 2)
				 * turn
				 */
				TURN = (TURN + 1) % 2;
				turnLabel.setText("Player " + (TURN + 1) + "'s turn");
			} else if (m_Game.checkTakeableTurn(players[PLAYER_ONE]) == false
					&& m_Game.checkTakeableTurn(players[1]) == false) {
				try {
					displayWinner();
				} catch (IOException exception2) {
				}
			}
		}
	}

	private void displayWinner() throws IOException {
		if (m_Game.getWinner() == 1 || m_Game.getWinner() == 2) {
			System.out.println("Winner: Player " + (m_Game.getWinner()));
			turnLabel.setText("Player " + m_Game.getWinner()
					+ " is the winner!");
		} else if (m_Game.getWinner() == 3) {
			System.out.println("Draw!");
			turnLabel.setText("It's a draw!");
		} else {
			System.out.println("The only way to win is to not play the game.");
			turnLabel.setText("The only way to win is to not play the game.");
		}

		Highlight highlight = new Highlight();

		if (m_IsC4 == true) {
			highlight.C4Highlight(m_Game.getWinningi(), m_Game.getWinningj(),
					m_Game, m_Square_Labels);
		} else {
		}

	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public static void main(String[] args) throws IOException {
		ProgramController controller = new ProgramController();
		controller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controller.ProgramController();
	}

	int TURN = 0;
	int PLAYER_ONE = 0;
	int PLAYER_TWO = 1;

	private static boolean m_IsC4;
	private static boolean m_IsOthello;

	static GameImplementation m_Game;

	static File m_Background_File;
	static BufferedImage m_Background_Image;
	private Board BOARD;
	private JLabel[][] m_Square_Labels;
	private JPanel PANEL;
	private static int m_WIDTH;
	private static int m_HEIGHT;

	JButton newGameButton;
	JLabel turnLabel;
	Container container;
	GridBagConstraints c;
}