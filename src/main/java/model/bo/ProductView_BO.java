package model.bo;

import java.util.ArrayList;

import model.bean.ProductView;
import model.dao.ProductView_DAO;

public class ProductView_BO {
	private ProductView_DAO dao;
	public ProductView_BO() {
		this.dao = new ProductView_DAO();
	}
	
	public ArrayList<ProductView> getProductView_BO(){
		return dao.getProductView();
	}
	
	
}
