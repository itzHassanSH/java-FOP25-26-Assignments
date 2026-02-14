package h08;

import h08.functions.MyDoubleBiFunction;
import h08.functions.MyDoubleFunction;
import h08.functions.MyDoubleSupplier;
import h08.implementations.AnonymousClasses;
import h08.implementations.Lambdas;
import h08.implementations.Methods;
import h08.implementations.Sqr;
import h08.nodes.Node;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;
import org.tudalgo.algoutils.student.test.StudentTestUtils;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {

//        Node doubleNode = () -> 42;
//        System.out.println(doubleNode.evaluate());
//
//        MyDoubleSupplier doubleSupplier = () -> 42;
//        System.out.println(doubleSupplier.get());
//        double[] x = {1, 2, 3, 4, 5};
//        System.out.println(Methods.prod(x));
        sanityChecks();
        StudentTestUtils.printTestResults();
    }

    /**
     * Test student's implementation
     */
    @StudentImplementationRequired("H8.8")
    public static void sanityChecks() {
        // TODO H8.8 testing
        MyDoubleFunction sqrClass = new Sqr();
        MyDoubleFunction sqrLambda = Lambdas.sqr();
        MyDoubleFunction sqrAnonymClass = AnonymousClasses.sqr();
        MyDoubleFunction sqrMethod = Methods::sqr;

        testDoubleEquals(4.0, sqrClass.apply(2.0));
        testDoubleEquals(4.0, sqrLambda.apply(2.0));
        testDoubleEquals(4.0, sqrAnonymClass.apply(2.0));
        testDoubleEquals(4.0, sqrMethod.apply(2.0));

        testDoubleEquals(6.0, Heron.heron(()->0,()->0,()->3,()->0,()->3,()->4).evaluate());
        testDoubleEquals(2.4494, MyChainedFunction.add2sqrt(()->4).evaluate());

        testDoubleEquals(120.0, Methods.prod(new double[]{1, 2, 3, 4, 5}));
    }

    /**
     * Tests if the two supplied doubles are equal (with precision 0.0001).
     * If they are, the method returns normally.
     * If they are not equal, the method terminates the program printing the two values.
     *
     * @param expected the double value the result is expected to be
     * @param actual   the actually computed value to be tested
     */
    @DoNotTouch
    private static void testDoubleEquals(double expected, double actual) {
        double epsilon = 0.0001;
        StudentTestUtils.testWithinRange(expected - epsilon, expected + epsilon, actual);
    }

}
