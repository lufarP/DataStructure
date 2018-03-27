

import java.util.Arrays;

abstract class SegmentTree < T , V >
{
	private T tree[];
	private V arr[];
	public final int HEIGHT;
	public final int SIZE;
	public final int ELEMENT;

	@SuppressWarnings("unchecked")
	SegmentTree(V arr[])
	{
		ELEMENT=arr.length;

		this.arr=Arrays.copyOf(arr, ELEMENT);

		HEIGHT=(int)(Math.ceil(Math.log(ELEMENT)/Math.log(2)));

		SIZE=2*(int)Math.pow(2, HEIGHT)-1;

		tree=(T[])new Object[SIZE];

		buildSegmentTree(0,ELEMENT-1,0);
	}

	public V getValue(int index)
	{
		return arr[index];
	}
	private void update(int index,int start,int end,int queryStart,int queryEnd)
	{
		int overlapValue=overLap(start, end, queryStart, queryEnd);

		int mid=getMid(start, end);
		switch(overlapValue)
		{
		case 0:
			break;
		case 1:
			tree[index]=leaf(start);
			break;
		case 2:

			update(getLeftIndex(index), start, mid, queryStart, queryEnd);
			update(getRightIndex(index), mid+1,end, queryStart, queryEnd);
			tree[index]= process(tree[getLeftIndex(index)],tree[getRightIndex(index)]);
			break;
		}
	}
		
	public void update(int queryStart,int queryEnd,V x)
	{
		for(int index=queryStart;index<=queryEnd;index++)
				arr[index]=updateAtLeaf(index,x);
		update(0,0,ELEMENT-1,queryStart,queryEnd);
	}

	public abstract V updateAtLeaf(int index, V x);
	private int getLeftIndex(int index)
	{
		return index*2+1;
	}
	private int getRightIndex(int index)
	{
		return index*2+2;
	}
	public void update(int index,V value)
	{
		arr[index]=value;
		update(0, 0,ELEMENT-1, index, index);
	}

	private void buildSegmentTree(int start, int end, int index)
	{
		if(start>end||index>=SIZE)
			return;
		else if(start==end)
		{
			tree[index]=leaf(start);
		}
		else 
		{
			int mid=getMid(start, end);
			buildSegmentTree( start, mid, getLeftIndex(index));
			buildSegmentTree( mid+1, end, getRightIndex(index));
			tree[index]=process(tree[getLeftIndex(index)],tree[getRightIndex(index)]);
		}
	}
	public abstract T leaf(int index) ;

	private int overLap(int start,int end,int queryStart,int queryEnd)
	{
		int mid=getMid(start,end);
		if(queryStart<=start && end<=queryEnd )//full overlap
			return 1;
		else if(mid>=queryEnd)
			return 2;
		else if(mid < queryStart)
			return 3;
		else 
			return 4;
	}
	/*
	 * define this method 
	 * as a value which should pass on when there is no overlapping
	 * eg. return 0 when sum range
	 * return infinity when min range
	 * return -infinity when max range
	 *  ;)
	 */

	private T getResult(int index,int start,int end,int queryStart,int queryEnd)
	{
		int overlapValue=overLap(start, end, queryStart, queryEnd);

		int mid=getMid(start,end);
		switch(overlapValue)
		{
		case 1:
			return tree[index];
		case 2:
			return getResult(getLeftIndex(index), start, mid, queryStart, queryEnd);
		case 3:
			return getResult(getRightIndex(index), mid+1,end, queryStart, queryEnd);
		case 4:
			T left=getResult(getLeftIndex(index), start, mid, queryStart, queryEnd);
			T right=getResult(getRightIndex(index), mid+1,end, queryStart, queryEnd);
			return process(left,right);
		}
		return null;
	}

	private int getMid(int start, int end)
	{
		// TODO Auto-generated method stub
		return (start+end)/2;
	}
	public T getResult(int queryStart,int queryEnd)
	{
		return getResult(0, 0, ELEMENT-1, queryStart, queryEnd);
	}
	/* define this method
	 * as the relation between two child of the root node

	 */

	public abstract T process(T left, T right);

	@Override
	public String toString() 
	{
		return Arrays.toString(tree);
	}

}
