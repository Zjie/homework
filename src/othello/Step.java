/**
  * 工程名： Othello
  * 作者    ： without me
  * 描述    ：
  * 最后修改时间： 2006-11-29  下午05:14:38
  */
package othello;

/**
 * @author without me
 *
 */
public class Step
{
	public Step(int[][] chessmanMatrix, int[] score, boolean isBlackTurn, int[] lastChessCoord)
	{
		int len = chessmanMatrix.length;
		this.chessboardStatus = new int[ len ][ len ];
		for(int i = 0 ; i < len; i ++)
		{
			for(int j = 0 ; j < len; j ++)
			{
				chessboardStatus[i][j] = chessmanMatrix[i][j];
			}
		}
		this.score = new int[] {score[0], score[1]};
		this.isBlackTurn = isBlackTurn;
		this.lastChessCoord = new int[] {lastChessCoord[0], lastChessCoord[1]};
	}
	
	public int[][] getChessboardStatus()
	{
		return chessboardStatus;
	}
	public boolean isBlackTurn()
	{
		return isBlackTurn;
	}
	public int[] getScore()
	{
		return score;
	}
	public int[] getLastChessCoord()
	{
		return lastChessCoord;
	}
	
	private int[][] chessboardStatus;
	private int[] score;
	private boolean isBlackTurn;
	private int[] lastChessCoord;
}
