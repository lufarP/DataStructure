
import java.util.*;
class  DisjointSet<T>
{
    private HashMap<T,Node> map=new HashMap<T,Node>();
    private HashSet<T> collection=new HashSet<T>();

    private class Node
    {
        int rank;
        T data;
        int size;
        Node parent;
        Node(T a)
        {
            rank=0;
            data=a;
            parent=this;
            size=1;
        }

    }
     public boolean isASet(T data)
    {
        return map.containsKey(data);
    }
    public int numberOfSets()
    {
        return collection.size();
    }
    public boolean isGroupRepresentative(T data)
    {
        if(map.containsKey(data))
        {
            Node node=map.get(data);
            return collection.contains(node.data);
        }
        return false;
    }

    public Iterator<T> getCollection()
    {
        return collection.iterator();
    }

    public boolean union(T data1,T data2)
    {
        Node node1=map.get(data1);
        Node node2=map.get(data2);
        Node parent1=findSet(node1);
        Node parent2=findSet(node2);
        if(parent1.data.equals(parent2.data))
            return false;
        if(parent1.rank>=parent2.rank)
        {
            parent1.rank=parent1.rank==parent2.rank?parent1.rank+1:parent1.rank;
            parent2.parent=parent1;
            parent1.size+=parent2.size;
            collection.remove(parent2.data);
        }
        else
        {
            parent1.parent=parent2;
            parent2.size+=parent1.size;

            collection.remove(parent1.data);
        }
        return  true;
    }

    public T findSet(T data)
    {
        return findSet(map.get(data)).data;
    }

    public int getSize(T data)
    {
        return findSet(map.get(data)).size;
    }

    private Node findSet(Node node)
    {
        Node parent=node.parent;
        if(parent==node)
            return parent;
        else
        {
            node.parent=findSet(node.parent);
            return node.parent;
        }
    }

    public void makeSet(T data)
    {
        
        if(map.containsKey(data))
            return;
        Node node=new Node(data);
        map.put(data,node);
        collection.add(node.data);
    }
}
