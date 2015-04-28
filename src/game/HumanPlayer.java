package game;
/**
*
* @file HumanPlayer.java
* @author Adam Yaziji
* @date 27 Feb 2014
* 
*
* @brief This class is used to define a human player.
* 
*/

class HumanPlayer <C> extends Player{
	
/**
* This is the constructor for HumanPlayer.
*
*@param newName- This is a string which contains the players name
*@param newPlayerColour- This is an abstract object so you can have othello piece colour or connect four piece colour 
*/	
	
	public HumanPlayer(String newName, C newPlayerColour){
        	super(newName,newPlayerColour,"Human");
	}

	
/**
* This is to test that Othello game works with HumanPlayer
* This tests that the name, colour and score for each player can be retreived 
*/

    private static void test1 (){
        final int TEST_PLAYER_SCORE = 2;
        
        OthelloPiece player1Piece;
        player1Piece = new OthelloPiece(OthelloPiece.OthelloPieceColour.BLACK);
        HumanPlayer player1 = 
                new HumanPlayer("Tom",player1Piece.getPieceColour());
        System.out.println("Player1: " +player1.getName());
        System.out.println("Colour: " +player1.getColour());
        System.out.println("score: " +player1.getScore());
        player1.setScore(TEST_PLAYER_SCORE);
        System.out.println("Change score: " +player1.getScore());
    }


/**
* This is to test that Connect Four game works with HumanPlayer
* This tests that the name, colour and score for each player can be retreived 
*/


    private static void test2(){
        final int TEST_PLAYER_SCORE = 2;
        
        ConnectFourPiece player1Piece;
        player1Piece = 
        new ConnectFourPiece(ConnectFourPiece.ConnectFourPieceColour.YELLOW);
        HumanPlayer player1 =
            new HumanPlayer("Tom",player1Piece.getPieceColour());
        System.out.println("Player1: " +player1.getName());
        System.out.println("Colour: " +player1.getColour());
        System.out.println("score: " +player1.getScore());
        player1.setScore(TEST_PLAYER_SCORE);
        System.out.println("Change score: " + player1.getScore());
    }
    
/** 		
* This is the main method of the HumanPlayer class. This method is used to test the 
* methods within the class with both valid and invalid parameters.
*/

    public static void main (String[] args){
        test1();
        test2();
    }
}		
						
