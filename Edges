
class Edge<T>
{
    private boolean isDirected = false;
    private Vertex<T> vertex1;
    private Vertex<T> vertex2;
    private int weight;

    Edge(Vertex<T> vertex1, Vertex<T> vertex2)
    {
        //unweighted undirected graph
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;

    }

    Edge(Vertex<T> vertex1, Vertex<T> vertex2,int weight)
    {
        //weighted undirected graph
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight=weight;
    }

    Edge(Vertex<T> vertex1, Vertex<T> vertex2,boolean isDirected,int weight)
    {
        //weighted directed graph 
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
        this.isDirected = isDirected;
    }

    Edge(Vertex<T> vertex1, Vertex<T> vertex2,boolean isDirected) 
    {
        //unweighted directed graph
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.isDirected = isDirected;

    }

    Vertex<T> getVertex1()
    {
        return vertex1;
    }

    Vertex<T> getVertex2()
    {
        return vertex2;
    }

    int getWeight()
    {
        return weight;
    }

    public boolean isDirected()
    {
        return isDirected;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vertex1 == null) ? 0 : vertex1.hashCode());
        result = prime * result + ((vertex2 == null) ? 0 : vertex2.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) 
    {

        if (obj == null)
            return false;
        else if (getClass() != obj.getClass())
            return false;
        else if (this == obj)
            return true;
        @SuppressWarnings("unchecked")
        Edge<T> other = (Edge<T>) obj;
        if (vertex1 == null) 
        {
            if (other.vertex1 != null)
                return false;
        } 
        else if (!vertex1.equals(other.vertex1))
            return false;
        if (vertex2 == null) 
        {
            if (other.vertex2 != null)
                return false;
        } 
        else if (!vertex2.equals(other.vertex2))
            return false;
        return true;
    }

    @Override
    public String toString() 
    {
        return "Edge [isDirected=" + isDirected + ", vertex1=" + vertex1
        + ", vertex2=" + vertex2 + ", weight=" + weight + "]";
    }
}
