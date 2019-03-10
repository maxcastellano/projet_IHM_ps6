package program.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ListCourse {

    private final StringProperty name;
    private ObservableList<Article> listCourse;
    private LongProperty prix;
    private final String nom;


    public ListCourse(List<Article> listCourse, String name){
        this.name = new SimpleStringProperty(name);
        this.nom = name;
        this.listCourse = FXCollections.observableArrayList();
        int prixInter = 0;
        for(Article article : listCourse){
            this.listCourse.add(article);
            prixInter += article.getPrix();
        }
        this.prix = new SimpleLongProperty(prixInter);
    }

    public long getPrix() {
        return prix.get();
    }

    public String getName(){
        return name.get();
    }

    public ObservableList<Article> getListCourse(){
        return listCourse;
    }

    public String getNom() {
        return nom;
    }
    public StringProperty getStrPrix(){
        return new SimpleStringProperty(this.prix.getValue().toString() + " €");}

    public StringProperty getStrNom(){return this.name;}
}
