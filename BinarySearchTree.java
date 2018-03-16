import java.util.Comparator;

class BinarySearchTree<T> 
{
    private BSTNode root;
    private Comparator <T>key;
    private class BSTNode
    {
        T data;
        BSTNode left,right;
        long height;
        BSTNode(T data)
        {
            this.data=data;
            left=null;
            right=null;
        }
    }
    public BinarySearchTree(Comparator<T> key)
    {
        this.key=key;
        root=null;
    }

    public boolean insert(T data)
    {
        BSTNode node=search(root,data);
        if(node!=null && node.data.equals(data))//element already present
            return false;
        else
        {
            BSTNode newNode=new BSTNode(data);
            if(node==null)
                root=newNode;
            else
            {
                int insertKey=key.compare(node.data, data);
                if(insertKey>1)
                {
                    node.left=newNode;
                }
                else
                    node.right=newNode;

            }
            return true;

        }
        //	root=insert(root,new BSTNode(data));
    }

    public boolean search(T data)
    {
        BSTNode node=search(root,data);
        if(node==null)//bst empty
            return false;
        else if(node.data.equals(data))//element found
            return true;
        else
            return false;//element not present
    }

    private BSTNode search(BSTNode root,T data)
    {
        if(root==null)
            return null;
        int searchKey=key.compare(root.data, data);
        if(searchKey==0)
            return root;
        else if(searchKey>1)
        {

            System.out.println("left");
            if(root.left==null)
                return root;
            else
                return search(root.left,data);
        }
        else
        {

            System.out.println("right");
            if(root.right==null)
                return root;
            else
                return search(root.right,data);
        }
    }

    boolean remove(T data)
    {
        //yet to complete
        return false;
    }

    public void preOrder()
    {

        System.out.println("PreOrder");
        preOrder(root);
    }

    private void preOrder(BinarySearchTree<T>.BSTNode root2)
    {
        if(root2==null)
            return;
        System.out.print(root2.data.toString()+" ");
        preOrder(root2.left);
        preOrder(root2.right);

    }

    public void inOrder()
    {

        System.out.println("inOrder");
        inOrder(root);
    }

    private void inOrder(BinarySearchTree<T>.BSTNode root2)
    {
        // TODO Auto-generated method stub
        if(root2==null)
            return;
        inOrder(root2.left);
        System.out.print(root2.data.toString()+" ");
        inOrder(root2.right);
    }

    public void postOrder()
    {

        System.out.println("PostOrder");
        postOrder(root);
    }

    private void postOrder(BinarySearchTree<T>.BSTNode root2)
    {
        // TODO Auto-generated method stub
        if(root2==null)
            return;
        postOrder(root2.left);
        postOrder(root2.right);
        System.out.print(root2.data.toString()+" ");

    }
}
