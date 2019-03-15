package program.controller;

import javafx.beans.value.ObservableLongValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import program.ReadArticleJSON;
import program.View;
import program.WriteListeJson;
import program.model.Article;
import program.model.ListCourse;

import java.io.IOException;
import java.util.ArrayList;

import static program.View.CSS;


public class CreerListController {

    @FXML
    private Button ajouterbouton;

    @FXML
    private  Button addArticleButton;

    @FXML
    private Button annulerbouton;

    @FXML
    private Button creerbouton;

    @FXML
    private ListView<String> listearticles ;

    @FXML
    private ListView<String> listecree;

    @FXML
    private TextField saisienom;

    @FXML
    private Text total;

    private Float prixtotal;

    private ArrayList<Article> articleArrayList = new ArrayList<>();
    private ArrayList<Article> articleschoisis = new ArrayList<>();
    private static ObservableList<ListCourse> listeCourseObservableList ;
    private static ObservableList<String> listeDepenseObservableList;
    private ObservableLongValue depenseobservable;
    private ObservableLongValue seuilobservable;
    private ReadArticleJSON reader;

    public void init (ObservableList<Article>listedesarticles, ObservableList<ListCourse>listCourses, ObservableList<String>listeDepenses, ObservableLongValue depenseobservalbe, ObservableLongValue seuilobservable){
        this.depenseobservable = depenseobservalbe;
        this.seuilobservable = seuilobservable;
        listeDepenseObservableList = listeDepenses;
        listeCourseObservableList = listCourses;
        for (Article article: listedesarticles){
            String articleString = article.getNom() + "\t\t" + article.getPrix()+"€";
            this.listearticles.getItems().add(articleString);
            this.articleArrayList.addAll(listedesarticles);
        }
        this.listearticles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.ajouterbouton.setOnAction(event -> addTo());
        this.prixtotal = Float.parseFloat("0");
        this.creerbouton.setOnAction(event -> creerListe());
        this.saisienom.getCharacters();
        this.annulerbouton.setOnAction(event -> retourListeCourses());
        this.addArticleButton.setOnAction(event -> this.createArticle());


        reader = new ReadArticleJSON();
    }

    private void addTo() {

        ObservableList listArticles = listearticles.getSelectionModel().getSelectedItems();
            for (Object articlechoisi : listArticles) {
                this.listecree.getItems().add(String.valueOf(articlechoisi));
                this.total.setText(String.valueOf(prixtotal+=getPrix())+ " €");
            }
        }


    private Float getPrix (){

        ObservableList listArticles = listearticles.getSelectionModel().getSelectedItems();
        for (Article article : articleArrayList) {
            String articleString = article.getNom() + "\t\t" + article.getPrix() + "€";
            for (Object articleobj : listArticles) {
                if (articleString.equals(String.valueOf(articleobj))) {
                    this.articleschoisis.add(article);
                    return article.getPrix();
                }
            }
        }
        return Float.parseFloat("0");
    }

    private void creerListe(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/Listes_Courses.fxml"));
        if(this.articleschoisis.isEmpty() || this.saisienom.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez choisir un nom pour la liste et ajouter au moins un article");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }
        ListCourse listCourse= new ListCourse(this.articleschoisis,this.saisienom.getText());

        WriteListeJson writeListeJson = new WriteListeJson();
        for(ListCourse listCourse1: listeCourseObservableList ){
            writeListeJson.addList(listCourse1);
        }
        writeListeJson.addList(listCourse);
        writeListeJson.writeFile();

        try {
            Stage window = (Stage) creerbouton.getScene().getWindow();

            Parent listedescoursesparent = loader.load(getClass().getResourceAsStream(View.LISTE_COURSES));
            Scene listedescoursesscene = new Scene(listedescoursesparent);
            window.setScene(listedescoursesscene);
            window.setTitle("Liste des Courses");

            ((ListesCoursesController)loader.getController()).init(listeCourseObservableList,listeDepenseObservableList,this.depenseobservable,this.seuilobservable);

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void retourListeCourses(){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/Listes_Courses.fxml"));

        try {
            Stage window = (Stage) annulerbouton.getScene().getWindow();

            Parent listedescoursesparent = loader.load(getClass().getResourceAsStream(View.LISTE_COURSES));
            Scene listedescoursesscene = new Scene(listedescoursesparent);
            window.setScene(listedescoursesscene);
            window.setTitle("Liste des Courses");

            ((ListesCoursesController)loader.getController()).init(listeCourseObservableList,listeDepenseObservableList,this.depenseobservable,this.seuilobservable);

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createArticle(){
        FXMLLoader loader = new FXMLLoader();

        Stage window;
        Parent parent;
        try {

            parent = loader.load(getClass().getResourceAsStream(View.CREER_ARTICLE));
            parent.getStylesheets().add(CSS);

            ((CreateArticle) loader.getController()).init();

            Scene scene = new Scene(parent);

            window = new Stage();
            window.setScene(scene);
            window.setTitle("New Article");
            window.showAndWait();
             ObservableList<Article> listeCourseObservableListtmp  = reader.readFromJSON(View.ARTICLEJSON);
            for (Article article: listeCourseObservableListtmp){
                String articleString = article.getNom() + "\t\t" + article.getPrix()+"€";
                this.listearticles.getItems().add(articleString);
                this.articleArrayList.addAll(listeCourseObservableListtmp);
            }


        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

}

