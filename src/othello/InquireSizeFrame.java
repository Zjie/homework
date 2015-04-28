package othello;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 询问大小窗口
 * @author without me
 *
 */
public class InquireSizeFrame extends JFrame 
{
	private static final long serialVersionUID = -114739662967951420L;
	public InquireSizeFrame()
	{
		//设置标题和大小
		setSize(300, 200);
		setTitle("选择棋盘大小");
		
		//网格布局,将三个单选按钮加入
		setLayout(new GridLayout(5, 1));
		ButtonGroup group = new ButtonGroup();
		ButtonGroup playModelGroup = new ButtonGroup();
		
		notWithComBtn = new JRadioButton("双人对战");
		withComBtn = new JRadioButton("人机对战", true);
		
		extraSmallBtn = new JRadioButton("小小型棋盘  6 * 6");
		smallSizeBtn = new JRadioButton("小型棋盘  10 * 10", true);
		middleSizeBtn = new JRadioButton("中型棋盘  16 * 16");
		bigSizeBtn = new JRadioButton("大型棋盘  20 * 20");
		
		//加入这些单选按钮
		add(notWithComBtn);
		add(withComBtn);
		
		add(extraSmallBtn);
		add(smallSizeBtn);
		add(middleSizeBtn);
		add(bigSizeBtn);
		
		//将这些按钮分在不同的组中
		playModelGroup.add(notWithComBtn);
		playModelGroup.add(withComBtn);
		group.add(extraSmallBtn);
		group.add(smallSizeBtn);
		group.add(middleSizeBtn);
		group.add(bigSizeBtn);
		
		//确认按钮
		JButton sureBtn = new JButton("开始游戏");
		//监听确认按钮
		sureBtn.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						int size;
						//根据单选按钮的选择情况确定棋盘的大小
						boolean playWithCom = false;
						if(withComBtn.isSelected())
							playWithCom = true;
						if(extraSmallBtn.isSelected())
							size = 6;
						else if(smallSizeBtn.isSelected())
							size = 10;
						else if(middleSizeBtn.isSelected())
							size = 16;
						else
							size = 20;
						//将询问大小的窗口销毁
						InquireSizeFrame.this.dispose();
						
						//根据用户的选择创建并显示主窗体
						OthelloFrame frame = new OthelloFrame(size, playWithCom);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setVisible(true);
					}
				});
		//把确认钮放在panel中,把panel加到窗体中
		JPanel btnPanel = new JPanel();
		btnPanel.add(sureBtn);
		add(btnPanel);
	}

	private JRadioButton notWithComBtn;
	private JRadioButton withComBtn;
	private JRadioButton extraSmallBtn;
	private JRadioButton smallSizeBtn;
	private JRadioButton middleSizeBtn;
	private JRadioButton bigSizeBtn;

}
