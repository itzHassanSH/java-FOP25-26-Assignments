package h11;

import org.jetbrains.annotations.NotNull;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * An iterator-based implementation of a self-organizing list that moves accessed elements to the front of the list.
 *
 * @param <T> the type of elements in the list
 * @author Nhan Huynh
 */
@DoNotTouch
public class MoveToFrontListIterator<T> extends MoveToFrontList<T> implements SelfOrganizingList<T> {

    /**
     * Creates a new empty list.
     */
    @DoNotTouch
    public MoveToFrontListIterator() {
    }

    /**
     * Creates a list with the given elements.
     *
     * @param elements the elements to be added to the list
     */
    @DoNotTouch
    public MoveToFrontListIterator(@NotNull T[] elements) {
        super(elements);
    }

    @StudentImplementationRequired("H11.4.2")
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        // TODO H11.4.2
        BidirectionalListIterator<T> iterator = new BidirectionalListIterator<>(this);
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            return iterator.next();
        }

        for (int i = 0; i<index; i++) {
            iterator.next();
        }
        T nextValue = iterator.next();

        iterator.remove();

        for (int i = 0; i<index; i++) {
            iterator.previous();
        }
        iterator.add(nextValue);
        return nextValue;
    }
}
