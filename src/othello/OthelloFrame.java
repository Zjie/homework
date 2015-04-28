package othello;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import othello.listener.DisRepentActionListener;
import othello.listener.RepentActionListener;
import othello.listener.RestartBtnActionListener;
/**
 * ������������
 * @author without me
 *
 */
public class OthelloFrame extends JFrame 
{
	private static final long serialVersionUID = 1795707724437152669L;
	/**
	 * �����ڹ��캯��
	 * @param size ���̵Ĵ�С �����̵�������
	 */
	public OthelloFrame(int size)
	{
		//���ñ��⣬��С�ͼ��
		setTitle("������--by withoutme_hw ");
		this.size = (int) size;
		dis = (int) (70 - this.size);  
		off = (int) (dis >> 1);
		repentStack = new RecordStack(size * size * 2);
		disRepentStack = new RecordStack(size * size * 2);
		recentChess = new int[2];
		isBegin = true;
		
		setSize((this.size + 1) * dis + off - 5, (this.size + 1)* dis + off + 30);
		
		//������ʾ�����̵�panel
		setLayout(new BorderLayout());
		
		//������������panel
		JPanel trivialPanel = new JPanel();
		trivialPanel.setLayout(new GridLayout(5, 1));
		
		prompt = new JLabel("��������");
		numPrompt = new JLabel("��ɫ����ɫ �� 2 : 2");
		
		//��ʼ��������ť
		JButton repentBtn = new JButton("  ����  "); //?new ImageIcon("/left.gif")
		JButton disRepentBtn = new JButton("��������");
		JButton restartBtn = new JButton("���¿�ʼ");
		
		//��������ť���ϼ�����
		repentBtn.addActionListener(new RepentActionListener(this));
		disRepentBtn.addActionListener(new DisRepentActionListener(this));
		restartBtn.addActionListener(new RestartBtnActionListener(this));
		
		//��������panel�ֱ���������������ť
		JPanel repentBtnPanel = new JPanel();
		JPanel disRepentBtnPanel = new JPanel();
		JPanel restartBtnPanel = new JPanel();
		
		//��panel�ϼ�����������ť
		repentBtnPanel.add(repentBtn);
		disRepentBtnPanel.add(disRepentBtn);
		restartBtnPanel.add(restartBtn);
		
		//������������panel�ϼ�����ʾ �������а�ť��panel
		trivialPanel.add(prompt);
		trivialPanel.add(numPrompt);
		trivialPanel.add(repentBtnPanel);
		trivialPanel.add(disRepentBtnPanel);
		trivialPanel.add(restartBtnPanel);
		
		//����������panel�ֱ���봰�����������
		//add(trivialPanel, BorderLayout.WEST);
		panel = new OthelloPanel(this);
		panel.setBackground(Color.GREEN);
		add(panel, BorderLayout.CENTER);
	}
	
	public OthelloFrame(int size, boolean playWithCom)
	{
		this(size);
		this.withCom = playWithCom;
	}
	
	/**
	 * �޲ι��췽�����Դ�СΪ10����һ�������Ĺ��췽��
	 *
	 */
	public OthelloFrame()
	{
		this(10);
	}
	
	
	public void repent()
	{
		//���ջ�ա������ڻ�û�п�ʼ�����״̬�����ܻ���
		if(repentStack.isEmpty())
			return ;
		
		//�洢��ǰ��״̬�����������ջ
		disRepentStack.push(chessmanMatrix, isBlackTurn, blackNum, whiteNum, recentChess);
		
		//�ı䵽��һ״̬�������壬��������״̬������˭���ӣ��ȷֶ���
		Step step = repentStack.pop();
		renewStatus(step);

		System.out.println("-----------------------------------");
		//�յĸ�������
		remainNum ++;
		repaint();
	}
	
	
	/**
	 * ������Ϸ״̬�� �ָ���step��״̬
	 * @param step Ŀ��ָ�״̬
	 */
	public void renewStatus(Step step)
	{
		int[][] tempArr = step.getChessboardStatus();
		for(int i = 1; i <= size; i ++)
			for(int j = 1; j <= size; j ++)
				chessmanMatrix[i][j] = tempArr[i][j];
		isBlackTurn = step.isBlackTurn();
		recentChess[0] = step.getLastChessCoord()[0];
		recentChess[1] = step.getLastChessCoord()[1];

		int[] tempScore = step.getScore();
		blackNum = tempScore[0];
		whiteNum = tempScore[1];
	}
	
	
	
	public boolean isBegin;
	public int[] recentChess;
	public boolean withCom;
	public RecordStack repentStack;
	public RecordStack disRepentStack;
	public int[][] chessmanMatrix;
	public int whiteNum;
	public int blackNum;
	public int remainNum;
	public int isEnd = 0;
	public final int off;
	public int dis;
	public int size;
	public boolean isBlackTurn = true;
	public JLabel prompt;
	public JLabel numPrompt;
	
	public OthelloPanel panel;
}