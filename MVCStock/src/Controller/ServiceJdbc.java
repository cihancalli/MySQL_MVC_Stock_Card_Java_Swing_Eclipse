package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Stock;

public class ServiceJdbc {
	
	private Connection connection;
	private PreparedStatement statement;
	
	private static String dburl = "jdbc:mysql://SERVER_IP/DB_NAME";
	private static String username = "DB_USERNAME";
	private static String password = "DB_PASSWORD";
	
	
	private List<Stock> Stocks;
	
	
	public ServiceJdbc() {
		Stocks = new ArrayList<>();
	}
	
	private Connection getConnection() {
		try {
			if(connection == null) {
				connection = DriverManager.getConnection(dburl,username,password);
			}
			
		}catch (Exception ex){
			Logger.getLogger(ServiceJdbc.class.getName()).log(Level.SEVERE, null, ex);
		}
		return connection;
	}
	
	public void save (Stock stock) {
		try {
			//stockcard is DB_TABLE_NAME
			statement =  getConnection().prepareStatement("select StockCode from stockcard where StockCode=?");
			statement.setString(1, stock.getStockCode()); 
			ResultSet rs =statement.executeQuery();
			statement =  getConnection().prepareStatement("insert into stockcard values(?,?,?,?,?,?,?,?)");
			
			if(rs.next()) {
				statement =  getConnection().prepareStatement("insert into stockcard values(?,?,?,?,?,?,?,?)");
				stock.setStockCode(stock.getStockCode()+"_C");
			}
			statement.setString(1, stock.getStockCode());
			statement.setString(2, stock.getStockName());
			statement.setInt(3, stock.getStockType());
			statement.setString(4, stock.getUnit());
			statement.setString(5, stock.getBarcode());
			statement.setDouble(6, stock.getKDV());
			statement.setString(7, stock.getExplanation());
			statement.setString(8,  stock.getCreateDate());
			statement.executeUpdate();
			//getConnection().close();
			
		}catch (Exception ex){
			Logger.getLogger(ServiceJdbc.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void update (Stock stock) {
		try {
			statement =  getConnection().prepareStatement("update stockcard set StockName=?, StockType=?, Unit=?, Barcode=?, KDV=?, Explanation=?, CreateDate=? where StockCode=?");
			statement.setString(1, stock.getStockName());
			statement.setInt(2, stock.getStockType());
			statement.setString(3, stock.getUnit());
			statement.setString(4, stock.getBarcode());
			statement.setDouble(5, stock.getKDV());
			statement.setString(6, stock.getExplanation());
			statement.setString(7,  stock.getCreateDate());
			statement.setString(8, stock.getStockCode());
			statement.executeUpdate();
			//getConnection().close();
		}catch (Exception ex){
			Logger.getLogger(ServiceJdbc.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void delete (Stock stock) {
		try {
			statement =  getConnection().prepareStatement("delete from stockcard where StockCode=?");
			statement.setString(1, stock.getStockCode());
			statement.executeUpdate();
			//getConnection().close();
		}catch (Exception ex){
			Logger.getLogger(ServiceJdbc.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	
	
	public List<Stock> getStocks(String Stock_Code) {
		
		try {
			
			if (Stock_Code !=null) {
				statement =  getConnection().prepareStatement("select StockCode,StockName,StockType,Unit,Barcode,KDV,Explanation,CreateDate from stockcard where "
						+ "StockCode=? or StockName=?");
				statement.setString(1, Stock_Code);
				statement.setString(2, Stock_Code);
			}
			else {
				statement =  getConnection().prepareStatement("select * from stockcard");
			}
			
			
			ResultSet rs =statement.executeQuery();
			List<Stock> stocks = new ArrayList<>();
			while(rs.next()) {
				Stock stock = new Stock();
				stock.setStockCode(rs.getString("StockCode"));
				stock.setStockCode(rs.getString("StockCode"));
				stock.setStockName(rs.getString("StockName"));
				stock.setStockType(rs.getInt("StockType"));
				stock.setUnit(rs.getString("Unit"));
				stock.setBarcode(rs.getString("Barcode"));
				stock.setKDV(rs.getDouble("KDV"));
				stock.setExplanation(rs.getString("Explanation"));
				stock.setCreateDate(rs.getString("CreateDate"));
				stocks.add(stock);
			}
			return stocks;
		}catch (Exception ex){
			Logger.getLogger(ServiceJdbc.class.getName()).log(Level.SEVERE, null, ex);
		}
		return new ArrayList<>();
		
	}

}
