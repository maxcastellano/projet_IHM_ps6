package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import model.AccueilModel;


public class AccueilController {
    @FXML
    private ListView<String> listeDepense;

    @FXML
    private ChoiceBox<String> choicebox;




    public void init(){
        AccueilModel accueilModel = new AccueilModel();
        listeDepense.setItems(accueilModel.getListDepense());
        choicebox.getItems().addAll("Annuel","Mensuel" ,"Hebdomadaire");
        choicebox.getSelectionModel().select(1);


    }


}
