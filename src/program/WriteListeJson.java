package program;

import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import program.model.Article;
import program.model.Depense;
import program.model.ListCourse;

import java.io.FileWriter;

public class WriteListeJson {

    private JSONObject data;


    public WriteListeJson(){
        data = new JSONObject();
        data.put("Courses", new JSONArray());
    }

    /**
     * ajout d'une liste dans le json
     * @param list
     */
    public void addList(ListCourse list){

        JSONObject listjson= new JSONObject();
        listjson.put("name", list.getNom());
        listjson.put("listCourse", new JSONArray());

        JSONArray array= (JSONArray) listjson.get("listCourse");

        for( Article article : list.getListCourse()){
            JSONObject articleobj = new JSONObject();
            articleobj.put("catégorie", article.getCatégorie().toSTring());
            articleobj.put("prix", article.getPrix());
            articleobj.put("nom", article.getNom());

            array.add(articleobj);
        }

        JSONArray array2= (JSONArray) data.get("Courses");

        array2.add(listjson);

    }

    /**
     * ecriture des listes de dépenses dans le fichier /listeDepenses.json
     */
    public void writeFile(){
        try(FileWriter file = new FileWriter("src/resources/json/listCourse.json")){
            file.write(data.toString());
            file.flush();
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}
