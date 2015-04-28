package othello;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * �ڲ��࣬�������̵�panel
 * @author without me
 *
 */
public  class OthelloPanel extends JPanel
{
	private static final long serialVersionUID = 3166001866694664344L;
	OthelloFrame of;
	ComPlay com;
	/**
	 * �޲ι��췽��
	 *
	 */
	public OthelloPanel(OthelloFrame of)
	{
		this.of = of;
		int size = of.size;
		int dis = of.dis;
		//����size��dis�趨panel��С
		this.setSize((size - 3)* (dis), (size - 3)* (dis));
		this.setBackground(Color.GRAY);
		
		// ����һ����¼�������������������
		of.chessmanMatrix = new int[size + 2][size + 2];
		of.chessmanMatrix[size / 2][size / 2] = 1;
		of.chessmanMatrix[size / 2][size / 2 + 1] = 1;
		of.chessmanMatrix[size / 2 + 1][size / 2] = -1;
		of.chessmanMatrix[size / 2 + 1][size / 2 + 1] = -1;
		of.remainNum = size * size - 4;
		of.whiteNum = 2;
		of.blackNum = 2;
		
		com = new ComPlay();
		//������panel�ϵ�����¼�
		this.addMouseListener(new PutChessMouseListener());
	}

	/**
	 * �������̣����ݵ�ǰ���������ӵ��������
	 */
	public void paintComponent(Graphics g)
	{
		int size = of.size;
		int[][] chessmanMatrix = of.chessmanMatrix;
		int off = of.off;
		int dis = of.dis;
		super.paintComponent(g);
		
		//���ݵ�ǰ���ĸ���ɫ���Ӹı���ʾ��������
		if(of.isBlackTurn)
			of.prompt.setText("��������");
		else
			of.prompt.setText("��������");
		of.numPrompt.setText("��ɫ����ɫ��" + of.blackNum + ":" + of.whiteNum);
		
		//�������̸�
		Graphics2D g2 = (Graphics2D)g;
		int width = of.size * of.dis;
		for(int i = 0 ; i <= of.size; i ++)
		{
			g2.drawLine(off, i * dis + off, width + off, i * dis + off);
			g2.drawLine(i * dis + off,  off, i * dis + off, width + off);
		}
		
		//��������
		for(int i = 1; i <= size; i ++)
		{
			for(int j = 1; j <= size; j ++)
			{
				//û������
				if(chessmanMatrix[i][j] == 0)
					continue;
				//��ɫ����
				else if(chessmanMatrix[i][j] == 1)
					g2.setColor(Color.BLACK);
				//��ɫ����
				else
					g2.setColor(Color.WHITE);
				g2.fillOval((j - 1)* dis  + off, (i - 1) * dis + off , dis, dis);
			}
		}
		
		//�Ը��µ��Ӽ��Ͽ�
		if(!of.isBegin)
		{
			g2.setColor(Color.RED);
			g2.drawRect((of.recentChess[1] - 1) * dis + off, (of.recentChess[0] - 1) * dis + off, dis, dis);
		}
	}
	
	/**
	 * ����¼�������������������ĵط���ȷ�����ӵĵص�
	 * @author without me
	 *
	 */
	public class PutChessMouseListener extends MouseAdapter
	{
		int size = of.size;
		int[][] chessmanMatrix = of.chessmanMatrix;
		int off = of.off;
		int dis = of.dis;
		//������
		public void mousePressed(MouseEvent e) 
		{
			//��ȡ����������� �������Ӧ����¼���ӵ�������
			int x = (int) ((e.getY() - off) / dis + 1);
			int y = (int) ((e.getX() - off) / dis + 1);
			
			//	�������ط�����겻���κβ���
			if(x > size || y > size)
				return;
			
			//��ͼ��(x��y) ����
			putChess(x, y);
			System.out.println("+++++++++++++++++++" + of.isBlackTurn);
			
			//�˲�û�л��壬���������ջ�ÿ�
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
		
		//����ڴ˴����Ӳ����ܳԵ��Է�����, �������Ч��������ԳԵ������Ե����ӵ���ɫ�ı�
		if( eat(x, y, of.isBlackTurn ? 1 : (-1), false) == 0)
		{
			System.out.println("putChess return false");
			of.repentStack.pop();
			return false;
		}

		of.recentChess[0] = x;
		of.recentChess[1] = y;
		
		//�ı䵱ǰ����������ɫ�����������ѵ�ǰ���������Ӽ��뵽�洢����״̬��������
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
		//ʣ�µĿո���������
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
		//���������û�пո���Ϸ����
		if(remainNum == 0 || blackNum == 0 || whiteNum == 0)
		{
			//����Ӯ�Һͱȷ���ʾ
			if(blackNum > whiteNum || whiteNum == 0)
				JOptionPane.showMessageDialog(of, "��ɫ��ʤ!!!" + "  ��ɫ����ɫ��" + blackNum + ":" + whiteNum);
			else if(whiteNum > blackNum || blackNum == 0)
				JOptionPane.showMessageDialog(of, "��ɫ��ʤ!!!" + "  ��ɫ����ɫ��" + blackNum + ":" + whiteNum);
			else
				JOptionPane.showMessageDialog(of, "ƽ��!!!" + "  ��ɫ����ɫ��" + blackNum + ":" + whiteNum);
			of.isEnd = 2;
			return true;
		}
		System.out.println("1");
		//ɨ�����еĸ���,�����һ������ܷ�����
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
			//�����һ����Ҳ������ӣ�������ʾ�����ǵ�ǰ�������

			System.out.println("2");
			if(!nextPlayerCanEat)
			{
				of.isEnd ++;
			
				//���˫�����������ӣ�����Ϸ����
				if(of.isEnd == 2)
				{
					JOptionPane.showMessageDialog(of,  "˫�������������ӣ���Ϸ����" + "��ɫ����ɫ����" + blackNum + ":" + whiteNum);
					return true;
				}

				System.out.println("/////////////////////" + of.isBlackTurn);
				//ĳһ����������,������ʾ
				if(of.isBlackTurn)
					JOptionPane.showMessageDialog(of,  "��ɫ��Ҵ��ֲ�������,�����ɰ�ɫ�������");
				else
					JOptionPane.showMessageDialog(of,  "��ɫ��Ҵ��ֲ�������,�����ɺ�ɫɫ�������");
				of.isBlackTurn = !of.isBlackTurn;
			}
			else
				of.isEnd = 0;
		}

		System.out.println("3");
		return false;
	}
	
	/**
	 * ���ӵķ�������������ʵ�ʷ���һ����(���޸����������ӵ���ɫ)�������жϴ˴��Ƿ��ܹ�����
	 * @param x  ���ӵ�λ�õĺ�����
	 * @param y  ���ӵ�λ�õ�������
	 * @param keyValue ���ŵ��ӵ���ɫ
	 * @param isForJudge �Ƿ�ֻ�������ж��Ƿ��ܹ����� ����ʵ�ʳԵ���Щ��
	 * @return �˴��ܷ����
	 */
	private int eat(int x, int y, int keyValue, boolean isForJudge)
	{
		int eatNum = 0;
		int[][] chessmanMatrix = of.chessmanMatrix;
		//�˴��Ѿ�����  ��������
		if(chessmanMatrix[x][y] != 0)
			return 0;
		
		//�Ը������������һ������������
		for(int i = 0; i < 4; i ++)
		{
			int off = 1;
			int tempX = x;
			int tempY = y;
			boolean canEat = false;
			int end = 1;
			
			//j������ȡֵ��ʾһ����������ˣ�����˵��ֱ�����ϵ����Ϻ�����
			for(int j = 0; j < 2; j ++)
			{
				off = 1;
				while(true)
				{
					switch(i)
					{
						//��ֱ����
						case 0:
							if(j == 0)
								tempX = x + off;
							else
								tempX = x - off;
							break;
						//ˮƽ����
						case 1:
							if(j == 0)
								tempY = y + off;
							else
								tempY = y - off;
							break;
						//3���Ӻ�7���ӷ���
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
						//11���Ӻ�5���ӷ���
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
					
					//���������շ��µ�����ͬ�����ӣ�����û�����ӣ����������¸�����
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
					//û����
					if(chessmanMatrix[tempX][tempY] == 0)
						break;
					
					//���ԳԵ�һЩ����
					if(canEat)
					{
						//��ǰ�ҵ���������������Ŀ����֮�䣬 �Ե���ǰ�ҵ�����
						if(off < end)	
						{
							//���ݱ��Ե����Ӹı�������ɫ�����ӵ�����
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
							//���Ե����ӱ�־Ϊ��һ����ɫ
								chessmanMatrix[tempX][tempY] = - chessmanMatrix[tempX][tempY];
							}
							eatNum ++;
						}
						else
						{
							//���Ƿ���Գ��ӵı�־��Ϊ�񣬲������˷���Ĳ���
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
	 * �������ӵ���
	 * @author without me
	 *
	 */
	public class ComPlay
	{
		/**
		 * �жϵ�(x, y)���������ϣ��ߵı�Ű�˳ʱ�뷽�����Ϸ��ı�Ϊ0��
		 * @param x ��ĺ�����
		 * @param y ���������
		 * @return �ߵı��
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
		 * �ж����������(x, y)���ӵĻ�,�����ܷ�ռ��--���ܷ���(x,y)���ڵı�������
		 * @param x ������
		 * @param y ������
		 * @return �ܷ�ռ��
		 */
		public boolean nextCanTakeSide(int x, int y)
		{
			int size = of.size;
			System.out.println("call nextCanTakeSide()");
			int onSideNum = onSideNumber(x, y);
			int nextX = 1, nextY = 1;
			
			//����(x, y) ����
			putChess(x, y);
			
			//ɨ��(x, y)���ڵı�
			for(int off = 1; off <= size; off ++)
			{
				//����x,y���ڵıߵĲ�ͬ ɨ�費ͬ�ı�
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
				//��Ŀ����ϵ����е����һ���ж�
				int[][] chessmanMatrix = of.chessmanMatrix;
				if(putChess(nextX, nextY))
				{
					if( chessmanMatrix[x][y] == 1)
					{
						//������(x, y)������
						of.repent();
						of.disRepentStack.pop();
						//������(x,y)������,�ָ������ô˷���ǰ��״̬
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
			//Ŀ�����û��һ��������ö���ռ�ߣ�������(x,y)������,�ָ������ô˷���ǰ��״̬
			of.repent();
			of.disRepentStack.pop();
			System.out.println("nextCANNNNNOTTTAKESIDEEEEEEEEEEEEE");
			return false;
		}
		
		/**
		 * ��������
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
			//���ȿ��ĸ����ܲ�������
			for(int x = 1; x <= size; x += size - 1)
				for(int y = 1; y <= size; y += size - 1)
					if((eatNum = eat(x, y, (-1), true)) > maxEatNum)
					{
						maxEatX = x;
						maxEatY = y;
						maxEatNum = eatNum;
					}
			//��ĳ�����Ͽ������ӣ�����������Ϸ���
			if(maxEatNum > 0)
			{
				putChess(maxEatX, maxEatY);
				return ;
			}
		
			//���ı��ܲ�������
			int start = 1;
			//���¹���ͬ�����������
			offEnd = (int) ((size / 2 - start) * 2 + 1);
			for(int yOff = 0; yOff <= offEnd; yOff ++)
			{
				System.out.println("side  y=" + yOff );
				if(yOff > 0 && yOff < offEnd)
				{
					System.out.print(start+ offEnd + ",");
					System.out.println(start+yOff);
					//������ط���������
					if( (eatNum = eat(start + offEnd, start + yOff, (-1), true))  > maxEatNum)
					{
						//��������ܹ�ռ��˱�������������
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
					//������ط���������
					if( (eatNum = eat(start, start + yOff, (-1), true)) > maxEatNum)
					{
						//��������ܹ�ռ��˱�������������
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
						//������ط���������
						if((eatNum = eat(start + xOff, start + yOff, (-1), true)) > maxEatNum)
						{
							//��������ܹ�ռ��˱�������������
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
				
				//������ĳ����������
				if(maxEatNum > 0)
				{
					putChess(maxEatX, maxEatY);
					return;
				}
			}
		
			//���м������߿����ܷ����ӣ��������߽�ӽ���������
			for(start = size / 2; start > 1; start --)
			{
				//startΪ��ʼλ�ã� ���˴�Ҫ�����������ο�����Ͻ� offEndΪ�������ο�ı߳�
				offEnd =( size / 2 - start) * 2 + 1;
			
				//�����������������startһֱ������start + offEnd
				for(int yOff = 0; yOff <= offEnd; yOff ++)
				{
					//�������ε��м䲿�֣�����ֻ��������
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
				
					//�������ε����ˣ�����ȫ�����뿼��
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
				
				//������Բ��ڴ��������ӣ� �Ͳ��ڴ���������
				if(start == 3 && maxEatNum > 0)
				{
					putChess(maxEatX, maxEatY);
					return;
				}
				//�Դ�������������ڲ��ò��ڴ�����µ������ ������������
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