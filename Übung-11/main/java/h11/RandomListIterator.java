package h11;

import org.jetbrains.annotations.NotNull;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.function.BiFunction;

/**
 * An iterator-based implementation of a self-organizing list that moves accessed elements to a random position in the
 * list based on a random index which is smaller than the position of the accessed element.
 *
 * @param <T> the type of elements in the list
 * @author Nhan Huynh
 */
@DoNotTouch
public class RandomListIterator<T> extends RandomList<T> implements SelfOrganizingList<T> {

    /**
     * Creates a new list with the given elements and randomizer function.
     *
     * @param elements   the elements to be added to the list
     * @param randomizer the randomizer function used to generate random indices
     */
    @DoNotTouch
    public RandomListIterator(@NotNull T[] elements, @NotNull BiFunction<Integer, Integer, Integer> randomizer) {
        super(elements, randomizer);
    }

    /**
     * Creates a new list with the given elements and a default randomizer function.
     *
     * @param elements the elements to be added to the list
     */
    @DoNotTouch
    public RandomListIterator(@NotNull T[] elements) {
        super(elements);
    }

    /**
     * Creates a new empty list with the given randomizer function.
     *
     * @param randomizer the randomizer function used to generate random indices
     */
    @DoNotTouch
    public RandomListIterator(@NotNull BiFunction<Integer, Integer, Integer> randomizer) {
        super(randomizer);
    }

    /**
     * Creates a new empty list with a default randomizer function.
     */
    @DoNotTouch
    public RandomListIterator() {
    }

    @StudentImplementationRequired("H11.4.4")
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        // TODO H11.4.4
        BidirectionalListIterator<T> iterator = new BidirectionalListIterator<>(this);
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            return iterator.next();
        }

        int randomInt = getRandomIndex(index);

        if (randomInt == 0) {
            T nextValue2 = iterator.next();
            iterator.remove();
            for (int i = 0; i<index-1; i++) {
                iterator.next();
            }
            iterator.add(nextValue2);
            T nextValue = iterator.next();
            iterator.remove();

            for (int i = 0; i<index; i++) {
                iterator.previous();
            }
            iterator.add(nextValue);

            return nextValue;

        } else if (randomInt == index-1) {
            for (int i = 0; i<index; i++) {
                iterator.next();
            }
            T nextValue = iterator.next();
            iterator.remove();
            iterator.previous();
            iterator.add(nextValue);
            return nextValue;
        } else {
            for (int i = 0; i < randomInt; i++) {
                iterator.next();
            }
            T nextValue2 = iterator.next();
            iterator.remove();
            for (int i = 0; i<index-(randomInt+1); i++) {
                iterator.next();
            }
            iterator.add(nextValue2);
            T nextValue = iterator.next();
            iterator.remove();

            for (int i = 0; i<index-randomInt; i++) {
                iterator.previous();
            }
            iterator.add(nextValue);

            return nextValue;
        }
    }
}
