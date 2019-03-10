package program;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import program.controller.AccueilController;

import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static program.View.START;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));
        FXMLLoader loader = new FXMLLoader();
        //Attach XML File
        Parent root = loader.load(getClass().getResourceAsStream(START));
        //Attach css
        ((AccueilController)loader.getController()).init();
        //Create the view
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Accueil");
        //Show the view
        primaryStage.show();
        System.out.println(LocalDateTime.now());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
