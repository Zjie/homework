package game;
/**
* @file Game.java
* @author Kris Page
* @date 31 Jan 2014
* @see 'JAVA for everyone' - Cay Horstmann, Second edition, page 434 for 
*      abstract class and abstract methods design.
* 
* @brief This is an abstract class that is the logic behind the game and 
*        controls the running of the game.
*
* This class controls the order of the game, interactions between the player 
* classes and the board classes. It takes player types and piece placements and 
* uses the board to set and get the pieces and their colours, it also interacts 
* with the board to check for any wining seniors and if the board is full. It 
* gives its information to the game GUI for the display of the game to be 
* updated.
*/

public abstract class Game<C> {
    
    protected static int m_PlayerTurn = 0;

    /**< This is the board of the current game */
    protected Board m_Board;

    /**< This is an array of the players in the game of player objects */
    protected static Player[] m_player;
    
    /**< This actual board that is visible to the players */
    protected GameGUI m_GameGUI;
    
    /**< String constant used for defining a human player type */
    public static final String HUMAN = "human";

    /**< Constant for the first player, used for checking who's turn it is*/
    public static final int PLAYER_ONE = 0;

   /**< Constant for the second player, used for checking who's turn it is*/
    public static final int PLAYER_TWO = 1;

    /**
     * This method returns the name of one of the players, either player one or 
     * two depending on the input parameter.
     *
     * @param x -This variable passed in stores which player, one or two,
     *           the name needed to be returned.
     * @return String -The name of the player, either player one or two.
     */
     
     public void resetTurn() {
        m_PlayerTurn = PLAYER_ONE;
    }
    
    public void incrementTurn() {
        m_PlayerTurn++;
    }
    
    public static int getPlayerTurn() {
        return m_PlayerTurn;
    }
    
     public static String getPlayerName(int x) {
         if (x < PLAYER_ONE) {
             x = PLAYER_ONE;
         }
         if (x > PLAYER_TWO) {
             x = PLAYER_TWO;
         }
         return m_player[x].getName();
     }
     
     public static Player getPlayer(int x){
    	 return m_player[x];
     }
     
     /**
      * This method returns the name of both players playing the game.
      *
      * @return playersNames -The array which contains the name of both players.
      */
      public String[] getPlayerNames() {
          String[] playersNames = {m_player[PLAYER_ONE].getName(), m_player[PLAYER_TWO].getName()};
          return playersNames;
      }

    /**
    * Constructor for Game this is used to create a new game
    * @param playerOneName -This variable passed in stores the player one's name
    * @param playerOneType -This variable passed in stores the type of the 
    *                       player either a human or computer player
    * @param playerOneColour -This variable passed in stores the player one's 
    *                         piece colour
    * @param playerTwoName -This variable passed in stores the player two's name
    * @param playerTwoType -This variable passed in stores the type of the 
    *                       player either a human or computer player
    * @param playerTwoColour -This variable passed in stores the player two's 
    *                         piece colour
    */
    public Game(Player playerOne, Player playerTwo) {
        // Jon's fix idea. Pass in 2 player objects, instead of 6 parameters.
        // then set humanPlayer[0] and HumanPlayer[1] to those.
        // Only 2 parameters needed.
        // Player Objects can be made in MenuGUI, where there player decides what colour
        // They want and things liek that. - Jon
        m_player = new Player[2];
        
        m_player[PLAYER_ONE] = playerOne;
        m_player[PLAYER_TWO] = playerTwo;
        /* Old code
        if (playerOneType.equalsIgnoreCase(HUMAN)) {
        	m_player[0] = new HumanPlayer(playerOneName, playerOneColour);
        }

        if (playerTwoType.equalsIgnoreCase(HUMAN)) {
        	m_player[1] = new HumanPlayer(playerTwoName, playerTwoColour);
        }
        */
    }

    /**
    * This method handles the order and interactions of the game.
    *
    * @return boolean -If the method is successfully executed.
    */
    abstract boolean gameLoop();

    
    /**
    *  This method handles the piece being placed onto the board.
    *
    * @return boolean -If the method is successfully executed.
    */  
    abstract boolean move(int x, int y, C colour);
}
