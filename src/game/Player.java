package game;
/**
 * @file    -Player.java
 * @author  -Thomas Vass
 * @date    -03/02/14
 * @see     -Needs to be Changed!!!::Deitel and Deitel, Fig. 13.5, page 502
 *           from Java for Programmers, Prentice Hall
 *
 *  @brief This class is responsible for defining a player
 *
 *  The Player superclass defines a player. Each player will have a name,
 *  score, and colour. There is no setName or setColour method as once a player is 
 *  defined you do not need to change their name or colour. The player will need to 
 *  collaborate with the game through the move method where it will take input 
 * (from either the user or a computer-generated move); the move method is abstract,
 * as each type of player will have a different algorithm for deciding a move.
 *
 */
 


abstract class Player <C> {
    private String m_Name;
    private C m_connectFourPlayerColour;
    private int m_Score = 0;
    private String m_PlayerType;
    
    public String getName () {
        return m_Name;
    }
    
    public int getScore() {
        return m_Score;
    }

    public C getColour () {
            return m_connectFourPlayerColour;
    }
    public void setScore(int points) {
        m_Score+=points;
    }

    public Player (String newName, C newPlayerColour, String playerType){
        m_Name = newName;
        m_connectFourPlayerColour = newPlayerColour;
        m_PlayerType = playerType;
    }
    
    public String getPlayerType() {
        return m_PlayerType;
    }
    	
     boolean move (int x, int y) {
		return false;
	}
}
