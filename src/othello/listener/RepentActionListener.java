package othello.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import othello.OthelloFrame;

/**
 * ���尴ť������
 * @author without me
 *
 */
public class RepentActionListener implements ActionListener
{
	public OthelloFrame of;
	public RepentActionListener(OthelloFrame of) {
		this.of = of;
	}
	//���»��尴ť
	public void actionPerformed(ActionEvent e) 
	{
		of.repent();
	}
}
