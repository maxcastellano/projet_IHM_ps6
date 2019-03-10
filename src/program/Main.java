package program;

import javafx.application.Application;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ObservableLongValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        ObservableList<String> listedepenseObservable = FXCollections.observableArrayList();
        ObservableLongValue depenseobservalbe = new SimpleLongProperty();
         ((SimpleLongProperty) depenseobservalbe).set(0);
        ObservableLongValue seuilobservable = new SimpleLongProperty();
        ((SimpleLongProperty) seuilobservable).set(200);
        //Attach XML File
        Parent root = loader.load(getClass().getResourceAsStream(START));
        //Attach css
        ((AccueilController)loader.getController()).init(listedepenseObservable, depenseobservalbe,seuilobservable);
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
