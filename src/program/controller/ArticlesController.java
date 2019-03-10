package program.controller;

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
import program.model.Article;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static program.View.ACCUEIL;
import static program.View.VIEWARTICLE;

public class ArticlesController {

    @FXML
    private ComboBox<Catégorie> comboBox;

    @FXML
    private Button buttonAccueil;

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

    public void init(){
        reader = new ReadArticleJSON();
        articleObservableList = reader.readFromJSON(View.ARTICLEJSON);

        list.setItems(articleObservableList);
        nomcolumn.setCellValueFactory(cellData -> cellData.getValue().getStrNom());
        prixcolumn.setCellValueFactory(cellData -> cellData.getValue().getStrPrix());


        comboBox.getItems().setAll(Catégorie.values());
        comboBox.getSelectionModel().select(0);

        buttonAccueil.setOnAction(event -> gotoAccueil());
    }

    public void afficher(){
        list.setOnMouseClicked(e -> {
                    try {
                        currentArticle = list.getSelectionModel().getSelectedItem(); //L'incident sur lequel on a cliqué
                        if(currentArticle!= null && e.getClickCount() == 2) {

                            FXMLLoader loader = new FXMLLoader();

                            Stage window;
                            Parent parent;
                            try {

                                //ViewArticle viewArticle = new ViewArticle(currentArticle);
                                //loader.setController(viewArticle);
                                parent = loader.load(getClass().getResourceAsStream(View.VIEWARTICLE));

                                ((ViewArticle) loader.getController()).display(currentArticle);

                                Scene scene = new Scene(parent);

                                window = new Stage();
                                window.setScene(scene);
                                window.setTitle("Article");
                                window.show();

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

            ((AccueilController)loader.getController()).init();

            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
