package model;


import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import model.ItemCarrello;

public class Cart{
		
	private ProductModelDS model;
	private List<ItemCarrello> products;
	private double totale = 0;
	
	public Cart() {
		products = new ArrayList<ItemCarrello>();
		model = new ProductModelDS();
		
	}
	
	public void addProduct(ItemCarrello product) throws SQLException{
		boolean aggiunto = false;
		for(ItemCarrello prod : products) {
			if(prod.getID_ProdottoItemCarrello().equals(product.getID_ProdottoItemCarrello())) {
				prod.setQuantitaItemCarrello(prod.getQuantitaItemCarrello()+1);
				aggiunto = true;
				
				ProdottoBean p = model.doRetrieveByKey(prod.getID_ProdottoItemCarrello());
				totale = totale + (p.getPrezzo() * prod.getQuantitaItemCarrello());
				break;
			}
		}
			if(!aggiunto) {
				this.getProducts().add(product);
				ProdottoBean p = model.doRetrieveByKey(product.getID_ProdottoItemCarrello());
				totale = totale + p.getPrezzo();
			}
			
	}
	
	public void deleteProduct(ItemCarrello product) throws SQLException {
		
		for(ItemCarrello prod : products) {
			if(prod.getID_ProdottoItemCarrello().equals(product.getID_ProdottoItemCarrello()) && prod.getQuantitaItemCarrello() > 1) {
				prod.setQuantitaItemCarrello(prod.getQuantitaItemCarrello()-1);
				
				ProdottoBean p = model.doRetrieveByKey(prod.getID_ProdottoItemCarrello());
				totale = totale - p.getPrezzo();
				
				break;
			}else {		
				
				ProdottoBean p = model.doRetrieveByKey(prod.getID_ProdottoItemCarrello());
				totale = totale - p.getPrezzo();
				
				products.remove(prod);
				break;
			}
		}
 	}
	
	public List<ItemCarrello> getProducts() {
		return  products;
	}
	
	public ProdottoBean findProduct(String id) {
		
		ProdottoBean p = new ProdottoBean();
		
		try {
			p = model.doRetrieveByKey(id);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return p;
	}
	
	public double getTotale() {
		return this.totale;
	}
	
	public void setTotale(double t) {
		this.totale = t;
	}
}