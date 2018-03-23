
import java.util.Arrays;

abstract class SegmentTree 
{
	private int tree[];
	private int arr[];
	private final int height;
	private final int size;
	private final int element;

	SegmentTree(int arr[])
	{
		element=arr.length;
		
		this.arr=Arrays.copyOf(arr, element);
		
		height=(int)(Math.ceil(Math.log(element)/Math.log(2)));
		
		size=2*(int)Math.pow(height, 2);
		
		tree=new int[size];
		
		buildSegmentTree(0,element-1,1);
	}
	private void update(int index,int start,int end,int queryStart,int queryEnd,int value)
	{
		int overlapValue=overLap(start, end, queryStart, queryEnd);
		switch(overlapValue)
		{
		case 0:
			break;
		case 1:
			tree[index]=value;
			break;
		case 2:
			update(index*2, start, (start+end)/2, queryStart, queryEnd,value);
			update(index*2+1, (start+end)/2+1,end, queryStart, queryEnd,value);
			tree[index]= process(tree[index*2],tree[index*2+1]);
			break;
		}
	}
	public void update(int index,int value)
	{
		arr[index]=value;
		update(1, 0, element-1, index, index, value);
	}

	private void buildSegmentTree(int start, int end, int index)
	{
		if(start>end||index>=size)
			return;
		else if(start==end)
		{
			tree[index]=arr[start];
		}
		else
		{
			int mid=(start+end)/2;
			buildSegmentTree( start, mid, index*2);
			buildSegmentTree( mid+1, end, index*2+1);
			tree[index]=process(tree[index*2],tree[index*2+1]);
		}
	}
	private int overLap(int start,int end,int queryStart,int queryEnd)
	{
		if(end<queryStart ||start>queryEnd)//no overlap
			return 0;
		else if(queryStart<=start && end<=queryEnd )//full overlap
			return 1;
		else
			return 2;//partial overlap
	}
	/*
	 * define this method 
	 * as a value which should pass on when there is no overlapping
	 * eg. return 0 when sum range
	 * return infinity when min range
	 * return -infinity when max range
	 *  ;)
	 */
	public abstract int joker();

	private int getResult(int index,int start,int end,int queryStart,int queryEnd)
	{
		int overlapValue=overLap(start, end, queryStart, queryEnd);
		switch(overlapValue)
		{
		case 0:
			return joker();
		case 1:
			return tree[index];
		case 2:
			int left=getResult(index*2, start, (start+end)/2, queryStart, queryEnd);
			int right=getResult(index*2+1, (start+end)/2+1,end, queryStart, queryEnd);
			return process(left,right);
		}
		return 0;
	}

	public int getResult(int queryStart,int queryEnd)
	{
		return getResult(1, 0, element-1, queryStart, queryEnd);
	}
	/* define this method
	 * as the relation between two child of the root node

	 */

	public abstract int process(int left, int right);

	@Override
	public String toString() 
	{
		return Arrays.toString(tree);
	}



}
