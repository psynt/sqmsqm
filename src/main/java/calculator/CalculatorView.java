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
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorView implements Initializable {

    private CalculatorController controller;
    private DoubleProperty tfvalue = new SimpleDoubleProperty();

    @FXML public TilePane buttons;
    @FXML public TextField textf;
    @FXML public VBox layout;


    public void setCon(CalculatorController cc){
        controller = cc;
        textf.textProperty().bind(Bindings.format("%.0f", tfvalue));
    }


    private static final String[][] template = {
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

    @Contract(pure = true)
    private Op determineOperand(@NotNull String s) {
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

    private void makeOperandButton(@NotNull Button button, final Op boundOp) {
        button.setOnAction(actionEvent -> {
            controller.setCurOp(boundOp);
            controller.setValue(tfvalue.get());
            tfvalue.set(0);
        });
    }

    @NotNull
    private Button makeStandardButton(String s) {
        return new Button(s);
    }


    private void makeNumericButton(final String s, @NotNull Button button) {
        button.setOnAction(actionEvent -> tfvalue.set(tfvalue.get() * 10 + Integer.parseInt(s)));
    }

    private void makeClearButton(@NotNull Button button) {
        button.setOnAction(actionEvent -> {
            controller.setValue(0);
            tfvalue.setValue(0);
        });
    }

    private void makeEqualsButton(@NotNull Button button) {
        button.setOnAction(actionEvent -> {
            controller.setValue(tfvalue.get());
            tfvalue.set(controller.calculate());
        });
    }
}