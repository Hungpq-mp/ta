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
}

class Order {
    private static int counter = 0;
    private final String code;
    private double totalAmount;

    public Order(Cart cart) {
        counter++;
        this.code = String.format("DH%05d", counter);
        this.totalAmount = cart.total();
    }

    public Order applyVoucher(double amount) {
        this.totalAmount -= amount;
        if (this.totalAmount < 0) {
            this.totalAmount = 0;
        }
        return this;
    }

    public static int getCounter() {
        return counter;
    }

    public void printOrderInfo() {
        System.out.printf("Mã đơn: %s | Tổng thanh toán: %,.0f\n", this.code, this.totalAmount);
    }
}

public class th3 {
    public static void main(String[] args) {
        Product p1 = new Product("SP01", "Bàn phím cơ", 450000, 20);
        Product p2 = new Product("SP02", "Chuột không dây", 250000, 50);

        Cart cart1 = new Cart("CART_001");
        cart1.addItem(p1, 2); 
        Cart cart2 = new Cart("CART_002");
        cart2.addItem(p2, 1); 
        Cart cart3 = new Cart("CART_003");
        cart3.addItem(p1, 1);
        cart3.addItem(p2, 1); 

        Order order1 = new Order(cart1);
        Order order2 = new Order(cart2);
        Order order3 = new Order(cart3);

        order1.applyVoucher(50000).applyVoucher(20000);

        order1.printOrderInfo();
        order2.printOrderInfo();
        order3.printOrderInfo();

        System.out.println("Tổng số đơn đã tạo: " + Order.getCounter());
    }
}