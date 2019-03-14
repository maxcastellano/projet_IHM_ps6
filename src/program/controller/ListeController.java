package program.controller;

import javafx.beans.value.ObservableLongValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import program.View;
import program.model.Article;
import program.model.ListCourse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ListeController {

    @FXML
    private Button payerbouton;

    @FXML
    private Button retourliste;

    @FXML
    private Text total;

    @FXML
    private Text nomliste;

    @FXML
    private ListView<String> articlesliste;

    private ListCourse listCoursechoisie;
    private String date;
    private String listepaye;

    private static ObservableList<ListCourse> listCourseObservableList;
    private static ObservableList<String>listedepenseObservable;
    private ObservableLongValue depenseobservable;
    private ObservableLongValue seuilobservable;

    public void init(ObservableList<ListCourse> listCourses, ObservableList<String>listedepenseObservable, ObservableLongValue depenseobservalbe,ObservableLongValue seuilobservable){
        this.depenseobservable = depenseobservalbe;
        this.seuilobservable = seuilobservable;
        this.listedepenseObservable = listedepenseObservable;
        listCourseObservableList = listCourses;
        this.retourliste.setOnAction(event -> retourListeCourses());
        this.payerbouton.setOnAction(event -> ajouterlistedepense());
    }

    public void initListe(ListCourse listCourse){

        listCoursechoisie = listCourse;
        for (Article article:listCoursechoisie.getListCourse()) {
            articlesliste.getItems().add(article.getNom() + "  " +article.getPrix()+"€");
        }
        nomliste.setText(listCoursechoisie.getName());
        total.setText(listCoursechoisie.getPrix()+"€");
    }

    private void retourListeCourses(){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/Listes_Courses.fxml"));

        try {
            Stage window = (Stage) retourliste.getScene().getWindow();

            Parent listedescoursesparent = loader.load(getClass().getResourceAsStream(View.LISTE_COURSES));
            Scene listedescoursesscene = new Scene(listedescoursesparent);
            window.setScene(listedescoursesscene);
            window.setTitle("Liste des Courses");

            ((ListesCoursesController)loader.getController()).init(listCourseObservableList,listedepenseObservable,this.depenseobservable,this.depenseobservable);

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private String payerListeCourse(){
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION,"Pay ? ",ButtonType.YES, ButtonType.NO);
        alert.setTitle("Paiement");
        alert.setContentText("Êtes - vous sûr de vouloir payer cette liste ?");
        alert.setHeaderText(null);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES){
            date = LocalDateTime.now().getDayOfMonth()+"/"+LocalDateTime.now().getMonthValue()+"/"+LocalDateTime.now().getYear();
            listepaye = date + "  " + listCoursechoisie.getNom() + "  " + listCoursechoisie.getPrix()+"€";
        }
        return listepaye;
    }

    private void ajouterlistedepense(){
        String listeachetee = payerListeCourse();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/Historique d'achats.fxml"));
        listedepenseObservable.add(0,listeachetee);
        try {
            Stage window = (Stage) payerbouton.getScene().getWindow();
            Parent historiqueparent = loader.load(getClass().getResourceAsStream(View.HISTORIQUE_DACHATS));
            Scene historiquescene = new Scene(historiqueparent);

            window.setScene(historiquescene);
            window.setTitle("Liste des Dépenses");

            ((HistoriqueAchatController) loader.getController()).initListsDepenses(listeachetee);
            ((HistoriqueAchatController)loader.getController()).totaldepense(listCoursechoisie.getPrix(),this.depenseobservable,this.seuilobservable);
            ((HistoriqueAchatController) loader.getController()).init(listedepenseObservable,this.depenseobservable,this.seuilobservable);

            window.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
