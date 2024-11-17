package com.basic;

import java.sql.*;
import java.util.ArrayList;

import connection.ConnectionPool;
import connection.ConnectionPoolImpl;

public class BasicImpl implements Basic {
	// đối tượng làm việc với basic
		private String objectNane;
		// bộ quản lý kết nối được chia sẻ
		private ConnectionPool cp = ConnectionPoolImpl.getInstance();
		// kết nối riêng basic sử dụng
		protected Connection con;

		
		public BasicImpl(String objectNane) {
			super();
			// xác định đối tượng làm việc
			this.objectNane = objectNane;
			// xin kết nối
			try {
				this.con = this.cp.getConnection(this.objectNane);
				
				// kiểm tra chế độ thực thi kết nối
				if(this.con.getAutoCommit()) {
					this.con.setAutoCommit(false); // hủy chế độ thực thi tự động
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private boolean exe(PreparedStatement pre) {
			if(pre!=null) {
				// lấy số lượng bản ghi được tác động
				try {
					int results = pre.executeUpdate();
					
					if(results==0) {
						this.con.rollback();
						return false;
					}
					// xác định thực thi sau cùng
					this.con.commit();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					// trở lại trạng thái an toàn của kết nối
					try {
						this.con.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			}
			return false;
		}
		

		@Override
		public boolean add(PreparedStatement pre) {
			// TODO Auto-generated method stub
			return this.exe(pre);
		}

		@Override
		public boolean edit(PreparedStatement pre) {
			// TODO Auto-generated method stub
			return this.exe(pre);
		}

		@Override
		public boolean delete(PreparedStatement pre) {
			// TODO Auto-generated method stub
			return this.exe(pre);
		}

		@Override
		public ArrayList<ResultSet> gets(String multilSelect) {
			
			ArrayList<ResultSet> res = new ArrayList<>();
			try {
				PreparedStatement stmt = this.con.prepareStatement(multilSelect);
				
				boolean results = stmt.execute();
				
				do {
					if(results) {
						res.add(stmt.getResultSet());
					}
					results = stmt.getMoreResults(PreparedStatement.KEEP_CURRENT_RESULT);
					// giữ Results hiện tại để làm
				}while(results);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			// TODO Auto-generated method stub
			return res;
		}

		@Override
		public ResultSet get(String sql, int value) {
			// TODO Auto-generated method stub
			
			// biên dịch câu lệnh
			try {
				PreparedStatement pre = this.con.prepareStatement(sql);
				if(value > 0) {
					pre.setInt(1, value);
				}
				return pre.executeQuery();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}

		@Override
		public ResultSet get(String sql, String name, String pass) {
			// TODO Auto-generated method stub
			
			try {
				PreparedStatement pre = this.con.prepareStatement(sql);
				pre.setString(1, name);
				pre.setString(2, pass);
				
				return pre.executeQuery();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			return null;
		}

		@Override
		public void releaseConnection() {
			// TODO Auto-generated method stub
			
			try {
				this.cp.releaseConnection(this.con, this.objectNane);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
}
