package program.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import program.ReadListCourseJSON;
import program.model.AccueilModel;

import program.model.ListCourse;


import java.io.IOException;

import static program.View.*;


public class AccueilController {

    @FXML
    private ListView<String> listeDepense;

    @FXML
    private ChoiceBox<String> choicebox;

    @FXML
    private Button listedecoursesbouton;

    @FXML
    private Button articlesbouton;

    @FXML
    private Button historiquebouton;

    @FXML
    private Button profilbouton;

    private ObservableList<ListCourse> listCourses;
    private static ObservableList<String> listedepenseObservable = FXCollections.observableArrayList();
    public void init(ObservableList<String> listedepenseObservableListe){
        listedepenseObservable = listedepenseObservableListe;
        AccueilModel accueilModel = new AccueilModel();
        listeDepense.setItems(listedepenseObservable);
        choicebox.getItems().addAll("Annuel","Mensuel" ,"Hebdomadaire");
        choicebox.getSelectionModel().select(1);
        this.listCourses = ReadListCourseJSON.readFromJSON(LISTEJSON);

        listedecoursesbouton.setOnAction(event -> gotolistescourses());
        profilbouton.setOnAction(event -> gotoprofil());
        historiquebouton.setOnAction(event -> gothistorique());
        articlesbouton.setOnAction(event -> gotoarticle());
        
    }

    private void gotolistescourses() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/Listes_Courses.fxml"));

        try {
            Stage window = (Stage) listedecoursesbouton.getScene().getWindow();
            Parent listedepenseparent = loader.load(getClass().getResourceAsStream(LISTE_COURSES));

            Scene coursesScene = new Scene(listedepenseparent);
            window.setScene(coursesScene);
            window.setTitle("Listes Courses");

            ((ListesCoursesController)loader.getController()).init(this.listCourses,listedepenseObservable);

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void gotoprofil(){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/Profil.fxml"));

        try {

            Stage window = (Stage) profilbouton.getScene().getWindow();
            Parent profilparent = loader.load(getClass().getResourceAsStream(PROFIL));

            Scene profilscene = new Scene(profilparent);
            window.setScene(profilscene);
            window.setTitle("Profil");

            ((ProfilController)loader.getController()).init(listedepenseObservable);

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gothistorique(){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/Historique d'achats.fxml"));

        try {

            Stage window = (Stage) historiquebouton.getScene().getWindow();
            Parent historiqueparent = loader.load(getClass().getResourceAsStream(HISTORIQUE_DACHATS));
            Scene historiquescene = new Scene(historiqueparent);

            window.setScene(historiquescene);
            window.setTitle("Historique d'achats");

            ((HistoriqueAchatController)loader.getController()).init(listedepenseObservable);

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gotoarticle(){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/Articles.fxml"));

        try {
            Stage window = (Stage) articlesbouton.getScene().getWindow();
            Parent articleparent = loader.load(getClass().getResourceAsStream(ARTICLES));
            Scene scene = new Scene(articleparent);
            window.setScene(scene);
            window.setTitle("Articles");

            ((ArticlesController)loader.getController()).init(listedepenseObservable);

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
