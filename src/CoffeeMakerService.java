import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CoffeeMakerService {
    private Long noOfOutlets;
    private HashMap<String,Long> totalItemsQuantity;
    private HashMap<String,HashMap<String,Long>> beverages;

    public Long getNoOfOutlets() {
        return noOfOutlets;
    }

    public void setNoOfOutlets(Long noOfOutlets) {
        this.noOfOutlets = noOfOutlets;
    }

    public HashMap<String, Long> getTotalItemsQuantity() {
        return totalItemsQuantity;
    }

    public void setTotalItemsQuantity(HashMap<String, Long> totalItemsQuantity) {
        this.totalItemsQuantity = totalItemsQuantity;
    }

    public HashMap<String, HashMap<String, Long>> getBeverages() {
        return beverages;
    }

    public void setBeverages(HashMap<String, HashMap<String, Long>> beverages) {
        this.beverages = beverages;
    }

    public ArrayList<String> getBeveragesName(){
        ArrayList<String> beveragesName = new ArrayList<String>();
        beveragesName.addAll(beverages.keySet());
        return beveragesName;
    }

    public  boolean canPrepareBeverage(String beverageName){
        HashMap<String,Long> beverageItems = beverages.get(beverageName);
        Iterator item = beverageItems.entrySet().iterator();
        boolean res=true;
        while(item.hasNext()){
            Map.Entry<String,Long> pair = ( Map.Entry<String,Long>)item.next();
            String itemName = pair.getKey();
            Long itemQuantity = pair.getValue();
            if(totalItemsQuantity.get(itemName)<itemQuantity){
                res=false;
                break;
            }
        }
        return res;
    }

    /**
     * Checks if the beverage can be prepared or not.
     * @return
     */
    public  void prepareBeverage(String beverageName){
        HashMap<String,Long> beverageItems = beverages.get(beverageName);
        for (Map.Entry<String, Long> pair : beverageItems.entrySet()) {
            String itemName = pair.getKey();
            Long itemQuantity = pair.getValue();
            totalItemsQuantity.put(itemName, totalItemsQuantity.get(itemName)-itemQuantity);
        }
    }

    /**
     * Returns the reason why the beverage cannot be prepared.
     * @return
     */
    public  String getReasonForNotPreparation(String beverageName){
        HashMap<String,Long> beverageItems = beverages.get(beverageName);
        Iterator item = beverageItems.entrySet().iterator();
        String res="";
        while(item.hasNext()){
            Map.Entry<String,Long> pair = ( Map.Entry<String,Long>)item.next();
            String itemName = pair.getKey();
            Long itemQuantity = pair.getValue();
            if(totalItemsQuantity.get(itemName)<itemQuantity){
                res= beverageName+" cannot be prepared because item "+itemName+" is not sufficient";
                break;
            }
        }
        return res;
    }

    /**
     * Returns the beverages which cannot be served by checking if all the items in the beverages are present in the machine.
     * @return
     */
    public HashMap<String, String> getNotAvailableBeverages(){
        Iterator iterator = beverages.entrySet().iterator();
        Map.Entry  pair,pair2;
        Iterator data;
        HashMap<String,String> notAvailable = new HashMap<>();
        HashMap<String, Long> beverageValues;
        while (iterator.hasNext()){
            pair = (Map.Entry) iterator.next();
            beverageValues = beverages.get(pair.getKey());
            data = beverageValues.entrySet().iterator();
            while(data.hasNext()) {
                pair2 = (Map.Entry) data.next();
                if(!totalItemsQuantity.containsKey(pair2.getKey())){
                    notAvailable.put((String) pair.getKey(),(String) pair.getKey() + " cannot be prepared because " + (String) pair2.getKey() + " is not available");
                    break;
                }
            }
        }
        return notAvailable;
    }


}
