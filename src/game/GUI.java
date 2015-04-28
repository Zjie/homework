package game;
/**
* @file GUI.java
* @author Daniel Kelleher
* @date 30 Jan 2014
* @see 'JAVA for everyone' - Cay Horstmann, Second edition, page 434 for 
*	   abstract class and abstract methods design.
*
* @brief This creates the abstract class GUI 
* 		 which will be extended by the other GUI classes.
* 
* This class is an abstract class to be inherited by subclasses of the GUI,
* it has the methods of setting title, height and width of the window, 
* it takes the three variables for these methods as input in the constructor.
*/

import javax.swing.JFrame; 

public abstract class GUI extends JFrame {

	/**< variable storing the title of the display window */ 
	protected String m_Title; // These are protected, so child classes can access them. - Jon
	
	/**< variable storing the width of the display window */ 
	protected int m_Width; // These are protected, so child classes can access them. - Jon
	
	/**< variable storing the height of the display window */ 
	protected int m_Height; // These are protected, so child classes can access them. - Jon

	/**< constant for checkig the maximum width of the display window */ 
	private final int MAX_WIDTH = 800;

	/**< constant for checkig the maximum width of the display window */ 
	private final int MIN_WIDTH = 450; //old 365

	/**<constant for checkig the maximum height of the display window */ 
	private final int MAX_HEIGHT = 600;

	/**< constant for checkig the minimum height of the display window */ 
	private final int MIN_HEIGHT = 400; //old 295

	/** 
	* This is the constructor for the OthelloGameGui class.
	*
	* @param title -the variable storing the title of the window.
	* @param width -the variable storing width of the display window.
	* @param height -the variable storing height of the display window.
	*/
	public GUI(String title, int width, int height) {
		m_Title = title;
		setWidth(width);
		setHeight(height);
	}
	
	/** 
	* This method sets the width of the display window.
	*
	* @param width -the variable storing width of the display window.
	* @return setWidth -a boolean variable returned as true if the width  
	*					 was set or false if it was not.
	*/
	public boolean setWidth(int width) {
		boolean setWidth = false;
		if (width>=MIN_WIDTH && width<=MAX_WIDTH) {
			m_Width = width;
			setWidth = true;
		} else {
			if (width<MIN_WIDTH) {
				m_Width = MIN_WIDTH;
				System.out.println("The width of the Start Screen"+ 
	 			" is out of the set below the bound.");
	 			System.out.println("The width has been set to the minimum: " 
	 			+ MIN_WIDTH);
			} else if (width>MAX_WIDTH) {
				m_Width = MAX_WIDTH;
				System.out.println("The width of the Start Screen"+ 
	 			" is out of the set above the bound.");
	 			System.out.println("The width has been set to the maximum: " 
	 			+ MIN_WIDTH);
			}		
		}
		return setWidth;
	}
	
	/** 
	* This method sets the height of the display window.
	*
	* @param height -the variable storing height of the display window.
	* @return setHeight -a boolean variable returned as true if the height  
	*					 was set or false if it was not.
	*/
	public boolean setHeight(int height) {
		boolean setHeight = false;
		if (height>=MIN_HEIGHT && height<=MAX_HEIGHT) {
			m_Height = height;
			setHeight = true;
		} else {	
			if (height<MIN_HEIGHT) {
				m_Height = MIN_HEIGHT;
				System.out.println("The height of the Start Screen"+ 
	 			" is out of the set below the bound.");
	 			System.out.println("The height has been set to the minimum: " 
		 			+ MIN_HEIGHT);
			} else if (height>MAX_HEIGHT) {
				m_Height = MAX_HEIGHT;
				System.out.println("The height of the Start Screen"+ 
	 			" is out of the set above the bound.");
	 			System.out.println("The height has been set to the maximum: " 
	 			+ MAX_HEIGHT);
			}	
		}
		return setHeight;
	}
}