package h11;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.NoSuchElementException;

/**
 * An implementation of {@link BidirectionalIterator} that allows iterating over the list in both directions for
 * a {@link SelfOrganizingList}.
 */
class BidirectionalListIterator<T> implements BidirectionalIterator<T> {

    /**
     * The list to iterate over.
     */
    private final @NotNull AbstractSelfOrganizingList<T> list;

    /**
     * The last element returned by a call to {@code next()} or {@code previous()}.
     * This field is used to determine whether a subsequent call to {@code remove()}
     * is valid. A call to {@code remove()} is only allowed if it directly follows
     * a call to {@code next()} or {@code previous()}, and it cannot be called twice
     * in a row without an intervening cursor movement.
     * <p>
     * After a call to {@code add()}, this field is set to {@code null} to prevent
     * an immediate {@code remove()}. After a call to {@code remove()}, it is also
     * set to {@code null} because the previously returned element no longer exists
     * in the list.
     */
    @Nullable ListItem<T> lastReturned;

    /**
     * The cursor for the current position in the list.
     */
    @Nullable ListItem<T> cursor;

    /**
     * The previous references of the cursor for the reverse iteration.
     */
    @Nullable ListItem<ListItem<T>> previouses;

    /**
     * Creates a new iterator for the given list.
     *
     * @param list the list to iterate over
     */
    BidirectionalListIterator(@NotNull AbstractSelfOrganizingList<T> list) {
        this.list = list;
        this.cursor = list.head;
    }

    @StudentImplementationRequired("H11.4.1")
    @Override
    public boolean hasPrevious() {
        // TODO H11.4.1
        if (previouses != null) {
            return true;
        }
        return false;
    }

    @StudentImplementationRequired("H11.4.1")
    @Override
    public T previous() throws NoSuchElementException {
        // TODO H11.4.1
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        cursor = previouses.key;
        lastReturned = previouses.key;
        previouses = previouses.next;

        return lastReturned.key;
    }

    @StudentImplementationRequired("H11.4.1")
    @Override
    public boolean hasNext() {
        // or alternatively cursor == list.tail
        if (cursor == null) {
            return false;
        }
        return true;
    }

    @StudentImplementationRequired("H11.4.1")
    @Override
    public T next() throws NoSuchElementException {
        // TODO H11.4.1
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        lastReturned = cursor ;

        if (cursor == list.head) {
            previouses = new ListItem<>(cursor);
        } else {
            ListItem<ListItem<T>> temp = new ListItem<>(cursor);
            temp.next = previouses;
            previouses = temp;
        }

        cursor = cursor.next;

        return lastReturned.key;
    }

    @StudentImplementationRequired("H11.4.1")
    @Override
    public void add(T element) {
        // TODO H11.4.1
        ListItem<T> itemToAdd = new ListItem<>(element);
        if (lastReturned == null && list.size() != 0) {
            return;
        } else if (previouses == null) {
            if (list.size() == 0) {
                cursor = itemToAdd;
                list.tail = itemToAdd;
            } else {
                previouses = new ListItem<>(itemToAdd);
                itemToAdd.next = cursor;
            }
            list.head = itemToAdd;
        } else {
            if (previouses.key == list.tail) {
                list.tail = itemToAdd;
            }

            previouses.key.next = itemToAdd;
            itemToAdd.next = cursor;


            ListItem<ListItem<T>> temp = new ListItem<>(itemToAdd);
            temp.next = previouses;
            previouses = temp;
        }
        list.size++;
        lastReturned = null;

    }

    @StudentImplementationRequired("H11.4.1")
    @Override
    public void remove() throws IllegalStateException {
        // TODO H11.4.1
        if (lastReturned == null) {
            throw new IllegalStateException();
        }
        // If lastReturned was returned through next(), then we remove item at previouses
        // Else we remove item at cursor.next (when it was returned through previous())

        // Removing for previous()
        if (lastReturned == cursor) {
            // Again list.size() may never be 1 AND lastReturned is not null (at the same time)
            if (list.size() == 2) {
                list.head = cursor.next;
                cursor = cursor.next;
                previouses = null;  // previousness should already be null (but just in case)

            } else {
                // Remove item AT cursor, since we returned value AFTER moving cursor back
                previouses.key.next = cursor.next;
                cursor = cursor.next;

            }
        // Removing for next()
        } else if (lastReturned == previouses.key) {
            // If the list.size() is EVER 1, then lastReturned must be Null´and can't be changed (as there's no previous or next)
            // list.size() == 2 is same as cursor == head
            if (cursor == list.head.next) {
                list.head = cursor;
                previouses = null;
                if (list.size() == 2) {
                    list.tail = cursor;
                }

            } else {
                // Remove item AT previouses, since we returned value BEFORE moving cursor forward
                if (previouses.key == list.tail) {
                    list.tail = previouses.next.key;
                }
                previouses.next.key.next = cursor;
                previouses = previouses.next;

            }
        }
        list.size--;
        lastReturned = null;
    }
}


