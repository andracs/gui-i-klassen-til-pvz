package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/** Et eksempel på brug af GUI, som vi har udviklet i klassen. Passer til kapitel 14 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Button btZ = new Button("Attack Zombie");
        Button btP = new Button("Attack Plant");
        btZ.setLayoutX(40.0);
        btZ.setLayoutY(40.0);

        /* Pane bruges som en beholder til vores GUI elementer
           se mere på https://docs.oracle.com/javafx/2/get_started/hello_world.htm
         */
        Pane pane = new Pane();

        // Vi prøver at udskrive en masse random cirkler
        String[] farver = {"red", "green", "blue", "yellow"};
        for (int i = 0; i < 25; i++) {
            double r =  (int) (Math.random()*20);
            System.out.println(r);
            Circle circle = new Circle();
            circle.setRadius(r*5);
            double x =  (int) (Math.random()*10);
            circle.setCenterX(x*25);
            double y =  (int) (Math.random()*10);
            circle.setCenterY(y*25);
            double f =  (int) (Math.random()*3);
            circle.setFill(Paint.valueOf(farver[(int)f]));
            circle.setOpacity(0.1);
            pane.getChildren().add(circle);
        }
        pane.getChildren().add(btZ);
        Scene scene = new Scene(pane, 200, 250);
        primaryStage.setTitle("Nu er jeg i gang med GUI,yep!");
        primaryStage.setScene(scene);
        primaryStage.setX(100);
        primaryStage.show();

        // Vi laver en anden scene også, bar for sjovt
        Stage stage = new Stage();
        Scene scene2 = new Scene(btP, 200, 250);
        stage.setScene(scene2);
        stage.setX(500);
        stage.show();

        // Den her har jeg tilføjet efter undervisning. Det er en action handler, dvs.
        // en metode, som reagerer på klik på button "btZ"
        btZ.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("Hjælper planten mod zombien.");
                btP.setWrapText(true);
                btP.setText( btP.getText() + " Av. ");
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
