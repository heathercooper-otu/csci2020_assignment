//Heather Cooper 100695827
//Assignment Q3

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import java.util.Random;

public class DragPoints extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();

        //create base circle
        Circle circle = new Circle(200,200,100);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.TRANSPARENT);
        pane.getChildren().add(circle);

        Circle[] point = new Circle[3];
        Line[] lines = new Line[3];
        Text[] angles = new Text[3];

        for (int i = 0; i < 3; i++) {
            lines[i] = new Line(0, 0, 0, 0);
            point[i]= new Circle(0,0,0);
            angles[i] = new Text(" ");
        }

        for (int i = 0; i < 3; i++){
            //create the points of the triangle
            point[i]= new Circle(0,0,5);
            point[i].setStroke(Color.BLACK);
            point[i].setFill(Color.RED);

            //randomly generate an angle for each point
            Random r = new Random();
            int num = r.nextInt(360);
            //calculate the coordinates of the point, while keeping it on the circumference of the circle
            point[i].setCenterX(circle.getCenterX() + circle.getRadius()*Math.cos(Math.toRadians(num)));
            point[i].setCenterY(circle.getCenterY() + circle.getRadius()*Math.sin(Math.toRadians(num)));
            //add point to pain
            pane.getChildren().add(point[i]);

            final int j = i;
            final Line[] oldLines = lines;
            final Text[] oldAngles = angles;
            point[j].setOnMouseDragged(e ->{
                //calculate angle that the mouse is at while dragging the selected point
                double mouseAngle = (Math.atan2((e.getY() - circle.getCenterY()),(e.getX() - circle.getCenterX())));
                //use angle to calculat the new coordinates of the point
                point[j].setCenterX(circle.getCenterX() + circle.getRadius()*Math.cos(mouseAngle));
                point[j].setCenterY(circle.getCenterY() + circle.getRadius()*Math.sin(mouseAngle));

                //remove the previous lines of the triangle and add the new ones
                pane.getChildren().removeAll(oldLines);
                pane.getChildren().addAll(drawLines(point, circle, oldLines));
                //remove the previous angles and add the new ones
                pane.getChildren().removeAll(oldAngles);
                pane.getChildren().addAll(labelAngles(point, circle, oldAngles));
            });

        }
        //draw the initial lines of the triangle
        lines = drawLines(point, circle, lines);
        pane.getChildren().addAll(lines);
        //add initial angles to the triangle
        angles = labelAngles(point, circle, angles);
        pane.getChildren().addAll(angles);

        Scene scene = new Scene(pane,400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    Line[] drawLines(Circle[] points, Circle circle, Line[] lines){
        //create the first line
        lines[0].setStartX(points[0].getCenterX());
        lines[0].setStartY(points[0].getCenterY());
        lines[0].setEndX(points[1].getCenterX());
        lines[0].setEndY(points[1].getCenterY());

        //create the second line
        lines[1].setStartX(points[0].getCenterX());
        lines[1].setStartY(points[0].getCenterY());
        lines[1].setEndX(points[2].getCenterX());
        lines[1].setEndY(points[2].getCenterY());

        //create the third line
        lines[2].setStartX(points[1].getCenterX());
        lines[2].setStartY(points[1].getCenterY());
        lines[2].setEndX(points[2].getCenterX());
        lines[2].setEndY(points[2].getCenterY());
        return lines;
    }

    Text[] labelAngles(Circle[] points, Circle circle, Text[] angleLabels){
        //Text[] angleLabels = new Text[3];
        double[] angles = new double[3];
        double[] distance = new double[3];
        //calculate the distance of each line in the triangle
        distance[0] = Math.sqrt(Math.pow((points[1].getCenterX() - points[2].getCenterX()),2)
                + Math.pow((points[1].getCenterY() - points[2].getCenterY()),2));
        distance[1] = Math.sqrt(Math.pow((points[0].getCenterX() - points[2].getCenterX()),2)
                + Math.pow((points[0].getCenterY() - points[2].getCenterY()),2));
        distance[2] = Math.sqrt(Math.pow((points[1].getCenterX() - points[0].getCenterX()),2)
                + Math.pow((points[1].getCenterY() - points[0].getCenterY()),2));

        //calculate each point's angle:
        angles[0] = Math.acos((distance[0]*distance[0] - distance[1]*distance[1] - distance[2]*distance[2])
                / (-2*distance[1]*distance[2]));
        angles[1] = Math.acos((distance[1]*distance[1] - distance[0]*distance[0] - distance[2]*distance[2])
                / (-2*distance[0]*distance[2]));
        angles[2] = Math.acos((distance[2]*distance[2] - distance[1]*distance[1] - distance[0]*distance[0])
                / (-2*distance[1]*distance[0]));

        //convert all angles to degrees, round them, and then convert to text
        for(int i = 0; i < 3; i++){
            angles[i] = Math.toDegrees(angles[i]);
            angles[i] = Math.round(angles[i]);
            angleLabels[i] = new Text(points[i].getCenterX() + 15, points[i].getCenterY() - 10, Double.toString(angles[i]));
        }

        return angleLabels;
    }
}
