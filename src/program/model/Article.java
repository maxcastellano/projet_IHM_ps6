package program.model;

import javafx.beans.property.*;

public class Article {
    private LongProperty prix;
    private  StringProperty nom;

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

    public void setPrix(Long prix){ this.prix = new SimpleLongProperty(prix) ;}

    public void setNom(String nom){ this.nom = new SimpleStringProperty(nom);}

    public StringProperty getStrPrix(){
        return new SimpleStringProperty(this.prix.getValue().toString() + " €");}

    public StringProperty getStrNom(){return this.nom;}

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Article)) return false;
        Article o = (Article) obj;
        return (o.nom == this.nom && o.prix == this.prix);
    }
}
