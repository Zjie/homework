package game;
/**
* @file GroupPWGapplication.java
* @author Daniel Kelleher
* @date 24 March 2014
* @see 'JAVA for everyone' - Cay Horstmann, Second edition, page 465 for 
*	   	setting up a Graphical User Interface.
*
* @brief This creates the start up screen for the game system.
*
* 
* This class is the start up screen for the game system, it has an m_Image of 
* both games and has a progress bar which runs to completion and then brings up 
* the Menu window.
*/

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class GroupPWGapplication extends GUI{
	private static final long serialVersionUID = 1L;

	/**< This is the m_Frame to which the other components are added */
	private JFrame m_Frame;

	/**< This is for storing the m_Image to be displayed */
	private ImageIcon m_Image;

	/**< This is panel for the m_Image to be added onto */
	private JPanel m_Panel1;

	/**< This is panel for the progress bar to be added onto */
	private JPanel m_Panel2;

	/**< This is JLabel to which the ImageIcon is to be added onto, 
		so the m_Image can be displayed */
	private JLabel m_Icon;

	/**< This is progress bar to display the load completion */
	private JProgressBar m_ProgressBar;

	/**< This is constant used to store the progress bar's maximum  */
	public final int MAX_RUN_LENGTH = 100;

	/**< This is constant used to store the progress bar's minimum  */
	public final int MIN_RUN_LENGTH = 0;

	/**< This is constant used to store the Menu m_Frame width */
	public final int MENU_WIDTH = 370;		

	/**< This is constant used to store the Menu m_Frame width */
	public final int MENU_HIEGHT = 300;

	/**< This is constant used to store the sleep time of the thread so to 
		increment the progress bar's completion by a constant rate of time*/
	public final int SLEEP_TIME = 10;

	/**< constant for checking the maximum width of the display window */ 
	public final int MAX_WIDTH = 550;

	/**< constant for checking the minimum width of the display window */ 
	public final int MIN_WIDTH = 450;

	/**<constant for checking the maximum height of the display window */ 
	public final int MAX_HEIGHT = 550;

	/**< constant for checking the minimum height of the display window */ 
	public final int MIN_HEIGHT = 450;

	/**< constant for the frame separation between components in the x axis */
	private int SEPARATION_X_AXIS = 1;

	/**< constant for the frame separation between components in the x axis */
	private int SEPARATION_Y_AXIS = 1;

	/**< constant for the frame position on the x axis */
	private int COLUMN_ONE = 0;
	
	/**< constant for the frame position on the y axis */
	private int  ROW_ONE = 0;
	
	/**< constant for the frame position on the y axis */
	private int  ROW_TWO = 1;
	
	/**< constant for progress bar's minimum and initial value */
	private int PROGRESS_BAR_START = 0;
	
	/**< constant for progress bar's maximum value */
	private int PROGRESS_BAR_END = 100;
	
	/**< constant for setting the system to terminate */
	private int SYSTEM_EXIT = 0;

	/** 
	* This is the constructor for the OthelloGameGui class, it creates a m_Frame 
	* window with a m_Image and a progress bar.
	*
	* @param title -the variable storing the title of the window.
	* @param width -the variable storing width of the display window.
	* @param height -the variable storing height of the display window.
	*/
	public GroupPWGapplication(String title, int width, int height) {
		super(title,width,height);

        m_Image = new ImageIcon(this.getClass()
                .getResource("Window.jpeg"));
		m_Frame = new JFrame(m_Title);
		m_Frame.setIconImage(m_Image.getImage());
		m_Frame.setLayout(new GridBagLayout());

		GridBagConstraints layout = new GridBagConstraints();
		layout.fill = GridBagConstraints.HORIZONTAL;
		layout.weightx = SEPARATION_X_AXIS;
		layout.weighty = SEPARATION_Y_AXIS;

		m_Panel1 = new JPanel(new BorderLayout());
		layout.gridx = COLUMN_ONE;
		layout.gridy = ROW_ONE;
		m_Frame.add(m_Panel1,layout);
		
		m_Panel2 = new JPanel(new BorderLayout());
		layout.gridx = COLUMN_ONE;
		layout.gridy = ROW_TWO;
		m_Frame.add(m_Panel2,layout);

		m_Icon = new JLabel(m_Image);
		m_Panel1.add(m_Icon, BorderLayout.CENTER);

		m_ProgressBar = new JProgressBar(PROGRESS_BAR_START, PROGRESS_BAR_END);
		m_ProgressBar.setValue(PROGRESS_BAR_START);
		m_ProgressBar.setStringPainted(true);
		m_Panel2.add(m_ProgressBar, BorderLayout.CENTER);

		m_Frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to exit the program?", 
                "Exit Program Message Box",
                JOptionPane.YES_NO_OPTION);
                
                if (confirmed == JOptionPane.YES_OPTION) {
                   	System.exit(SYSTEM_EXIT);
                } 
            }
        });

		m_Frame.pack();
        m_Frame.setLocationRelativeTo(null);
		m_Frame.setSize(m_Width,m_Height);
        m_Frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        m_Frame.setVisible(true);	

		for (int i = MIN_RUN_LENGTH; i <= MAX_RUN_LENGTH; i++) {
	      	final int PERCENT = i;
	      	try {
	        	SwingUtilities.invokeLater(new Runnable() {
	          		public void run() {
	          			m_ProgressBar.setValue(PERCENT);
	          			if (PERCENT == MAX_RUN_LENGTH) {
							new MenuGUI("Main Menu",MENU_WIDTH,MENU_HIEGHT);
							m_Frame.dispose();
	          			}
	          		}
	        	});
	        	java.lang.Thread.sleep(SLEEP_TIME);
	      	} catch (InterruptedException e) {
	        	System.out.println("An Interrupted Exception" + 
	        		"error has occurred");
	      	}
      	}
	}

	/** 
	* This is the main method of the GroupPWGapplication class, it is used to increment 
	* the progress bar's completion rate and then display the Menu window and 
	* close the GroupPWGapplication window.
	*
	*/
	public static void main(String[] args) {
		/**< This is constant used to store the Start Screen m_Frame width */
		final int START_SCREEN_WIDTH = 500;
			
		/**< This is constant used to store the Start Screen m_Frame height */
		final int START_SCREEN_HEIGHT = 500;

		new GroupPWGapplication("Start Screen",START_SCREEN_WIDTH,START_SCREEN_HEIGHT);
	}

}