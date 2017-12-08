package calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Calc extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        CalculatorModel mod = new CalculatorModel();
        CalculatorController cc = new CalculatorController();
        cc.setModel(mod);

        FXMLLoader fx = new FXMLLoader(getClass().getResource("/CalculatorView.fxml"));
        Parent root = fx.load();
        ((CalculatorView)fx.getController()).setCon(cc);


        Scene sc = new Scene(root);

        primaryStage.setTitle("Simple Calculator");
        primaryStage.setScene(sc);
        primaryStage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }


}
