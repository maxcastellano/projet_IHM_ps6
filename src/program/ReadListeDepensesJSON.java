package program;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import program.model.Depense;
import java.io.FileReader;


public class ReadListeDepensesJSON {

    public static ObservableList<Depense> readFromJSON(String filePath){

        ObservableList<Depense> listeDepenses = FXCollections.observableArrayList();

        try {
            FileReader reader = new FileReader(filePath);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            JSONArray lang = (JSONArray) jsonObject.get("Liste des Dépenses");
            for(Object o : lang) {

                JSONObject innerObj = (JSONObject) o;
                String date = (String) innerObj.get("date");
                String nom = (String) innerObj.get("nom");
                long prix = (long) innerObj.get("prix");

                listeDepenses.add(new Depense(date,nom,prix));

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  listeDepenses;
    }
}
