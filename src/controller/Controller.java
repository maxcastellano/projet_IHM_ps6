package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import model.Model;


public class Controller {
    @FXML
    private ListView<String> listeDepense;

    @FXML
    private ChoiceBox<String> choicebox;


    public void init(){
        Model model = new Model();
        listeDepense.setItems(model.getListDepense());

        choicebox.getItems().addAll("Annuel","Mensuel" ,"Hebdomadaire");
        choicebox.getSelectionModel().select(1);


    }


}
