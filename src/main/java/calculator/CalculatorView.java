package calculator;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorView implements Initializable {

    private CalculatorController controller;
    private DoubleProperty tfvalue = new SimpleDoubleProperty();

    @FXML public TilePane buttons;
    @FXML public TextField textf;
    @FXML public VBox layout;

//    private final Map<String, Button> accelerators = new HashMap<>();

    public void setCon(CalculatorController cc){
        controller = cc;
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
            final Op triggerOp = determineOperand(s);
            if (triggerOp != Op.NOOP) {
                makeOperandButton(button, triggerOp);
            } else if ("c".equals(s)) {
                makeClearButton(button);
            } else if ("=".equals(s)) {
                makeEqualsButton(button);
            }
        }

        return button;
    }

    private Op determineOperand(String s) {
        Op triggerOp = Op.NOOP;
        switch (s) {
            case "+":
                triggerOp = Op.ADD;
                break;
            case "-":
                triggerOp = Op.SUBTRACT;
                break;
            case "*":
                triggerOp = Op.MULTIPLY;
                break;
            case "/":
                triggerOp = Op.DIVIDE;
                break;
        }
        return triggerOp;
    }

    private void makeOperandButton(Button button, final Op boundOp) {
        button.setOnAction(actionEvent -> {
            controller.setCurOp(boundOp);
            controller.setValue(tfvalue.get());
            tfvalue.set(0);
        });
    }

    private Button makeStandardButton(String s) {
        Button button = new Button(s);
//        accelerators.put(s, button);
        return button;
    }

    /*
        This function can help us enable hotkeys, should we wish to later.
     */
//    private void handleAccelerators(VBox layout) {
//        layout.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
//            Button activated = accelerators.get(keyEvent.getText());
//            if (activated != null) {
//                activated.fire();
//            }
//        });
//    }


    private void makeNumericButton(final String s, Button button) {
        button.setOnAction(actionEvent -> tfvalue.set(tfvalue.get() * 10 + Integer.parseInt(s)));
    }

    private void makeClearButton(Button button) {
        button.setOnAction(actionEvent -> {
            controller.setValue(0);
            tfvalue.setValue(0);
        });
    }

    private void makeEqualsButton(Button button) {
        button.setOnAction(actionEvent -> {
            controller.setValue(tfvalue.get());
            tfvalue.set(controller.calculate());
        });
    }
}