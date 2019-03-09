package program.controller;

import javafx.collections.FXCollections;
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
import program.ReadArticleJSON;
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
    private  ListView<String> listecourses;

    @FXML
    private ChoiceBox<String> choicebox;

    @FXML
    private Button retour;

    @FXML
    private Button retouraccueil;

    @FXML
    private Button creerlistebouton;

    private ObservableList<Article> articles;
    private static ObservableList<String> listCourseObservableList = FXCollections.observableArrayList();

    public void init() {
        listecourses.getItems().addAll(listCourseObservableList);
        this.choicebox.getItems().addAll("Toutes(chronologique)", "Favorites");
        this.choicebox.getSelectionModel().select(0);

        this.creerlistebouton.setOnAction(event -> goToCreateList());

        this.retouraccueil.setOnAction(event -> gotoAccueil());

        this.articles = ReadArticleJSON.readFromJSON(View.ARTICLEJSON);

    }

    public void addListeCourse(String name, String prix){
        listCourseObservableList.add(name +"\t" + prix);
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

    private void goToCreateList() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/creeruneliste.fxml"));

        try {
            Stage stage = (Stage) creerlistebouton.getScene().getWindow();
            Parent creerListeparent = loader.load(getClass().getResourceAsStream(CREER_LISTE));
            Scene creerListeScene = new Scene(creerListeparent);

            stage.setScene(creerListeScene);
            stage.setTitle("Creer Liste");

            ((CreerListController)loader.getController()).init(this.articles);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
