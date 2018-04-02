
import java.util.*;
class MaxHeap
{
    private int capacity;
    private int heapSize;
    private long heap[];

    public MaxHeap()
    {
        this.capacity=10;
        heap = new long[capacity];
        heapSize=0;
    }

    public MaxHeap(int capacity)
    {
        this.capacity=capacity;
        heap=new long [capacity];
        heapSize=0;
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
        while(hasParent(index) && parent(index)<heap[index])
        {
            swap(getParentIndex(index), index);
            index=getParentIndex(index);
        }
    }

    private void heapifyDown(int index)
    {
        int biggerChildIndex;
        while(hasLeftChild(index))
        {
            biggerChildIndex=getLeftChildIndex(index);
            if(hasRightChild(index) && rightChild(index)>heap[biggerChildIndex])
                biggerChildIndex=getRightChildIndex(index);
            if(heap[biggerChildIndex]<heap[index])
            {
                swap(biggerChildIndex,index);
                index=biggerChildIndex;
            }
            else
                break;
        }

    }

    public long getMaximum()
    {
        if(heapSize==0)
            throw new IllegalStateException("Heap is Empty");
        else
            return heap[0];
    }

    public long extractMaximum()
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
        heap[index]=Long.MAX_VALUE;
        heapifyUp(index);
        extractMaximum();

    }

    public void deleteValue(long value)
    {
        if(heapSize==0)
            throw new IllegalStateException("Heap is empty");
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

    public boolean isEmpty()
    {
        return heapSize==0;
    }
    private static void heapify(int arr[], int n, int i)  
		{
			if(i>=n-1)
				return;
			else
				{
					int left=i*2+1;
					int right=i*2+2;
					int maxi=i;
					if(left<n && arr[maxi]<arr[left])
						maxi=left;
					if(right<n && arr[maxi]<arr[right])
						maxi=right;
					if(maxi!=i)
						{
							swap(arr,i,maxi);
						heapify(arr,n,maxi);
						}
					return;
				}
		}
	public static void heapSort(int arr[], int n) 
		{
			buildHeap(arr, n);
			for (int i=n-1; i>=0; i--)  
				{
					swap(arr,0, i);
					heapify(arr, i, 0);
				}
		} 
	private static void swap(int arr[],int i,int j)
		{
			int temp=arr[i];
			arr[i]=arr[j];
			arr[j]=temp;
		}
	// Rearranges input array so that it becomes a max heap
	private static void buildHeap(int arr[], int n)  
		{
			for(int i=n;i>=0;i++)
				heapify(arr,n,i);
			
		}
	
	public static boolean isMaxHeap(int arr[])
		{
			return isMaxHeap(arr, 0);
		}
	
	private static boolean isMaxHeap(int arr[],int index)
		{
			if(index>=arr.length)
				return true;
			int leftIndex,rightIndex;
			leftIndex=index*2+1;
			rightIndex=index*2+2;
			if(leftIndex>=arr.length)
				return true;
			else if(arr[leftIndex]>arr[index])
				return false;
			else if(rightIndex < arr.length && arr[rightIndex]>arr[index] )
				return false;
			else
				return isMaxHeap(arr,leftIndex) && isMaxHeap(arr, rightIndex);
			
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
