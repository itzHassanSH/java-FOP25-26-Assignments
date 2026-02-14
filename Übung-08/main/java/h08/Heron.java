package h08;

import h08.implementations.Lambdas;
import h08.implementations.MethodReferences;
import h08.nodes.ArrayNode;
import h08.nodes.BinaryNode;
import h08.nodes.Node;
import h08.nodes.UnaryNode;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * Implementation of Heron's formula
 */
public final class Heron {

    @DoNotTouch
    private Heron() {
        // do not instantiate helper class
    }

    /**
     * Creates a node that computes the Euclidean distance between point A and B.
     *
     * @param ax A's x coordinate
     * @param ay A's y coordinate
     * @param bx B's x coordinate
     * @param by B's y coordinate
     * @return a node that computes dist(A, B)
     */
    @StudentImplementationRequired("H8.6.1")
    public static Node pointDistance(Node ax, Node ay, Node bx, Node by) {
        // TODO H8.6.1
        Node node1 = new UnaryNode(Lambdas.sqr(), new BinaryNode(Lambdas.sub(), ax, bx));
        Node node2 = new UnaryNode(Lambdas.sqr(), new BinaryNode(Lambdas.sub(), ay, by));

        Node node3 = new BinaryNode(Double::sum, node1, node2);

        return new UnaryNode(MethodReferences.sqrt(), node3);
    }

    /**
     * Creates a node that computes half of a triangle's perimeter
     * given the lengths of all three edges.
     *
     * @param a length of first edge
     * @param b length of second edge
     * @param c length of third edge
     * @return a node that computes half of the triangle's perimeter.
     */
    @StudentImplementationRequired("H8.6.2")
    public static Node halfTrianglePerimeter(Node a, Node b, Node c) {
        // TODO H8.6.2
        Node node1 = new BinaryNode(Double::sum, a, new BinaryNode(Double::sum, b, c));
        return new UnaryNode(x -> x/2, node1);
    }

    /**
     * Creates a node that computes the area of the triangle ABC using Heron's formula.
     *
     * @param ax A's x coordinate
     * @param ay A's y coordinate
     * @param bx B's x coordinate
     * @param by B's y coordinate
     * @param cx C's x coordinate
     * @param cy C's y coordinate
     * @return a node that computes the triangle's area
     */
    @StudentImplementationRequired("H8.6.3")
    public static Node heron(Node ax, Node ay, Node bx, Node by, Node cx, Node cy) {
        // TODO H8.6.3
        Node ab = pointDistance(ax, ay, bx, by);
        Node ac = pointDistance(ax, ay, cx, cy);
        Node bc = pointDistance(bx, by, cx, cy);

        Node s = halfTrianglePerimeter(ab, ac, bc);
        Node[] factors = {s, new BinaryNode(Lambdas.sub(), s, ab), new BinaryNode(Lambdas.sub(), s, ac), new BinaryNode(Lambdas.sub(), s, bc)};

        Node sProd = new ArrayNode(Lambdas.prod(), factors);
        return new UnaryNode(MethodReferences.sqrt(), sProd);
    }
}
