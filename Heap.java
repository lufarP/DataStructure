package DataStructure;

import java.util.Arrays;
import java.util.Comparator;

class Heap<T>
	{
		private int capacity;
		private int heapSize;
		private T heap[];
		private Comparator<T> comparisonCriteria;
		
		@SuppressWarnings("unchecked")
		public Heap(Comparator<T> comparisonCriteria)
			{
				this.capacity=10;
				heap = (T[]) new Object[capacity];
				heapSize=0;
				this.comparisonCriteria=comparisonCriteria;
				
			}
		
		@SuppressWarnings("unchecked")
		public Heap(int capacity,Comparator<T> comparisonCriteria)
			{
				this.capacity=capacity;
				heap=(T[]) new Object [capacity];
				heapSize=0;
				this.comparisonCriteria=comparisonCriteria;
			}
		
		
		public boolean  setComparator(Comparator<T> comparisonCriteria)
			{
				if(heapSize>1)
					return false;
				this.comparisonCriteria=comparisonCriteria;
					return true;
			}
		
		public int getHeapSize()
			{
				return heapSize;
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
		private T getLeftChild(int index)
			{
				return heap[getLeftChildIndex(index)];
			}
		
		private T getRightChild(int index)
			{
				return heap[getRightChildIndex(index)];
			}
		
		private T getParent(int index)
			{
				return heap[getParentIndex(index)];
			}
		
		private void swap(int index1,int index2)
			{
				T temp=heap[index1];
				heap[index1]=heap[index2];
				heap[index2]=temp;
			}
		
		@SuppressWarnings("unused")
		private void heapify(int index)
			{
				int swapIndex=index;
				if(hasLeftChild(index))
					{
						heapify(getLeftChildIndex(index));
						swapIndex=swapCiteria(index,getLeftChildIndex(index),comparisonCriteria);
					}
				if(hasRightChild(index))
					{
						heapify(getRightChildIndex(index));
						swapIndex=swapCiteria(swapIndex, getRightChildIndex(index), comparisonCriteria);
					}
				if(swapIndex!=index)
					{
						swap(index,swapIndex);
						heapify(swapIndex);
					}
				
			}
		
		private void ensureEnoughSpace()
			{
				if(heapSize==capacity)
					{
						capacity= capacity<<1;
						heap=Arrays.copyOf(heap,capacity);
					}
			}
		
		private void heapifyUp(int index)
			{
				while(hasParent(index) && comparisonCriteria.compare(heap[index],getParent(index)) <=0)
					{
						swap(getParentIndex(index),index);
						index=getParentIndex(index);
					}
			}
		
		private int swapCiteria(int index1, int index2, Comparator<T> comparisonCriteria)
			{
				int value=comparisonCriteria.compare(heap[index1], heap[index2]);
				if(value==-1)
					return index2;
				else
					return index1;
				
			}
		
		public void add(T item)
			{
				ensureEnoughSpace();
				heap[heapSize]=item;
				heapSize++;
				heapifyUp(heapSize-1);
			}
		
		public T peek()
			{

				if(heapSize==0)
					throw new IllegalStateException("Heap is Empty");
				return  heap[0];
			}
		
		public T poll()
			{
				if(heapSize==0)
					throw new IllegalStateException("Heap is Empty");
				T item=heap[0];
				swap(0, heapSize-1);
				// heap[heapSize--]=null;
				heapSize--;
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
						if(hasRightChild(index) && comparisonCriteria.compare(getRightChild(index),heap[swapIndex])<0)
							swapIndex=getRightChildIndex(index);
						if(comparisonCriteria.compare(heap[swapIndex],heap[index])<0)
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
