package homework3;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Highlight{
	public void C4Highlight(int i, int j, GameImplementation m_Game, JLabel[][] m_Square_Labels) throws IOException{
	
			if(m_Game.getWinner() == 1){
                BufferedImage piece_Image_Highlighted = ImageIO.read(new File("Images/RedPieceHighlighted.png"));
				if(m_Game.getWinningMove() == 0){
					m_Square_Labels[i][j].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i + 1][j].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i + 2][j].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i + 3][j].setIcon(new ImageIcon(piece_Image_Highlighted));
				}
				if(m_Game.getWinningMove() == 1){
					m_Square_Labels[i][j].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i][j + 1].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i][j + 2].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i][j + 3].setIcon(new ImageIcon(piece_Image_Highlighted));
				}
				if(m_Game.getWinningMove() == 2){
					m_Square_Labels[i][j].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i + 1][j + 1].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i + 2][j + 2].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i + 3][j + 3].setIcon(new ImageIcon(piece_Image_Highlighted));
				}
				if(m_Game.getWinningMove() == 3){
					m_Square_Labels[i][j].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i - 1][j + 1].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i - 2][j + 2].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i - 3][j + 3].setIcon(new ImageIcon(piece_Image_Highlighted));
				}
			}  
			if(m_Game.getWinner() == 2){
                BufferedImage piece_Image_Highlighted = ImageIO.read(new File("Images/YellowPieceHighlighted.png"));
				if(m_Game.getWinningMove() == 0){
					m_Square_Labels[i][j].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i + 1][j].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i + 2][j].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i + 3][j].setIcon(new ImageIcon(piece_Image_Highlighted));
				}
				if(m_Game.getWinningMove() == 1){
					m_Square_Labels[i][j].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i][j + 1].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i][j + 2].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i][j + 3].setIcon(new ImageIcon(piece_Image_Highlighted));
				}
				if(m_Game.getWinningMove() == 2){
					m_Square_Labels[i][j].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i + 1][j + 1].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i + 2][j + 2].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i + 3][j + 3].setIcon(new ImageIcon(piece_Image_Highlighted));
				}
				if(m_Game.getWinningMove() == 3){
					m_Square_Labels[i][j].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i - 1][j + 1].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i - 2][j + 2].setIcon(new ImageIcon(piece_Image_Highlighted));
					m_Square_Labels[i - 3][j + 3].setIcon(new ImageIcon(piece_Image_Highlighted));
				}
			} 		
	}

}