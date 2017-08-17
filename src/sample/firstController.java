package sample;

import javafx.animation.Transition;
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

    private double width = 600;
    private double height = 400;
    double start = 0;
    double target = 600;
    Rectangle2D rect = new Rectangle2D(0, 0, 600, 400);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initiateImage();
        animateViewport();
    }

    private void animateViewport() {
        //Needs to slowly update the XOffset value from 0 -> 600
        Transition t = new Transition() {
            {
                setCycleDuration(Duration.seconds(3));
            }
            @Override
            protected void interpolate(double frac) {
                System.out.println(frac);
                double offset = start + frac * (target - start);
                rect = new Rectangle2D(offset, 0, width, height);
                imgView.setViewport(rect);
            }
        };

        t.play();

    }

    private void initiateImage(){
        imgView.setImage(new Image("images/testTransition.png"));

        //*Min x Is the one that sets the offset in the image, where it draws the width and height from (top left)

        imgView.setViewport(new Rectangle2D(600, 0, width, height));
        imgView.setFitHeight(400);
        imgView.setFitWidth(600);
        imgView.setPreserveRatio(false);


        //imgView.fitHeightProperty().bind(mainPane.heightProperty());
        //imgView.setPreserveRatio(false);
        //imgView.fitWidthProperty().bind(mainPane.widthProperty());
    }
}
