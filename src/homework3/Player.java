/**
 * @file    -Player.java
 * @author  -A. Alakeel
 * @date    - ...
 * 
 * \brief Store the name of the player and their colour.
 * 
 */
package homework3;
import java.awt.event.*;

abstract public class Player {
	
	private String m_Name;
	private String m_Colour;
	private boolean m_CurrentTurn;
	
	public void Player(){
        }
        
    public String getName()
	{
		return this.m_Name;
	}
	
	public String getColour()
	{
		return this.m_Colour;
	}
	
	public boolean isTurn()
	{
		return this.m_CurrentTurn;
	}
	
	public void setName(String name)
	{
		this.m_Name = name;
	}
	
	public void setColour(String colour)
	{
		this.m_Colour = colour;
	}
	
	public boolean move(int x, int y, ProgramController PC)
	{
            return false;
	}
}
