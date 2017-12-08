package calculator;

public class CalculatorModel {

    private Op op = Op.NOOP;
    private double previous = 0.0;
    private double current = 0.0;

    public void setOp(Op op) {
        this.op = op;
    }

    public void setValue(double value) {
        current = value;
    }

    public Op getOp() {
        return op;
    }

    public double getValue() {
        return current;
    }

    public void setPrev(double prev) {
        this.previous = prev;
    }

    public double getPrevious(){
        return previous;
    }
}
