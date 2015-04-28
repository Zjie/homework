package othello.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import othello.OthelloFrame;

/**
 * »ÚÆå°´Å¥¼àÌýÆ÷
 * @author without me
 *
 */
public class RepentActionListener implements ActionListener
{
	public OthelloFrame of;
	public RepentActionListener(OthelloFrame of) {
		this.of = of;
	}
	//°´ÏÂ»ÚÆå°´Å¥
	public void actionPerformed(ActionEvent e) 
	{
		of.repent();
	}
}
