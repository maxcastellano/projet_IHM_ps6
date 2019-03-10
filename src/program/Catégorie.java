package program;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public enum Catégorie {

    Tout ("Tout"),
    Entrée ("Entrée"),
    Plat ("Plat"),
    Dessert ("Dessert"),
    Autres ("Autres");

    private String name="";

    Catégorie(String name){
        this.name = name;
    }

    public String toSTring(){
        return name;
    }

}
