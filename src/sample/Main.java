package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    static Stage mainStage;
    static Scene primaryScene;

    public static Parent root2;

    public static firstController first;
    public static secondController second;


    @Override
    public void start(Stage primaryStage) throws Exception{
        mainStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
        root2 = FXMLLoader.load(getClass().getResource("secondary.fxml"));
        primaryStage.setTitle("Test Transition");
        primaryScene = new Scene(root, 600, 400);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void showSecondaryScreen(){
        mainStage.getScene().setRoot(root2);
        second.fadeElementsIn();
    }

    public static List getDimensions(){
        return Arrays.asList(mainStage.getScene().widthProperty(), mainStage.getScene().heightProperty());
    }
}
