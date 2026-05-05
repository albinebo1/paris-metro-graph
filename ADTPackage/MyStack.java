package ADTPackage;

import java.util.LinkedList;

public class MyStack<T> implements StackInterface<T> {
    private LinkedList<T> list;

    public MyStack() {
        list = new LinkedList<>();
    }

    @Override
    public void push(T element) {
        list.push(element);
    }

    @Override
    public T pop() {
        return list.pop();
    }

    @Override
    public T peek() {
        return list.peek();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {

    }

}

