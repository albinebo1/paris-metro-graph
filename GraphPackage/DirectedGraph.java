package GraphPackage;
import java.util.Iterator;
import ADTPackage.*;
import java.util.PriorityQueue;
import java.util.Comparator;
public class DirectedGraph<T extends Comparable<? super T>> implements GraphInterface<T> {
    private DictionaryInterface<T, VertexInterface<T>> vertices;
    private int edgeCount;

    public DirectedGraph() {
        vertices = new SortedLinkedDictionary<T, VertexInterface<T>>();
        edgeCount = 0;
    }

    //< Implementations of the graph operations go here. >
    public boolean addVertex(T vertexLabel) {
        VertexInterface<T> addOutcome =
                vertices.add(vertexLabel, new Vertex<>(vertexLabel));
        return addOutcome == null; // Was addition to dictionary successful?
    } // end addVertex

    public boolean hasVertex(T vertexLabel) {
        return vertices.contains(vertexLabel);
    }

    public boolean addEdge(T begin, T end, double edgeWeight) {

        boolean result = false;
        VertexInterface<T> beginVertex =
                vertices.getValue(begin);
        VertexInterface<T> endVertex =
                vertices.getValue(end);
        if ((beginVertex != null) && (endVertex !=
                null))
            result = beginVertex.connect(endVertex,
                    edgeWeight);
        if (result)
            edgeCount++;
        return result;
    } // end addEdge

    public boolean addEdge(T begin, T end) {
        return addEdge(begin, end, 0);
    } // end addEdge

    public boolean hasEdge(T begin, T end) {
        boolean found = false;
        VertexInterface<T> beginVertex =
                vertices.getValue(begin);
        VertexInterface<T> endVertex =
                vertices.getValue(end);
        if ((beginVertex != null) && (endVertex !=
                null)) {
            Iterator<VertexInterface<T>> neighbors =
                    beginVertex.getNeighborIterator();
            while (!found && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor =
                        neighbors.next();
                if (endVertex.equals(nextNeighbor))
                    found = true;
            } // end while
        } // end if
        return found;
    } // end hasEdge

    public boolean isEmpty() {
        return vertices.isEmpty();
    } // end isEmpty

    public void clear() {
        vertices.clear();
        edgeCount = 0;
    } // end clear

    public int getNumberOfVertices() {
        return vertices.getSize();
    } // end getNumberOfVertices

    public int getNumberOfEdges() {
        return edgeCount;
    } // end getNumberOfEdges

    protected void resetVertices() {
        Iterator<VertexInterface<T>> vertexIterator =
                vertices.getValueIterator();
        while (vertexIterator.hasNext()) {
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.unvisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);
        }
    }


    public QueueInterface<T> getBreadthFirstTraversal(T origin) {
        resetVertices();
        QueueInterface<T> traversalOrder = new LinkedQueue<>();
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
        VertexInterface<T> originVertex =
                vertices.getValue(origin);
        originVertex.visit();
        traversalOrder.enqueue(origin); // EnqueueVertex label
        vertexQueue.enqueue(originVertex); // EnqueueVertex
        while (!vertexQueue.isEmpty()) {
            VertexInterface<T> frontVertex =
                    vertexQueue.dequeue();
            Iterator<VertexInterface<T>> neighbors =
                    frontVertex.getNeighborIterator();
            while (neighbors != null && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor =
                        neighbors.next();
                if (!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();
                    traversalOrder.enqueue(nextNeighbor.getLabel());
                    vertexQueue.enqueue(nextNeighbor);
                } // end if
            } // end while
        } // end while
        return traversalOrder;
    }

    @Override
    public QueueInterface<T> getDepthFirstTraversal(T origin) {
        return null;
    }

    @Override
    public StackInterface<T> getTopologicalOrder() {
        return null;
    }

    public int getShortestPath(T begin, T end, StackInterface<T> path) {
        resetVertices();
        boolean done = false;
        QueueInterface<VertexInterface<T>> vertexQueue =
                new LinkedQueue<>();
        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        originVertex.visit();

        vertexQueue.enqueue(originVertex);
        while (!done && !vertexQueue.isEmpty()) {
            VertexInterface<T> frontVertex =
                    vertexQueue.dequeue();
            Iterator<VertexInterface<T>> neighbors =
                    frontVertex.getNeighborIterator();
            while (!done && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor =
                        neighbors.next();
                if (!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();
                    nextNeighbor.setCost(1 +
                            frontVertex.getCost());
                    nextNeighbor.setPredecessor(frontVertex);
                    vertexQueue.enqueue(nextNeighbor);
                } // end if
                if (nextNeighbor.equals(endVertex))
                    done = true;
            } // end while
        } // end while

        int pathLength = (int) endVertex.getCost();
        path.push(endVertex.getLabel());
        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor()) {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());

        } // end while
        return pathLength;
    }

    @Override
    public double getCheapestPath(T begin, T end, StackInterface<T> path) {
        resetVertices();
        PriorityQueue<VertexInterface<T>> vertexQueue =
                new PriorityQueue<>(Comparator.comparingDouble(VertexInterface::getCost));
        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        originVertex.visit();

        vertexQueue.add(originVertex);
        while (!vertexQueue.isEmpty()) {
            VertexInterface<T> frontVertex = vertexQueue.poll();
            Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
            while (neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited()) {
                    double newCost = frontVertex.getCost() + frontVertex.getWeight(nextNeighbor);
                    if (newCost < nextNeighbor.getCost()) {
                        nextNeighbor.setCost(newCost);
                        nextNeighbor.setPredecessor(frontVertex);
                        vertexQueue.add(nextNeighbor);
                    }
                }
            }
            frontVertex.visit();
        }

        double pathCost = endVertex.getCost();
        if (pathCost != Double.POSITIVE_INFINITY) {
            path.push(endVertex.getLabel());
            VertexInterface<T> vertex = endVertex;
            while (vertex.hasPredecessor()) {
                vertex = vertex.getPredecessor();
                path.push(vertex.getLabel());
            }
        }

        return pathCost;
    }
}


