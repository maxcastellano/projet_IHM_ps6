package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Model;


public class Controller {
    @FXML
    private ListView<String> listeDepense;

    public void init(){
        Model model = new Model();
        listeDepense = new ListView<>();
        listeDepense.getItems().add(model.getListDepense().get(0));
        listeDepense.getItems().add(model.getListDepense().get(1));
        listeDepense.getItems().add(model.getListDepense().get(2));
    }
}
