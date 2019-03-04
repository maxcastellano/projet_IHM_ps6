package program;

import program.controller.AccueilController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static program.View.ACCUEIL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));

        FXMLLoader loader = new FXMLLoader();
        //Create a accueilController
        AccueilController accueilController =new AccueilController();
        //Attach accueilController
        loader.setController(accueilController);
        //Attach XML File
        Parent root = loader.load(getClass().getResourceAsStream(View.ACCUEIL));
        //Attach css
        //Initialize accueilController
        accueilController.init();
        //Create the view
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Accueil");
        //Show the view
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
