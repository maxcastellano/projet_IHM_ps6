package program;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import program.model.Article;

import java.io.FileReader;

public class ReadArticleJSON {

    public static ObservableList<Article> readFromJSON(String filePath){
        ObservableList<Article> listArticle = FXCollections.observableArrayList();
        try{
            FileReader reader = new FileReader(filePath);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            JSONArray lang= (JSONArray) jsonObject.get("Articles");
            for(Object o : lang){
                JSONObject innerObj = (JSONObject) o;
                String nom = (String) innerObj.get("nom");
                long prix = (long) innerObj.get("prix");
                listArticle.add(new Article(nom, prix));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  listArticle;
    }
}
