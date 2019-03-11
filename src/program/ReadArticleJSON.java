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
                Double prix2 = (Double) innerObj.get("prix");
                Float prix = Float.parseFloat(String.valueOf(prix2));

                String cat = (String)innerObj.get("catégorie");
                Catégorie catégorie = Catégorie.valueOf(cat);
                listArticle.add(new Article(nom, prix, catégorie));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  listArticle;
    }
}
