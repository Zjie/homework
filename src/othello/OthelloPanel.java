package othello;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 内部类，容纳棋盘的panel
 * @author without me
 *
 */
public  class OthelloPanel extends JPanel
{
	private static final long serialVersionUID = 3166001866694664344L;
	OthelloFrame of;
	ComPlay com;
	/**
	 * 无参构造方法
	 *
	 */
	public OthelloPanel(OthelloFrame of)
	{
		this.of = of;
		int size = of.size;
		int dis = of.dis;
		//根据size和dis设定panel大小
		this.setSize((size - 3)* (dis), (size - 3)* (dis));
		this.setBackground(Color.GRAY);
		
		// 建立一个记录棋盘上棋子情况的数组
		of.chessmanMatrix = new int[size + 2][size + 2];
		of.chessmanMatrix[size / 2][size / 2] = 1;
		of.chessmanMatrix[size / 2][size / 2 + 1] = 1;
		of.chessmanMatrix[size / 2 + 1][size / 2] = -1;
		of.chessmanMatrix[size / 2 + 1][size / 2 + 1] = -1;
		of.remainNum = size * size - 4;
		of.whiteNum = 2;
		of.blackNum = 2;
		
		com = new ComPlay();
		//监听本panel上的鼠标事件
		this.addMouseListener(new PutChessMouseListener());
	}

	/**
	 * 绘制棋盘，根据当前棋盘上棋子的情况绘制
	 */
	public void paintComponent(Graphics g)
	{
		int size = of.size;
		int[][] chessmanMatrix = of.chessmanMatrix;
		int off = of.off;
		int dis = of.dis;
		super.paintComponent(g);
		
		//根据当前由哪个颜色下子改变提示文字内容
		if(of.isBlackTurn)
			of.prompt.setText("黑棋下子");
		else
			of.prompt.setText("白棋下子");
		of.numPrompt.setText("黑色：白色＝" + of.blackNum + ":" + of.whiteNum);
		
		//绘制棋盘格
		Graphics2D g2 = (Graphics2D)g;
		int width = of.size * of.dis;
		for(int i = 0 ; i <= of.size; i ++)
		{
			g2.drawLine(off, i * dis + off, width + off, i * dis + off);
			g2.drawLine(i * dis + off,  off, i * dis + off, width + off);
		}
		
		//绘制棋子
		for(int i = 1; i <= size; i ++)
		{
			for(int j = 1; j <= size; j ++)
			{
				//没有棋子
				if(chessmanMatrix[i][j] == 0)
					continue;
				//黑色棋子
				else if(chessmanMatrix[i][j] == 1)
					g2.setColor(Color.BLACK);
				//白色棋子
				else
					g2.setColor(Color.WHITE);
				g2.fillOval((j - 1)* dis  + off, (i - 1) * dis + off , dis, dis);
			}
		}
		
		//对刚下的子加上框
		if(!of.isBegin)
		{
			g2.setColor(Color.RED);
			g2.drawRect((of.recentChess[1] - 1) * dis + off, (of.recentChess[0] - 1) * dis + off, dis, dis);
		}
	}
	
	/**
	 * 鼠标事件监听器，根据鼠标点击的地方来确定放子的地点
	 * @author without me
	 *
	 */
	public class PutChessMouseListener extends MouseAdapter
	{
		int size = of.size;
		int[][] chessmanMatrix = of.chessmanMatrix;
		int off = of.off;
		int dis = of.dis;
		//鼠标点下
		public void mousePressed(MouseEvent e) 
		{
			//获取鼠标点击的坐标 并将其对应到记录棋子的数组上
			int x = (int) ((e.getY() - off) / dis + 1);
			int y = (int) ((e.getX() - off) / dis + 1);
			
			//	在其他地方按鼠标不做任何操作
			if(x > size || y > size)
				return;
			
			//试图在(x，y) 下子
			putChess(x, y);
			System.out.println("+++++++++++++++++++" + of.isBlackTurn);
			
			//此步没有悔棋，撤销悔棋的栈置空
			of.disRepentStack.setEmpty();
			if(!judgeStatus())
				if(!of.isBlackTurn && of.withCom)
				{
					com.comPutChess();
					judgeStatus();
				}
		}
	}

	private boolean putChess(int x, int y)
	{
		int size = of.size;
		int[][] chessmanMatrix = of.chessmanMatrix;
		int off = of.off;
		int dis = of.dis;
		
		if(chessmanMatrix[x][y] != 0)
			return false;
		if(of.isBegin)
			of.isBegin = !of.isBegin;

		of.repentStack.push(chessmanMatrix, of.isBlackTurn, of.blackNum, of.whiteNum, of.recentChess);
		
		//如果在此处放子并不能吃掉对方的子, 则放子无效，如果可以吃掉，将吃掉的子的颜色改变
		if( eat(x, y, of.isBlackTurn ? 1 : (-1), false) == 0)
		{
			System.out.println("putChess return false");
			of.repentStack.pop();
			return false;
		}

		of.recentChess[0] = x;
		of.recentChess[1] = y;
		
		//改变当前下下来的颜色的数量　并把当前下下来的子加入到存储棋盘状态的数组中
		if(of.isBlackTurn)
		{
			of.blackNum ++;
			chessmanMatrix[x][y] = 1;
		}
		else
		{
			of.whiteNum ++;
			chessmanMatrix[x][y] = -1;
		}

		repaint();
		of.isBlackTurn = !of.isBlackTurn;
		//剩下的空格数数减少
		of.remainNum --;
		return true;
	}

	private boolean judgeStatus()
	{
		int size = of.size;
		int[][] chessmanMatrix = of.chessmanMatrix;
		int off = of.off;
		int dis = of.dis;
		int remainNum = of.remainNum;
		int blackNum = of.blackNum;
		int whiteNum = of.whiteNum;
		//如果棋盘上没有空格，游戏结束
		if(remainNum == 0 || blackNum == 0 || whiteNum == 0)
		{
			//给出赢家和比分提示
			if(blackNum > whiteNum || whiteNum == 0)
				JOptionPane.showMessageDialog(of, "黑色获胜!!!" + "  黑色：白色＝" + blackNum + ":" + whiteNum);
			else if(whiteNum > blackNum || blackNum == 0)
				JOptionPane.showMessageDialog(of, "白色获胜!!!" + "  黑色：白色＝" + blackNum + ":" + whiteNum);
			else
				JOptionPane.showMessageDialog(of, "平局!!!" + "  黑色：白色＝" + blackNum + ":" + whiteNum);
			of.isEnd = 2;
			return true;
		}
		System.out.println("1");
		//扫描所有的格子,检查下一个玩家能否下子
		for(int cnt = 0; cnt <= 1; cnt ++)
		{
			boolean nextPlayerCanEat = false;
			for(int i = 1; i <= size; i ++)
				for(int j = 1; j <= size; j ++)
					if(chessmanMatrix[i][j] == 0)
						if(eat(i, j, of.isBlackTurn ?  1 :  (-1), true) > 0)
						{
							nextPlayerCanEat = true;
							i = size + 1;
							cnt = 2;
							break;
						} 
			//如果下一个玩家不能下子，给出提示，则还是当前玩家下子

			System.out.println("2");
			if(!nextPlayerCanEat)
			{
				of.isEnd ++;
			
				//如果双方都不能下子，则游戏结束
				if(of.isEnd == 2)
				{
					JOptionPane.showMessageDialog(of,  "双方都不能再下子，游戏结束" + "黑色：白色　＝" + blackNum + ":" + whiteNum);
					return true;
				}

				System.out.println("/////////////////////" + of.isBlackTurn);
				//某一方不能下子,给出提示
				if(of.isBlackTurn)
					JOptionPane.showMessageDialog(of,  "黑色玩家此轮不能下子,继续由白色玩家下子");
				else
					JOptionPane.showMessageDialog(of,  "白色玩家此轮不能下子,继续由黑色色玩家下子");
				of.isBlackTurn = !of.isBlackTurn;
			}
			else
				of.isEnd = 0;
		}

		System.out.println("3");
		return false;
	}
	
	/**
	 * 放子的方法，可以用来实际放下一个子(并修改其他被吃子的颜色)，或者判断此处是否能够放子
	 * @param x  放子的位置的横坐标
	 * @param y  放子的位置的纵坐标
	 * @param keyValue 所放的子的颜色
	 * @param isForJudge 是否只是用来判断是否能够放子 而不实际吃掉那些子
	 * @return 此处能否放子
	 */
	private int eat(int x, int y, int keyValue, boolean isForJudge)
	{
		int eatNum = 0;
		int[][] chessmanMatrix = of.chessmanMatrix;
		//此处已经有子  不做操作
		if(chessmanMatrix[x][y] != 0)
			return 0;
		
		//对各个方向的连在一起的棋子作检查
		for(int i = 0; i < 4; i ++)
		{
			int off = 1;
			int tempX = x;
			int tempY = y;
			boolean canEat = false;
			int end = 1;
			
			//j的两个取值表示一个方向的两端，比如说垂直方向上的向上和向下
			for(int j = 0; j < 2; j ++)
			{
				off = 1;
				while(true)
				{
					switch(i)
					{
						//垂直方向
						case 0:
							if(j == 0)
								tempX = x + off;
							else
								tempX = x - off;
							break;
						//水平方向
						case 1:
							if(j == 0)
								tempY = y + off;
							else
								tempY = y - off;
							break;
						//3点钟和7点钟方向
						case 2:
							if(j == 0)
							{
								tempX = x + off;	
								tempY = y + off;
							}
							else
							{
								tempX = x - off;	
								tempY = y - off;
							}
							break;
						//11点钟和5点钟方向
						case 3:
							if(j == 0)
							{
								tempX = x - off;	
								tempY = y + off;
							}
							else
							{
								tempX = x + off;	
								tempY = y - off;
							}
							break;
						default:
							JOptionPane.showMessageDialog(of, "error!");
					}
					
					//如果不是与刚放下的子相同的棋子（包括没有棋子）继续查找下个方向
					if(!canEat && chessmanMatrix[tempX][tempY] == keyValue)
					{
						if(off > 1)
						{
							j --;
							canEat = true;
							end = off;
						}
						break;
					}
					//没有子
					if(chessmanMatrix[tempX][tempY] == 0)
						break;
					
					//可以吃掉一些棋子
					if(canEat)
					{
						//当前找到的棋子在两个的目标子之间， 吃掉当前找到的子
						if(off < end)	
						{
							//根据被吃掉的子改变两种颜色的棋子的数量
							if(!isForJudge)
							{
								if(chessmanMatrix[tempX][tempY] == 1)
								{
									of.whiteNum ++;
									of.blackNum --;
								}
								else
								{
									of.whiteNum --;
									of.blackNum ++;
								}
							//被吃掉的子标志为另一种颜色
								chessmanMatrix[tempX][tempY] = - chessmanMatrix[tempX][tempY];
							}
							eatNum ++;
						}
						else
						{
							//将是否可以吃子的标志置为否，并跳出此方向的查找
							canEat = false;
							break;
						}
					}
					off ++;
				}//end of while
			}//end of for
		}//end of for
//		System.out.println( "  eated " + eatNum);
		return eatNum;
	}//end of eat();
	
	/**
	 * 电脑下子的类
	 * @author without me
	 *
	 */
	public class ComPlay
	{
		/**
		 * 判断点(x, y)在哪条边上（边的编号按顺时针方向）正上方的边为0；
		 * @param x 点的横坐标
		 * @param y 点的纵坐标
		 * @return 边的编号
		 */
		public  int onSideNumber(int x, int y)
		{
			if(x == 1)
				return 0;
			if(y == of.size)
				return 1;
			if(x == of.size)
				return 2;
			if(y == 1)
				return 3;
			return 4;
		}
		
		/**
		 * 判断如果电脑在(x, y)下子的话,对手能否占边--即能否在(x,y)所在的边上下子
		 * @param x 横坐标
		 * @param y 纵坐标
		 * @return 能否占边
		 */
		public boolean nextCanTakeSide(int x, int y)
		{
			int size = of.size;
			System.out.println("call nextCanTakeSide()");
			int onSideNum = onSideNumber(x, y);
			int nextX = 1, nextY = 1;
			
			//先在(x, y) 下子
			putChess(x, y);
			
			//扫描(x, y)所在的边
			for(int off = 1; off <= size; off ++)
			{
				//根据x,y所在的边的不同 扫描不同的边
				switch(onSideNum)
				{
					case 0:
						nextY = off;
						break;
					case 1:
						nextX = off;
						nextY = size;
						break;
					case 2:
						nextX = size;
						nextY = off;
						break;
					case 3:
						nextX = off;
						break;
				}
				
				System.out.print("nextCanEat" + nextX + "," + nextY);
				//对目标边上的所有点进行一次判断
				int[][] chessmanMatrix = of.chessmanMatrix;
				if(putChess(nextX, nextY))
				{
					if( chessmanMatrix[x][y] == 1)
					{
						//撤销在(x, y)的下子
						of.repent();
						of.disRepentStack.pop();
						//撤销在(x,y)的下子,恢复到调用此方法前的状态
						of.repent();
						of.disRepentStack.pop();
						return true;
					}
					else 
					{
						of.repent();
						of.disRepentStack.pop();
					}
				}
			}
			//目标边上没有一个点可以让对手占边，撤销在(x,y)的下子,恢复到调用此方法前的状态
			of.repent();
			of.disRepentStack.pop();
			System.out.println("nextCANNNNNOTTTAKESIDEEEEEEEEEEEEE");
			return false;
		}
		
		/**
		 * 电脑下子
		 *
		 */
		public void  comPutChess()
		{
			int offEnd;
			int eatNum = 0;
			int maxEatNum = 0;
			int maxEatX = 0, maxEatY = 0;
			int worstX = 0, worstY = 0;
			int size = of.size;
			System.out.println("=====================================");
			//首先看四个角能不能下子
			for(int x = 1; x <= size; x += size - 1)
				for(int y = 1; y <= size; y += size - 1)
					if((eatNum = eat(x, y, (-1), true)) > maxEatNum)
					{
						maxEatX = x;
						maxEatY = y;
						maxEatNum = eatNum;
					}
			//在某个角上可以下子，则在这个角上放子
			if(maxEatNum > 0)
			{
				putChess(maxEatX, maxEatY);
				return ;
			}
		
			//看四边能不能下子
			int start = 1;
			//以下功能同下面的若干行
			offEnd = (int) ((size / 2 - start) * 2 + 1);
			for(int yOff = 0; yOff <= offEnd; yOff ++)
			{
				System.out.println("side  y=" + yOff );
				if(yOff > 0 && yOff < offEnd)
				{
					System.out.print(start+ offEnd + ",");
					System.out.println(start+yOff);
					//在这个地方可以下子
					if( (eatNum = eat(start + offEnd, start + yOff, (-1), true))  > maxEatNum)
					{
						//如果对手能够占领此边则不在这里下子
						if(!nextCanTakeSide(start + offEnd, start + yOff))
						{
							maxEatX = start + offEnd;
							maxEatY = start + yOff;
							maxEatNum = eatNum;
						}
						else
						{
							worstX = start + offEnd;
							worstY = start + yOff;
						}
					}
					System.out.print(start + ",");
					System.out.println(start+yOff);
					//在这个地方可以下子
					if( (eatNum = eat(start, start + yOff, (-1), true)) > maxEatNum)
					{
						//如果对手能够占领此边则不在这里下子
						if(!nextCanTakeSide(start , start + yOff))
						{
							maxEatX = start;
							maxEatY = start + yOff;
							maxEatNum = eatNum;
						}
						else
						{
							worstX = start;
							worstY = start + yOff;
						}
					}
				}
				else
				{
					for(int xOff = 0; xOff <= offEnd; xOff ++)
					{
						System.out.print(start+ xOff + ",");
						System.out.println(start+yOff);
						//在这个地方可以下子
						if((eatNum = eat(start + xOff, start + yOff, (-1), true)) > maxEatNum)
						{
							//如果对手能够占领此边则不在这里下子
							if(!nextCanTakeSide(start + xOff, start + yOff))
							{
								maxEatX = start + xOff;
								maxEatY = start + yOff;
								maxEatNum = eatNum;
							}
							else
							{
								worstX = start + xOff;
								worstY = start + yOff;
							}
						}
					}
				}
				
				//可以在某条边上下子
				if(maxEatNum > 0)
				{
					putChess(maxEatX, maxEatY);
					return;
				}
			}
		
			//从中间往两边考虑能否下子，最后考虑与边界接近的四条边
			for(start = size / 2; start > 1; start --)
			{
				//start为起始位置， 即此次要搜索的正方形框的左上角 offEnd为此正方形框的边长
				offEnd =( size / 2 - start) * 2 + 1;
			
				//按行搜索，纵坐标从start一直搜索到start + offEnd
				for(int yOff = 0; yOff <= offEnd; yOff ++)
				{
					//在正方形的中间部分，此行只考虑两端
					if(yOff > 0 && yOff < offEnd)
					{
						if( (eatNum = eat(start + offEnd, start + yOff, (-1), true)) > maxEatNum)
						{
							maxEatX = start + offEnd;
							maxEatY = start + yOff;
							maxEatNum = eatNum;
						}
						if( (eatNum = eat(start, start + yOff, (-1), true)) > maxEatNum)
						{
							maxEatX = start;
							maxEatY = start + yOff;
							maxEatNum = eatNum;
						}
					}
				
					//在正方形的两端，此行全部都须考虑
					else
					{
						for(int xOff = 0; xOff <= offEnd; xOff ++)
							if((eatNum = eat(start + xOff, start + yOff, (-1), true)) > maxEatNum)
							{
								maxEatX = start + xOff;
								maxEatY = start + yOff;
								maxEatNum = eatNum;
							}
					}//end of else
				}//end of for(yOff)
				
				//如果可以不在此外层框下子， 就不在此外层框下子
				if(start == 3 && maxEatNum > 0)
				{
					putChess(maxEatX, maxEatY);
					return;
				}
				//对次外层框最后处理，即在不得不在此外层下的情况下 才在这里下子
				else if(start == 2 && maxEatNum > 0)
				{
					putChess(maxEatX, maxEatY);
					return;
				}
			}//end of for(start)
			System.out.println("worstX, worstY=  " +worstX + "," + worstY);
			putChess(worstX, worstY);
			return;
			/*
			if(!isBlackTurn  &&  withCom && isEnd == 2)
				com.comPutChess();*/
		}//end of ComPutChess();
		
	}
	
}//end of OthelloPanel