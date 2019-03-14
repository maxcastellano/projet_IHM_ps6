package program.ReadorWriteJSONFile;

import org.json.simple.JSONObject;

import java.io.FileWriter;

public class WriteMontantDepenseJSON {

    private JSONObject jsonObject = new JSONObject();

    public WriteMontantDepenseJSON(long depenseprix) {
        try{
            FileWriter fileWriter = new FileWriter("src/resources/json/montantdepense.json");


            jsonObject.put("depenseprix",depenseprix);

            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
