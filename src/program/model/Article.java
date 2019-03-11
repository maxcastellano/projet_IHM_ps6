package program.model;

import javafx.beans.property.*;
import program.Catégorie;

public class Article {
    private FloatProperty prix;
    private  StringProperty nom;
    private Catégorie catégorie;

    public Article(String nom, Float prix, Catégorie catégorie){
        this.prix = new SimpleFloatProperty(prix);
        this.nom = new SimpleStringProperty(nom);
        this.catégorie = catégorie;
    }

    public Float getPrix() {
        return prix.get();
    }

    public String getNom(){
        return nom.get();
    }

    public Catégorie getCatégorie(){return catégorie;}

    public void setPrix(Float prix){ this.prix = new SimpleFloatProperty(prix) ;}

    public void setNom(String nom){ this.nom = new SimpleStringProperty(nom);}

    public  void setCatégorie(Catégorie catégorie){ this.catégorie = catégorie;}

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
