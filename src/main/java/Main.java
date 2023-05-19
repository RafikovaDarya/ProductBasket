import java.io.File;
import java.util.Scanner;

public class Main {
    public static File textFile = new File("basket.json");

    public static void main(String[] args) {
        ClientLog clientlog = new ClientLog();
        clientlog.logString(new String[]{"productNum", "amount"});

        String[] products = {"Хлеб", "Яблоки", "Молоко"};
        int[] prices = {100, 200, 300};
        Basket basket = null;

        if (textFile.exists()) {
            basket = Basket.loadFromJSONFile(textFile);
        } else {
            basket = new Basket(products, prices);
        }

        basket.printCart();
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if (input.equals("end")) {

                System.out.println("Ваша корзина: ");
                basket.amountOfProducts();
                clientlog.exportAsCSV(new File("log.csv"));
                break;
            }
            String[] parts = input.split(" ");
            int productNum = Integer.parseInt(parts[0]) - 1;
            int amount = Integer.parseInt(parts[1]);

            basket.addToCart(productNum, amount);
            clientlog.log(productNum, amount);


            basket.saveJSON(textFile);


        }


    }


}