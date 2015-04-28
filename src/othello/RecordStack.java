package othello;

public class RecordStack
{

	/**
	 * Ĭ�Ͻ���100��С�Ķ�ջ
	 * 
	 */
	public RecordStack()
	{
		this(20);
	}

	/**
	 * ���캯��,����һ��size��С�Ķ�ջ
	 * 
	 * @param size
	 */
	public RecordStack(int size)
	{
		this.size = size;
		stepRecord = new Step[size];
		top = this.size - 1;
	}

	public void push(Step step)
	{
		push(step.getChessboardStatus(), step.isBlackTurn(), step.getScore()[0], step.getScore()[1], step.getLastChessCoord());
	}
	
	// ��һ��������Ԫ�ص��������ʽ����push
	public void push(int[][] preStatus, boolean isBlackTurn, int blackNum, int whiteNum, int[] lastChessCoord)
	{
		stepRecord[top] = new Step(preStatus,new int[] {blackNum, whiteNum} , isBlackTurn, lastChessCoord);
//		System.out.println("top=" + top);
		top--;
	}

	// �������һ�����������
	public Step pop()
	{
		++top;
//		System.out.println("top=" + top);
		return stepRecord[top];
	}

	// ����ջ�Ƿ�Ϊ��
	public boolean isEmpty()
	{
		if (top == size - 1)
			return true;
		return false;
	}

	// ����ջ�ÿ�
	public void setEmpty()
	{
		top = size - 1;
	}

	private Step[] stepRecord;
	private int size;
	private int top;
}