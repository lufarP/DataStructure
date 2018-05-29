import java.util.Comparator;

class BinarySearchTree<T> 
	{
		private BSTNode root;
		private Comparator <T>key;
		private class BSTNode
		{
			T data;
			BSTNode left,right;
			@SuppressWarnings("unused")
			int level;
			BSTNode(T data)
			{
				this.data=data;
				left=null;
				right=null;
				level=1;
			}
			public BSTNode getLeft()
				{
					return left;
				}
			public BSTNode getRight()
				{
					return right;
				}
			public int getLevel()
				{
					return level;
				}
			private void copy(BSTNode inorderPrecessor)
				{
					// TODO Auto-generated method stub
					
				}
		
		}
		public BinarySearchTree(Comparator<T> key)
			{
				this.key=key;
				root=null;
			}
		public void insert(T data)
			{
				root=insert(root,data);
			}
		private BSTNode insert(BSTNode root,T data)
		{
			if(root==null)
				return new BSTNode(data);
			else
				{
					if(key.compare(root.data, data)<0)
						root.left=insert(root.left,data);
					else
						root.right=insert(root.right,data);
					return root;
				}
		}
		
		public int getLevel(T data)
			{
				return search(root, data).getLevel();
			}
		
		public BSTNode search(T data)
		{
			return search(root,data);
		}
		private BSTNode search(BSTNode root,T data)
		{
			int result=key.compare(root.data, data);
			if(root==null||result==0)
				return root;
			else if(result<0)
				return search(root.left,data);
			else
				return search(root.right,data);
		}
		public void remove(T data)
		{
			remove(root,data);
		}
		private BSTNode maxNode(BSTNode node)
		{
			while(node.right!=null)
				node=node.right;
			return node;
		}
		private BSTNode remove(BSTNode root,T data)
			{
				if(root==null)
					return root;
				int result=key.compare(root.data, data);
				if(result<0)
					{
						root.left=remove(root.left, data);
					}
				else if(result>0)
					{
						root.right=remove(root.right, data);
					}
				else
					{
						if(root.left==null)
							{
								return root.right;
							}
						else if(root.right==null)
							{
								return root.left;
							}
						else
							{
								BSTNode inorderPrecessor=maxNode(root.left);
								root.copy(inorderPrecessor);
								root.left=remove(root.left, inorderPrecessor.data);
							}
					}
				return root;
			}
	
		}
