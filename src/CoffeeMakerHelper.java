
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;

public class CoffeeMakerHelper {
    private String fileName;

    CoffeeMakerHelper(String fileName){
        this.fileName="/TestFiles/" + fileName;
    }
    /**
     * Reads the json and converts into hashMap.
     * @return
     */
    public CoffeeMakerService parseJsonFile() {
        CoffeeMakerService coffeeMakerService = new CoffeeMakerService();
        try {
            String absfilePath = new File("").getAbsolutePath();
            JSONParser parser = new JSONParser();
            JSONObject data =  (JSONObject)parser.parse(new FileReader(absfilePath+fileName));
            HashMap machineData =  (HashMap) data.get("machine");
            HashMap outlets =(HashMap)machineData.get("outlets");
            coffeeMakerService.setNoOfOutlets((Long) outlets.get("count_n"));
            HashMap<String,Long> totalItemsQuantity =(HashMap<String,Long>)machineData.get("total_items_quantity");
            coffeeMakerService.setTotalItemsQuantity(totalItemsQuantity);
            HashMap<String, HashMap<String,Long>> beverages =(HashMap<String, HashMap<String,Long>>)machineData.get("beverages");
            coffeeMakerService.setBeverages(beverages);
        }catch (Exception e){
            System.out.print(Arrays.toString(e.getStackTrace()));
        }
        return coffeeMakerService;
    }

}
