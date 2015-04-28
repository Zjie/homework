package othello.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import othello.OthelloFrame;

/**
 * 重新开始按钮监听器
 * @author without me
 *
 */
public class RestartBtnActionListener implements ActionListener
{
	public OthelloFrame of;
	public RestartBtnActionListener(OthelloFrame of) {
		this.of = of;
	}
	//按下重新开始按钮
	public void actionPerformed(ActionEvent e) 
	{
		int size = of.size;
		//所有属性全部置为最初状态
		for(int i = 1; i <= size; i ++)
			for(int j = 1; j <= size; j ++)
				of.chessmanMatrix[i][j] = 0;
		of.chessmanMatrix[size / 2][size / 2] = 1;
		of.chessmanMatrix[size / 2][size / 2 + 1] = 1;
		of.chessmanMatrix[size / 2 + 1][size / 2] = -1;
		of.chessmanMatrix[size / 2 + 1][size / 2 + 1] = -1;
		of.remainNum = size * size - 4;
		of.repentStack.setEmpty();
		of.disRepentStack.setEmpty();
		of.whiteNum = 2;
		of.blackNum = 2;
		of.isEnd = 0;
		of.isBegin = true;
		of.isBlackTurn = true;
		of.repaint();
	}
}