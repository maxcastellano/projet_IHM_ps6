package program.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public void init(ObservableList<String>listedepensesObservable){
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

            ((AccueilController)loader.getController()).init(listedepensesObservable);

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     void initListsDepenses(String listepayee, long prix){
        listedepensesObservable.add(listepayee);
    }
}
