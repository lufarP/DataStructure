package DataStructure;

import java.util.Arrays;

class DynamicArray<T>
	{
		private T array[];
		private int capacity;
		private int size;
		@SuppressWarnings("unchecked")
		public DynamicArray()
			{
				// TODO Auto-generated constructor stub
				capacity=10;
				setArray((T[])new Object[capacity]);
				setSize(0);
			}
		private void setSize(int i)
			{
				// TODO Auto-generated method stub
				size=i;
			}
		private void setArray(T[] ts)
			{
				// TODO Auto-generated method stub
				array=ts;
				
			}
		@SuppressWarnings("unchecked")
		public DynamicArray(int capacity)
			{
				this.capacity=capacity;
				setArray((T[])new Object[capacity]);
				setSize(0);
			}
		public int getSize()
			{
					return size;
			}
		public T get(int index)
			{
				if(index>=size)
					throw new IllegalStateException("Index Illegal");
				else
					return array[index];
			}
		private void ensureExtraCapacity()
		{
			if(size==capacity)
				{
					capacity<<=1;
					setArray(Arrays.copyOf(array, capacity));
				}
		}
		public void add(T value)
		{
			ensureExtraCapacity();
			array[size++]=value;
		}
		public boolean contains(T value)
		{
			for(int i=0;i<size;i++)
				{
					if(array[i].equals(value))
						return true;
				}
			return false;
		}
		public T[] getArray()
			{	
					return Arrays.copyOf(array, size);
			}
		public String toString()
		{
			if(size==0)
				return "[]";
			else
				{
					StringBuilder buffer=new StringBuilder();
					buffer.append("[");
					for(int i=0;i<size-1;i++)
						buffer.append(array[i].toString()+", ");
					buffer.append(array[size-1].toString()+" ]");
					return buffer.toString();
				}
		}
	}
