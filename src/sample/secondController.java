package sample;

import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class secondController implements Initializable{

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
        Main.second = this;
        initiateImage();
        //animateViewport();
        btnChange.setOpacity(0);
    }

    private void initiateImage(){
        System.out.println("In initiate!");
        imgView.setImage(new Image("images/FullRes.png"));

        imgView.setViewport(new Rectangle2D(600, 0, width, height));
        imgView.fitHeightProperty().bind(mainPane.heightProperty());
        imgView.fitWidthProperty().bind(mainPane.widthProperty());
        imgView.setPreserveRatio(false);

        rect = new Rectangle2D(790, 0, width, height);
        imgView.setViewport(rect);
    }

    public void fadeElementsIn() {
        double start = 0;
        double end = 1;
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
}
