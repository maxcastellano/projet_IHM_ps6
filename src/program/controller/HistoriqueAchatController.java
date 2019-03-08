package program.controller;

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

import java.io.IOException;


public class HistoriqueAchatController {
    @FXML
    private ListView<String> listHA;

    @FXML
    private ChoiceBox<String> choiceboxHA;

    @FXML
    private Button accueilbouton;

    public void init(){
        HistoriqueAchats historiqueAchatsModel = new HistoriqueAchats();
        listHA.setItems(historiqueAchatsModel.getListAchats());
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

            ((AccueilController)loader.getController()).init();

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
