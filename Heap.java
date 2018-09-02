package DataStructure;


import java.util.ArrayList;

class Heap<T extends Comparable<T>>
	{
		private int heapSize;
		private ArrayList<T> heap;
		
		public Heap()
			{
				heap= new ArrayList<T>();
				heapSize=0;
			}
		
		public Heap(int capacity)
			{
				heap= new ArrayList<T>(capacity);
				heapSize=0;
			}
		
		public int getHeapSize()
			{
				return heap.size();
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
		private T getLeftChild(int index)
			{
				return heap.get(getLeftChildIndex(index));
			}
		
		private T getRightChild(int index)
			{
				return heap.get(getRightChildIndex(index));
			}
		
		private T getParent(int index)
			{
				return heap.get(getParentIndex(index));
			}
		
		private void swap(int index1,int index2)
			{
				T temp=heap.get(index1);
				heap.set(index1,heap.get(index2));
				heap.set(index2,temp);
			}
		
		private void heapify(int index)
			{
				int swapIndex=index;
				if(hasLeftChild(index))
					{
						heapify(getLeftChildIndex(index));
						swapIndex=swapCiteria(index,getLeftChildIndex(index));
					}
				if(hasRightChild(index))
					{
						heapify(getRightChildIndex(index));
						swapIndex=swapCiteria(swapIndex, getRightChildIndex(index));
					}
				if(swapIndex!=index)
					{
						swap(index,swapIndex);
						heapify(swapIndex);
					}
				
			}	
		private void heapifyUp(int index)
			{
				while(hasParent(index) && heap.get(index).compareTo(getParent(index)) <=0)
					{
						swap(getParentIndex(index),index);
						index=getParentIndex(index);
					}
			}
		
		private int swapCiteria(int index1, int index2)
			{
				int value=heap.get(index1).compareTo( heap.get(index2));
				if(value==-1)
					return index2;
				else
					return index1;
				
			}
		
		public void add(T item)
			{
			
				heap.add(item);
				heapifyUp(heap.size()-1);
			}
		
		public T peek()
			{

				if(heap.size()==0)
					throw new IllegalStateException("Heap is Empty");
				return  heap.get(0);
			}
		
		public T poll()
			{
				if(heap.size()==0)
					throw new IllegalStateException("Heap is Empty");
				T item=heap.get(0);
				swap(0, heap.size()-1);
				heapifyDown(0);
				return item;
				
			}
		
		private void heapifyDown(int index)
			{
				
				// TODO Auto-generated method stub
				int swapIndex;
				index=0;
				while(hasLeftChild(index))
					{
						swapIndex=getLeftChildIndex(index);
						if(hasRightChild(index) && getRightChild(index).compareTo(heap.get(swapIndex))<0)
							swapIndex=getRightChildIndex(index);
						if(heap.get(swapIndex).compareTo(heap.get(index))<0)
							{
								swap(swapIndex,index);
								index=swapIndex;
							}
						else
							break;
					}
			}
		public String toString()
			{
				return heap.toString();
			}
		
	}	
