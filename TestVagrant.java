
import java.util.*;
class Product {
    private String myname;
    private double untPrice;
    private double gstPercent;
    private int quant;

    public Product(String myname, double untPrice, double gstPercent, int quant) {
        this.myname = myname;
        this.untPrice = untPrice;
        this.gstPercent = gstPercent;
        this.quant = quant;
    }

    public double calculateGSTAmount() {
        return (untPrice * gstPercent / 100) * quant;
    }

    public double calculateTotalPrice() {
        return (untPrice + (untPrice * gstPercent / 100)) * quant;
    }

    public String getName() {
        return myname;
    }
}

class Basket {
    private List<Product> prod;

    public Basket(List<Product> prod) {
        this.prod = prod;

    }

    public Product getMaxGSTProduct() {
        return Collections.max(prod, Comparator.comparing(Product::calculateGSTAmount));
    }

    public double calculateTotalAmount() {
        return prod.stream().mapToDouble(Product::calculateTotalPrice).sum();
    }
}

public class TestVagrant {
    public static void main(String[] args) {
        List<Product> prod = Arrays.asList(
                new Product("Leather wallet", 1100, 18, 1),
                new Product("Umbrella", 900, 12, 4),
                new Product("Cigarette", 200, 28, 3),
                new Product("Honey", 100, 0, 2)
        );

        Basket basket = new Basket(prod);

        
        Product maxGSTProduct = basket.getMaxGSTProduct();
        System.out.println("Product with maximum GST amount: " + maxGSTProduct.getName());

        double totalAmount = basket.calculateTotalAmount();
        System.out.println("Total amount to be paid: " + totalAmount);
    }
}