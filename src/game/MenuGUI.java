package game;
/**
* @file MenuGUI.java
* @author Callum Dicker
* @date 07 Feb 2014
* @see 'JAVA for everyone' - Cay Horstmann, Second edition, page 465 for 
*	   	setting up a Graphical User Interface.
*
* @brief This class will display the menu for the game allowing a user to select
*		 a game type
*		
* This class allows the user to choose a game and input their player
* names after they have chosen either Connect four or Othello. This MenuGUI is 
* called from the StartScreen to start up the game system.
*
*/

import game.othello.Utility;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import othello.OthelloFrame;

public class MenuGUI extends GUI {
	
	private static final long serialVersionUID = -7925484816479044571L;
	/**
	* This creates the MenuGUI frame which holds the buttons
	* for Othello and Connect Four
    *
	* @param title -This is what the title of the frame will be.
	* @param width -This is what the width of the frame will be.
	* @param height -This is what the height of the frame will be.
	*/
    public MenuGUI(String title, int width, int height) {
		
        super(title,width,height);

        m_MainMenu = new JFrame(m_Title);
        m_Image = new ImageIcon(this.getClass()
                .getResource("Window.jpeg"));
        m_MainMenu.setIconImage(m_Image.getImage());
        m_MainMenu.setSize(m_Width, m_Height);
 		m_MainMenu.setLayout(new GridBagLayout());

        m_Select = new JLabel("Select a Game");
        m_Select.setFont(new Font("SansSerif", Font.PLAIN, HEADING_FONTSIZE));

        m_OthelloLbl = new JLabel("Othello", JLabel.CENTER);
        m_OthelloLbl.setFont(new Font("SansSerif", Font.PLAIN, 
            SUBHEADING_FONTSIZE));
		
        m_ConnectFourLbl = new JLabel("Connect Four", JLabel.CENTER);
        m_ConnectFourLbl.setFont(new Font("SansSerif", Font.PLAIN, 
            SUBHEADING_FONTSIZE));
     
		
        ButtonIconSetUp();	//Sets up the images for the buttons
		ButtonPanelSetUp();	//Adds the buttons to the frame

		//Adding the panels to the frame		
		m_SelectPanel = new JPanel(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();
		d.gridx = COLUMN_ONE;							
		d.gridy = ROW_ONE;
		m_SelectPanel.add(m_Select, d);   
		d.gridx = COLUMN_ONE;
		d.gridy = ROW_ONE;
		m_MainMenu.add(m_SelectPanel,d);
		d.gridx = COLUMN_ONE;
		d.gridy = ROW_TWO;
		m_MainMenu.add(m_ButtonPanel,d);
        
        InputScreen();
		d.gridx = COLUMN_ONE;
		d.gridy = ROW_ONE;
		m_MainMenu.add(m_InputScreen,d);
        m_InputScreen.setVisible(false);

		//Sets up the necessaries Handlers
		GUIEventHandler handler = new GUIEventHandler();

		m_ConnectFourButton.addActionListener(handler);
		m_OthelloButton.addActionListener(handler);
		m_MainMenu.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
	            int confirmed = JOptionPane.showConfirmDialog(null, 
	            "Are you sure you want to exit the program?", 
	            "Exit Program Message Box",
	            JOptionPane.YES_NO_OPTION);
	            //if yes is pressed close frame else keep open
            	if (confirmed == JOptionPane.YES_OPTION) {
            		System.exit(0);
            	} 
            }
        });
        
        m_MainMenu.setLocationRelativeTo(null);
	    m_MainMenu.setVisible(true);
        m_MainMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	/**
	* This method sets the game type of the game.
	* @param gameType - brings in the type of game
	* @return true -if the method was successfully executed.
	*
	*/
	private boolean setGameType(int gameType) {

		boolean conGame;
		if(gameType == OTHELLO || gameType == CONNECT){
			m_Game = gameType;
			conGame = true;
		} else {
			conGame =  false;
		}

		return conGame;
	}
	
	/**
	* This method returns the current game type of the game.
	* @param -nil
	* @return m_Game -This will return 1 or 2, 1 being Connect 4
	*				  or 2 being Othello.
	*/
	private int getGameType() {
		return m_Game;
	}
	
	/**
	* This method will will set the name for player one.
	*
	* @param playerOneName -This variable brings in the First players name.
    * @return true -if the method was successfully executed.
	*/
	private boolean setPlayerOneName(String playerOneName) {
		m_PlayerOneName = playerOneName;
		return true;
	}
	
    /**
	* This method will will set the name for player two.
	*
	* @param playerTwoName -This variable brings in the Second players name.
    * @return true -If the method was successfully executed.
	*/
	private boolean setPlayerTwoName(String playerTwoName) {
	
		m_PlayerTwoName = playerTwoName;
		return true;

	}
	
 	/**
	* This method will return Player one name.
	* 
	* @param nil
	* @return m_PlayerOneName -This variable is the first players name.
	*/   
	private String getPlayerOneName() {
	
		return m_PlayerOneName;

	}
    
 	/**
	* This method will return Players Two name.
	* 
	* @param nil
	* @return m_PlayerTwoName -This variable is the second players name.
	*/  
	private String getPlayerTwoName() {
	
		return m_PlayerTwoName;

	}

	/**
	* This method initializes the buttons and sets the icon of each button to be 
	* the relevant image.
	* 
	* @param nil
	* @return true -This will return true if the method is successfully executed.
	*/
	private boolean ButtonIconSetUp() {

        m_ConnectFourButton = new JButton();	//Button to launch Connect Four
        m_OthelloButton = new JButton();        //Button to launch Othello
		
        try {
        //Sets up the images for the buttons
            Image imgOthello = 
                ImageIO.read(getClass().getResource("Othello.jpeg"));
            Image imgConnectFour = 
	        ImageIO.read(getClass().getResource("ConnectFour.jpeg"));
	        m_ConnectFourButton.setIcon(new ImageIcon(imgConnectFour));
		m_OthelloButton.setIcon(new ImageIcon(imgOthello));
		} catch (IOException ex) {
		    //Prints in case of an error
	        ex.printStackTrace();
		}
		return true;
	}
	
    /**
    * This method sets the position of the connect four and othello buttons 
    * and adds them to the frame to be displayed.
    * 
    * @param nil
    * @return true -This returns true if the method is successfully executed.
    */
	private boolean ButtonPanelSetUp() {
	
		m_ButtonPanel = new JPanel(new GridBagLayout());
		//Othello Label
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = COLUMN_ONE;
		c.gridy = ROW_THREE;
		c.insets = new Insets(GAP_TWENTY,NO_GAP,NO_GAP,NO_GAP);
		m_ButtonPanel.add(m_OthelloLbl, c);
		//Othello Button
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = COLUMN_ONE;
		c.gridy = ROW_FOUR;
		c.insets = new Insets(GAP_TEN,NO_GAP,NO_GAP,NO_GAP); 
		m_ButtonPanel.add(m_OthelloButton, c);
		//Connect Four Label
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = COLUMN_TWO;						
		c.gridy = ROW_THREE;
		c.insets = new Insets(GAP_TWENTY,NO_GAP,NO_GAP,NO_GAP);  		
		m_ButtonPanel.add(m_ConnectFourLbl, c);
		//Connect Four Button
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = COLUMN_TWO;							
		c.gridy = ROW_FOUR;
		c.insets = new Insets(GAP_TEN,GAP_FIFTEEN,NO_GAP,NO_GAP);  			
		m_ButtonPanel.add(m_ConnectFourButton, c);
		
		return true;

	}
    private void ComboBoxes(){
    	if(getGameType()== CONNECT) {
    		System.out.println("chosen connect 4");
    		m_ComboPlayer1Colour.removeAllItems();
    		m_ComboPlayer2Colour.removeAllItems();
    		
    		m_ComboPlayer1Colour.addItem("Red");
    		m_ComboPlayer1Colour.addItem("Yellow");
    		
    		m_ComboPlayer2Colour.addItem("Red");
    		m_ComboPlayer2Colour.addItem("Yellow");
    		
    	} else if(getGameType()==OTHELLO) {
    		System.out.println("chosen othello");
    		
    		m_ComboPlayer1Colour.removeAllItems();
    		m_ComboPlayer2Colour.removeAllItems();
    		
    		m_ComboPlayer1Colour.addItem("Black");
    		m_ComboPlayer1Colour.addItem("White");
    		
    		m_ComboPlayer2Colour.addItem("Black");
    		m_ComboPlayer2Colour.addItem("White");
    	}
    	
    }
    /**
    * This method changes the current screen to allow user to input the two 
    * player names after they have selected a game to play.
    * 
    * @param nil
    * @return true -This method returns true if the method is successfully 
    *				executed.
	*/
	private boolean InputScreen() {
		
				
		m_ComboPlayer1Mode = new JComboBox(m_PlayerNames);
		m_ComboPlayer1Mode.setSelectedIndex(0);
		//playerModes.addActionListener(this);
		
		m_ComboPlayer2Mode = new JComboBox(m_PlayerNames);
		m_ComboPlayer2Mode.setSelectedIndex(0);
		
		
		m_ComboPlayer1Colour = new JComboBox();
		m_ComboPlayer2Colour = new JComboBox();
		
		m_ComboPlayer1Mode.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        if ("Red".equals(m_ComboPlayer1Mode.getSelectedItem())){
		        	m_ComboPlayer2Mode.setSelectedIndex(0);    
		        } else {
		        	m_ComboPlayer2Mode.setSelectedIndex(1);   
		        }
		    }
		});
		

        m_InputScreen = new JPanel(new GridBagLayout());
        
		m_InputLabel = new JLabel("Player Setup");
		m_InputLabel.setFont(new Font("SansSerif", 
			Font.PLAIN,SECONDHEAD_FONTSIZE));
		
		m_Player1Label = new JLabel("Player 1 details");
		m_Player1Label.setFont(new Font("SansSerif", 
			Font.ITALIC,SMALLHEADING_FONTSIZE));
		
		m_Player2Label = new JLabel("Player 2 details");
		m_Player2Label.setFont(new Font("SansSerif", 
			Font.ITALIC,SMALLHEADING_FONTSIZE));
		
		m_Player1TypeLabel = new JLabel("Player type:");
		
		m_Player2TypeLabel = new JLabel("Player type:");
		
		m_Player1ColourLabel = new JLabel("Player's colour:");
		
		m_Player2ColourLabel = new JLabel("Player's colour:");
		
		m_PlayerOK = new JButton("OK");
		m_PlayerBack = new JButton("Cancel");
		
		m_PlayInputOne = new JTextField("Player One",TEXTFIELD_COLUMNS);
		m_PlayInputOne.setFont(new Font("SansSerif", 
			Font.PLAIN, SUBHEADING_FONTSIZE));
		
		m_PlayInputTwo = new JTextField("Player Two",TEXTFIELD_COLUMNS);
		m_PlayInputTwo.setFont(new Font("SansSerif", Font.PLAIN, 
			SUBHEADING_FONTSIZE));
		
		GridBagConstraints MainPanel = new GridBagConstraints();
		GUIEventHandler handler = new GUIEventHandler();
		InputEventHandler check = new InputEventHandler();
		
		JPanel inputPanel = new JPanel(new GridBagLayout());
		GridBagConstraints input = new GridBagConstraints();
		
		input.fill = GridBagConstraints.HORIZONTAL;
		input.gridx = COLUMN_ONE;							
		input.gridy = ROW_ONE;
		input.insets = new Insets(20,20,0,60);
		inputPanel.add(m_InputLabel, input);
		
		
		input.fill = GridBagConstraints.HORIZONTAL;
		input.gridx = COLUMN_ONE;							
		input.gridy = ROW_TWO;
		input.insets = new Insets(20,20,0,60);  		
		inputPanel.add(m_Player1Label, input);
		
		input.fill = GridBagConstraints.HORIZONTAL;
		input.gridx = 1;							
		input.gridy = ROW_TWO;
		input.insets = new Insets(20,10,0,60);  		
		inputPanel.add(m_Player2Label, input);
		
		input.fill = GridBagConstraints.HORIZONTAL;
		input.gridx = 0;							
		input.gridy = 3;
		input.insets = new Insets(50,20,0,120); 
		inputPanel.add(m_ComboPlayer1Mode, input);
		
		input.fill = GridBagConstraints.HORIZONTAL;
		input.gridx = 0;							
		input.gridy = 3;
		input.insets = new Insets(0,20,0,120); 
		inputPanel.add(m_Player1TypeLabel, input);
		
		input.fill = GridBagConstraints.HORIZONTAL;
		input.gridx = 1;							
		input.gridy = 3;
		input.insets = new Insets(50,10,0,120); 
		inputPanel.add(m_ComboPlayer2Mode, input);
		
		input.fill = GridBagConstraints.HORIZONTAL;
		input.gridx = 1;							
		input.gridy = 3;
		input.insets = new Insets(0,10,0,120); 
		inputPanel.add(m_Player2TypeLabel, input);
		
		input.fill = GridBagConstraints.HORIZONTAL;
		input.gridx = COLUMN_ONE;							
		input.gridy = 2;
		input.insets = new Insets(20,20,0,100);  		
		inputPanel.add(m_PlayInputOne, input);
		
		

		input.fill = GridBagConstraints.HORIZONTAL;
		input.gridx = 1;							
		input.gridy = 2;
		input.insets = new Insets(20,10,NO_GAP,100);  			
		inputPanel.add(m_PlayInputTwo, input);
		
		input.fill = GridBagConstraints.HORIZONTAL;
		input.gridx = 0;							
		input.gridy = 4;
		input.insets = new Insets(50,20,0,120);  			
		inputPanel.add(m_ComboPlayer1Colour, input);
		
		input.fill = GridBagConstraints.HORIZONTAL;
		input.gridx = 0;							
		input.gridy = 4;
		input.insets = new Insets(0,20,0,120); 
		inputPanel.add(m_Player1ColourLabel, input);
		
		input.fill = GridBagConstraints.HORIZONTAL;
		input.gridx = 1;							
		input.gridy = 4;
		input.insets = new Insets(50,10,NO_GAP,120);  			
		inputPanel.add(m_ComboPlayer2Colour, input);
		
		input.fill = GridBagConstraints.HORIZONTAL;
		input.gridx = 1;							
		input.gridy = 4;
		input.insets = new Insets(0,10,0,120); 
		inputPanel.add(m_Player2ColourLabel, input);
		
		JPanel confirmPanel = new JPanel(new GridBagLayout());
		GridBagConstraints confirm = new GridBagConstraints();
		
		confirm.fill = GridBagConstraints.HORIZONTAL;
		confirm.gridx = COLUMN_ONE;
		confirm.gridy = ROW_ONE;
		confirm.insets = new Insets(15,0,0,20); 
		confirmPanel.add(m_PlayerBack, confirm);
		
		confirm.fill = GridBagConstraints.HORIZONTAL;
		confirm.gridx = COLUMN_TWO;	
		confirm.gridy = ROW_ONE;
		confirm.insets = new Insets(15,0,0,NO_GAP);  		
		confirmPanel.add(m_PlayerOK, confirm);

		MainPanel.insets = new Insets(GAP_TEN,NO_GAP,NO_GAP,NO_GAP); 
		MainPanel.gridx = COLUMN_ONE;
		MainPanel.gridy = ROW_ONE;
		m_InputScreen.add(inputPanel,MainPanel);
		MainPanel.gridx = COLUMN_ONE;
		MainPanel.gridy = ROW_TWO;
		m_InputScreen.add(confirmPanel,MainPanel);
		
		m_PlayerOK.addActionListener(handler);
		m_PlayerBack.addActionListener(handler);
		m_PlayInputOne.addKeyListener(check);
		m_PlayInputTwo.addKeyListener(check);

		return true;

	}

	/**
	* This method validates the name to make sure that when the player inputs
	* a name, the name they input is either not empty or has a maximum length 
	* of 20 characters.
	* 
	* @param nil
	* @return true -This returns true if the name suits the requirements or 
	*				false otherwise.
	*/
	private boolean PlayerInputValidation() {

		boolean validation = true;

		if (m_PlayInputOne.getText().equals("")){
			validation = false;
		} 

		if (m_PlayInputTwo.getText().equals("")){
			validation = false;
		}

		if (m_PlayInputOne.getText().length() > MAX_NAME) {
			validation = false;
		}

		if (m_PlayInputTwo.getText().length() > MAX_NAME) {
			validation = false;
		}
		
		if (m_PlayInputOne.getText().equals(m_PlayInputTwo.getText())) { //checks if both names are identical
			validation = false;
		}
		
		if (m_PlayInputOne.getText().trim().length()<1) { //checks if names contain white space
			validation = false;
		}
		
		if (m_PlayInputTwo.getText().trim().length()<1) {
			validation = false;
		}

		 return validation;
	}

	/**
	* This inner class validates the input of the player to make sure that the
	* text box is not empty or there are too many character
	*/
    private class InputEventHandler implements KeyListener {

    	public void keyReleased(KeyEvent e) {

        	if (PlayerInputValidation()) {
			    m_PlayerOK.setEnabled(true);
			} else {
			    m_PlayerOK.setEnabled(false);
			}

		}
    	// method not implemented
      	public void keyTyped(KeyEvent e) {}
      	// method not implemented
      	public void keyPressed(KeyEvent e) {}

    }
	/**
	* This inner class is used to handle the user's clicking events, for each 
	* button on the main menu window
	*
	*/
	private class GUIEventHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == m_ConnectFourButton) {

			    m_ConnectFourButton.setEnabled(false);
			    m_OthelloButton.setEnabled(false);
			    setGameType(CONNECT);
                m_ButtonPanel.setVisible(false);
                m_SelectPanel.setVisible(false);
                m_InputScreen.setVisible(true);
                m_MainMenu.setTitle("Connect Four Setup");
                ComboBoxes();
				
			} else if(event.getSource() == m_OthelloButton) {
				
			    m_ConnectFourButton.setEnabled(false);
			    m_OthelloButton.setEnabled(false);
			    setGameType(OTHELLO);
                m_ButtonPanel.setVisible(false);
                m_SelectPanel.setVisible(false);
                m_InputScreen.setVisible(true);
                m_MainMenu.setTitle("Othello Setup");
                ComboBoxes();

			} else if(event.getSource() == m_PlayerOK){
			
			    setPlayerOneName(m_PlayInputOne.getText());
			    setPlayerTwoName(m_PlayInputTwo.getText());
			    //m_MainMenu.dispose();	//Disposes the main menu
				if (getGameType() == CONNECT) {
					//m_MainMenu.dispose();
					ConnectFourGameGUI connectFour = 
					    new ConnectFourGameGUI(m_PlayInputOne.getText(),
	                    m_PlayInputTwo.getText());				
					
				} else if(getGameType() == OTHELLO){
					//m_MainMenu.dispose();
					OthelloGameGUI othello = new OthelloGameGUI("Othello",
						OTH_FRM_WIDTH,OTH_FRM_HEIGHT);
					String[] players = new String[NUM_PLAYERS];
					players[PLAYER_ONE] = m_PlayInputOne.getText();
					players[PLAYER_TWO] = m_PlayInputTwo.getText();
					generatePlayers(othello);
					othello.setPlayers(players);
					othello.creatingGui();
					
				}

				m_InputScreen.setVisible(false);
                m_SelectPanel.setVisible(true);
                m_ButtonPanel.setVisible(true);
		        m_ConnectFourButton.setEnabled(true);
			    m_OthelloButton.setEnabled(true);
			    m_MainMenu.setTitle("Main Menu");
			    m_PlayInputOne.setText("Player One");
				m_PlayInputTwo.setText("Player Two");
                
				 
			} else if (event.getSource() == m_PlayerBack) {
				
                m_InputScreen.setVisible(false);
                m_SelectPanel.setVisible(true);
                m_ButtonPanel.setVisible(true);
		        m_ConnectFourButton.setEnabled(true);
			    m_OthelloButton.setEnabled(true);
			    m_MainMenu.setTitle("Main Menu");
			    m_PlayInputOne.setText("Player One");
				m_PlayInputTwo.setText("Player Two");
			
			}
		}
		private void generatePlayers(OthelloGameGUI othello) {
			//generate players
			game.othello.Player player1 = null;
			OthelloPiece playerColour = null;
			ImageIcon piece = null;
			if (m_ComboPlayer1Colour.getSelectedIndex() == Utility.COLOUR_BLACK) {
				playerColour = new OthelloPiece(Piece.OthelloPieceColour.BLACK);
				piece = othello.blackPiece;
			} else if (m_ComboPlayer1Colour.getSelectedIndex() == Utility.COLOUR_WHITE) {
				playerColour = new OthelloPiece(Piece.OthelloPieceColour.WHITE);
				piece = othello.whitePiece;
			} else {
				System.out.println("colour type error");
				System.exit(1);
			}
			
			if (m_ComboPlayer1Mode.getSelectedIndex() == Utility.PLAYER_TYPE_HUMAN) {
				player1 = new game.othello.HumanPlayer(piece, playerColour, othello, 1);
			} else {
				player1 = new game.othello.ComputerHardPlayer(piece, playerColour, othello, 1);
			}
			
			
			
			game.othello.Player player2 = null;
			if (m_ComboPlayer2Colour.getSelectedIndex() == Utility.COLOUR_BLACK) {
				playerColour = new OthelloPiece(Piece.OthelloPieceColour.BLACK);
				piece = othello.blackPiece;
			} else if (m_ComboPlayer2Colour.getSelectedIndex() == Utility.COLOUR_WHITE) {
				playerColour = new OthelloPiece(Piece.OthelloPieceColour.WHITE);
				piece = othello.whitePiece;
			} else {
				System.out.println("colour type error");
				System.exit(1);
			}
			
			if (m_ComboPlayer2Mode.getSelectedIndex() == Utility.PLAYER_TYPE_HUMAN) {
				player2 = new game.othello.HumanPlayer(piece, playerColour, othello, 2);
			} else {
				player2 = new game.othello.ComputerHardPlayer(piece, playerColour, othello, 2);
			}
			
			othello.players = new game.othello.Player[]{player1, player2};
		}
	}
	
	/**
	* This method tests the methods of this class with valid inputs
	*
	* @param menu - brings in the menu object to be tested
	* @return true This method returns true if the method is successfully 
    *				executed.
	*/
	private static boolean validInputs(MenuGUI menu) {

		int VALID_WIDTH = 370;		
		int VALID_HEIGHT = 300;

		System.out.println("Valid Inputs\n");
		System.out.println("MenuGUI.setWidth() - Begin");
		System.out.println("Expected output: true");
		System.out.println("");
		System.out.println("Actual output: " + menu.setWidth(VALID_WIDTH));

		System.out.println("GameGUI.setWidth() - End"); 
		System.out.println("");
		System.out.println("MenuGUI.setHeight() - Begin");
		System.out.println("Expected output: true");
		System.out.println("");
		System.out.println("Actual output: " + menu.setHeight(VALID_HEIGHT));
		System.out.println("GameGUI.setHeight() - End"); 
		System.out.println("");

		System.out.println("MenuGUI.setPlayerOneName() - Begin");
		System.out.println("Expected output: true");
		System.out.println("");
		System.out.println("Actual output: " + menu.setPlayerOneName("Bob"));
		System.out.println("GameGUI.setPlayerOneName() - End"); 
		System.out.println("");

		System.out.println("MenuGUI.setPlayerTwoName() - Begin");
		System.out.println("Expected output: true");
		System.out.println("");
		System.out.println("Actual output: " + menu.setPlayerTwoName("Steve"));
		System.out.println("GameGUI.setPlayerTwoName() - End"); 
		System.out.println("");

		System.out.println("MenuGUI.getPlayerOneName() - Begin");
		System.out.println("Expected output: Bob");
		System.out.println("");
		System.out.println("Actual output: " + menu.getPlayerOneName());
		System.out.println("GameGUI.getPlayerOneName() - End"); 
		System.out.println("");

		System.out.println("MenuGUI.getPlayerTwoName() - Begin");
		System.out.println("Expected output: Steve");
		System.out.println("");
		System.out.println("Actual output: " + menu.getPlayerTwoName());
		System.out.println("GameGUI.getPlayerTwoName() - End"); 
		System.out.println("");

		System.out.println("MenuGUI.setGameType() - Begin");
		System.out.println("Expected output: true");
		System.out.println("");
		System.out.println("Actual output: " + menu.setGameType(OTHELLO));
		System.out.println("GameGUI.setGameType() - End"); 
		System.out.println("");

		System.out.println("MenuGUI.getGameType() - Begin");
		System.out.println("Expected output: 2");
		System.out.println("");
		System.out.println("Actual output: " + menu.getGameType());
		System.out.println("GameGUI.getGameType() - End"); 
		System.out.println("");

		return true;

	}

	/**
	* This method tests the methods of this class with invalid inputs
	*
	* @param menu - brings in the menu object to be tested
	* @return true This method returns true if the method is successfully 
    *				executed.
	*/
	private static boolean invalidInputs(MenuGUI menu) {
		
		int INVALID_WIDTH = 1000;
		int INVALID_HEIGHT = 1000;
		int INVALID_GAME_TPYE = 5;

		System.out.println("\nInvalid Inputs\n");

		System.out.println("MenuGUI.setWidth() - Begin");
		System.out.println("Expected output: false");
		System.out.println("");
		System.out.println("Actual output: " + menu.setWidth(INVALID_WIDTH));
		System.out.println("GameGUI.setWidth() - End"); 
		System.out.println("");
		System.out.println("MenuGUI.setHeight() - Begin");
		System.out.println("Expected output: false");
		System.out.println("");
		System.out.println("Actual output: " + menu.setHeight(INVALID_HEIGHT));
		System.out.println("GameGUI.setHeight() - End"); 
		System.out.println("");

		System.out.println("MenuGUI.setGameType() - Begin");
		System.out.println("Expected output: false");
		System.out.println("");
		System.out.println("Actual output: " + 
							menu.setGameType(INVALID_GAME_TPYE));
		System.out.println("GameGUI.setGameType() - End"); 
		System.out.println("");

		return true;

	}

	/**
	* This is the main method and it is used for unit testing purposes, it calls 
	* every method in this class for testing valid and invalid input parameters.
	*
	*/
	public static void main(String[] args) {

		int VALID_WIDTH = 370;		
		int VALID_HEIGHT = 300;
	
		MenuGUI menu = new MenuGUI("Main Menu", VALID_WIDTH, VALID_HEIGHT);
		
		validInputs(menu);
		invalidInputs(menu);

		System.out.println("\nGUI Tests:\n");
		System.out.println("MenuGUI.ButtonPanelSetUp() - Begin");
		System.out.println("Expected output: true");
		System.out.println("");
		System.out.println("Actual output: " + menu.ButtonPanelSetUp());
		System.out.println("MenuGUI.ButtonPanelSetUp() - End"); 
		System.out.println("");

		System.out.println("MenuGUI.InputScreen() - Begin");
		System.out.println("Expected output: true");
		System.out.println("");
		System.out.println("Actual output: " + menu.InputScreen());
		System.out.println("MenuGUI.InputScreen() - End"); 
		System.out.println("");

	}
	/**< Stores the first players name */
	private String m_PlayerOneName;

	/**< Stores the second players name*/		
	private String m_PlayerTwoName;

	/** < Stores the type of game that the user has selected*/
	private int m_Game;
	
	/**< This is the main frame*/
	private JFrame m_MainMenu;

	/**< This is for the image icon of the frame */
	private ImageIcon m_Image;
	
	/**< The text field where the user inputs the name for player one*/
	private JTextField m_PlayInputOne;

	/**< The text field where the user inputs the name for player two*/
	private JTextField m_PlayInputTwo;
	
	/**< This is the label for the Heading on the select window */
	private JLabel m_Select;
	
	/**< This is the label for the connect four game */
	private JLabel m_ConnectFourLbl;
	
	/**< This is the label for the othello game */
	private JLabel m_OthelloLbl;
	
	/**< This is the label for the Heading on the input window */
	private JLabel m_InputLabel;
	
	private JLabel m_Player1Label;
	
	private JLabel m_Player2Label;
	
	private JComboBox<String> m_ComboPlayer1Mode;
	
	private JComboBox<String> m_ComboPlayer2Mode;
	
	private JComboBox<String> m_ComboPlayer1Colour;
	
	private JComboBox<String> m_ComboPlayer2Colour;
	
	private JLabel m_Player1TypeLabel;
	
	private JLabel m_Player2TypeLabel;
	
	private JLabel m_Player1ColourLabel;
	
	private JLabel m_Player2ColourLabel;
	
	/**< This variable hold the string of the name for player one */
	private String m_PlayerOne;

	/**< This variable hold the string of the name for player two */
	private String m_PlayerTwo;
	
	/**< The buttons for the user to select the othello game type */
	private JButton m_OthelloButton;

	/**< The buttons for the user to select the connect four game type */
	private JButton m_ConnectFourButton;

	/**< The buttons for the user to start up which ever game they have selected 
	after inputing the two player names */
	private JButton m_PlayerOK;

	/**< The buttons for the user to go back to the select game screen */
	private JButton m_PlayerBack;
	
    /**< This panel will hold the buttons for selecting either the connect four 
    game or othello game*/
	private JPanel m_ButtonPanel;

	/**< This panel will hold the labels, buttons and text fields for inputting 
	the player names*/
    private JPanel m_InputScreen;

     /**< This panel will hold the label for the select screen */
    private JPanel m_SelectPanel;
	
	/**< Constants for the width of the menu frame */
	private final int FRAME_WIDTH = 400;	

	/**< Constants for the height of the menu frame */	
	private final int FRAME_HEIGHT = 300;

	/**< Constants for the width of the othello frame */
	private final int OTH_FRM_WIDTH = 600;	

	/**< Constants for the height of the othello frame */	
	private final int OTH_FRM_HEIGHT = 600;

	/**< Constant for Number of Players */
	private final int NUM_PLAYERS = 2;

	/**< Constant for player one */
	private final int PLAYER_ONE = 0;

	/**< Constant for player two */
	private final int PLAYER_TWO = 1;

	/**< Constants for connect four game type */
	private static final int CONNECT = 1;

	/**< Constants for othello game type */
	private static final int OTHELLO = 2;

	/**< This hold the font size for the main heading label on the select 
	screen */
	private final int HEADING_FONTSIZE = 36;
	
	/**< This hold the font sizes for the subheading labels on the select 
	screen */
	private final int SUBHEADING_FONTSIZE = 18;
	
	private final int SMALLHEADING_FONTSIZE = 16;
	
	/**< This hold the font size for the heading label on the input screen */
	private final int SECONDHEAD_FONTSIZE = 23;
	
	/**< This hold the column size for the text fields on the input screen */
	private final int TEXTFIELD_COLUMNS = 20;
	
	/**< Constants for the GridBagLayout column one on the x axis */
	private final int COLUMN_ONE = 0;
	
	/**< Constants for the GridBagLayout column two on the x axis */
	private final int COLUMN_TWO = 1;
	
	/**< Constants for the GridBagLayout column three on the x axis */
	private final int COLUMN_THREE = 2;
	
	/**< Constants for the GridBagLayout row one on the y axis */
	private final int ROW_ONE = 0;
	
	/**< Constants for the GridBagLayout row two on the y axis */
	private final int ROW_TWO = 1;
	
	/**< Constants for the GridBagLayout row three on the y axis */
	private final int ROW_THREE = 2;
	
	/**< Constants for the GridBagLayout row four on the y axis */
	private final int ROW_FOUR = 3;
	
	/**< Constant for the insets of no gap between components */
	private final int NO_GAP = 0;
	
	/**< Constant for the insets of 10 size gap between components */
	private final int GAP_TEN = 10;
	
	/**< Constant for the insets of 15 size gap between components */
	private final int GAP_FIFTEEN = 15;

	/**< Constant for the insets of 20 size gap between components */
	private final int GAP_TWENTY = 20;
	
	/**< Constant for maximum name length */
	private final int MAX_NAME = 20;
	
	/**< This sets up the timer for the validation checks*/
	private Timer m_Timer;
	
	/**< This is used in the timer for the validation checks*/
	private int m_Time;
	
	String[] m_PlayerNames = { "Human", "Computer: Easy", "Computer: Medium", "Computer: Hard"};
	
}