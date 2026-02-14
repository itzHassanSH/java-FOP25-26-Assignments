package h08.nodes;

import h08.functions.MyDoubleFunction;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * A node that summerizes a chain of unary function applications to one node
 * using composition.
 */
public class ChainNode implements Node {

// TODO H8.7.1 - uncomment

    private final Node node;
    private final MyDoubleFunction[] functions;

    /**
     * @param node      the single operand to start with
     * @param functions all functions to be applied in a chain (starting with the first one)
     */
    public ChainNode(Node node, MyDoubleFunction... functions) {
        this.node = node;
        this.functions = functions;
    }

    @Override
    @StudentImplementationRequired("H8.7.1")
    public double evaluate() {
        // TODO H8.7.1
        if (functions.length == 0) {
            return node.evaluate();
        }
        double nodeCalculated = node.evaluate();
        for (MyDoubleFunction function: functions) {
            nodeCalculated = function.apply(nodeCalculated);
        }
        return nodeCalculated;
    }
}
