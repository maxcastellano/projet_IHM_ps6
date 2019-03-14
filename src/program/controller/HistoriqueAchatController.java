package program.controller;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ObservableLongValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import program.View;
import program.model.HistoriqueAchats;
import program.model.ListCourse;

import java.io.IOException;


public class HistoriqueAchatController {
    @FXML
    private ListView<String> listHA;

    @FXML
    private ChoiceBox<String> choiceboxHA;

    @FXML
    private Button accueilbouton;

    private ObservableList<String> listedepensesObservable = FXCollections.observableArrayList();
    private ObservableLongValue depenseobservable;
    private ObservableLongValue seuilobservable;

     void init(ObservableList<String>listedepensesObservable, ObservableLongValue depenseobservalbe, ObservableLongValue seuilobservable){
        this.depenseobservable = depenseobservalbe;
        this.seuilobservable = seuilobservable;
        this.listedepensesObservable = listedepensesObservable;
        listHA.setItems(listedepensesObservable);
        choiceboxHA.getItems().addAll("Annuel","Mensuel" ,"Hebdomadaire");
        choiceboxHA.getSelectionModel().select(1);

        accueilbouton.setOnAction(event -> gotoaccueil());
    }

    private void gotoaccueil(){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/polytech_home.fxml"));

        try {
            Stage window = (Stage) accueilbouton.getScene().getWindow();
            Parent accueilparent = loader.load(getClass().getResourceAsStream(View.ACCUEIL));
            Scene accueilscene = new Scene(accueilparent);

            window.setScene(accueilscene);
            window.setTitle("Accueil");
            ((AccueilController)loader.getController()).init(listedepensesObservable,depenseobservable,seuilobservable);

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     void initListsDepenses(String listepayee){
        listedepensesObservable.add(listepayee);
    }
    void totaldepense(long prix,ObservableLongValue depenseobservable,ObservableLongValue seuilobservable){
         long total = depenseobservable.get();
         long seuil = seuilobservable.get();
         total+=prix;
        ((SimpleLongProperty) depenseobservable).set(total);
        if(total > seuil && seuil > 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Vous avez dépassé votre seuil budgétaire de "+ (total - seuil)+"€");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

}
