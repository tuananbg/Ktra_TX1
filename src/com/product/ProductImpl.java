package com.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.basic.BasicImpl;
import com.object.ProductObject;

public class ProductImpl extends BasicImpl implements Product{

	public ProductImpl() {
        super("san_pham");
    }

    @Override
    public boolean addProduct(ProductObject item) {
        String sql = "INSERT INTO san_pham (cpu, don_gia, don_vi_ban, don_vi_kho, dung_luong_pin, he_dieu_hanh, "
                   + "man_hinh, ram, ten_san_pham, thiet_ke, thong_tin_bao_hanh, thong_tin_chung, ma_danh_muc, ma_hang_sx) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, item.getCpu());
            pre.setLong(2, item.getDon_gia());
            pre.setInt(3, item.getDon_vi_ban());
            pre.setInt(4, item.getDon_vi_kho());
            pre.setString(5, item.getDung_luong_pin());
            pre.setString(6, item.getHe_dieu_hanh());
            pre.setString(7, item.getMan_hinh());
            pre.setString(8, item.getRam());
            pre.setString(9, item.getTen_san_pham());
            pre.setString(10, item.getThiet_ke());
            pre.setString(11, item.getThong_tin_bao_hanh());
            pre.setString(12, item.getThong_tin_chung());
            pre.setLong(13, item.getMa_danh_muc());
            pre.setLong(14, item.getMa_hang_sx());
            return this.add(pre);
        } catch (SQLException e) {
            System.err.println("Error adding product: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean editProduct(ProductObject item) {
        String sql = "UPDATE san_pham SET cpu = ?, don_gia = ?, don_vi_ban = ?, don_vi_kho = ?, dung_luong_pin = ?, "
                   + "he_dieu_hanh = ?, man_hinh = ?, ram = ?, ten_san_pham = ?, thiet_ke = ?, thong_tin_bao_hanh = ?, "
                   + "thong_tin_chung = ?, ma_danh_muc = ?, ma_hang_sx = ? WHERE id = ?";
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, item.getCpu());
            pre.setLong(2, item.getDon_gia());
            pre.setInt(3, item.getDon_vi_ban());
            pre.setInt(4, item.getDon_vi_kho());
            pre.setString(5, item.getDung_luong_pin());
            pre.setString(6, item.getHe_dieu_hanh());
            pre.setString(7, item.getMan_hinh());
            pre.setString(8, item.getRam());
            pre.setString(9, item.getTen_san_pham());
            pre.setString(10, item.getThiet_ke());
            pre.setString(11, item.getThong_tin_bao_hanh());
            pre.setString(12, item.getThong_tin_chung());
            pre.setLong(13, item.getMa_danh_muc());
            pre.setLong(14, item.getMa_hang_sx());
            pre.setLong(15, item.getId());
            return this.edit(pre);
        } catch (SQLException e) {
            System.err.println("Error editing product: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteProduct(ProductObject item) {
        String sql = "DELETE FROM san_pham WHERE id = ?";
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setLong(1, item.getId());
            return this.delete(pre);
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
        }
        return false;
    }

    @Override
    public ArrayList<ResultSet> getProduct(ProductObject similar, int at, byte total) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM san_pham ");
        sql.append("ORDER BY id DESC ");
        sql.append("LIMIT ").append(at).append(", ").append(total).append("; ");
        
        // Đếm tổng số sản phẩm
        sql.append("SELECT COUNT(id) AS total FROM san_pham;");
        
        return this.gets(sql.toString());
    }

    @Override
    public ResultSet getProduct(int id) {
        String sql = "SELECT * FROM san_pham WHERE id = ?";
        return this.get(sql, id);
    }

    @Override
    public ArrayList<ProductObject> searchProductByName(String productName) {
        String sql = "SELECT * FROM san_pham WHERE ten_san_pham LIKE ?";
        ArrayList<ProductObject> products = new ArrayList<>();
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, "%" + productName + "%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                ProductObject product = new ProductObject();
                product.setId(rs.getLong("id"));
                product.setCpu(rs.getString("cpu"));
                product.setDon_gia(rs.getLong("don_gia"));
                product.setDon_vi_ban(rs.getInt("don_vi_ban"));
                product.setDon_vi_kho(rs.getInt("don_vi_kho"));
                product.setDung_luong_pin(rs.getString("dung_luong_pin"));
                product.setHe_dieu_hanh(rs.getString("he_dieu_hanh"));
                product.setMan_hinh(rs.getString("man_hinh"));
                product.setRam(rs.getString("ram"));
                product.setTen_san_pham(rs.getString("ten_san_pham"));
                product.setThiet_ke(rs.getString("thiet_ke"));
                product.setThong_tin_bao_hanh(rs.getString("thong_tin_bao_hanh"));
                product.setThong_tin_chung(rs.getString("thong_tin_chung"));
                product.setMa_danh_muc(rs.getLong("ma_danh_muc"));
                product.setMa_hang_sx(rs.getLong("ma_hang_sx"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error searching product by name: " + e.getMessage());
        }
        return products;
    }

	@Override
	public ResultSet getProduct(String username, String userpass) {
	
		return null;
	}
}
