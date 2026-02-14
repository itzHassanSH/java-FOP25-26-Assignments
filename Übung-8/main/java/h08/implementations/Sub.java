package h08.implementations;

import h08.functions.MyDoubleBiFunction;

public class Sub implements MyDoubleBiFunction {
    @Override
    public double apply(double x, double y) {
        return x-y;
    }
}
