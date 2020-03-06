//Heather Cooper 100695827
//Assignment Q1

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.Random;

public class ShowCards extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new HBox();

        //generate random number for the first card
        Random r = new Random();
        int num = r.nextInt(54) + 1;
        String fileName = "/Cards/" + String.valueOf(num) + ".png";
        //get image from correspondingly named file
        Image image = new Image(fileName);
        //create imageview for card
        ImageView imageView1 = new ImageView(image);
        //add to pane
        pane.getChildren().add(imageView1);

        //create second card
        num = r.nextInt(54) + 1;
        fileName = "/Cards/" + String.valueOf(num) + ".png";
        Image image2 = new Image(fileName);
        ImageView imageView2 = new ImageView(image2);
        pane.getChildren().add(imageView2);

        //create third card
        num = r.nextInt(54) + 1;
        fileName = "/Cards/" + String.valueOf(num) + ".png";
        Image image3 = new Image(fileName);
        ImageView imageView3 = new ImageView(image3);
        pane.getChildren().add(imageView3);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);;
        primaryStage.show();
    }
}