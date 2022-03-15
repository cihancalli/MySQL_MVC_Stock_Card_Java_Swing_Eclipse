package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Stock;

public class Service {
	
	private List<Stock> stocks;
	
	public Service() {
		stocks = new ArrayList<>();
	}
	
	
	
	public void save (Stock stock) {
		stocks.add(stock);
	}
	
	public void update (Stock stock) {
		for(int i=0; i<stocks.size();i++) { 
		Stock stc = stocks.get(i);
		if(stc.getStockCode().equals(stock.getStockCode())) {
			stocks.set(i, stock);
			break;
			}
		}
	}
	
	public void delete (Stock stock) {
		for(int i=0; i<stocks.size();i++) { 
			Stock stc = stocks.get(i);
			if(stc.getStockCode().equals(stock.getStockCode())) {
				stocks.remove(i);
				break;
			}
		}
	}
	
	public List<Stock> getStocks(){
		
		return stocks;
	}

}
