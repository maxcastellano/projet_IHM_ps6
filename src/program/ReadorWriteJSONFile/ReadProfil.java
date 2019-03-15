package program.ReadorWriteJSONFile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import program.model.ProfilModel;


import java.io.FileReader;

public class ReadProfil {

    public static ProfilModel readFromJSON(String filePath){
        ProfilModel profil =new ProfilModel() ;
        try{
            FileReader reader = new FileReader(filePath);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            JSONArray lang= (JSONArray) jsonObject.get("profil");
            for(Object o : lang){
                JSONObject innerObj = (JSONObject) o;
                String nom = (String) innerObj.get("nom");
                String prénom = (String) innerObj.get("prénom");
                String mail = (String) innerObj.get("mail");
                String ville = (String) innerObj.get("ville");
                Double seuil = (Double) innerObj.get("seuil");
                Float seuilf = Float.parseFloat(String.valueOf(seuil));

                profil=new ProfilModel(nom,prénom,mail,ville,seuilf);


            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  profil;
    }
}
