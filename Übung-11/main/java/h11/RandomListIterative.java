package h11;

import org.jetbrains.annotations.NotNull;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.function.BiFunction;

/**
 * An iterative implementation of a self-organizing list that moves accessed elements to a random position in the list
 * based on a random index which is smaller than the position of the accessed element.
 *
 * @param <T> the type of elements in the list
 * @author Nhan Huynh
 */
@DoNotTouch
public class RandomListIterative<T> extends RandomList<T> implements SelfOrganizingList<T> {

    /**
     * Creates a new list with the given elements and randomizer function.
     *
     * @param elements   the elements to be added to the list
     * @param randomizer the randomizer function used to generate random indices
     */
    @DoNotTouch
    public RandomListIterative(@NotNull T[] elements, @NotNull BiFunction<Integer, Integer, Integer> randomizer) {
        super(elements, randomizer);
    }

    /**
     * Creates a new empty list with the given randomizer function.
     *
     * @param randomizer the randomizer function used to generate random indices
     */
    @DoNotTouch
    public RandomListIterative(@NotNull BiFunction<Integer, Integer, Integer> randomizer) {
        super(randomizer);
    }

    /**
     * Creates a new list with the given elements and a default randomizer function.
     *
     * @param elements the elements to be added to the list
     */
    @DoNotTouch
    public RandomListIterative(@NotNull T[] elements) {
        super(elements);
    }

    /**
     * Creates a new empty list with a default randomizer function.
     */
    @DoNotTouch
    public RandomListIterative() {
    }

    @StudentImplementationRequired("H11.3")
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        // TODO H11.3
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            return head.key;
        }

        int randomInt = getRandomIndex(index);

        if (randomInt == 0) {
            ListItem<T> cursor = head;
            // Reach the element just before our target element
            for (int i = 0; i<index-1; i++) {
                cursor = cursor.next;
            }
            // Get reference for target
            ListItem<T> target = cursor.next;

            // Item before target should now point to head;
            cursor.next = head;
            // if target is the last element, then tail now points to head;
            if (tail == target) {
                tail = head;
            }

            // target is placed at first position
            ListItem<T> temp = head.next;
            head.next = target.next;
            target.next = temp;
            head = target;

            return target.key;
        } else if (randomInt == index-1) {
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
        } else {
            ListItem<T> cursor = head;
            ListItem<T> element1 = head;
            ListItem<T> element2 = head;
            for (int i = 0; i < index; i++) {
                if (i == randomInt-1) {
                    element1 = cursor;
                } else if (i == index-1) {
                    element2 = cursor;
                }
                cursor = cursor.next;

            }
            swapRandom(element1, element2);
            return element1.key;
        }
    }
}
