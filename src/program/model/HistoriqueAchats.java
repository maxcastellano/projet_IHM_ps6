package program.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueAchats {

    private List<String> listAchats;

    public HistoriqueAchats(){
        listAchats = new ArrayList();
        listAchats.add("Achat 1");
        listAchats.add("Achat 2");
        listAchats.add("Achat 3");
        listAchats.add("Achat 4");
    }

    public ObservableList<String> getListAchats(){
        return FXCollections.observableArrayList(listAchats);
    }

}
