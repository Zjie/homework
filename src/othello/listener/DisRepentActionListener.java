package othello.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import othello.OthelloFrame;
import othello.Step;

/**
 * 撤销悔棋按钮监听器
 * @author without me
 *
 */
public class DisRepentActionListener implements ActionListener
{
	public OthelloFrame of;
	public DisRepentActionListener(OthelloFrame of) {
		this.of = of;
	}
	//按下撤销悔棋按钮
	public void actionPerformed(ActionEvent e) 
	{
		//如果栈空　即最近还没有悔棋过，则不能撤销悔棋
		if(of.disRepentStack.isEmpty())
			return ;
		
		//存储当前的状态到悔棋的栈
		of.repentStack.push(of.chessmanMatrix, of.isBlackTurn, of.blackNum, of.whiteNum, of.recentChess);

		//改变到悔棋之前的状态，即撤销悔棋，包括棋盘状态，该由谁下子，比分多少
		Step step = of.disRepentStack.pop();
		of.renewStatus(step);
		
		//空的格子减少
		of.remainNum --;
		of.isBegin = false;
		of.repaint();
	}
}