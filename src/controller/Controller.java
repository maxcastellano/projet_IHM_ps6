package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Model;


public class Controller {
    @FXML
    private ListView<String> listeDepense;

    public void init(){
        Model model = new Model();
        listeDepense.setItems(model.getListDepense());
    }
}
