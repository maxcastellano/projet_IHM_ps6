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
import program.ReadorWriteJSONFile.ReadProfil;
import program.ReadorWriteJSONFile.WriteMontantSeuilJSON;
import program.ReadorWriteJSONFile.WriteProfil;
import program.View;
import program.model.ProfilModel;

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
        ReadProfil reader = new ReadProfil();
        ProfilModel profilModel = reader.readFromJSON(View.PROFILJSON);

        nomtext.setText(profilModel.getName());
        prenomtext.setText(profilModel.getFirstname());
        mailtext.setText(profilModel.getEmail());
        villetext.setText(profilModel.getCity());
        seuiltext.setText(String.valueOf(profilModel.getSeuil()));

        seuilobservable = ReadMontantSeuilJSON.ReadMontantSeuilJSON("src/resources/json/montantseuil.json");

        this.enregistrerbouton.setOnAction(event -> enregistrer());
    }

    private void enregistrer(){
        float seuil = Float.valueOf(seuiltext.getText());
        ((SimpleLongProperty) seuilobservable).set((long)seuil);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/Profil.fxml"));
        WriteMontantSeuilJSON writeMontantSeuilJSON = new WriteMontantSeuilJSON((long)seuil);
        ProfilModel profilModel =new ProfilModel(nomtext.getText(), prenomtext.getText(),mailtext.getText(),villetext.getText(), new Float(seuiltext.getText()));
        WriteProfil writer = new WriteProfil();
        writer.update(profilModel);
        try {
            Stage window = (Stage) enregistrerbouton.getScene().getWindow();
            Parent accueilparent = loader.load(getClass().getResourceAsStream(View.PROFIL));
            Scene accueilscene = new Scene(accueilparent);

            window.setScene(accueilscene);
            window.setTitle("Profil");
            ((ProfilController)loader.getController()).init(listedepensesObservable,depenseobservable,seuilobservable);

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
