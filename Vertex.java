
import java.util.ArrayList;
import java.util.List;

class Vertex<T> 
{
    long id;// use for indexing
    private T data; // vertex name example city, entity name
    private List<Edge<T>> edges = new ArrayList<>(); //its corrosponding edges
    private List<Vertex<T>> adjacentVertex = new ArrayList<>();// its adjacent vertices
    
    Vertex(long id)
    {
        /*constructor to intialize indexing
         * use for indexing
         */
        this.id = id;
    }

    public long getId()
    {
        //getter function to get  vertex id
        return id;
    }

    public void setData(T data)
    {
        //set vertex name,entity name
        this.data = data;
    }

    public T getData()
    {
        // get vertex ,entity name
        return data;
    }

    public void addAdjacentVertex(Edge<T> e, Vertex<T> v)
    {
        edges.add(e);
        adjacentVertex.add(v);
    }

    public String toString()
    {
        return "[id="+String.valueOf(id)+", data="+data.toString()+"]";
    }

    public List<Vertex<T>> getAdjacentVertices()
    {
        //get a list of all its adjacent vertices
        return adjacentVertex;
    }

    public List<Edge<T>> getEdges()
    {
        //get a list of all ts edges
        return edges;
    }

    public int getDegree()
    {
        // get the value of its degree
        return edges.size();
    }

    @Override
    public int hashCode()
    {
        //hash to use in hashmap
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    public boolean isAdjacent(Vertex<T> v)
    {
        if(adjacentVertex.contains(v))
            return true;
        else
            return false;
    }
    
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj)//reference checking
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        @SuppressWarnings("unchecked")
        Vertex<T> other = (Vertex<T>)obj;
        if (id != other.id)//deep checking
            return false;
        return true;
    }
    
}

