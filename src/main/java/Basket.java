import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.Arrays;

public class Basket implements Serializable {
    private static final long serialVersionUID = 1L;
    public String[] products;
    public int[] prices;
    int[] selectProducts;


    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        selectProducts = new int[products.length];
    }

    public Basket() {

    }


    public void addToCart(int productNum, int amount) {
        if (selectProducts[productNum] == 0) {
            selectProducts[productNum] = amount;
        } else {
            int cnt = selectProducts[productNum] + amount;
            selectProducts[productNum] = cnt;
        }
    }

    public void printCart() {
        System.out.println("Список возможных товаров для покупки");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + " " + products[i] + " " + prices[i] + " руб.");
        }
    }

    public void amountOfProducts() {
        int sumProducts = 0;
        for (int i = 0; i < products.length; i++) {

            if (selectProducts[i] != 0) {
                int prodPrice = selectProducts[i] * prices[i];
                sumProducts += prodPrice;
                System.out.println(" " + products[i] + " " + prices[i] + " руб./шт. "
                        + prodPrice + " руб. в сумме");
            }
        }
        System.out.println("Итого: " + sumProducts + " руб.");
    }


    //JSON
    public void saveJSON(File textFile) {
        try (FileWriter fileWriter = new FileWriter(textFile)) {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.setPrettyPrinting().create();
            String json = gson.toJson(this);
            fileWriter.write(json);


        } catch (IOException | RuntimeException e) {
            e.printStackTrace();

        }

    }

    public static Basket loadFromJSONFile(File textFile) {
        Basket basket = new Basket();

        try (BufferedReader reader = new BufferedReader(new FileReader(textFile))) {
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            Gson gson = new Gson();
            basket = gson.fromJson(builder.toString(), Basket.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return basket;

    }


}
