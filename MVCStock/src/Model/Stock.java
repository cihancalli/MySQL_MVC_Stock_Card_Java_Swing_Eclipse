package Model;

import java.util.Date;

public class Stock {

	private String StockCode;
	private String StockName;
	private int StockType;
	private String Unit;
	private String Barcode;
	private double KDV;
	private String Explanation;
	private String CreateDate;
	
	public String getStockCode() {
		return StockCode;
	}
	public void setStockCode(String stockCode) {
		StockCode = stockCode;
	}
	public String getStockName() {
		return StockName;
	}
	public void setStockName(String stockName) {
		StockName = stockName;
	}
	public int getStockType() {
		return StockType;
	}
	public void setStockType(int stockType) {
		StockType = stockType;
	}
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public String getBarcode() {
		return Barcode;
	}
	public void setBarcode(String barcode) {
		Barcode = barcode;
	}
	public double getKDV() {
		return KDV;
	}
	public void setKDV(double kDV) {
		KDV = kDV;
	}
	public String getExplanation() {
		return Explanation;
	}
	public void setExplanation(String explanation) {
		Explanation = explanation;
	}
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
}
