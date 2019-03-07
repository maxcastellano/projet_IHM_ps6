package program.model;

import javafx.beans.property.*;

public class Article {
    private final LongProperty prix;
    private final StringProperty nom;

    public Article(String nom, long prix){
        this.prix = new SimpleLongProperty(prix);
        this.nom = new SimpleStringProperty(nom);
    }

    public long getPrix() {
        return prix.get();
    }

    public String getNom(){
        return nom.get();
    }

    public StringProperty getStrPrix(){
        return new SimpleStringProperty(this.prix.getValue().toString() + " €");}

    public StringProperty getStrNom(){return this.nom;}
}
