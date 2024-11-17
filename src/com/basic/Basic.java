package com.basic;

import java.sql.*;
import java.util.*;
public interface Basic {
	boolean add(PreparedStatement pre);
	boolean edit(PreparedStatement pre);
	boolean delete(PreparedStatement pre);
	
	ArrayList<ResultSet>gets(String multiSelect);
	ResultSet get(String sql, int value);
	ResultSet get(String sql, String name, String pass);
	
	void releaseConnection();
}
