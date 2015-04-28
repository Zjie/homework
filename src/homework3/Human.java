/**
 * @file    -Human.java
 * @author  -I.C.Skinner & J. Bailey
 * @date    - ...
 * 
 * \brief 
 * 
 */
package homework3;
import java.io.*;
import javax.swing.*;

public class Human extends Player{
    public boolean move(int x, int y, ProgramController PC){
        Boolean validMove = false;
        Player[] player = new Player[NUMBER_OF_PLAYERS];
		player[PLAYER_ONE] = PC.getGame().getPlayer(PLAYER_ONE);
		player[PLAYER_TWO] = PC.getGame().getPlayer(PLAYER_TWO);

        validMove = (PC.getGame()).checkValid(x, y, this);
        if (validMove == false) {
            return validMove;
        }
        
        PC.getGame().setPiece(x, y, this);
        try {
            PC.update(((PC.getGame().getBoard()).getBoard()),
						player[PLAYER_ONE].getColour(), 
						player[PLAYER_TWO].getColour());
        } catch (IOException ex) {
			
        }
        
        PC.getGame().checkWin();
        
        
        return validMove;
    }

	private final int PLAYER_ONE = 0;
	private final int PLAYER_TWO = 1;
	private final int NUMBER_OF_PLAYERS = 2;
}