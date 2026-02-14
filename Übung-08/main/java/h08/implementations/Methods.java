package h08.implementations;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * Implementations of math operations using ordinary methods which can then be referenced
 */
public final class Methods {

    @DoNotTouch
    private Methods() {
        // do not instantiate helper class
    }

    /**
     * Squares x
     *
     * @param x value to be squared
     * @return x^2
     */
    @StudentImplementationRequired("H8.2.2")
    public static double sqr(double x) {
        // TODO H8.2.2
        return x*x;
    }

    /**
     * Calculates x - y
     *
     * @param x subtrahend
     * @param y minuend
     * @return x - y
     */
    @StudentImplementationRequired("H8.3.2")
    public static double sub(double x, double y) {
        // TODO H8.3.2
        return x-y;
    }


    /**
     * Computes the n-ary product of all input values
     *
     * @param x values to be multiplied
     * @return x_0 * ... x_n or 1, if x is empty
     */
    @StudentImplementationRequired("H8.4.2")
    public static double prod(double[] x) {
        // TODO H8.4.2
        if (x.length == 0) {
            return 1;
        }
        double[] x_new = new double[x.length - 1];
        System.arraycopy(x, 1, x_new, 0, x.length-1);
        return x[0] * prod(x_new);
    }
}
