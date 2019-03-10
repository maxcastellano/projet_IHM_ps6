package program.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import program.ReadArticleJSON;
import program.ReadListCourseJSON;
import program.View;
import program.model.Article;
import program.model.ListCourse;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken.Name;
import static program.View.ACCUEIL;
import static program.View.CREER_LISTE;
import static program.View.LISTE_COURSES;

public class ListesCoursesController {

    @FXML
    private TableView<ListCourse> tablelistes;

    @FXML
    private TableColumn<ListCourse, String> nomcolonne;

    @FXML
    TableColumn<ListCourse, String> prixcolonne;

    @FXML
    private ChoiceBox<String> choicebox;

    @FXML
    private Button retouraccueil;

    @FXML
    private Button creerlistebouton;

    @FXML
    private Button detailsbouton;

    private ObservableList<Article> articles;
    private static ObservableList<ListCourse> listeCourseObservableList ;
    private static ObservableList<String> listeDepenseObservableList ;
    private ListCourse currentListeCourse = null;

    public void init(ObservableList<ListCourse> listCourses,ObservableList<String>listeDepenses) {
        this.listeDepenseObservableList = listeDepenses;
        listeCourseObservableList = listCourses;
        this.tablelistes.setItems(listeCourseObservableList);
        this.nomcolonne.setCellValueFactory(cellData -> cellData.getValue().getStrNom());
        this.prixcolonne.setCellValueFactory(cellData -> cellData.getValue().getStrPrix());


        //listecourses.getItems().addAll(listCourseObservableList);
        //this.listecourses.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.choicebox.getItems().addAll("Toutes(chronologique)", "Favorites");
        this.choicebox.getSelectionModel().select(0);

        this.creerlistebouton.setOnAction(event -> goToCreateList());

        this.retouraccueil.setOnAction(event -> gotoAccueil());

        this.articles = ReadArticleJSON.readFromJSON(View.ARTICLEJSON);

        this.detailsbouton.setOnAction(event -> voirDetails(listeDepenseObservableList));
    }

       void addListeCourse(ListCourse listCourse) {
            listeCourseObservableList.add(listCourse);
    }

    private void gotoAccueil() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/polytech_home.fxml"));

        try {
            Stage window = (Stage) retouraccueil.getScene().getWindow();

            Parent accueilparent = loader.load(getClass().getResourceAsStream(ACCUEIL));
            Scene accueilscene = new Scene(accueilparent);
            window.setScene(accueilscene);
            window.setTitle("Accueil");

            ((AccueilController)loader.getController()).init(listeDepenseObservableList);

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

            ((CreerListController)loader.getController()).init(this.articles,listeCourseObservableList,listeDepenseObservableList);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void voirDetails(ObservableList<String> listeDepenseObservableList){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/cliquersuruneliste.fxml"));
        currentListeCourse = tablelistes.getSelectionModel().getSelectedItem();
        try {
            Stage window = (Stage) detailsbouton.getScene().getWindow();
            Parent detailsListeparent = loader.load(getClass().getResourceAsStream(View.VIEWLISTE));
            Scene detailsListescene = new Scene(detailsListeparent);

            window.setScene(detailsListescene);
            window.setTitle("Liste");

            ((ListeController) loader.getController()).initListe(currentListeCourse);
            ((ListeController) loader.getController()).init(listeCourseObservableList,listeDepenseObservableList);

            window.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
