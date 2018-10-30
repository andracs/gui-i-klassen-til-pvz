package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/** Et eksempel på brug af GUI, som vi har udviklet i klassen. Passer til kapitel 14 */
public class Main extends Application {

    int frameCounter = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Button btZ = new Button("Attack Zombie");
        Button btP = new Button("Attack Plant");
        btZ.setLayoutX(40.0);
        btZ.setLayoutY(40.0);
        btP.setWrapText(true);


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
                btP.setText( btP.getText() + " Av. ");
            }
        });

        // Det samme som ovenover, men med Lambda
        btP.setOnAction((e) -> {
            System.out.println("Hjælper zombien mod planten.");
            btZ.setWrapText(true);
            btZ.setMaxWidth(120.0);
            btZ.setText( btZ.getText() + " Av. ");
        });

        // Vi laver en TREDJES scene også, FOR zombies
        Stage stage3 = new Stage();
        Pane pane3 = new Pane();
        Scene scene3 = new Scene(pane3, 600, 250);
        stage3.setScene(scene3);
        stage3.setX(800);
        stage3.show();

        final ImageView imv = new ImageView();
        final Image image2 = new Image("res/male/Walk (1).png");
        imv.setImage(image2);
        imv.setFitWidth(160);
        imv.setFitHeight(200);
        //imv.setSmooth(true);
        final HBox pictureRegion = new HBox();
        pictureRegion.getChildren().add(imv);
        pane3.getChildren().add(pictureRegion);

        EventHandler<ActionEvent> eventHandler = event -> {
            int spriteNum = ((++frameCounter)%10)+1;
            String sprite = "res/male/Walk ("+ spriteNum + ").png";
            System.out.println(sprite);
            final Image image3 = new Image(sprite);
            imv.setImage(image3);
            pictureRegion.setLayoutX(pictureRegion.getLayoutX()+1);
        };

        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(100), eventHandler)
        );

        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
