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
 * 奥赛罗主窗口
 * @author without me
 *
 */
public class OthelloFrame extends JFrame 
{
	private static final long serialVersionUID = 1795707724437152669L;
	/**
	 * 主窗口构造函数
	 * @param size 棋盘的大小 即棋盘的行列数
	 */
	public OthelloFrame(int size)
	{
		//设置标题，大小和间距
		setTitle("奥赛罗--by withoutme_hw ");
		this.size = (int) size;
		dis = (int) (70 - this.size);  
		off = (int) (dis >> 1);
		repentStack = new RecordStack(size * size * 2);
		disRepentStack = new RecordStack(size * size * 2);
		recentChess = new int[2];
		isBegin = true;
		
		setSize((this.size + 1) * dis + off - 5, (this.size + 1)* dis + off + 30);
		
		//加上提示和棋盘的panel
		setLayout(new BorderLayout());
		
		//创建窗体的左侧panel
		JPanel trivialPanel = new JPanel();
		trivialPanel.setLayout(new GridLayout(5, 1));
		
		prompt = new JLabel("黑棋下子");
		numPrompt = new JLabel("黑色：白色 ＝ 2 : 2");
		
		//初始化三个按钮
		JButton repentBtn = new JButton("  悔棋  "); //?new ImageIcon("/left.gif")
		JButton disRepentBtn = new JButton("撤销悔棋");
		JButton restartBtn = new JButton("重新开始");
		
		//给三个按钮加上监听器
		repentBtn.addActionListener(new RepentActionListener(this));
		disRepentBtn.addActionListener(new DisRepentActionListener(this));
		restartBtn.addActionListener(new RestartBtnActionListener(this));
		
		//创建三个panel分别容纳上面三个按钮
		JPanel repentBtnPanel = new JPanel();
		JPanel disRepentBtnPanel = new JPanel();
		JPanel restartBtnPanel = new JPanel();
		
		//在panel上加入这三个按钮
		repentBtnPanel.add(repentBtn);
		disRepentBtnPanel.add(disRepentBtn);
		restartBtnPanel.add(restartBtn);
		
		//在主窗体的左侧panel上加入提示 和三个有按钮的panel
		trivialPanel.add(prompt);
		trivialPanel.add(numPrompt);
		trivialPanel.add(repentBtnPanel);
		trivialPanel.add(disRepentBtnPanel);
		trivialPanel.add(restartBtnPanel);
		
		//将两个主体panel分别加入窗体的左侧和中央
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
	 * 无参构造方法，以大小为10调用一个参数的构造方法
	 *
	 */
	public OthelloFrame()
	{
		this(10);
	}
	
	
	public void repent()
	{
		//如果栈空　即处在还没有开始下棋的状态，则不能悔棋
		if(repentStack.isEmpty())
			return ;
		
		//存储当前的状态到撤销悔棋的栈
		disRepentStack.push(chessmanMatrix, isBlackTurn, blackNum, whiteNum, recentChess);
		
		//改变到上一状态，即悔棋，包括棋盘状态，该由谁下子，比分多少
		Step step = repentStack.pop();
		renewStatus(step);

		System.out.println("-----------------------------------");
		//空的格子增多
		remainNum ++;
		repaint();
	}
	
	
	/**
	 * 更新游戏状态， 恢复到step的状态
	 * @param step 目标恢复状态
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