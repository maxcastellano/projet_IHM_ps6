package program.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class AccueilModel {
    private List<String> listDepense;

    public AccueilModel(){
        listDepense = new ArrayList();
        listDepense.add("2 Jan              7.50€");    //Tous les prix ont 4 tab
        listDepense.add("4 Jan              12.50€");
        listDepense.add("12 Jan             20.00€");
    }

    public ObservableList<String> getListDepense(){
        return FXCollections.observableArrayList(listDepense);
    }


}

