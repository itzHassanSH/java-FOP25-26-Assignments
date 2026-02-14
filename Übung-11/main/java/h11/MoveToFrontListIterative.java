package h11;

import org.jetbrains.annotations.NotNull;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * An iterative implementation of a self-organizing list that moves accessed elements to the front of the list.
 *
 * @param <T> the type of elements in the list
 * @author Nhan Huynh
 */
@DoNotTouch
public class MoveToFrontListIterative<T> extends MoveToFrontList<T> implements SelfOrganizingList<T> {

    /**
     * Creates a new empty list.
     */
    @DoNotTouch
    public MoveToFrontListIterative() {
    }

    /**
     * Creates a list with the given elements.
     *
     * @param elements the elements to be added to the list
     */
    @DoNotTouch
    public MoveToFrontListIterative(@NotNull T[] elements) {
        super(elements);
    }

    @StudentImplementationRequired("H11.1")
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        // TODO H11.1
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            return head.key;
        }

        ListItem<T> cursor = head;
        // Reach the element just before our target element
        for (int i = 0; i<index-1; i++) {
            cursor = cursor.next;
        }
        // Get reference for target
        ListItem<T> target = cursor.next;

        // Item before target should now point to item after target; target is removed
        cursor.next = target.next;
        // if target is the last element, then tail now points to item before target
        if (tail == target) {
            tail = cursor;
        }

        // target is placed at first position
        target.next = head;
        head = target;

        return target.key;
    }
}
