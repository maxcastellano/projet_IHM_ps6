package program.ReadorWriteJSONFile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import program.model.ProfilModel;

import java.io.FileWriter;

public class WriteProfil {

    private JSONObject data;


    public WriteProfil(){
        data = new JSONObject();
        data.put("profil", new JSONArray());
    }


    /**
     * update du profil dans le json
     * @param profil
     */
    public void update(ProfilModel profil){
        JSONObject jsonProfil = new JSONObject();

        jsonProfil.put("seuil",profil.getSeuil() );
        jsonProfil.put("ville", profil.getCity());
        jsonProfil.put("mail", profil.getEmail());
        jsonProfil.put("prénom", profil.getFirstname());
        jsonProfil.put("nom", profil.getName());

        JSONArray array= (JSONArray) data.get("profil");

        array.add(jsonProfil);

        try(FileWriter file = new FileWriter("src/resources/json/profil.json")){
            file.write(data.toString());
            file.flush();
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}
