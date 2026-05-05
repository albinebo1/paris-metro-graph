package ADTPackage;
import java.util.LinkedList;
import java.util.Iterator;

public class LinkedListWithIterator<T> implements ListWithIteratorInterface<T> {
    private LinkedList<T> list;

    public LinkedListWithIterator() {
        list = new LinkedList<>();
    }

    @Override
    public void add(T newEntry) {
        list.add(newEntry);
    }

    @Override
    public void add(int newPosition, T newEntry) {
        if (newPosition >= 1 && newPosition <= list.size() + 1) {
            list.add(newPosition - 1, newEntry);
        } else {
            throw new IndexOutOfBoundsException("Index is invalid");
        }
    }

    @Override
    public T remove(int givenPosition) {
        if (givenPosition >= 1 && givenPosition <= list.size()) {
            return list.remove(givenPosition - 1);
        } else {
            throw new IndexOutOfBoundsException("Index is invalid");
        }
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public T replace(int givenPosition, T newEntry) {
        if (givenPosition >= 1 && givenPosition <= list.size()) {
            return list.set(givenPosition - 1, newEntry);
        } else {
            throw new IndexOutOfBoundsException("Index is invalid");
        }
    }

    @Override
    public T getEntry(int givenPosition) {
        if (givenPosition >= 1 && givenPosition <= list.size()) {
            return list.get(givenPosition - 1);
        } else {
            throw new IndexOutOfBoundsException("Index is invalid");
        }
    }

    @Override
    public T[] toArray() {
        return list.toArray((T[]) new Object[0]);
    }

    @Override
    public boolean contains(T anEntry) {
        return list.contains(anEntry);
    }

    @Override
    public int getLength() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<T> getIterator() {
        return list.iterator();
    }
}

