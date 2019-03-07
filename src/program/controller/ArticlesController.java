package program.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import program.Catégorie;
import program.ReadArticleJSON;
import program.View;
import program.model.Article;

import java.util.Arrays;
import java.util.List;

public class ArticlesController {

    @FXML
    private ComboBox<Catégorie> comboBox;


    @FXML
    private TableView<Article> list;

    private static ObservableList<Article> articleObservableList= FXCollections.observableArrayList();


    @FXML
    private TableColumn<Article, String> nomcolumn;

    @FXML
    private TableColumn<Article, String> prixcolumn;

    @FXML
    private TextField recherche;



    ReadArticleJSON reader;

    public void init(){
        reader = new ReadArticleJSON();
        articleObservableList = reader.readFromJSON(View.ARTICLEJSON);

        list.setItems(articleObservableList);
        nomcolumn.setCellValueFactory(cellData -> cellData.getValue().getStrNom());
        prixcolumn.setCellValueFactory(cellData -> cellData.getValue().getStrPrix());


        comboBox.getItems().setAll(Catégorie.values());
        comboBox.getSelectionModel().select(0);

    }





}
