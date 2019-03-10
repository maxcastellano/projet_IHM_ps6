package program.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javafx.event.*;

import program.model.ListCourse;

public class ViewList {

    ListCourse currentList;

    @FXML
    private ListView<String> contenuListe;

    @FXML
    private Button retour;

    @FXML
    private Button payer;

    void back(ActionEvent event) {
        Stage window =  (Stage) retour.getScene().getWindow();
        window.close();
    }

    public void display(ListCourse list){
        this.currentList = list;
        retour.setOnAction(e -> {back(e);});


    }
}
