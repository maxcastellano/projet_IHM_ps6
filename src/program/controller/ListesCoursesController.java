package program.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import program.View;
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

        this.choicebox.getItems().addAll("Toutes(chronologique)", "Favorites");
        this.choicebox.getSelectionModel().select(0);

        this.creerlistebouton.setOnAction(event -> goToCreateList(event));

        this.retouraccueil.setOnAction(event -> gotoAccueil());

    }


    private void gotoAccueil() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/polytech_home.fxml"));

        try {
            Stage window = (Stage) retouraccueil.getScene().getWindow();

            Parent accueilparent = loader.load(getClass().getResourceAsStream(ACCUEIL));
            Scene accueilscene = new Scene(accueilparent);
            window.setScene(accueilscene);
            window.setTitle("Accueil");

            ((AccueilController)loader.getController()).init();

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
