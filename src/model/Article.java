package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Article {
    private final IntegerProperty prix;
    private final StringProperty nom;

    public Article(String nom, int prix){
        this.prix = new SimpleIntegerProperty(prix);
        this.nom = new SimpleStringProperty(nom);
    }

    public int getPrix() {
        return prix.get();
    }

    public String getNom(){
        return nom.get();
    }
}
