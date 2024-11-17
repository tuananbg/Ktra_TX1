package run;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.object.ProductObject;
import com.product.Product;
import com.product.ProductImpl;

public class TestRun {
	public static void main(String[] args) {
		ProductImpl productImpl = new ProductImpl();

        // Gọi hàm hiển thị danh sách sản phẩm
//        displayProducts(productImpl);

//         Gọi hàm tìm kiếm sản phẩm theo tên
//        String searchKeyword = "MAC"; // Từ khóa tìm kiếm
//        searchProductsByName(productImpl, searchKeyword);
        
     // Hiển thị sản phẩm theo ID
//        int productId = 3; // ID sản phẩm cần lấy
//        displayProductById(productImpl, productId);

        // Xóa sản phẩm theo ID
//        ProductObject productToDelete = new ProductObject();
//        productToDelete.setId(2); // ID sản phẩm cần xóa
//        deleteProductById(productImpl, productToDelete);
		
		// Thêm sản phẩm mới
//        ProductObject newProduct = new ProductObject();
//        newProduct.setCpu("Intel Core i7");
//        newProduct.setDon_gia(25000000);
//        newProduct.setDon_vi_ban(10);
//        newProduct.setDon_vi_kho(50);
//        newProduct.setDung_luong_pin("6000mAh");
//        newProduct.setHe_dieu_hanh("Windows 11");
//        newProduct.setMan_hinh("15.6 inch FHD");
//        newProduct.setRam("16GB");
//        newProduct.setTen_san_pham("Laptop Gaming");
//        newProduct.setThiet_ke("Mỏng nhẹ");
//        newProduct.setThong_tin_bao_hanh("1 năm chính hãng");
//        newProduct.setThong_tin_chung("Laptop gaming cao cấp");
//        newProduct.setMa_danh_muc(1);  
//        newProduct.setMa_hang_sx(1);  
//
//        boolean isAdded = productImpl.addProduct(newProduct);
//        if (isAdded) {
//            System.out.println("Thêm sản phẩm mới thành công.");
//        } else {
//            System.out.println("Thêm sản phẩm mới thất bại.");
//        }
//
//        // Sửa sản phẩm đã có
//        ProductObject updatedProduct = new ProductObject();
//        updatedProduct.setId(3); // ID của sản phẩm cần sửa
//        updatedProduct.setCpu("Intel Core i9");
//        updatedProduct.setDon_gia(30000000);
//        updatedProduct.setDon_vi_ban(5);
//        updatedProduct.setDon_vi_kho(30);
//        updatedProduct.setDung_luong_pin("7000mAh");
//        updatedProduct.setHe_dieu_hanh("Windows 11 Pro");
//        updatedProduct.setMan_hinh("17 inch 4K");
//        updatedProduct.setRam("32GB");
//        updatedProduct.setTen_san_pham("Laptop Ultra Gaming");
//        updatedProduct.setThiet_ke("Siêu mỏng");
//        updatedProduct.setThong_tin_bao_hanh("2 năm chính hãng");
//        updatedProduct.setThong_tin_chung("Laptop gaming siêu cao cấp");
//        updatedProduct.setMa_danh_muc(2);  
//        updatedProduct.setMa_hang_sx(2); 
//
//        boolean isUpdated = productImpl.editProduct(updatedProduct);
//        if (isUpdated) {
//            System.out.println("Cập nhật sản phẩm thành công.");
//        } else {
//            System.out.println("Cập nhật sản phẩm thất bại.");
//        }
    }

    // Hàm hiển thị danh sách sản phẩm
    public static void displayProducts(ProductImpl productImpl) {
        ArrayList<ResultSet> res = productImpl.getProduct(null, 0, (byte) 15);

        if (res != null && res.size() >= 2) {
            // Duyệt và hiển thị danh sách sản phẩm
            try (ResultSet rs = res.get(0)) {
                System.out.println("----------------------------------------------------------------------");
                String row;
                while (rs.next()) {
                    row = "ID: " + rs.getInt("id");
                    row += "\tTEN: " + rs.getString("ten_san_pham");
                    row += "\tGIA: " + rs.getString("don_gia");
                    row += "\tSOLUONGKHO: " + rs.getString("don_vi_kho");
                    row += "\tSOLUONGBAN: " + rs.getString("don_vi_ban");
                    row += "\tTHIETKE: " + rs.getString("thiet_ke");
                    System.out.println(row);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Tổng số sản phẩm
            try (ResultSet rs = res.get(1)) {
                if (rs.next()) {
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println("Tổng số sản phẩm: " + rs.getInt("total"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Không tìm thấy kết quả nào.");
        }
    }

    // Hàm tìm kiếm sản phẩm theo tên
    public static void searchProductsByName(ProductImpl productImpl, String searchKeyword) {
        ArrayList<ProductObject> products = productImpl.searchProductByName(searchKeyword);

        if (products.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào với từ khóa: " + searchKeyword);
        } else {
            // In tiêu đề bảng
            System.out.printf("%-5s %-20s %-10s %-10s %-10s %-20s%n",
                              "ID", "TÊN SẢN PHẨM", "GIÁ", "KHO", "BÁN", "THIẾT KẾ");
            System.out.println("----------------------------------------------------------------------");

            // Duyệt qua danh sách sản phẩm và in thông tin
            for (ProductObject product : products) {
                System.out.printf("%-5d %-20s %-10d %-10d %-10d %-20s%n",
                                  product.getId(),
                                  product.getTen_san_pham(),
                                  product.getDon_gia(),
                                  product.getDon_vi_kho(),
                                  product.getDon_vi_ban(),
                                  product.getThiet_ke());
            }
        }
    }
    public static void displayProductById(ProductImpl productImpl, int id) {
        ResultSet rs = productImpl.getProduct(id);

        try {
            if (rs != null && rs.next()) {
                System.out.println("Thông tin sản phẩm:");
                System.out.println("ID: " + rs.getLong("id"));
                System.out.println("Tên sản phẩm: " + rs.getString("ten_san_pham"));
                System.out.println("Giá: " + rs.getLong("don_gia"));
                System.out.println("Số lượng kho: " + rs.getInt("don_vi_kho"));
                System.out.println("Số lượng bán: " + rs.getInt("don_vi_ban"));
                System.out.println("Thiết kế: " + rs.getString("thiet_ke"));
                System.out.println("Thông tin chung: " + rs.getString("thong_tin_chung"));
                System.out.println("Dung lượng pin: " + rs.getString("dung_luong_pin"));
            } else {
                System.out.println("Không tìm thấy sản phẩm với ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error displaying product: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Hàm xóa sản phẩm theo ID
    public static void deleteProductById(ProductImpl productImpl, ProductObject productToDelete) {
        boolean isDeleted = productImpl.deleteProduct(productToDelete);

        if (isDeleted) {
            System.out.println("Sản phẩm với ID " + productToDelete.getId() + " đã được xóa thành công.");
        } else {
            System.out.println("Xóa sản phẩm thất bại hoặc sản phẩm không tồn tại.");
        }
    }
}
