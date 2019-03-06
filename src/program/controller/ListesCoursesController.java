package program.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import program.model.Article;
import program.model.ListCourse;


import java.io.IOException;
import java.util.ArrayList;

import static program.View.ACCUEIL;
import static program.View.CREER_LISTE;
import static program.View.LISTE_COURSES;

public class ListesCoursesController {

    @FXML
    private ListView<String> listeDepense;

    @FXML
    private ChoiceBox<String> choicebox;

    @FXML
    private Button retour;

    @FXML
    private Button retouraccueil;

    @FXML
    private Button creerlistebouton;

    public void init() {
        retouraccueil.setOnAction(event -> gotoAccueil(event));

        choicebox.getItems().addAll("Toutes(chronologique)", "Favorites");
        choicebox.getSelectionModel().select(0);

        creerlistebouton.setOnAction(event -> goToCreateList(event));
    }


    private void gotoAccueil(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader();

        Parent accueilparent;
        try {

            accueilparent = loader.load(getClass().getResource("../../resources/fxml/polytech_home.fxml"));
            Scene accueilscene = new Scene(accueilparent);

            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            window.setScene(accueilscene);
            window.setTitle("Accueil");

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goToCreateList(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader();

        Parent creerListeparent;
        try {

            creerListeparent = loader.load(getClass().getResource("../../resources/fxml/creeruneliste.fxml"));
            Scene creerListeScene = new Scene(creerListeparent);

            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            window.setScene(creerListeScene);
            window.setTitle("Creer Liste");

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
