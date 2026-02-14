package h11;

import org.jetbrains.annotations.NotNull;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * A recursive implementation of a self-organizing list that transposes accessed elements with their previous element.
 *
 * @param <T> the type of elements in the list
 * @author Nhan Huynh
 */
@DoNotTouch
public class TransposeListRecursive<T> extends TransposeList<T> implements SelfOrganizingList<T> {

    /**
     * Creates a new empty list.
     */
    @DoNotTouch
    public TransposeListRecursive() {
    }

    /**
     * Creates a list with the given elements.
     *
     * @param elements the elements to be added to the list
     */
    @DoNotTouch
    public TransposeListRecursive(@NotNull T[] elements) {
        super(elements);
    }

    @StudentImplementationRequired("H11.2")
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        // TODO H11.2
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return head.key;
        }
        ListItem<T> previous = head;
        head = head.next;

        T value = get(index-1);

        // when index = 1, that is the first unwind from having reached target. Here we can swap target and previous
        // Only when index = 2 exists do we connect previous of previous to target. For this to happen head must not be updated at index = 1
        // Otherwise we lose address of target during jump between unwinds
        if (index == 1) {
            previous.next = head.next;
            head.next = previous;
            if (tail == head) {
                tail = previous;
            }
//            System.out.println("index = 1, previous = " + previous.key + " " + head.key);
        } else if (index == 2) {
            previous.next = head;
        } else {
            head = previous;
        }

        return value;
    }
}
