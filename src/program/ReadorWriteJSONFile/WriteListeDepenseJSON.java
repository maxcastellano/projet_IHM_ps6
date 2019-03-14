package program.ReadorWriteJSONFile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import program.model.Depense;

import java.io.FileWriter;


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

        JSONObject jsonDepense = new JSONObject();

        jsonDepense.put("date",depense.getDate());
        jsonDepense.put("nom", depense.getNom());
        jsonDepense.put("prix", depense.getPrix());


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
