package program.controller;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ObservableLongValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import program.ReadorWriteJSONFile.ReadMontantSeuilJSON;
import program.ReadorWriteJSONFile.WriteMontantSeuilJSON;
import program.View;

import java.io.IOException;


public class ModifierProfilController {
    @FXML
    private Button enregistrerbouton;

    @FXML
    private Button retourbouton;

    @FXML
    private TextField nomtext;
    @FXML
    private TextField prenomtext;
    @FXML
    private TextField mailtext;
    @FXML
    private TextField villetext;
    @FXML
    private TextField seuiltext;

    private String nom, prenom, mail, ville;

    private ObservableList <String> listedepensesObservable;
    private ObservableLongValue seuilobservable;
    private ObservableLongValue depenseobservable;

    void init (ObservableList<String>listedepenseObservable,ObservableLongValue depenseobservable){
        this.listedepensesObservable = listedepenseObservable;
        this.depenseobservable = depenseobservable;
        nom =  nomtext.getText();
        prenom =  prenomtext.getText();
        mail =  mailtext.getCharacters().toString();
        ville =  villetext.getText();
        seuiltext.setText("0");

        seuilobservable = ReadMontantSeuilJSON.ReadMontantSeuilJSON("src/resources/json/montantseuil.json");

        this.enregistrerbouton.setOnAction(event -> enregistrer());
    }

    private void enregistrer(){
        long seuil = Long.valueOf(seuiltext.getText());
        ((SimpleLongProperty) seuilobservable).set(seuil);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/Profil.fxml"));
        WriteMontantSeuilJSON writeMontantSeuilJSON = new WriteMontantSeuilJSON(seuil);
        try {
            Stage window = (Stage) enregistrerbouton.getScene().getWindow();
            Parent accueilparent = loader.load(getClass().getResourceAsStream(View.PROFIL));
            Scene accueilscene = new Scene(accueilparent);

            window.setScene(accueilscene);
            window.setTitle("Profil");
            ((ProfilController)loader.getController()).modificationprofil(prenom,nom,mail,ville,seuil);
            ((ProfilController)loader.getController()).init(listedepensesObservable,depenseobservable,seuilobservable);

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
