

import java.util.Arrays;

abstract class SegmentTree <T,V>
{
	private T tree[];
	private V arr[];
	private final int height;
	private final int size;
	private final int element;

	@SuppressWarnings("unchecked")
	SegmentTree(V arr[])
	{
		element=arr.length;
		
		this.arr=Arrays.copyOf(arr, element);
		
		height=(int)(Math.ceil(Math.log(element)/Math.log(2)));
		
		size=2*(int)Math.pow(2, height)-1;
		
		tree=(T[])new Object[size];
		
		buildSegmentTree(0,element-1,0);
	}
	private void update(int index,int start,int end,int queryStart,int queryEnd)
	{
		int overlapValue=overLap(start, end, queryStart, queryEnd);

		int mid=start+(end-start)/2;
		switch(overlapValue)
		{
		case 0:
			break;
		case 1:
			tree[index]=leaf(arr[start]);
			break;
		case 2:

			update(index*2+1, start, mid, queryStart, queryEnd);
			update(index*2+1+1, mid+1,end, queryStart, queryEnd);
			tree[index]= process(tree[index*2+1],tree[index*2+1+1]);
			break;
		}
	}
	public void update(int index,V value)
	{
		arr[index]=value;
		update(0, 0, element-1, index, index);
	}

	private void buildSegmentTree(int start, int end, int index)
	{
		if(start>end||index>=size)
			return;
		else if(start==end)
		{
			tree[index]=leaf(arr[start]);
		}
		else 
		{
			int mid=start+(end-start)/2;
			buildSegmentTree( start, mid, index*2+1);
			buildSegmentTree( mid+1, end, index*2+1+1);
			tree[index]=process(tree[index*2+1],tree[index*2+1+1]);
		}
	}
	public abstract T leaf(V v) ;
	
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
				return getResult(index*2+1, start, mid, queryStart, queryEnd);
		case 3:
				return getResult(index*2+1+1, mid+1,end, queryStart, queryEnd);
		case 4:
			T left=getResult(index*2+1, start, mid, queryStart, queryEnd);
			T right=getResult(index*2+1+1, mid+1,end, queryStart, queryEnd);
			return process(left,right);
		}
		return null;
	}

	private int getMid(int start, int end) {
		// TODO Auto-generated method stub
		return (start+end)/2;
	}
	public T getResult(int queryStart,int queryEnd)
	{
		return getResult(0, 0, element-1, queryStart, queryEnd);
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
