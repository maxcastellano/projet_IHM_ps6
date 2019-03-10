package program.controller;

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

    private static ObservableList<ListCourse> listCourseObservableList;

    public void init(ObservableList<ListCourse> listCourses){
        listCourseObservableList = listCourses;
        this.retourliste.setOnAction(event -> retourListeCourses());
        this.payerbouton.setOnAction(event -> payerListeCourse());
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

            ((ListesCoursesController)loader.getController()).init(listCourseObservableList);

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void payerListeCourse(){
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Paiement");
        alert.setContentText("Êtes - vous sûr de vouloir payer cette liste ?");
        if(alert.getResult() == ButtonType.YES){

        }
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
