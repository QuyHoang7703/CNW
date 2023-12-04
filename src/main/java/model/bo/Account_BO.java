package model.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Account;
import model.bean.Detail_account;
import model.dao.Account_DAO;

public class Account_BO {
	private Account_DAO account_DAO;
	public Account_BO() {
		this.account_DAO = new Account_DAO();
	}
	
	public ArrayList<Account> getListAccount_BO(){
		
		return account_DAO.getListAccount();
	}
	
	public void addAccount_BO(Account account, Detail_account detail_account) throws SQLException {
		account_DAO.addAccount(account, detail_account);
	}
	
	public ArrayList<String> getListUser_BO(){
		return account_DAO.getListUser();
	}
}
