package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.X;

public class XDAO extends DAO {
	
	public XDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean insert(X x) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO \"X\" (codigo, \"Y\") "
				       + "VALUES ("+x.getCodigo()+ ", '" + x.getY() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public X get(int codigo) {
		X x = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM \"X\" WHERE codigo=" + codigo;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 x = new X(rs.getInt("codigo"), rs.getString("Y"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return x;
	}
	
	
	public List<X> get() {
		return get("");
	}

	
	public List<X> getOrderByCodigo() {
		return get("codigo");		
	}
	
	
	public List<X> getOrderByY() {
		return get("Y");		
	}
	
	private List<X> get(String orderBy) {	
	
		List<X> xs = new ArrayList<X>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM " + "\"X\"" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	X x = new X(rs.getInt("codigo"), rs.getString("Y"));
	            xs.add(x);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return xs;
	}
	
	public boolean update(X x, String y) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE \"X\" SET \"Y\" = '" + y 
				       + "' WHERE codigo = " + x.getCodigo();
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM \"X\" WHERE codigo = " + codigo;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}	
}