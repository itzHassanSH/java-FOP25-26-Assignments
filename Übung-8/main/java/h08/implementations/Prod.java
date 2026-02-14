package h08.implementations;

import h08.functions.MyDoubleArrayFunction;

public class Prod implements MyDoubleArrayFunction {
    @Override
    public double apply(double[] x) {
        double val = 1;
        for (double item: x) {
            val *= item;
        }
        return val;
    }
}
