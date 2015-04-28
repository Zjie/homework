package othello.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import othello.OthelloFrame;
import othello.Step;

/**
 * �������尴ť������
 * @author without me
 *
 */
public class DisRepentActionListener implements ActionListener
{
	public OthelloFrame of;
	public DisRepentActionListener(OthelloFrame of) {
		this.of = of;
	}
	//���³������尴ť
	public void actionPerformed(ActionEvent e) 
	{
		//���ջ�ա��������û�л���������ܳ�������
		if(of.disRepentStack.isEmpty())
			return ;
		
		//�洢��ǰ��״̬�������ջ
		of.repentStack.push(of.chessmanMatrix, of.isBlackTurn, of.blackNum, of.whiteNum, of.recentChess);

		//�ı䵽����֮ǰ��״̬�����������壬��������״̬������˭���ӣ��ȷֶ���
		Step step = of.disRepentStack.pop();
		of.renewStatus(step);
		
		//�յĸ��Ӽ���
		of.remainNum --;
		of.isBegin = false;
		of.repaint();
	}
}