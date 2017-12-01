package calculator;

public class CalculatorModel {

    private Op op;
    private double previous;
    private double current;

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
