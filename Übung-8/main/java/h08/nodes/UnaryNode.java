package h08.nodes;

import h08.functions.MyDoubleFunction;
import h08.functions.MyDoubleSupplier;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * A node that applies a unary MyDoubleFunction to one operand.
 */
public class UnaryNode implements Node {
    private final MyDoubleFunction function;
    private final Node operand;

    /**
     * @param function the function to be applied to the one operand
     * @param operand  the single operand
     */
    public UnaryNode(MyDoubleFunction function, Node operand) {
        this.function = function;
        this.operand = operand;
    }

    @StudentImplementationRequired("H8.5.1")
    @Override
    public double evaluate() {
        // TODO H8.5.1
//        if (function instanceof MyDoubleSupplier) {
//            return ((MyDoubleSupplier) function).get();
//        }
        return function.apply(operand.evaluate());
    }
}
