package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.util.List;


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
