package h11;

import org.jetbrains.annotations.NotNull;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * An iterative implementation of a self-organizing list that transposes accessed elements with their previous element.
 *
 * @param <T> the type of elements in the list
 * @author Nhan Huynh
 */
@DoNotTouch
public class TransposeListIterative<T> extends TransposeList<T> implements SelfOrganizingList<T> {

    /**
     * Creates a new empty list.
     */
    @DoNotTouch
    public TransposeListIterative() {
    }

    /**
     * Creates a list with the given elements.
     *
     * @param elements the elements to be added to the list
     */
    @DoNotTouch
    public TransposeListIterative(@NotNull T[] elements) {
        super(elements);
    }

    @StudentImplementationRequired("H11.2")
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        // TODO H11.2
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            return head.key;
        }
        ListItem<T> cursor = head;
        ListItem<T> previous1 = head;
        ListItem<T> previous2 = head;
        for (int i = 0; i < index; i ++) {
            if (i == index-2) {
                previous1 = cursor;
            }
            if (i == index-1) {
                previous2 = cursor;
            }
            cursor = cursor.next;
        }
        if (cursor == tail) {
            tail = previous2;
        }
        previous1.next = cursor;
        previous2.next = cursor.next;
        cursor.next = previous2;


        return cursor.key;
    }
}
