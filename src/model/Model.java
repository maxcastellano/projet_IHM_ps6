package model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<String> listDepense;

    public Model(){
        listDepense = new ArrayList();
        listDepense.add("2 Jan  7.50€");    //Tous les prix ont une tab
        listDepense.add("4 Jan  12.50€");
        listDepense.add("12 Jan  20.00€");
    }

    public List<String> getListDepense(){
        return listDepense;
    }

}

