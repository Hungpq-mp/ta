// Lớp SanPham
class SanPham {
    private String maSP;
    private String tenSP;
    private double donGia;
    private int soLuongTon;

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
    }

    public SanPham(String maSP, String tenSP, double donGia, int soLuongTon) {
        this(maSP, tenSP); 
        this.donGia = donGia;
        this.soLuongTon = soLuongTon;
    }

    public double getDonGia() {
        return this.donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getTenSP() {
        return this.tenSP;
    }

    public void hienThi() {
        System.out.printf("%-5s %-17s %,.3f   tồn %d\n", 
                this.maSP, this.tenSP, this.donGia / 1000.0, this.soLuongTon);
    }
}

// Lớp DonHang
class DonHang {
    private String tenKhach;
    private SanPham sanPham;
    private int soLuong;

    public DonHang(String tenKhach, SanPham sanPham, int soLuong) {
        this.tenKhach = tenKhach;
        this.sanPham = sanPham;
        
        if (soLuong < 1) {
            this.soLuong = 1;
        } else {
            this.soLuong = soLuong;
        }
    }

    public double tinhThanhTien() {
        double thanhTien = this.sanPham.getDonGia() * this.soLuong;
        if (this.soLuong >= 5) {
            thanhTien = thanhTien * 0.9;
        }
        return thanhTien;
    }

    public void inDonHang() {
        double thanhTien = tinhThanhTien();
        System.out.printf("%-6s %-17s %-4d %,.3f\n", 
                this.tenKhach, this.sanPham.getTenSP(), this.soLuong, thanhTien / 1000.0);
                
        if (this.soLuong >= 5) {
            System.out.println("       (đã giảm 10%)");
        }
    }
}

// Lớp Main
public class main {
    public static void main(String[] args) {
        SanPham banPhim = new SanPham("SP01", "Bàn phím cơ", 450000, 20);
        SanPham chuot = new SanPham("SP02", "Chuột không dây", 200000, 50);
        
        SanPham taiNghe = new SanPham("SP03", "Tai nghe");
        taiNghe.setDonGia(350000);
        taiNghe.setSoLuongTon(10);
        
        System.out.println("===== DANH SÁCH SẢN PHẨM =====");
        banPhim.hienThi();
        chuot.hienThi();
        taiNghe.hienThi();
        System.out.println();

        DonHang don1 = new DonHang("An", banPhim, 2);
        DonHang don2 = new DonHang("Bình", chuot, 5);
        DonHang don3 = new DonHang("Chi", taiNghe, 0); 
        
        System.out.println("===== DANH SÁCH ĐƠN HÀNG =====");
        System.out.printf("%-6s %-17s %-4s %s\n", "Khách", "Sản phẩm", "SL", "Thành tiền");
        don1.inDonHang();
        don2.inDonHang();
        don3.inDonHang();
    }
}