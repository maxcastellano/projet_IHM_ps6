package program.ReadorWriteJSONFile;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ObservableLongValue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class ReadMontantDepenseJSON {

    public static ObservableLongValue ReadMontantDepenseJSON(String filePath){

        ObservableLongValue montantdepense  = new SimpleLongProperty();

        try {
            FileReader reader = new FileReader(filePath);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

                long depenseprix = (long) jsonObject.get("depenseprix");
                ((SimpleLongProperty) montantdepense).set(depenseprix);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  montantdepense;
    }
}
