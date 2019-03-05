package program.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import program.View;
import program.model.ProfilModel;

import javax.swing.text.TableView;
import java.io.IOException;

public class ProfilController {

    @FXML
    private Text firstname;

    @FXML
    private Text name;

    @FXML
    private Text email;

    @FXML
    private Text city;

    @FXML
    private Text limit;

    @FXML
    private Button accbouton;

    @FXML
    private Button partagerbouton;

    @FXML
    private Button modifierprofil;

    public void init (){

        ProfilModel profilModel = new ProfilModel("Maxime","CASTELLANO","thune@unice.fr","New York",300);

        firstname.setText(profilModel.getFirstname());
        name.setText(profilModel.getName());
        email.setText(profilModel.getEmail());
        city.setText(profilModel.getCity());
        limit.setText(String.valueOf(profilModel.getSeuil()));

        accbouton.setOnAction(event -> gotoacc(event));
        partagerbouton.setOnAction(event -> partagerMessage(event));
        modifierprofil.setOnAction(event -> allerdansmodifierprofil(event));
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

    @FXML
    private void partagerMessage(ActionEvent event){
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Partage");
        alert.setContentText("Vous avez partagé vos informations");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void allerdansmodifierprofil(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent modifparent = loader.load(getClass().getResourceAsStream(View.MODIFIER_PORFIL));
            Scene modifscene = new Scene(modifparent);

            Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            window.setScene(modifscene);
            window.setTitle("Modifier Profil");

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

