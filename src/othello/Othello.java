package othello;
/*
 * ��������  �����ޣ�Othello��
 * ���ߣ�    without me
 * ������    ��������Ϸ
 * �������ڣ�2006 11 11
 * ������ڣ�2006 11 12
 */
import javax.swing.JFrame;

public class Othello 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//��������ѯ�����̴�С�Ĵ���
		InquireSizeFrame inquireFrame = new InquireSizeFrame();
		inquireFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inquireFrame.setVisible(true);
	}

}
