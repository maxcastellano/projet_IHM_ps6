package program.controller;

import javafx.beans.value.ObservableLongValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import program.ReadorWriteJSONFile.ReadMontantSeuilJSON;
import program.ReadorWriteJSONFile.ReadProfil;
import program.ReadorWriteJSONFile.WriteMontantSeuilJSON;
import program.View;
import program.model.ProfilModel;

import javax.swing.text.TableView;
import java.io.IOException;

import static program.View.ACCUEIL;
import static program.View.MODIFIER_PORFIL;

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

    private static ObservableList<String> listeDepensesObservable;
    private ObservableLongValue depenseobservable;
    private ObservableLongValue seuilobservable;


    public void init (ObservableList<String>listedepenseObservable, ObservableLongValue depenseobservable, ObservableLongValue seuilobservable){
        this.depenseobservable = depenseobservable;
        this.seuilobservable = seuilobservable;
        listeDepensesObservable = listedepenseObservable;
        ReadProfil reader = new ReadProfil();
        ProfilModel profilModel = reader.readFromJSON(View.PROFILJSON);

        firstname.setText(profilModel.getFirstname());
        name.setText(profilModel.getName());
        email.setText(profilModel.getEmail());
        city.setText(profilModel.getCity());
        limit.setText(String.valueOf(profilModel.getSeuil()));

        accbouton.setOnAction(event -> gotoacc());
        partagerbouton.setOnAction(event -> partagerMessage());
        modifierprofil.setOnAction(event -> allerdansmodifierprofil());
    }

    private void gotoacc(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/polytech_home.fxml"));

        try {
            Stage window = (Stage) accbouton.getScene().getWindow();

            Parent accueilparent = loader.load(getClass().getResourceAsStream(ACCUEIL));
            Scene accueilscene = new Scene(accueilparent);
            window.setScene(accueilscene);
            window.setTitle("Accueil");

            ((AccueilController)loader.getController()).init(listeDepensesObservable,this.depenseobservable,this.seuilobservable);

            window.show();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void partagerMessage(){
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Partage");
        alert.setContentText("Vous avez partagé vos informations");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    void modificationprofil(String prenom, String nom, String mail, String ville, long seuil){
        this.firstname.setText(nom);
        this.name.setText(prenom);
        this.email.setText(mail);
        this.city.setText(ville);

        this.limit.setText(String.valueOf(seuil));
    }

    private void allerdansmodifierprofil() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/modifierprofil.fxml"));

        try {
            Stage window = (Stage) modifierprofil.getScene().getWindow();

            Parent modifierparent = loader.load(getClass().getResourceAsStream(MODIFIER_PORFIL));
            Scene modifierscene = new Scene(modifierparent);
            window.setScene(modifierscene);
            window.setTitle("Modifier profil");

            ((ModifierProfilController)loader.getController()).init(listeDepensesObservable,this.depenseobservable);

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

