package calculator;

public class CalculatorController {

    private CalculatorModel model;
//    private CalculatorView view;
//
//    public void setView(CalculatorView vw){
//        view = vw;
//    }

    public void setModel(CalculatorModel mo){
        model = mo;
    }

    public void setCurOp(Op op) {
        model.setOp(op);
    }

    public void setValue(double value) {
        model.setPrev(model.getValue());
        model.setValue(value);
    }

    public Op getCurOp() {
        return model.getOp();
    }

    public double getValue() {
        return model.getValue();
    }

    public double calculate() {
        double res = 0;
        switch (model.getOp()) {
            case ADD:
                res = model.getPrevious() + model.getValue();
                break;
            case SUBTRACT:
                res = model.getPrevious() - model.getValue();
                break;
            case MULTIPLY:
                res = model.getPrevious() * model.getValue();
                break;
            case DIVIDE:
                res = model.getPrevious() / model.getValue();
                break;
        }
        setCurOp(Op.NOOP);
        setValue(res);
        return res;
    }
}
