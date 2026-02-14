package h08;

import h08.functions.MyDoubleFunction;
import h08.implementations.MethodReferences;
import h08.nodes.ChainNode;
import h08.nodes.Node;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Example usage of ChainNode
 */
public final class MyChainedFunction {

    @DoNotTouch
    private MyChainedFunction() {
        // do not instantiate helper class
    }

    /**
     * Create a ChainNode that computes sqrt(q + 1 + 1).
     *
     * @param q the initial operand q
     * @return a ChainNode that computes sqrt(q + 1 + 1)
     */
    @StudentImplementationRequired("H8.7.2")
    public static Node add2sqrt(Node q) {
        // TODO H8.7.2
        MyDoubleFunction[] functions = {x->x+1, x->x+1, MethodReferences.sqrt()};
        return new ChainNode(q, functions);
    }
}
