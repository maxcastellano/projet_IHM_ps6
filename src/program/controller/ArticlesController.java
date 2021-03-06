package program.controller;

import javafx.beans.value.ObservableLongValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import program.Catégorie;
import program.ReadArticleJSON;
import program.View;
import program.WriteArticleJSON;
import program.model.Article;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static program.View.ACCUEIL;
import static program.View.CSS;
import static program.View.VIEWARTICLE;

public class ArticlesController {

    @FXML
    private ComboBox<Catégorie> comboBox;

    @FXML
    private Button buttonAccueil;

    @FXML
    private Button buttonAdd;

    @FXML
    private TableView<Article> list;

    private static ObservableList<Article> articleObservableList= FXCollections.observableArrayList();

    @FXML
    private TableColumn<Article, String> nomcolumn;

    @FXML
    private TableColumn<Article, String> prixcolumn;

    @FXML
    private TextField recherche;

    private ReadArticleJSON reader;

    private Article currentArticle =null;
    private ObservableList<String> listedepensesObservable;
    private long depensemontant;
    private long seuilmontant;
    private ObservableLongValue depenseobservable;
    private ObservableLongValue seuilobservable;

    public void init(ObservableList<String> listedepenses, ObservableLongValue depenseobservable,ObservableLongValue seuilobservable){
        this.depenseobservable = depenseobservable;
        this.seuilobservable = seuilobservable;
        listedepensesObservable = listedepenses;
        reader = new ReadArticleJSON();


        nomcolumn.setCellValueFactory(cellData -> cellData.getValue().getStrNom());
        prixcolumn.setCellValueFactory(cellData -> cellData.getValue().getStrPrix());


        comboBox.getItems().setAll(Catégorie.values());
        comboBox.getSelectionModel().select(0);

        articleObservableList=reader.readFromJSON(View.ARTICLEJSON);
        list.setItems(articleObservableList);

        buttonAccueil.setOnAction(event -> gotoAccueil());

        buttonAdd.setOnAction(event -> this.createArticle());

        this.recherche.setVisible(false);

      //  comboBox.setOnAction(event -> this.sort());
    }


   /* private void sort(){

        this.comboBox.setOnShown(e -> {
            ObservableList<Article> tmp = reader.readFromJSON(View.ARTICLEJSON);
            articleObservableList =  FXCollections.observableArrayList();

            for( Article article : tmp){
                System.out.println(article.getCatégorie());
                if( article.getCatégorie() == this.comboBox.getValue()){
                    System.out.println("salut");
                    articleObservableList.add(article);
                }

            }
        });

    }*/



    public void afficher(){
        list.setOnMouseClicked(e -> {
                    try {
                        currentArticle = list.getSelectionModel().getSelectedItem();
                        if(currentArticle!= null && e.getClickCount() == 2) {

                            FXMLLoader loader = new FXMLLoader();

                            Stage window;
                            Parent parent;
                            try {

                                parent = loader.load(getClass().getResourceAsStream(View.VIEWARTICLE));
                                parent.getStylesheets().add(CSS);

                                ((ViewArticle) loader.getController()).display(currentArticle);

                                Scene scene = new Scene(parent);

                                window = new Stage();
                                window.setScene(scene);
                                window.setTitle("Article");
                                window.showAndWait();
                                articleObservableList = reader.readFromJSON(View.ARTICLEJSON);
                                list.setItems(articleObservableList);

                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }

                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
        );
    }


    private void gotoAccueil() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/polytech_home.fxml"));

        try {
            Stage window = (Stage) buttonAccueil.getScene().getWindow();

            Parent accueilparent = loader.load(getClass().getResourceAsStream(ACCUEIL));
            Scene accueilscene = new Scene(accueilparent);
            window.setScene(accueilscene);
            window.setTitle("Accueil");

            ((AccueilController)loader.getController()).init(listedepensesObservable,this.depenseobservable,this.seuilobservable);

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
            articleObservableList = reader.readFromJSON(View.ARTICLEJSON);
            list.setItems(articleObservableList);


        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

}
