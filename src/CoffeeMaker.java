import java.util.ArrayList;
import java.util.HashMap;

public class CoffeeMaker {
    private String []inputArgs;
    CoffeeMakerService coffeeMakerService;
    CoffeeMaker(String []argv){
        inputArgs = argv;
    }

    /**
     * Start of the Coffee Maker.
     * @return
     */
    public void start(){
        CoffeeMakerHelper coffeeMakerHelper = new CoffeeMakerHelper(inputArgs[0]);
        coffeeMakerService = coffeeMakerHelper.parseJsonFile();
        computeCoffeeMaker();
    }

    /**
     * Computes and prints the output
     * @return
     */
    private void computeCoffeeMaker(){
        HashMap<String,String> notAvailableBeverages = coffeeMakerService.getNotAvailableBeverages();
        ArrayList<String> beverageNames = coffeeMakerService.getBeveragesName();
        for (String beverage: beverageNames) {
            if(notAvailableBeverages.containsKey(beverage)){
                System.out.println(notAvailableBeverages.get(beverage));
            }else{
                if(coffeeMakerService.canPrepareBeverage(beverage)){
                    coffeeMakerService.prepareBeverage(beverage);
                    System.out.println(beverage+" is prepared");
                }else{
                    System.out.println(coffeeMakerService.getReasonForNotPreparation(beverage));
                }
            }
        }
    }
}
