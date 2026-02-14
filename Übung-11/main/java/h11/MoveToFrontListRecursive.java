package h11;

import org.jetbrains.annotations.NotNull;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * A recursive implementation of a self-organizing list that moves accessed elements to the front of the list.
 *
 * @param <T> the type of elements in the list
 * @author Nhan Huynh
 */
@DoNotTouch
public class MoveToFrontListRecursive<T> extends MoveToFrontList<T> implements SelfOrganizingList<T> {

    /**
     * Creates a new empty list.
     */
    @DoNotTouch
    public MoveToFrontListRecursive() {
    }

    /**
     * Creates a list with the given elements.
     *
     * @param elements the elements to be added to the list
     */
    @DoNotTouch
    public MoveToFrontListRecursive(@NotNull T[] elements) {
        super(elements);
    }


    @StudentImplementationRequired("H11.1")
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        // TODO H11.4.1
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return head.key;
        }
        ListItem<T> previous = head;
        head = head.next;

        // Recursive call
        T value = get(index-1);

        // Code here happens DURING Unwinding
//        System.out.println("During unwinding, previous: " + previous.key + ", head: " + head.key);
        // In each unwind, we store the value of previous for the head in that call,
        // This means we have access to each objects address (that comes before the requested value, so 0,1,2 come before 3 if get(3))
        // we simply point each previous' next value to next value of head and update heads next value to said previous.
        // so if 3 points to 4, then in the first unwind previous = 2, 2 then points to 4 and 3 points to 2,
        // we essentially permuted 3 to go before 2
        // this happens until 3 reaches the start
        if (tail == head) {
            tail = previous;
        }
        previous.next = head.next;
        head.next = previous;

        return value;

    }
}
