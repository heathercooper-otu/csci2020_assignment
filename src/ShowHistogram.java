//Heather Cooper 100695827
//Assignment Q4

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class ShowHistogram extends Application {

    static class Histogram extends Pane {
        private String text;
        private int[] count = new int[26];
        private char[] alphabet = new char[26];
        private Rectangle[] rectangles = new Rectangle[26];
        GridPane pane;

        public Histogram(String text) {
            this.text = text.toUpperCase();
            alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
            countLetters();
            drawGraph();
        }

        private void countLetters() {
            //initialize all character counts to 0
            for (int i = 0; i < 26; i++){
                count[i] = 0;
            }
            //int index = 0
            for (char j : text.toCharArray()){
                for (int k = 0; k < 26; k++){
                    if (j == alphabet[k]){
                       count[k] += 1;
                       break;
                    }
                }
            }
        }

        private void drawGraph(){
            pane = new GridPane();
            pane.setHgap(1);
            double w = 10;
            for (int i = 0; i < 26; i++){
                //CHANGE SCALE IF TIME AVAILABLE
                double h = count[i]*10 + 1;
                rectangles[i] = new Rectangle(w,h);
                rectangles[i].setStroke(Color.BLACK);
                rectangles[i].setFill(Color.TRANSPARENT);
                pane.add(rectangles[i], i+1,0);
                GridPane.setValignment(rectangles[i], VPos.BASELINE);
                Label letter = new Label(String.valueOf(alphabet[i]));
                pane.add(letter, i+1,1);
            }
            getChildren().addAll(pane);
        }

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new VBox();
        pane.setPadding(new Insets(10,10,10,10));
        Histogram graph = new Histogram(" ");
        pane.getChildren().add(graph);
        //FIX ALIGNMENT!!!!!!!!!!!!
        Pane bottom = new HBox();
        TextField tText = new TextField();
        bottom.getChildren().add(tText);
        Button btView = new Button("View");
        bottom.getChildren().add(btView);
        pane.getChildren().add(bottom);

        btView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String fileText = tText.getText();
                fileText = readFile(fileText);
                Histogram graph = new Histogram(fileText);
                Pane pane = new VBox();
                pane.getChildren().add(graph);
                pane.getChildren().add(bottom);
                Scene scene = new Scene(pane);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });
        //ADD ENTER KEY EVENT, MAKE REDRAW FUNCTION
        btView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String fileText = tText.getText();
                fileText = readFile(fileText);
                Histogram graph = new Histogram(fileText);
                Pane pane = new VBox();
                pane.getChildren().add(graph);
                pane.getChildren().add(bottom);
                Scene scene = new Scene(pane);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static String readFile(String filePath){
        String text= "";
        File file = new File(filePath);
        try {
            Scanner input = new Scanner(file);

            while (input.hasNext()) {
                text = text + input.next();
            }

            input.close();
        }
        catch(FileNotFoundException exc) {
            System.out.println(exc);
        }
        return text;
    }

    static void redraw(){

    }

}
