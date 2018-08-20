import java.util.ArrayList;

public class MergeSortTree<V extends Comparable<V>> extends SegmentTree<ArrayList<V>, V>
{

	MergeSortTree(V[] arr) {
		super(arr);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<V>  leaf(int index) {
		ArrayList<V> a=new ArrayList<V>();
		a.add(getValue(index));
		return a;
	}
	
	public ArrayList<V> process(ArrayList <V> left, ArrayList <V> right) 
	{
		ArrayList <V> merged=new ArrayList<V>();
		int l,r;
		l=0;r=0;
		while(l<left.size() && r<right.size())
		{
			merged.add(left.get(l).compareTo(right.get(r))<0?left.get(l++):right.get(r++));
		}
		while(r<right.size())
			merged.add(right.get(r++));
		while(l<left.size())
			merged.add(left.get(l++));
		return merged;
	}

	@Override
	public V updateAtLeaf(int index, V x) 
	{
		return x;
	}
}
