import java.util.Arrays;

class PalindromicTree 
{
	private final char START='a';
	private char[] word;
	private String wordS;
	private int size;
	private Node[] tree;
	private int currentNode;
	private int pointer;
	private static class Node
	{

		private final int NUMBER=26;
		private int start,end;
		private int length;
		private int insertionEdge[]=new int[NUMBER];
		private int maximumPalindromicSuffixEdge;
		public Node(int start,int end)
		{
			this.setStart(start);
			this.setEnd(end);
			this.setLength();
		}
		public void setStart(int start)
		{
			this.start=start;
		}
		public void setEnd(int end)
		{
			this.end=end;
		}
		public void setLength()
		{
			this.length=end-start+1;
		}
		public void setMaximumPalindromicSuffixEdge(int index)
		{
			this.maximumPalindromicSuffixEdge=index;
		}
		public int getValueOfInsertionEdgeAtIndex(int index) {
			// TODO Auto-generated method stub
			return insertionEdge[index];
		}
		public void setValueOfInsertionEdgeAtIndex(int index, int pointer) {
			// TODO Auto-generated method stub
			insertionEdge[index]=pointer;

		}
		public int getMaximumPalindromicSuffixEdge() {
			// TODO Auto-generated method stub
			return maximumPalindromicSuffixEdge;
		}
		public int getLength() {
			// TODO Auto-generated method stub
			return length;
		}
		public int getStart() {
			// TODO Auto-generated method stub
			return start;
		}
		public int getEnd() {
			// TODO Auto-generated method stub
			return end;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Insertion Edge: "+Arrays.toString(insertionEdge)+" ,MaximumPailndromicSuffixEdge: "+maximumPalindromicSuffixEdge;
		}
	}

	public PalindromicTree(String word)
	{
		this(word.toCharArray());
	}


	public  PalindromicTree(char[] word)
	{
		this.wordS=String.valueOf(word);
		this.word=word;
		size=word.length;
		tree=new Node[size+2+1];//2 dummy nodes, 1 indexed base , n possible palindrome
		
		tree[1]=new Node(0,-2);
		tree[2]=new Node(0,-1);
		tree[1].setMaximumPalindromicSuffixEdge(1);
		tree[2].setMaximumPalindromicSuffixEdge(1);
		currentNode=1;
		pointer=2;
		initialize();

	}
	private void initialize()
	{
		for(int index=0;index<size;index++)
			insert(index);
	}
	private boolean wasInsertionEdgeAlreadySet(int temp,int index)
	{
		if(tree[temp].getValueOfInsertionEdgeAtIndex(word[index]-START)!=0)
		{
			currentNode=tree[temp].getValueOfInsertionEdgeAtIndex(word[index]-START);
			return true;
		}

		pointer++;
		tree[temp].setValueOfInsertionEdgeAtIndex(word[index]-START,pointer);
		tree[pointer]=new Node(index-tree[temp].length-1,index);
		currentNode=pointer;
		return false;

	}

	private void setSuffixEdge(int temp,int index)
	{
		temp=tree[temp].getMaximumPalindromicSuffixEdge();

		//set suffix edge
		if(tree[currentNode].getLength()==1)
		{
			tree[currentNode].setMaximumPalindromicSuffixEdge(2);
			return;
		}
		temp=getPalindrome(temp,index);
		tree[currentNode].setMaximumPalindromicSuffixEdge(tree[temp].getValueOfInsertionEdgeAtIndex(word[index]-START));
		return;

	}
	private void insert(int index)
	{

		int temp=getPalindrome(currentNode,index);
		if(wasInsertionEdgeAlreadySet(temp, index))
			return;
		setSuffixEdge(temp, index);
	}
	private int getPalindrome(int root,int index)
	{
		int temp=root;
		int currentLength;
		while(true)
		{
			currentLength=tree[temp].length;
			if(index-currentLength>=1 && word[index]==word[index-currentLength-1])
				return temp;
			temp=tree[temp].getMaximumPalindromicSuffixEdge();
		}
	}


	private String toString(int index) {
		// TODO Auto-generated method stub
		int start=tree[3+index].getStart();
		int end=tree[3+index].getEnd();
		return wordS.substring(start, end+1);

	}
	public String[] getAllPalindrome()
	{
		String palindrome[]=new String[size];
		for(int i=0;i<size;i++)
			palindrome[i]=toString(i);
		return palindrome;
	}
}
