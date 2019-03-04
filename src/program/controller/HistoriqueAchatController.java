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

        accueilbouton.setOnAction(event -> gotoaccueil(event));
    }

    private void gotoaccueil(ActionEvent event){

        FXMLLoader loader = new FXMLLoader();

        Parent accueilparent;
        try {


            accueilparent = loader.load(getClass().getResource("../../resources/fxml/polytech_home.fxml"));
            Scene accueilscene = new Scene(accueilparent);

            Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            window.setScene(accueilscene);
            window.setTitle("Accueil");

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
