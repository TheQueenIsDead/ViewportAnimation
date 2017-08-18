package sample;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class firstController implements Initializable{

    @FXML
    Pane mainPane;

    @FXML
    ImageView imgView;

    private double width = 1920;
    private double height = 1080;
    double start = 0;
    double target = 790;
    Rectangle2D rect = new Rectangle2D(0, 0, 600, 400);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initiateImage();
        animateViewport();
    }

    private void animateViewport() {
        //Needs to slowly update the XOffset value from 0 -> 600
        Transition slideRight = new Transition() {
            {
                setCycleDuration(Duration.seconds(3));
            }
            @Override
            protected void interpolate(double frac) {
                double offset = start + frac * (target - start);
                rect = new Rectangle2D(offset, 0, width, height);
                imgView.setViewport(rect);
            }
        };

        Transition slideLeft = new Transition() {
            {
                setCycleDuration(Duration.seconds(3));
            }
            @Override
            protected void interpolate(double frac) {
                double offset = start + frac * (target - start);
                rect = new Rectangle2D(target - offset, 0, width, height);
                imgView.setViewport(rect);
            }
        };


        slideRight.setOnFinished(event -> slideLeft.play());
        slideLeft.setOnFinished(event -> slideRight.play());
        slideRight.play();

    }

    private void initiateImage(){
        imgView.setImage(new Image("images/FullRes.png"));

        //*Min x Is the one that sets the offset in the image, where it draws the width and height from (top left)

        imgView.setViewport(new Rectangle2D(600, 0, width, height));
        imgView.fitHeightProperty().bind(mainPane.heightProperty());
        imgView.fitWidthProperty().bind(mainPane.widthProperty());
        imgView.setPreserveRatio(false);


        //imgView.fitHeightProperty().bind(mainPane.heightProperty());
        //imgView.setPreserveRatio(false);
        //imgView.fitWidthProperty().bind(mainPane.widthProperty());
    }
}
