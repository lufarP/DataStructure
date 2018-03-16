import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Graph<T>
{

    private List<Edge<T>> allEdges;
    private Map<Long,Vertex<T>> allVertex;
    boolean isDirected = false;

    public Graph()
    {
        allEdges = new ArrayList<Edge<T>>();
        allVertex = new HashMap<Long,Vertex<T>>();

    }

    public Graph(boolean isDirected)
    {
        allEdges = new ArrayList<Edge<T>>();
        allVertex = new HashMap<Long,Vertex<T>>();
        this.isDirected = isDirected;
    }

    public void addEdge(long id1, long id2)
    {
        addEdge(id1,id2,0);
    }

    //This works only for directed graph because for undirected graph we can end up
    //adding edges two times to allEdges
    public void addVertex(Vertex<T> vertex)
    {
        /*
         * It adds a vertex to the graph.
         * it searches in the graph if it already has, if it has then it does nothing.
         * else it puts the object of vertex class in the hashmap mapped with its id and puts all its edges into the edges list
         * 
         */
        if(allVertex.containsKey(vertex.getId()))
        {
            return;
        }
        allVertex.put(vertex.getId(), vertex);
        for(Edge<T> edge : vertex.getEdges())
        {
            allEdges.add(edge);
        }
    }

    public Vertex<T> addSingleVertex(long id)
    {
        if(allVertex.containsKey(id))
        {
            return allVertex.get(id);
        }
        Vertex<T> v = new Vertex<T>(id);
        allVertex.put(id, v);
        return v;
    }

    public Vertex<T> getVertex(long id)
    {
        return allVertex.get(id);
    }

    public void addEdge(long id1,long id2, int weight)
    {
        Vertex<T> vertex1 = null;
        if(allVertex.containsKey(id1))
        {
            vertex1 = allVertex.get(id1);
        }else
        {
            vertex1 = new Vertex<T>(id1);
            allVertex.put(id1, vertex1);
        }
        Vertex<T> vertex2 = null;
        if(allVertex.containsKey(id2))
        {
            vertex2 = allVertex.get(id2);
        }
        else
        {
            vertex2 = new Vertex<T>(id2);
            allVertex.put(id2, vertex2);
        }

        Edge<T> edge = new Edge<T>(vertex1,vertex2,isDirected,weight);
        allEdges.add(edge);
        vertex1.addAdjacentVertex(edge, vertex2);
        if(!isDirected)
        {
            vertex2.addAdjacentVertex(edge, vertex1);
        }

    }
    
    public boolean isAdjacent(int id1,int id2)
    {
        Vertex<T> v1=null;
        if(allVertex.containsKey(id1))
           v1=allVertex.get(id1);
        else
            return false;
        Vertex<T> v2=null;
        if(allVertex.containsKey(id2))
            v2=allVertex.get(id2);
        else
            return false;
        return v1.isAdjacent(v2);

    }
    
    public List<Edge<T>> getAllEdges()
    {
        return allEdges;
    }

    public Collection<Vertex<T>> getAllVertex()
    {
        return allVertex.values();
    }

    public void setDataForVertex(long id, T data)
    {
        if(allVertex.containsKey(id))
        {
            Vertex<T> vertex = allVertex.get(id);
            vertex.setData(data);
        }
    }

    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        for(Edge<T> edge : getAllEdges())
        {
            buffer.append(edge.getVertex1() + " " + edge.getVertex2() + " " + edge.getWeight());
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
