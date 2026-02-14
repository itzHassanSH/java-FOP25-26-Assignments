package h08.nodes;

import h08.functions.MyDoubleArrayFunction;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * A node that applies a DoubleArrayFunction to an arbitrary amount of operands.
 */
public class ArrayNode implements Node {

// TODO H8.5.3 - uncomment

    private final MyDoubleArrayFunction function;
    private final Node[] operands;

    /**
     * @param function the function to be applied to the operands
     * @param operands the operands to apply the function to
     */
    public ArrayNode(MyDoubleArrayFunction function, Node... operands) {
        this.function = function;
        this.operands = operands;
    }

    @StudentImplementationRequired("H8.5.3")
    @Override
    public double evaluate() {
        // TODO H8.5.3
        double[] operandsCalculated = new double[operands.length];
        for (int i = 0; i< operands.length; i++) {
            operandsCalculated[i] = operands[i].evaluate();
        }
        return function.apply(operandsCalculated);
    }
}
