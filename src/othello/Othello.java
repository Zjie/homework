package othello;
/*
 * 工程名：  奥赛罗（Othello）
 * 作者：    without me
 * 描述：    奥赛罗游戏
 * 创建日期：2006 11 11
 * 完成日期：2006 11 12
 */
import javax.swing.JFrame;

public class Othello 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//创建并打开询问棋盘大小的窗口
		InquireSizeFrame inquireFrame = new InquireSizeFrame();
		inquireFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inquireFrame.setVisible(true);
	}

}
