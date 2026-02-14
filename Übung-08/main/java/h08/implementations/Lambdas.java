package h08.implementations;

import h08.functions.MyDoubleArrayFunction;
import h08.functions.MyDoubleBiFunction;
import h08.functions.MyDoubleFunction;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.Arrays;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Implementations of functional interfaces using lambdas
 */
@SuppressWarnings({"CodeBlock2Expr", "unused", "RedundantSuppression"})
public final class Lambdas {

    @DoNotTouch
    private Lambdas() {
        // do not instantiate helper class
    }

    /**
     * @return MyDoubleFunction implementation for x^2
     */
    @StudentImplementationRequired("H8.2.2")
    public static MyDoubleFunction sqr() {
        // TODO H8.2.2 - change return type to MyDoubleFunction
        return x -> x*x;
    }

    /**
     * @return MyDoubleFunction implementation for x-y
     */
    @StudentImplementationRequired("H8.3.2")
    public static MyDoubleBiFunction sub() {
        // TODO H8.3.2 - change return type to MyDoubleBiFunction
        return (double x, double y) -> {
            return x-y;
        };
    }

    /**
     * @return MyDoubleArrayFunction implementation for x_0 * ... * x_n
     */
    @StudentImplementationRequired("H8.4.2")
    public static MyDoubleArrayFunction prod() {
        // TODO H8.4.2 - change return type to MyDoubleArrayFunction
        return (double[] x) -> {
            return Arrays.stream(x).reduce(1,(y1,y2)-> y1*y2);
        };
    }
}
