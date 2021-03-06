package program;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import program.model.Article;
import program.model.ListCourse;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadListCourseJSON {

    public static ObservableList<ListCourse> readFromJSON(String filePath){
        ObservableList<ListCourse> listCourse = FXCollections.observableArrayList();
        try {
            FileReader reader = new FileReader(filePath);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            JSONArray lang= (JSONArray) jsonObject.get("Courses");
            for(Object o : lang){
                List<Article> listCourseToAdd = new ArrayList<>();
                JSONObject innerObj = (JSONObject) o;
                String name = (String) innerObj.get("name");
                JSONArray listCourseRead = (JSONArray) innerObj.get("listCourse");
                for(Object oList : listCourseRead){
                    JSONObject innerObjList = (JSONObject) oList;


                    String nom = (String) innerObjList.get("nom");

                   Double prix2 = (Double) innerObjList.get("prix");
                    Float prix = Float.parseFloat(String.valueOf(prix2));

                    String cat = (String) innerObjList.get("catégorie");

                    Catégorie catégorie = Catégorie.valueOf(cat);
                    listCourseToAdd.add(new Article(nom, prix,catégorie));
                }

                listCourse.add(new ListCourse(listCourseToAdd, name));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  listCourse;
    }
}
