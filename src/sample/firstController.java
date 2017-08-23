package sample;

import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class firstController implements Initializable{

    @FXML
    Pane mainPane;

    @FXML
    ImageView imgView;

    @FXML
    Button btnChange;

    private double width = 1920;
    private double height = 1080;
    double start = 0;
    double target = 790;
    Rectangle2D rect = new Rectangle2D(0, 0, 600, 400);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.first = this;
        initiateImage();
        //animateViewport();
    }

    private void slideRight(){
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

        slideRight.playFromStart();
    }

    private void slideLeft(){
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
    }

//    private void animateViewport() {
//        slideRight.setOnFinished(event -> slideLeft.play());
//        slideLeft.setOnFinished(event -> slideRight.play());
//        slideRight.play();
//    }

    private void initiateImage(){
        imgView.setImage(new Image("images/FullRes.png"));

        imgView.setViewport(new Rectangle2D(600, 0, width, height));
        imgView.fitHeightProperty().bind(mainPane.heightProperty());
        imgView.fitWidthProperty().bind(mainPane.widthProperty());
        imgView.setPreserveRatio(false);

        rect = new Rectangle2D(0, 0, width, height);
        imgView.setViewport(rect);

    }

    public void fadeElements(){
        double start = 1;
        double end = 0;
        Transition fadeElements = new Transition() {
            {
                setCycleDuration(Duration.seconds(0.5));
            }
            @Override
            protected void interpolate(double frac) {
                double opacity = start + frac * (end - start);
                btnChange.setOpacity(opacity);
            }
        };

        fadeElements.playFromStart();
    }

    public void startSceneSwitch(){
        fadeElements();
        slideRight();
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> Main.showSecondaryScreen());
        pause.play();
    }
}
