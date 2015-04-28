package othello;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * ѯ�ʴ�С����
 * @author without me
 *
 */
public class InquireSizeFrame extends JFrame 
{
	private static final long serialVersionUID = -114739662967951420L;
	public InquireSizeFrame()
	{
		//���ñ���ʹ�С
		setSize(300, 200);
		setTitle("ѡ�����̴�С");
		
		//���񲼾�,��������ѡ��ť����
		setLayout(new GridLayout(5, 1));
		ButtonGroup group = new ButtonGroup();
		ButtonGroup playModelGroup = new ButtonGroup();
		
		notWithComBtn = new JRadioButton("˫�˶�ս");
		withComBtn = new JRadioButton("�˻���ս", true);
		
		extraSmallBtn = new JRadioButton("СС������  6 * 6");
		smallSizeBtn = new JRadioButton("С������  10 * 10", true);
		middleSizeBtn = new JRadioButton("��������  16 * 16");
		bigSizeBtn = new JRadioButton("��������  20 * 20");
		
		//������Щ��ѡ��ť
		add(notWithComBtn);
		add(withComBtn);
		
		add(extraSmallBtn);
		add(smallSizeBtn);
		add(middleSizeBtn);
		add(bigSizeBtn);
		
		//����Щ��ť���ڲ�ͬ������
		playModelGroup.add(notWithComBtn);
		playModelGroup.add(withComBtn);
		group.add(extraSmallBtn);
		group.add(smallSizeBtn);
		group.add(middleSizeBtn);
		group.add(bigSizeBtn);
		
		//ȷ�ϰ�ť
		JButton sureBtn = new JButton("��ʼ��Ϸ");
		//����ȷ�ϰ�ť
		sureBtn.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						int size;
						//���ݵ�ѡ��ť��ѡ�����ȷ�����̵Ĵ�С
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
						//��ѯ�ʴ�С�Ĵ�������
						InquireSizeFrame.this.dispose();
						
						//�����û���ѡ�񴴽�����ʾ������
						OthelloFrame frame = new OthelloFrame(size, playWithCom);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setVisible(true);
					}
				});
		//��ȷ��ť����panel��,��panel�ӵ�������
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
