package h08.implementations;

import h08.functions.MyDoubleFunction;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentCreationRequired;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Implementations of functional interfaces using method references
 */
public final class MethodReferences {

    @DoNotTouch
    private MethodReferences() {
        // do not instantiate helper class
    }

    /**
     * @return MyDoubleFunction implementation for square root
     */
    @StudentCreationRequired("H8.2.2")
    public static MyDoubleFunction sqrt() {
        // TODO H8.2.2 - change return type to MyDoubleFunction
        return Math::sqrt;
    }
}
