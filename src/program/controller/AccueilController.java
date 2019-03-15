package program.controller;

import javafx.beans.value.ObservableLongValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import program.ReadListCourseJSON;
import program.ReadorWriteJSONFile.ReadListeDepensesJSON;
import program.View;
import program.model.AccueilModel;

import program.model.Depense;
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

    @FXML
    private Text depense;

    @FXML
    private Text seuil;

    @FXML
    private LineChart<?, ?> graph;

    private ObservableList<ListCourse> listCourses;
    private  ObservableList<String> listedepenseObservable = FXCollections.observableArrayList();
    private  ObservableList<Depense> listedepenseObservableGraph = FXCollections.observableArrayList();
    private  ObservableLongValue depenseobservable;
    private  ObservableLongValue seuilobservable;

    public void init(ObservableList<String> listedepenseObservableListe, ObservableLongValue depenseobservalbe, ObservableLongValue seuilobservable){

        this.depenseobservable = depenseobservalbe;
        this.seuilobservable = seuilobservable;
        this.depense.setText(this.depenseobservable.get()+"€");
        this.depense.setFill(Color.rgb(0,255,0,1));
        if(this.depenseobservable.longValue() > this.seuilobservable.longValue() && this.seuilobservable.longValue() > 0){this.depense.setFill(Color.rgb(255, 0, 0, 1));}
        this.seuil.setText(this.seuilobservable.get()+"€");

        listedepenseObservable = listedepenseObservableListe;
        listeDepense.setItems(listedepenseObservable);
        choicebox.getItems().addAll("Annuel","Mensuel" ,"Hebdomadaire");
        choicebox.getSelectionModel().select(1);

        this.listCourses = ReadListCourseJSON.readFromJSON(LISTEJSON);

        listedecoursesbouton.setOnAction(event -> gotolistescourses());
        profilbouton.setOnAction(event -> gotoprofil());
        historiquebouton.setOnAction(event -> gothistorique());
        articlesbouton.setOnAction(event -> gotoarticle());

        ReadListeDepensesJSON readDepense = new ReadListeDepensesJSON();
        this.listedepenseObservableGraph = readDepense.readFromJSON(View.LISTE_DEPENSES);


        XYChart.Series series = new XYChart.Series();
        int i=0;
        for (Depense depense: this.listedepenseObservableGraph){
            series.getData().add(new XYChart.Data(String.valueOf(i),depense.getPrix()));
            i++;
        }

        this.graph.getData().addAll(series);
        
    }

    private void gotolistescourses() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/Listes_Courses.fxml"));

        try {
            Stage window = (Stage) listedecoursesbouton.getScene().getWindow();
            Parent listedepenseparent = loader.load(getClass().getResourceAsStream(LISTE_COURSES));

            Scene coursesScene = new Scene(listedepenseparent);
            window.setScene(coursesScene);
            window.setTitle("Listes Courses");

            ((ListesCoursesController)loader.getController()).init(this.listCourses,listedepenseObservable,this.depenseobservable, this.seuilobservable);

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

            ((ProfilController)loader.getController()).init(listedepenseObservable,this.depenseobservable,this.seuilobservable);

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

            ((HistoriqueAchatController)loader.getController()).init(listedepenseObservable,this.depenseobservable, this.seuilobservable);

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

            ((ArticlesController)loader.getController()).init(listedepenseObservable,this.depenseobservable, this.seuilobservable);

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
