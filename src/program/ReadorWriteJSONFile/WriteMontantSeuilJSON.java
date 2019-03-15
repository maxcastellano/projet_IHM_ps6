package program.ReadorWriteJSONFile;

import org.json.simple.JSONObject;

import java.io.FileWriter;

public class WriteMontantSeuilJSON {

    private JSONObject jsonObject = new JSONObject();

    public WriteMontantSeuilJSON(long seuilprix) {
        try{
            FileWriter fileWriter = new FileWriter("src/resources/json/montantseuil.json");


            jsonObject.put("seuilprix",seuilprix);

            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
