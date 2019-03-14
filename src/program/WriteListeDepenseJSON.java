package program;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import program.model.Depense;

import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class WriteListeDepenseJSON {

    private JSONObject data;


    public WriteListeDepenseJSON(){
        data = new JSONObject();
        data.put("Liste des Dépenses", new JSONArray());
    }

    /**
     * ajout d'une depense dans le json
     * @param depense
     */
    public void addDepense(Depense depense){

        Map map = new LinkedHashMap();
        map.put("date",depense.getDate());
        map.put("nom", depense.getNom());
        map.put("prix", depense.getPrix());
        JSONObject jsonDepense = new JSONObject(map);

        JSONArray array= (JSONArray) data.get("Liste des Dépenses");


        array.add(jsonDepense);
    }

    /**
     * ecriture des listes de dépenses dans le fichier /listeDepenses.json
     */
    public void writeFile(){
        try(FileWriter file = new FileWriter("src/resources/json/listeDepenses.json")){
            file.write(data.toString());
            file.flush();
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
    public void clear(){
        data = new JSONObject();
        data.put("Liste des Dépenses", new JSONArray());
        writeFile();
    }
}
