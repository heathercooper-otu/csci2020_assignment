//Heather Cooper 100695827
//Assignment Q2

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ShowInvestmentValue extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10,10,10,10));
        pane.setVgap(5);
        pane.setHgap(5);

        //create necessary text fields and their labels
        pane.add(new Label("Investment Amount"),0,0);
        TextField tAmount = new TextField();
        pane.add(tAmount, 1,0);

        pane.add(new Label("Years"), 0,1);
        TextField tYears = new TextField();
        pane.add(tYears, 1,1);

        pane.add(new Label("Annual Interest Rate"), 0 , 2);
        TextField tInterest = new TextField();
        pane.add(tInterest, 1 ,2);

        pane.add(new Label("Future Value"), 0, 3);
        TextField tValue = new TextField();
        pane.add(tValue, 1 ,3);

        //create calculate button
        Button btCalculate = new Button("Calculate");
        pane.add(btCalculate, 1,4);
        GridPane.setHalignment(btCalculate, HPos.RIGHT);

        btCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //assign values entered by the user to the corresponding variables
                int amount = Integer.parseInt(tAmount.getText());
                int years = Integer.parseInt(tYears.getText());
                float interest = Float.valueOf(tInterest.getText());
                //calculate monthly interest
                float monthly = interest /(12*100);
                //calculate the future value of the investment and output it
                double value = amount * Math.pow((1 + monthly),(years*12));
                tValue.setText(String.format("%.2f",value));
            }
        });

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}