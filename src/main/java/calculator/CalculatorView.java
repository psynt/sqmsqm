package calculator;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorView implements Initializable {

    private CalculatorController controller;
    private DoubleProperty tfvalue = new SimpleDoubleProperty();

    @FXML public TilePane buttons;
    @FXML public TextField textf;

    public void setController(CalculatorController cc){
        controller = cc;
//        tfvalue.bind(controller.getValue());
        textf.textProperty().bind(Bindings.format("%.0f", tfvalue));
    }


    public static final String[][] template = {
            {"7", "8", "9", "/"},
            {"4", "5", "6", "*"},
            {"1", "2", "3", "-"},
            {"0", "c", "=", "+"}
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (String[] r : template) {
            for (String s : r) {
                buttons.getChildren().add(createButton(s));
            }
        }
    }

    private Button createButton(final String s) {
        Button button = makeStandardButton(s);

        if (s.matches("[0-9]")) {
            makeNumericButton(s, button);
        } else {
            final ObjectProperty<Op> triggerOp = determineOperand(s);
            if (triggerOp.get() != Op.NOOP) {
                makeOperandButton(button, triggerOp);
            } else if ("c".equals(s)) {
                makeClearButton(button);
            } else if ("=".equals(s)) {
                makeEqualsButton(button);
            }
        }

        return button;
    }

    private ObjectProperty<Op> determineOperand(String s) {
        final ObjectProperty<Op> triggerOp = new SimpleObjectProperty<>(Op.NOOP);
        switch (s) {
            case "+":
                triggerOp.set(Op.ADD);
                break;
            case "-":
                triggerOp.set(Op.SUBTRACT);
                break;
            case "*":
                triggerOp.set(Op.MULTIPLY);
                break;
            case "/":
                triggerOp.set(Op.DIVIDE);
                break;
        }
        return triggerOp;
    }

    private void makeOperandButton(Button button, final ObjectProperty<Op> triggerOp) {
        button.setOnAction(actionEvent -> {
            controller.setCurOp(triggerOp.get());
            controller.setValue(tfvalue.get());
        });
    }

    private Button makeStandardButton(String s) {
        Button button = new Button(s);
//        accelerators.put(s, button);
//        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return button;
    }


    private void makeNumericButton(final String s, Button button) {
        button.setOnAction(actionEvent -> {
//            if (controller.getCurOp() == Op.NOOP) {
                tfvalue.set(tfvalue.get() * 10 + Integer.parseInt(s));
//            } else {
//                controller.setValue(tfvalue.get());
//                controller.setStackValue(tfvalue.get());
//                controller.setValue(Integer.parseInt(s));
//                controller.setStackOp(controller.getCurOp());
//                controller.setCurOp(Op.NOOP);
//            }
        });
    }

    private void makeClearButton(Button button) {
        button.setOnAction(actionEvent -> controller.setValue(0));
    }

    private void makeEqualsButton(Button button) {
        button.setOnAction(actionEvent -> tfvalue.set(controller.calculate()));
    }
}