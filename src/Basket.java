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

    public String[] getProducts() {
        return products;
    }

    public int[] getPrices() {
        return prices;
    }

    public void setProducts(String[] products) {
        this.products = products;
    }

    public void setPrices(int[] prices) {
        this.prices = prices;
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

    public void saveBin(File textFile) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new
                FileOutputStream(textFile))) {
            oos.writeObject(this);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Basket loadFromBinFile(File textFile) {
        Basket basket = new Basket();

        try (ObjectInputStream objectInputStream =
                     new ObjectInputStream(new FileInputStream(textFile))) {

            basket = (Basket) objectInputStream.readObject();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return basket;

    }


}
