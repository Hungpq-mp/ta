import java.util.ArrayList;

class Product {
    private String sku;
    private String name;
    private double price;
    private int stock;

    public Product(String sku, String name, double price, int stock) {
        this.sku = sku;
        this.name = name;
        this.stock = stock;
        this.setPrice(price);
    }

    public String getSku() { return this.sku; }
    public String getName() { return this.name; }
    public double getPrice() { return this.price; }

    public void setPrice(double price) {
        if (price > 0) this.price = price;
    }

    public double amount(int qty) {
        return this.price * qty;
    }
}

class CartItem {
    Product product;
    int qty;

    public CartItem(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }
}

class Cart {
    private String code;
    private ArrayList<CartItem> items;

    public Cart(String code) {
        this.code = code;
        this.items = new ArrayList<>();
    }

    public void addItem(Product p, int qty) {
        for (CartItem item : items) {
            if (item.product.getSku().equals(p.getSku())) {
                item.qty += qty;
                return;
            }
        }
        items.add(new CartItem(p, qty));
    }

    public double total() {
        double sum = 0;
        for (CartItem item : items) {
            sum += item.product.amount(item.qty);
        }
        return sum;
    }

    public void showCart() {
        System.out.println("--- Giỏ hàng: " + this.code + " ---");
        for (CartItem item : items) {
            System.out.printf("%-17s x %-2d = %,10.0f\n", 
                              item.product.getName(), item.qty, item.product.amount(item.qty));
        }
        System.out.printf(">> TỔNG TIỀN: %,.0f\n\n", total());
    }
}

public class th2 {
    public static void main(String[] args) {
        Product p1 = new Product("SP01", "Bàn phím cơ", 450000, 20);
        Product p2 = new Product("SP02", "Chuột không dây", 250000, 50);

        Cart cart1 = new Cart("CART_001");
        
        cart1.addItem(p1, 1);
        cart1.addItem(p2, 2);
        cart1.addItem(p1, 1);
        
        System.out.println("Kết quả cart1 ban đầu:");
        cart1.showCart();

        Cart cart2 = cart1;
        cart2.addItem(p2, 1);

        System.out.println("Kết quả cart1 sau khi biến cart2 thêm mặt hàng:");
        cart1.showCart();
    }
}