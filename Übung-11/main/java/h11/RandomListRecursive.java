package h11;

import org.jetbrains.annotations.NotNull;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.List;
import java.util.function.BiFunction;

/**
 * A recursive implementation of a self-organizing list that moves accessed elements to a random position in the list
 * based on a random index which is smaller than the position of the accessed element.
 *
 * @param <T> the type of elements in the list
 * @author Nhan Huynh
 */
@DoNotTouch
public class RandomListRecursive<T> extends RandomList<T> implements SelfOrganizingList<T> {

    /**
     * Creates a new list with the given elements and randomizer function.
     *
     * @param elements   the elements to be added to the list
     * @param randomizer the randomizer function used to generate random indices
     */
    @DoNotTouch
    public RandomListRecursive(@NotNull T[] elements, @NotNull BiFunction<Integer, Integer, Integer> randomizer) {
        super(elements, randomizer);
    }

    /**
     * Creates a new empty list with the given randomizer function.
     *
     * @param randomizer the randomizer function used to generate random indices
     */
    @DoNotTouch
    public RandomListRecursive(@NotNull BiFunction<Integer, Integer, Integer> randomizer) {
        super(randomizer);
    }

    /**
     * Creates a new list with the given elements and a default randomizer function.
     *
     * @param elements the elements to be added to the list
     */
    @DoNotTouch
    public RandomListRecursive(@NotNull T[] elements) {
        super(elements);
    }

    /**
     * Creates a new empty list with a default randomizer function.
     */
    @DoNotTouch
    public RandomListRecursive() {
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

        //int randomInt = getRandomIndex(index);
        int randomInt = 2;


        if (randomInt == 0) {
            return swapFirstElement(index);
        } else if (randomInt == index-1) {
            return swapTranspose(index);
        } else {
            return swapMiddle(index, randomInt);
        }
    }

    /**
     * Applies transpose recursive if {code randomInt == index-1}
     */
    private T swapTranspose (int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return head.key;
        }
        ListItem<T> previous = head;
        head = head.next;

        T value = swapTranspose(index-1);

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

    private T swapFirstElement (int index) {
        if (index == 0) {
            return head.key;
        }
        // Move first element to the end (while going down)
        ListItem<T> nextToPrevious = head.next;
        head.next = head.next.next;
        nextToPrevious.next = head;

        if (tail.next == head) {
//            System.out.println(head);
            tail = head;
        }

        T value = swapFirstElement(index-1);

        if (index == 1) {
            nextToPrevious.next = head;
            head = nextToPrevious;

        } else {
            nextToPrevious.next = head.next;
            head.next = nextToPrevious;
        }

        return value;
    }

    private T swapMiddle (int index, int randomInt) {
        if (index == 0) {
            return head.key;
        }
        // Move first element to the end (while going down)
        ListItem<T> previous = head;
        ListItem<T> nextToPrevious = null;

        if (index <= randomInt) {

            nextToPrevious = head.next;
            head.next = head.next.next;
            nextToPrevious.next = head;
        } else {
            head = head.next;
            if (index == randomInt+1) {
                previous.next = head.next;
            }
        }


        if (tail.next == head) {
//            System.out.println(head);
            tail = head;
        }

        T value = swapMiddle(index-1, randomInt);

        if (index == 1) {
            nextToPrevious.next = head;
            head = nextToPrevious;

        } else if (index <= randomInt){
            nextToPrevious.next = head.next;
            head.next = nextToPrevious;
        } else {
            previous.next = head;
            head = previous;
        }

        return value;
    }
}
