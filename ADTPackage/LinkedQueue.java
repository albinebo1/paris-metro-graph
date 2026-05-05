package ADTPackage;

public final class LinkedQueue<T> implements QueueInterface<T>
{
    private Node firstNode; // References node at front of queue
    private Node lastNode;  // References node at back of queue

    public LinkedQueue()
    {
        firstNode = null;
        lastNode = null;
    } // end default constructor

    public void enqueue(T newEntry)
    {
        Node newNode = new Node(newEntry, null);

        if (isEmpty())
            firstNode = newNode;
        else
            lastNode.setNextNode(newNode);

        lastNode = newNode;
    }

    public T getFront()
    {
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return firstNode.getData();
    }

    public T dequeue()
    {
        T front = getFront();  // Might throw EmptyQueueException
        // Assertion: firstNode != null
        firstNode.setData(null);
        firstNode = firstNode.getNextNode();

        if (firstNode == null)
            lastNode = null;

        return front;
    }

    public boolean isEmpty()
    {
        return (firstNode == null) && (lastNode == null);
    } // end isEmpty

    public void clear()
    {
        firstNode = null;
        lastNode = null;
    }

    private class Node
    {
        private T    data; // Entry in queue
        private Node next; // Link to next node

        private Node(T dataPortion)
        {
            data = dataPortion;
            next = null;
        }

        private Node(T dataPortion, Node linkPortion)
        {
            data = dataPortion;
            next = linkPortion;
        }

        private T getData()
        {
            return data;
        }

        private void setData(T newData)
        {
            data = newData;
        }

        private Node getNextNode()
        {
            return next;
        }

        private void setNextNode(Node nextNode)
        {
            next = nextNode;
        }
    }
}

