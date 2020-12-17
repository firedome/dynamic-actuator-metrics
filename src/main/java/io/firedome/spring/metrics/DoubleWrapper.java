package io.firedome.spring.metrics;

class DoubleWrapper {
    double value;

    public DoubleWrapper(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
