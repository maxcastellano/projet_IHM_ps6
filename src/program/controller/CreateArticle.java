package program.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import program.Catégorie;
import program.ReadArticleJSON;
import program.View;
import program.WriteArticleJSON;
import program.model.Article;

public class CreateArticle {

    @FXML
    private Label categorie;

    @FXML
    private Button annulerbutton;

    @FXML
    private Button okbutton;

    @FXML
    private TextField nom;

    @FXML
    private TextField prix;

    @FXML
    private ComboBox<Catégorie> categori;

    private Article article;

    private ReadArticleJSON reader;

    private WriteArticleJSON writer;
    private static ObservableList<Article> articleObservableList= FXCollections.observableArrayList();


    public void init(){
        this.categori.getItems().setAll(Catégorie.values());
        categori.getSelectionModel().select(0);
        this.article = new Article(null,0);
        this.reader = new ReadArticleJSON();
        articleObservableList = reader.readFromJSON(View.ARTICLEJSON);
        this.writer = new WriteArticleJSON();

        this.okbutton.setOnAction(event -> sendArticle());

        this.annulerbutton.setOnAction(event -> cancel());
    }

    private void sendArticle(){


        if(nom.getText().equals("") || prix.getText().equals("")){
            Stage window =  (Stage) okbutton.getScene().getWindow();
            window.close();
        }else{
            this.article.setNom(nom.getText());
            this.article.setPrix(new Long(prix.getText()));
            articleObservableList.add(this.article);
            this.writer.clear();
            for(Article article: articleObservableList) {
                this.writer.addArticle(article);
            }
            this.writer.writeFile();
            Stage window =  (Stage) okbutton.getScene().getWindow();
            window.close();
        }


    }

    private void cancel(){
        Stage window =  (Stage) okbutton.getScene().getWindow();
        window.close();
    }

}
