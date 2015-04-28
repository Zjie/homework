package game;
/**
* @file ConnectFourGameGUI.java
* @author Kristoffer Page
* @date 31 Jan 2014
* @see JavaDoc for jFrames -  
*      http://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html
* 
* @brief This class extends GameGUI and is the graphical interface for the 
*        connect four game. It handles input from the user, as well as some 
*        logic of the game.
*
* This class extends GameGUI and is the graphical interface for the connect four
* game. It handles input from the user, as well as some game logic.
* It is responsible for for displaying the board, pieces and score.
* It uses ConnectFourPanel for drawing the board image and pieces.
*/

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConnectFourGameGUI extends GameGUI {
    /**< This is the height (amount of slots/places vertically) in the board */
    public static final int BOARD_HEIGHT = 7;

    /**< This is the width (amount of slots/places horizontally) in the board */
    public static final int BOARD_WIDTH = 10;

    /**< This is the panel that of draws the graphics to the screen */
    private ConnectFourPanel panel;

    /**< Distance between the edge of the window to the board */
    final static int WINDOW_BORDER_WIDTH = ConnectFourPanel.BOARD_SIDE_SPACING;

    /**< Width of the Window. Width depends on the the board image's width */
    final static int WINDOW_WIDTH = ConnectFourPanel.BOARD_IMAGE_WIDTH 
        + WINDOW_BORDER_WIDTH * 2;

    /**< Height of the Window. Height depends on the the board image's height */
    private static final int WINDOW_HEIGHT = ConnectFourPanel.BOARD_IMAGE_HEIGHT
        + ConnectFourPanel.TOP_SPACING + 50;

    /**< Title of the JFrame */
    private static final String WINDOW_TITLE = "Connect Four";

    /**< Stores the mouse's X coordinate inside the window */
    public int mouseX;

    /**< Stores an instance of ConnectFourGame */
    private ConnectFourGame game;
    
    /**
    * Constructor for ConnectFourGameGUI
    * @param playerOneName - String representation of player one's name
    * @param playerTwoName - String representation of player two's name
    */
    public ConnectFourGameGUI(String playerOneName, 
        String playerTwoName) {
        //Call super class constructor
        super(WINDOW_TITLE, WINDOW_WIDTH, WINDOW_HEIGHT);

        game = new ConnectFourGame(new HumanPlayer(playerOneName,
                                                   Piece.
                                                       ConnectFourPieceColour.
                                                           YELLOW),
                               new ConnectFourEasyComputerPlayer(playerTwoName, ///////////////// Player Declaration, this is where the players are made
                                                   Piece.
                                                       ConnectFourPieceColour.
                                                           RED));    
            
        
        panel = new ConnectFourPanel(game.getPieces());
        panel.updatePieces(game.getPieces());
        panel.setCurrentPiece(new ConnectFourPiece(Piece.ConnectFourPieceColour.
            YELLOW));
        
        setTimerLabel();
        panel.add(getTimerLabel());
        
        
        //Add menu bar
        JMenu menu = new JMenu("Menu");
        add(menu);
        JMenuItem newGameButton = new JMenuItem("New Game");
        JMenuItem saveGameButton = new JMenuItem("Save Game");
        menu.add(newGameButton);
        menu.add(saveGameButton);
        menu.add(new JMenuItem("Pause game"));
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        setJMenuBar(menuBar);

        //Add mouse listener for mouse movement
        addMouseMotionListener(new MotionListener());

        //Add mouse click listener
        addMouseListener(new ClickListener());

        //Add listener for new game button in menu
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                    System.out.println("new game clicked");
                    displayPlayAgain("Start new game?");
            }
        });

        saveGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                    System.out.println("save game clicked");
                    new Connect4GameSaver("C:\\Users\\Ieuan\\Desktop\\newSave.xml");
            }
        });
        
        //Add window listener (listens for window close event)
        addWindowListener(new WindowAdapter() {         
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to exit the program?", 
                "Exit Program Message Box",
                JOptionPane.YES_NO_OPTION);
                
                if (confirmed == JOptionPane.YES_OPTION) {
                    //Close this window
                    dispose();
                } 
            }
        });

        startTimer();
        
        //Initialize and display the the GUI
        add(panel);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setIconImage(new ImageIcon(this.getClass()
                .getResource("ConnectFour.jpeg")).getImage());
        setTitle("Connect Four   Player One: \"" 
            + game.getPlayerName(Game.PLAYER_ONE) + "\"    Player Two: \"" 
            + game.getPlayerName(Game.PLAYER_TWO) +"\"");
    }
    
    /**< private class for handling mouse clicks */
    private class ClickListener implements MouseListener {

        public void mouseReleased(MouseEvent e) {
            //Game Logic
            if ((!game.gameWon() && !game.boardIsFull()) 
                    && !panel.animationThread.isAlive()) {
                int x = Math.round(mouseX / ConnectFourPanel.Y_SPACING);
                        

                if (x > BOARD_WIDTH) {
                    x = BOARD_WIDTH - 1;
                }

                if (game.move(x, BOARD_HEIGHT, panel.getCurrentPiece()
                        .getPieceColour())) {
                    //y is the height the piece will be dropped to
                    int y = game.getLowestEmptySlot(x) + 1;

                    if (y >= BOARD_HEIGHT + 1) {
                        y = BOARD_HEIGHT + 1;
                        System.out.println(y);
                    }

                    panel.dropPiece(x, BOARD_HEIGHT - y, panel.getCurrentPiece()
                        .getPieceColour());

                    new Thread(new Runnable() {
                        public void run() {

                            while(panel.animationThread.isAlive()){
                            }

                            panel.updatePieces(game.getPieces()); 
                            if (!game.boardIsFull()) {
                                if (panel.getCurrentPiece().getPieceColour()
                                    == ConnectFourPanel.YELLOW_PIECE) { 
                                    panel.setCurrentPiece(
                                        new ConnectFourPiece(
                                        ConnectFourPanel.RED_PIECE));
                                } else {
                                    panel.setCurrentPiece(
                                        new ConnectFourPiece(
                                        ConnectFourPanel.YELLOW_PIECE));
                                }
                            }
                            panel.refreshDisplay();
                        }
                    }).start();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, 
                        "Invalid move");
                }
            }
            if (game.gameWon()) {
            	getTimer().stop();
                if (panel.getCurrentPiece().getPieceColour() 
                        == ConnectFourPanel.YELLOW_PIECE) {
                    //RED WON
                    displayWinner(game.getPlayerName(Game.PLAYER_ONE));
                } else {
                    //YELLOW WON
                    displayWinner(game.getPlayerName(Game.PLAYER_TWO));
                }
                if (!displayPlayAgain("Play again?")) {
                    dispose();
                }
            }
            if (game.boardIsFull() && !game.gameWon()) {
                displayDraw();
                getTimer().stop();
            }
        }

        //Currently unused, but must be declared
        public void mouseClicked(MouseEvent e) {
        }

        //Currently unused, but must be declared
        public void mousePressed(MouseEvent e) {
            
        }

        //Currently unused, but must be declared
        public void mouseExited(MouseEvent e) {
        }

        //Currently unused, but must be declared
        public void mouseEntered(MouseEvent e) {
        }
    }
    /**< private class for detecting when mouse is moved */
    private class MotionListener implements MouseMotionListener {

        //Unused, but must be implemented
        public void mouseDragged(MouseEvent e) {
        }

        /**< Handle mouse moved event */
        public void mouseMoved(MouseEvent e) {
            //Dont allow the mouse to move when animating a piece dropping
            if (!panel.animationThread.isAlive()) {
                mouseX = e.getX();
                panel.setPieceX(mouseX);
            }
        }
    }

    /**
    * Message dialog tells players the game is a draw
    * @param none
    */
    private boolean displayDraw() { 
        JOptionPane.showMessageDialog(null, "It's a draw!");
        displayPlayAgain("Play again?");
        return true;
    }

    /**
    * Message dialog asks players if they want to play again
    * @param message - A string with the message to display
    */
    private boolean displayPlayAgain(String message) {
        int reply = JOptionPane.showConfirmDialog(null, message , message, 
            JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
        	resetTimer();
            //game = new ConnectFourGame(game.getPlayerName(Game.PLAYER_ONE), 
                //Game.HUMAN, Piece.ConnectFourPieceColour.YELLOW, 
                //game.getPlayerName(Game.PLAYER_TWO), Game.HUMAN, 
                //Piece.ConnectFourPieceColour.RED);

            game = new ConnectFourGame(
                           new HumanPlayer(game.getPlayerName(Game.PLAYER_ONE),
                                           Piece.
                                               ConnectFourPieceColour.
                                                   YELLOW),
                           new HumanPlayer(game.getPlayerName(Game.PLAYER_TWO),
                                           Piece.
                                               ConnectFourPieceColour.
                                                   RED));  

            panel.updatePieces(game.getPieces());
            panel.setCurrentPiece(new ConnectFourPiece(
            						Piece.ConnectFourPieceColour.YELLOW));
            panel.refreshDisplay();
        }
        return reply == JOptionPane.YES_OPTION;
    }
}