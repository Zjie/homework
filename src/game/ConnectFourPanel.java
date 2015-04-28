package game;
/**
* @file Game.java
* @author Kristoffer Page
* @date 31 Jan 2014
* @see jPanel and AWT Graphics JavaDoc -
*        http://docs.oracle.com/javase/7/docs/api/javax/swing/JPanel.html
*        http://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html
* 
* @brief This class is a jPanel in a jFrame. It is used for drawing and 
*        animating the board and pieces.
*
* This class is used for drawing the board and pieces to the screen.
* It handles the animation of falling pieces
*/

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ConnectFourPanel extends JPanel {
    /**< Image for storing the board image */
    Image board;
    /**< Image for storing the yellow piece image */
    Image yellowPiece;
    /**< Image for storing the yellow piece with star image */
    Image yellowPieceStar;
    /**< Image for storing the red piece image */
    Image redPiece;
    /**< Image for storing the red piece with star image */
    Image redPieceStar;

    /**< The current pieces X coordinate*/
    private int m_pieceX;

    /**< The current pieces Y coordinate*/
    private int m_pieceY;

    /**< Specifies if a piece is currently being animated or 'dropping' */
    private boolean dropping;

    /**< The colour of the current piece */
    Piece.ConnectFourPieceColour currentPieceColour;

    /**< The distance between the side of the board and the window */
    public static final int BOARD_SIDE_SPACING = 10;

    /**< Width of the piece (used for spacing of the pieces on the board) */
    private final int PIECE_WIDTH = 65;

    /**< Spacing from the top of the window to the board */
    public static final int TOP_SPACING = 92;

    /**< Width of the board image */
    final static int BOARD_IMAGE_WIDTH = 780;

    /**< Height of the board image */
    final static int BOARD_IMAGE_HEIGHT = 546;

    /**< Height of the board (i.e. the number of positions) */
    private final static int BOARD_HEIGHT = ConnectFourBoard.HEIGHT;

    /**< Width of the board (i.e the number of positions) */
    private final static int BOARD_WIDTH = ConnectFourBoard.WIDTH;

    /**< the spacing between pieces */
    private final int SPACING = 12;

    /**< Y coordinate of the currently selected piece (or mouse piece) */
    private final int MOUSE_PIECE_Y = TOP_SPACING - PIECE_WIDTH - SPACING;

    /**< Spacing in X direction between each piece*/
    public static final int X_SPACING = 78;

    /**< Spacing in Y direction between each piece*/
    public static final int Y_SPACING = 78;

    /**< Array with spacings between pieces in X */
    private final int[] PIECE_X = { 0*X_SPACING + SPACING, 
        1*X_SPACING + SPACING, 2*X_SPACING + SPACING, 
        3*X_SPACING + SPACING, 4*X_SPACING + SPACING, 
        5*X_SPACING + SPACING, 6*X_SPACING + SPACING, 
        7*X_SPACING + SPACING, 8*X_SPACING + SPACING, 
        9*X_SPACING + SPACING };

    /**< Array with spacings between pieces in Y */
    private final int[] PIECE_Y = {0*Y_SPACING, 1*Y_SPACING, 2*Y_SPACING, 
        3*Y_SPACING, 4*Y_SPACING, 5*Y_SPACING, 6*Y_SPACING};

    /**< Same as Piece.ConnectFourPieceColour.RED */
    public static final Piece.ConnectFourPieceColour RED_PIECE 
        = Piece.ConnectFourPieceColour.RED;
    
    /**< Same as Piece.ConnectFourPieceColour.YELLOW */
    public static final Piece.ConnectFourPieceColour YELLOW_PIECE
        = Piece.ConnectFourPieceColour.YELLOW;

    /**< Same as Piece.ConnectFourPieceColour.RED_STAR */
    public static final Piece.ConnectFourPieceColour RED_STAR
        = Piece.ConnectFourPieceColour.RED_STAR;

    /**< Same as Piece.ConnectFourPieceColour.YELLOW_STAR */
    public static final Piece.ConnectFourPieceColour YELLOW_STAR
        = Piece.ConnectFourPieceColour.YELLOW_STAR;

    /**< Array of pieces */
    private ConnectFourPiece[][] m_pieces;

    /**< Tread for animation of falling piece */
    public Thread animationThread = new Thread();

    /**
    * Constructor for ConnectFourPanel
    * @param pieces - 2D array of pieces to display
    */
    public ConnectFourPanel(ConnectFourPiece[][] pieces) {
        //Initialise m_pieces
        m_pieces = new ConnectFourPiece[BOARD_WIDTH][BOARD_HEIGHT];
        updatePieces(pieces);

        //Load board image
        ImageIcon boardImage;
        try {
            boardImage = new ImageIcon(this.getClass()
                .getResource("Board.png"));
            board = boardImage.getImage();
        } catch (Exception e) {
            System.out.println("Board not found");
        }

        //Load Yellow Piece Image
        ImageIcon yellowPieceImage;
        try {
            yellowPieceImage = new ImageIcon(this.getClass()
                .getResource("Yellow75.png"));
            yellowPiece = yellowPieceImage.getImage();
        } catch (Exception e) {
            System.out.println("Yellow Piece: not found");
        }
        //Load Yellow Star Image
        ImageIcon yellowPieceStarImage;
        try {
            yellowPieceStarImage = new ImageIcon(this.getClass()
                .getResource("Yellow75Final.png"));
            yellowPieceStar = yellowPieceStarImage.getImage();
        } catch (Exception e) {
            System.out.println("Yellow star Piece: not found");
        }

        //Load Red Image
        ImageIcon redPieceImage;
        try {
            redPieceImage = new ImageIcon(this.getClass()
                .getResource("Red75.png"));
            redPiece = redPieceImage.getImage();
        } catch (Exception e) {
            System.out.println("Red Piece: not found");
        }
        //Load Red Star Image
        ImageIcon redPieceStarImage;
        try {
            redPieceStarImage = new ImageIcon(this.getClass()
                .getResource("Red75Final.png"));
            redPieceStar = redPieceStarImage.getImage();
        } catch (Exception e) {
            System.out.println("Red Star Piece: not found");
        }
    }

    /**
    * Paints components to window 
    * @param g - Graphics (called by awt or repain())
    */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //if not animating center piece on mouse in X 
        //direction (m_pieceX - spacing/2)
        //Animate piece / follow mouse
        if (currentPieceColour == RED_PIECE) {
            if (animationThread.isAlive()) {
                g2d.drawImage(redPiece, m_pieceX, m_pieceY, null);
            } else {
                g2d.drawImage(redPiece, m_pieceX - X_SPACING / 2, m_pieceY, 
                    null);
            }
        } else if (currentPieceColour == YELLOW_PIECE) {
            if (animationThread.isAlive()) {
                g2d.drawImage(yellowPiece, m_pieceX, m_pieceY, null);
            } else {
                g2d.drawImage(yellowPiece, m_pieceX - X_SPACING / 2, m_pieceY, 
                    null);
            }
        }
        //Redraw placed pieces
        for (int j = 0; j < BOARD_HEIGHT; j++) {
            for (int i = 0; i < BOARD_WIDTH; i++) {
                if (m_pieces[i][(BOARD_HEIGHT - 1) - j].getPieceColour()
                        == YELLOW_PIECE) {
                    g2d.drawImage(yellowPiece, PIECE_X[i], PIECE_Y[j] 
                        + TOP_SPACING, null);

                } else if (m_pieces[i][(BOARD_HEIGHT - 1) - j]
                        .getPieceColour() == RED_PIECE) {
                    g2d.drawImage(redPiece, PIECE_X[i], PIECE_Y[j] 
                        + TOP_SPACING , null);

                } else if (m_pieces[i][(BOARD_HEIGHT - 1) - j]
                        .getPieceColour() == YELLOW_STAR) {
                    g2d.drawImage(yellowPieceStar, PIECE_X[i], PIECE_Y[j] 
                        + TOP_SPACING, null);


                } else if (m_pieces[i][(BOARD_HEIGHT - 1) - j]
                        .getPieceColour() == RED_STAR) {
                    g2d.drawImage(redPieceStar, PIECE_X[i], PIECE_Y[j] 
                        + TOP_SPACING, null);
                }
            }   
        }
        //Draw board
        g2d.drawImage(board, BOARD_SIDE_SPACING, TOP_SPACING, null);

    }

    /**
    * Animates a falling piece at (X,Y) on the board 
    * @param X - represents the column piece should be in
    * @param Y - represents the row piece should be in
    */
    public void dropPiece(final int X, final int Y, 
        final Piece.ConnectFourPieceColour col) {

        dropping = true;
        if (col == Piece.ConnectFourPieceColour.YELLOW) {
            currentPieceColour = YELLOW_PIECE;
        } else if (col == Piece.ConnectFourPieceColour.RED) {
            currentPieceColour = RED_PIECE;
        }

        animationThread = new Thread(new Runnable() {
            public void run() {
                int speed = 0;
                final double gravity = 1.2;
                int time = 0;
                final int TICK_TIME = 15;
                long tick = TICK_TIME;
                final int TERMINAL_VELOCITY = 50;
                m_pieceX = PIECE_X[X];
                while (m_pieceY < (PIECE_Y[Y] + TOP_SPACING)) {

                    time += TICK_TIME;
                    if (speed < TERMINAL_VELOCITY) {
                        speed += gravity;
                    }
                    m_pieceY += speed;
                    try {
                        Thread.sleep(tick);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        System.out.println("Sleep error");
                    }
                    repaint();
                }
                m_pieceY = MOUSE_PIECE_Y;
                dropping = false;
            }
        });
        animationThread.start();
    }

    /**
    * Updates the piece array
    * @param 2D array of pieces
    */
    public boolean updatePieces(ConnectFourPiece[][] c) {
        for (int j = 0; j < BOARD_HEIGHT; j++) {
            for (int i = 0; i < BOARD_WIDTH; i++) {
                m_pieces[i][j] = new ConnectFourPiece(c[i][j].getPieceColour());
            }
        }

        return true;
    }

    /**
    * Repain the panel
    */
    public void refreshDisplay() {
        repaint();
    }

    /**
    * Set the piece to be dropped
    * @param c colour of the piece
    */
    public boolean setCurrentPiece(ConnectFourPiece c) {
        if (c.getPieceColour() == Piece.ConnectFourPieceColour.YELLOW) {
            currentPieceColour = YELLOW_PIECE;
        } else if (c.getPieceColour() == Piece.ConnectFourPieceColour.RED) {
            currentPieceColour = RED_PIECE;
        }
        return true;
    }

    /**
    * get the current piece
    */
    public ConnectFourPiece getCurrentPiece() {
        return new ConnectFourPiece(currentPieceColour);
    }

    /** 
    * Set the x coordinate of current piece
    * @param x - x coordinate of the mouse relative to the window
    */
    public void setPieceX(int x) {
        if (!dropping) {
            m_pieceX = x;
            repaint();
        }
    }
}

