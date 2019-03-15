package program.ReadorWriteJSONFile;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ObservableLongValue;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class ReadMontantSeuilJSON {

    public static ObservableLongValue ReadMontantSeuilJSON(String filePath){

        ObservableLongValue montantseuil  = new SimpleLongProperty();

        try {
            FileReader reader = new FileReader(filePath);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            long seuilprix = (long) jsonObject.get("seuilprix");
            ((SimpleLongProperty) montantseuil).set(seuilprix);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  montantseuil;
    }
}
