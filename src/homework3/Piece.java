/**
 * @file    -Piece.java
 * @author  -A. Alakeel
 * @date    - ...
 * 
 * \brief Stores which player placed the piece(s).
 * 
 */
package homework3;
public class Piece {
	
	public String getColour()
	{
			return m_Colour;
	}
	
	public Piece(String colour)
	{
		changeColour(colour);
	}
	
	public void changeColour(String colour)
	{
		m_Colour = colour;
	}
	
	private String m_Colour;
}
