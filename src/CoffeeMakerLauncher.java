public class CoffeeMakerLauncher {
    public static void main(String []argv){
        CoffeeMaker coffeeMaker = new CoffeeMaker(argv);
        coffeeMaker.start();
    }
}
