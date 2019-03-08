package program.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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



    public void display(Article article){
        this.currentarticle = article;

        this.categorie.setVisible(false);
        this.categoriearticle.setVisible(false);

        this.nomarticle.setText(this.currentarticle.getNom());
        this.prixarticle.setText(this.currentarticle.getStrPrix().getValue());
        okbutton.setOnAction(e -> {back(e);});


    }
    void back(ActionEvent event) {
        Stage window =  (Stage) okbutton.getScene().getWindow();
        window.close();
    }

}
