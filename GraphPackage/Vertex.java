package GraphPackage;
import java.util.Iterator;
import java.util.NoSuchElementException;
import ADTPackage.*;

class Vertex<T> implements VertexInterface<T> {
    private T label;

    private ListWithIteratorInterface<Edge> edgeList;
    private boolean visited;
    private VertexInterface<T> previousVertex;
    private double cost;

    public Vertex(T vertexLabel){
        label = vertexLabel;
        edgeList = new LinkedListWithIterator<>();
        visited = false;
        previousVertex = null;
        cost = 0;
    }

    @Override
    public T getLabel() {
        return label;
    }

    @Override
    public void visit() {
        this.visited = true;
    }

    @Override
    public void unvisit() {
        this.visited = false;
    }

    @Override
    public boolean isVisited() {
        return this.visited;
    }

    //IMPLEMENTATIONS---------------------------
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight){
        boolean result = false;
        if(!this.equals(endVertex)){
            edgeList.add(new Edge(endVertex, edgeWeight));
            result = true;
            // }

        }
        return result;
    }
    public boolean connect(VertexInterface<T> endVertex){
        return connect(endVertex, 0);
    }

    @Override
    public Iterator<VertexInterface<T>> getNeighborIterator() {
        return new NeighborIterator();
    }

    @Override
    public Iterator<Double> getWeightIterator() {

        return new WeightIterator();
    }

    @Override
    public boolean hasNeighbor() {
        return !edgeList.isEmpty();
    }

    @Override
    public VertexInterface<T> getUnvisitedNeighbor() {
        VertexInterface<T> result = null;
        Iterator<VertexInterface<T>> neighbors =
                getNeighborIterator();
        while ( neighbors.hasNext() && (result == null) )
        {
            VertexInterface<T> nextNeighbor = neighbors.next();
            if (!nextNeighbor.isVisited())
                result = nextNeighbor;
        } // end while
        return result;
    }

    @Override
    public void setPredecessor(VertexInterface<T> predecessor) {
        this.previousVertex = predecessor;
    }

    @Override
    public VertexInterface<T> getPredecessor() {
        return this.previousVertex;
    }

    @Override
    public boolean hasPredecessor() {
        return this.previousVertex != null;
    }

    @Override
    public void setCost(double newCost) {
        this.cost = newCost;
    }

    @Override
    public double getCost() {
        return this.cost;
    }



    protected class Edge{
        private VertexInterface<T> vertex;
        private double weight;

        protected Edge(VertexInterface<T> endVertex, double edgeWeight){
            vertex = endVertex;
            weight = edgeWeight;
        }

        protected VertexInterface<T> getEndVertex(){
            return vertex;
        }

        protected double getWeight(){
            return weight;
        }
    }
    @Override
    public double getWeight(VertexInterface<T> neighbor) {
        Iterator<Edge> edgeIterator = edgeList.getIterator();
        while (edgeIterator.hasNext()) {
            Edge edge = edgeIterator.next();
            if (edge.getEndVertex().equals(neighbor)) {
                return edge.getWeight();
            }
        }
        return Double.POSITIVE_INFINITY; // If there is no direct edge to the neighbor
    }

    private class NeighborIterator implements Iterator<VertexInterface<T>> {

        private Iterator<Edge> edges;

        private NeighborIterator() {
            edges = edgeList.getIterator();
        }

        public boolean hasNext() {
            return edges.hasNext();

        }

        public VertexInterface<T> next() {
            VertexInterface<T> nextNeighbor = null;
            if (edges.hasNext()) {
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            } else throw new NoSuchElementException();
            return nextNeighbor;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
    private class WeightIterator implements Iterator<Double> {

        private Iterator<Edge> edges;

        private WeightIterator() {
            edges = edgeList.getIterator();
        }

        public boolean hasNext() {
            return edges.hasNext();
        }

        public Double next() {
            Double weight = null;
            if (edges.hasNext()) {
                Edge edge = edges.next();
                weight = edge.getWeight();
            } else throw new NoSuchElementException();
            return weight;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}