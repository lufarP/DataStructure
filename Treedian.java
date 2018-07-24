import java.util.PriorityQueue;

class Treedian 
{
		private  PriorityQueue<Integer> lower,higher;
		private int size;
		private double median;
		public Treedian()
		{
			  lower = new PriorityQueue<Integer>(size, (a, b) ->
                {
                    return -1 * (a.compareTo(b));
                });
             higher = new PriorityQueue<Integer>(size);
             
		}
		
		public int getSize()
		{
			return size;
		}
		public boolean isEmpty()
		{
			return size==0;
		}
	  public  void add(int number)
      {
          if(lower.size()==0 || number < lower.peek())
              lower.add(number);
          else
              higher.add(number);
          rebalance();
          size++;
          median=findMedian();
      }
	  
	  public boolean removeMedian() throws Exception
	  {
		  return remove((int)median);
	  }
  
	  private double findMedian()
      {
          if (lower.size() == higher.size())
              return (lower.peek() + higher.peek()+0.0) / 2;
          else
              return lower.peek();
      }
	  public double getMedian()throws Exception
	  {
		  if(size==0)
			  throw new Exception("NO DATA  ELEMENT TO FIND MEDIAN");
		  return median;
	  }
  
	  private  void rebalance()
      {
          if(lower.size()==higher.size())
              return ;
          else if(lower.size()-higher.size()>1)
              {
                  higher.add(lower.remove());
              }
          else if(higher.size()-lower.size()>0)
              {
                  lower.add(higher.remove());
              }    
      }
	  
	  public boolean remove(int number)throws Exception
	  {
		  if(size==0)
			  throw new Exception("NO DATA  ELEMENT TO REMOVE");
		  boolean removed=false;
	      if(number<=median)
	          removed=lower.remove(number);
	      else
	          removed=higher.remove(number);
	      if(!removed)
	    	  return false;
	      rebalance();
	      size--;
	      median=findMedian();
	      return true;
	  }
	  
}
