package program.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import program.model.ProfilModel;

import javax.swing.text.TableView;
import java.io.IOException;

public class ProfilController {

    @FXML
    private TableView tableView;

    @FXML
    private Button accbouton;

    @FXML
    private Button partagerbouton;

    public void init (){

        ProfilModel profilModel = new ProfilModel("Maxime","CASTELLANO","thune@unice.fr","New York",300);

        accbouton.setOnAction(event -> gotoacc(event));
    }


    private void gotoacc(ActionEvent event){

        try {
            Parent accparent = FXMLLoader.load(getClass().getResource("../../resources/fxml/polytech_home.fxml"));
            Scene accscene = new Scene(accparent);

            Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            window.setScene(accscene);
            window.setTitle("Accueil");

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

