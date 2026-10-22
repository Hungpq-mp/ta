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

    public String getSku() {
        return this.sku;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            System.out.println("Lỗi: Giá sản phẩm phải là số dương.");
        }
    }

    public double amount(int qty) {
        return this.price * qty;
    }

    public void showInfo() {
        System.out.printf("SKU: %-6s | Tên: %-17s | Giá: %,10.0f | Tồn kho: %d\n", 
                          this.sku, this.name, this.price, this.stock);
    }

    public boolean checkStock(int qty) {
        return this.stock >= qty;
    }

    public double amountWithDiscount(int qty, double discountPercent) {
        if (checkStock(qty)) {
            double total = amount(qty);
            return total - (total * (discountPercent / 100.0));
        } else {
            System.out.println("Không đủ tồn kho để bán " + qty + " sản phẩm.");
            return 0;
        }
    }
}

public class th1 {
    public static void main(String[] args) {
        Product p1 = new Product("SP01", "Bàn phím cơ", 450000, 20);
        Product p2 = new Product("SP02", "Chuột không dây", 250000, 50);
        Product p3 = new Product("SP03", "Tai nghe", 350000, 10);

        System.out.println("===== DANH MỤC SẢN PHẨM =====");
        p1.showInfo();
        p2.showInfo();
        p3.showInfo();
        
        System.out.println("\n===== KIỂM TRA NGHIỆP VỤ =====");
        p1.setPrice(-50000); 
        
        int soLuongMua = 2;
        System.out.printf("Thành tiền mua %d %s: %,.0f\n", 
                          soLuongMua, p1.getName(), p1.amount(soLuongMua));
                          
        System.out.printf("Thành tiền (đã giảm 10%%) mua 3 %s: %,.0f\n", 
                          p2.getName(), p2.amountWithDiscount(3, 10));
    }
}