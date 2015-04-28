package othello;

public class RecordStack
{

	/**
	 * 默认建立100大小的堆栈
	 * 
	 */
	public RecordStack()
	{
		this(20);
	}

	/**
	 * 构造函数,建立一个size大小的堆栈
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
	
	// 以一个含三个元素的数组的形式调用push
	public void push(int[][] preStatus, boolean isBlackTurn, int blackNum, int whiteNum, int[] lastChessCoord)
	{
		stepRecord[top] = new Step(preStatus,new int[] {blackNum, whiteNum} , isBlackTurn, lastChessCoord);
//		System.out.println("top=" + top);
		top--;
	}

	// 弹出最近一步的下棋情况
	public Step pop()
	{
		++top;
//		System.out.println("top=" + top);
		return stepRecord[top];
	}

	// 检查堆栈是否为空
	public boolean isEmpty()
	{
		if (top == size - 1)
			return true;
		return false;
	}

	// 将堆栈置空
	public void setEmpty()
	{
		top = size - 1;
	}

	private Step[] stepRecord;
	private int size;
	private int top;
}