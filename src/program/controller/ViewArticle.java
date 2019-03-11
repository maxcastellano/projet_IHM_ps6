package program.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import program.ReadArticleJSON;
import program.View;
import program.WriteArticleJSON;
import program.model.Article;

import javafx.event.*;

public class ViewArticle {

    Article currentarticle;

    @FXML
    private Label categoriearticle;
    @FXML
    private Label categorie;

    @FXML
    private Button suppbutton;

    @FXML
    private Button modifierbutton;

    @FXML
    private Label nomarticle;

    @FXML
    private Label prixarticle;

    @FXML
    private Button okbutton;

    private ReadArticleJSON reader;

    private WriteArticleJSON writer;
    private static ObservableList<Article> articleObservableList= FXCollections.observableArrayList();



    public void display(Article article){
        this.currentarticle = article;



        this.nomarticle.setText(this.currentarticle.getNom());
        this.prixarticle.setText(this.currentarticle.getStrPrix().getValue());
        this.categoriearticle.setText(this.currentarticle.getCatégorie().toSTring());
        this.reader = new ReadArticleJSON();
        articleObservableList = reader.readFromJSON(View.ARTICLEJSON);
        this.writer = new WriteArticleJSON();

        okbutton.setOnAction(e -> {back();});
        suppbutton.setOnAction(event -> supprimer());



    }
    void back() {
        Stage window =  (Stage) okbutton.getScene().getWindow();
        window.close();
    }

    void supprimer(){

        writer.clear();
        for(Article article: articleObservableList) {
            if(article.getNom().equals(currentarticle.getNom()) && article.getPrix().equals(currentarticle.getPrix())){
                    /** DO NOTHING**/
            }else{
                this.writer.addArticle(article);
            }
        }
        this.writer.writeFile();
        Stage window =  (Stage) okbutton.getScene().getWindow();
        window.close();

    }

}
