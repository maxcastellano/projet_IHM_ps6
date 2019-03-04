package program.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import program.model.AccueilModel;

import javafx.event.*;
import program.model.Article;


import java.io.IOException;

import static program.View.HISTORIQUE_DACHATS;
import static program.View.LISTE_COURSES;
import static program.View.PROFIL;


public class AccueilController {
    @FXML
    private ListView<String> listeDepense;

    @FXML
    private ChoiceBox<String> choicebox;

    @FXML
    private Button listedecoursesbouton;

    @FXML
    private Button articles;

    @FXML
    private Button historiquebouton;

    @FXML
    private Button profilbouton;


    public void init(){
        AccueilModel accueilModel = new AccueilModel();
        listeDepense.setItems(accueilModel.getListDepense());
        choicebox.getItems().addAll("Annuel","Mensuel" ,"Hebdomadaire");
        choicebox.getSelectionModel().select(1);

        listedecoursesbouton.setOnAction(event -> gotolistescourses(event));
        profilbouton.setOnAction(event -> gotoprofil(event));
        historiquebouton.setOnAction(event -> gothistorique(event));
    }

    private void goTo(String view, String title,ActionEvent event){

        FXMLLoader loader = new FXMLLoader();

        Parent parent;
        try {
            parent = loader.load(getClass().getResourceAsStream(view));
            Scene scene = new Scene(parent);

            Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle(title);

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void gotolistescourses(ActionEvent event) {

        //this.goTo("Liste des dépenses",LISTE_COURSES, event);

        FXMLLoader loader = new FXMLLoader();

        Parent listedepenseparent;
        try {
            listedepenseparent = loader.load(getClass().getResourceAsStream(LISTE_COURSES));
            Scene listedepenseScene = new Scene(listedepenseparent);

            Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            window.setScene(listedepenseScene);
            window.setTitle("Liste des dépenses");

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void gotoprofil(ActionEvent event){

        FXMLLoader loader = new FXMLLoader();

        Parent profilparent;
        try {
            profilparent = loader.load(getClass().getResourceAsStream(PROFIL));
            Scene profilscene = new Scene(profilparent);

            Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            window.setScene(profilscene);
            window.setTitle("Arcticles");

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void gothistorique(ActionEvent event){

        FXMLLoader loader = new FXMLLoader();

        Parent historiqueparent;
        try {
            historiqueparent = loader.load(getClass().getResourceAsStream(HISTORIQUE_DACHATS));
            Scene historiquescene = new Scene(historiqueparent);

            Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            window.setScene(historiquescene);
            window.setTitle("Historique d'achats");

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
