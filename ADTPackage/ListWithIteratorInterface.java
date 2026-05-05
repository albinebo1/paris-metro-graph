package ADTPackage;
import java.util.Iterator;

public interface ListWithIteratorInterface<T> extends ListInterface<T> {
    void add(T element);
     Iterator<T> getIterator();
}
