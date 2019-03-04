package program.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ListCourse {

    private final IntegerProperty id;
    private ObservableList<Article> listCourse;
    private IntegerProperty prix;

    public ListCourse(List<Article> listCourse, int id){
        this.id = new SimpleIntegerProperty(id);
        this.listCourse = FXCollections.observableArrayList();
        int prixInter = 0;
        for(Article article : listCourse){
            this.listCourse.add(article);
            prixInter += article.getPrix();
        }
        this.prix = new SimpleIntegerProperty(prixInter);
    }

    public int getPrix() {
        return prix.get();
    }

    public int getId(){
        return id.get();
    }


}
