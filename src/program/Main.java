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
import program.ReadorWriteJSONFile.ReadListeDepensesJSON;
import program.ReadorWriteJSONFile.ReadMontantDepenseJSON;
import program.ReadorWriteJSONFile.ReadMontantSeuilJSON;
import program.controller.AccueilController;
import program.model.Depense;

import static program.View.CSS;
import static program.View.START;
import static program.View.LISTE_DEPENSES;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));
        FXMLLoader loader = new FXMLLoader();

        ObservableList<Depense> depenseListe =  ReadListeDepensesJSON.readFromJSON(LISTE_DEPENSES);

        ObservableList<String> listedepenseObservable = FXCollections.observableArrayList();
        for (Depense depense: depenseListe) {
            listedepenseObservable.add(depense.getDate() + "  " +depense.getNom() + "  " + depense.getPrix()+"€" );
        }
        ObservableLongValue depenseobservalbe = ReadMontantDepenseJSON.ReadMontantDepenseJSON("src/resources/json/montantdepense.json");
        ObservableLongValue seuilobservable = ReadMontantSeuilJSON.ReadMontantSeuilJSON("src/resources/json/montantseuil.json");
        //Attach XML File
        Parent root = loader.load(getClass().getResourceAsStream(START));
        //Attach css
        root.getStylesheets().add(CSS);
        ((AccueilController)loader.getController()).init(listedepenseObservable, depenseobservalbe,seuilobservable);
        //Create the view
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Accueil");
       // primaryStage.getIcons().setAll(new Image(getClass().getResource(LOGO).toExternalForm()));
        //Show the view
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
