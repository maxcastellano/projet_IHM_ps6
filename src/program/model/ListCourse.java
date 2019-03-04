package program.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ListCourse {

    private final LongProperty id;
    private ObservableList<Article> listCourse;
    private LongProperty prix;

    public ListCourse(List<Article> listCourse, long id){
        this.id = new SimpleLongProperty(id);
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

    public long getId(){
        return id.get();
    }

    public ObservableList<Article> getListCourse(){
        return listCourse;
    }

}
