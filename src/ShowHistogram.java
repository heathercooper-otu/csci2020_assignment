//Heather Cooper 100695827
//Assignment Q4

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShowHistogram extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new VBox();
        pane.setPadding(new Insets(10,10,10,10));
        //create new blank histogram
        Histogram graph = new Histogram(" ");
        pane.getChildren().add(graph);
        Pane bottom = new HBox();
        //create textfield for user to enter file name into
        TextField tText = new TextField();
        bottom.getChildren().add(tText);
        //create view button
        Button btView = new Button("View");
        bottom.getChildren().add(btView);
        pane.getChildren().add(bottom);

        //display histogram if view button is pressed
        btView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //get the file url
                String fileText = tText.getText();
                //get the text from the file
                fileText = readFile(fileText);
                //create a histogram using the file
                Histogram graph = new Histogram(fileText);
                //reset the scene
                Pane pane = new VBox();
                pane.getChildren().add(graph);
                pane.getChildren().add(bottom);
                Scene scene = new Scene(pane);
                primaryStage.setScene(scene);
                primaryStage.sizeToScene();
                primaryStage.show();
            }
        });

        //display histogram if enter key is pressed
        tText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
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
            }
        });

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    String readFile(String filePath){
        //create empty string for the file to read into
        String text= "";
        File file = new File(filePath);
        try { //if file exists concatenate  it line by line to the string
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                text = text + input.next();
            }

            input.close();
        } //print excpetion to console if file not found
        catch(FileNotFoundException exc) {
            System.out.println(exc);
        }
        return text;
    }

    class Histogram extends Pane {
        private String text;
        private int[] count = new int[26];
        private char[] alphabet = new char[26];
        private Rectangle[] rectangles = new Rectangle[26];
        GridPane pane;

        public Histogram(String text) {
            //convert all characters from the file to uppercase
            this.text = text.toUpperCase();
            //store alphabet in char array
            alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
            //count the occurrence of each letter
            countLetters();
            //create the histogram
            drawGraph();
        }

        private void countLetters() {
            //initialize all character counts to 0
            for (int i = 0; i < 26; i++){
                count[i] = 0;
            }

            for (char j : text.toCharArray()){
                for (int k = 0; k < 26; k++){
                    //compare each letter in the alphabet to each character in the string
                    if (j == alphabet[k]){
                        //increase a letter's count each time it appears in the string
                        count[k] += 1;
                        //break from the inner loop once the correct character has been found
                        break;
                    }
                }
            }
        }

        private void drawGraph(){
            pane = new GridPane();
            pane.setHgap(1);
            double w = 10;
            double totalChar = text.length();
            //create rectangles for each letter of the alphabet
            for (int i = 0; i < 26; i++){
                //scale height of rectangle to the character's occurrence vs total number of characters in the file
                double h = (count[i]/totalChar)*200 + 1;
                System.out.println(h);
                rectangles[i] = new Rectangle(w,h);
                rectangles[i].setStroke(Color.BLACK);
                rectangles[i].setFill(Color.TRANSPARENT);
                pane.add(rectangles[i], i+1,0);
                GridPane.setValignment(rectangles[i], VPos.BASELINE);
                //add letter label below the rectangle
                Label letter = new Label(String.valueOf(alphabet[i]));
                pane.add(letter, i+1,1);
            }
            getChildren().addAll(pane);
        }

    }

}
