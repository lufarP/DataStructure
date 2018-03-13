package DataStructure;



import java.util.*;

class MinHeap
{
	private int capacity;
	private int heapSize;
	private long heap[];

	public MinHeap()
	{
		this.capacity=10;
		heap = new long[capacity];
		heapSize=0;
	}

	public MinHeap(int capacity)
	{
		this.capacity=capacity;
		heap=new long [capacity];
		heapSize=0;
	}

	public int getHeapSize()
	{
		return heapSize;
	}
	
	
  	  public boolean isEmpty()
	{
		return heapSize==0;
	}


	private int getLeftChildIndex(int parentIndex)
	{
		return 2*parentIndex+1;
	}
	private int getRightChildIndex(int parentIndex)
	{
		return 2*parentIndex+2;
	}
	private int getParentIndex(int childIndex)
	{
		return (childIndex-1)/2;
	}

	private boolean hasLeftChild(int parentIndex)
	{
		if(getLeftChildIndex(parentIndex)<heapSize)
			return true;
		else
			return false;
	}
	private boolean hasRightChild(int parentIndex)
	{
		if(getRightChildIndex(parentIndex)<heapSize)
			return true;
		else 
			return false;
	}
	private boolean hasParent(int childIndex)
	{
		if(childIndex>0)
			return true;
		else 
			return false;
	}

	@SuppressWarnings("unused")
	private long leftChild(int index)
	{
		return heap[getLeftChildIndex(index)];
	}
	private long rightChild(int index)
	{
		return heap[getRightChildIndex(index)];
	}
	private long parent(int index)
	{
		return heap[getParentIndex(index)];
	}

	private void swap(int index1,int index2)
	{
		long temp=heap[index1];
		heap[index1]=heap[index2];
		heap[index2]=temp;
	}
	private void ensureExtraCapacity()
	{
		if(heapSize==capacity)
		{
			capacity=capacity<<1;
			heap=Arrays.copyOf(heap, capacity);

		}
	}

	private void heapifyUp(int index)
	{
		while(hasParent(index) && parent(index)>heap[index])
		{
			swap(getParentIndex(index), index);
			index=getParentIndex(index);
		}
	}

	private void heapifyDown(int index)
	{
		int smallerChildIndex;
		while(hasLeftChild(index))
		{
			smallerChildIndex=getLeftChildIndex(index);
			if(hasRightChild(index) && rightChild(index)<heap[smallerChildIndex])
				smallerChildIndex=getRightChildIndex(index);
			if(heap[smallerChildIndex]<heap[index])
			{
				swap(smallerChildIndex,index);
				index=smallerChildIndex;
			}
			else
				break;
		}

	}


	public long getMinimum()
	{
		if(heapSize==0)
			throw new IllegalStateException("Heap is Empty");
		else
			return heap[0];
	}
	public long extractMinimum()
	{
		if(heapSize==0)
			throw new IllegalStateException("Heap is Empty");
		else
		{
			long item=heap[0];
			swap(0,heapSize-1);
			//heap[heapSize-1]=0;
			heapSize--;
			heapifyDown(0);
			return item;
		}
	}
	public void addValue(long value)
	{
		ensureExtraCapacity();
		heap[heapSize]=value;
		heapSize++;
		heapifyUp(heapSize-1);
	}
	private void deleteKey(int index)
	{
		if(index>=heapSize)
			throw  new IllegalStateException("Index not found");
		heap[index]=Long.MIN_VALUE;
		heapifyUp(heapSize-1);
		extractMinimum();

	}
	public void deleteValue(long value)
	{
		int index;
		for( index=0;index<heapSize;index++)
		{
			if(heap[index]==value)
			{
				deleteKey(index);
				return;
			}
		}
		throw new IllegalStateException("Element not in Heap ");
	}
	public String toString()
	{
		if(heapSize==0)
		return "[]";
		StringBuilder string=new StringBuilder("[");
		for(int index=0;index<heapSize-1;index++)
		{
			string.append(heap[index]+", ");
		}
		string.append(heap[heapSize-1]+"]");
		return string.toString();
	}
}
