
/**
 * 
 */
package com.springboot.my.bank.dao;

import java.sql.SQLException;
import java.util.List;

import com.springboot.my.bank.models.Customer;
import com.springboot.my.bank.models.Transaction;

public interface CustomerDAO {

	public Customer getCustomerById(Integer custId) throws SQLException;

	public Customer getCustomerByAccNo(Integer accNo) throws SQLException;

	public List<Customer> getCustomerByBranches(String branchCode) throws SQLException;

	public Boolean updateCustomerById(Integer custId, String name, String address) throws SQLException;

	public Boolean createCustomer(Customer customer) throws SQLException;

	public Boolean deleteCustomerById(Integer custId) throws SQLException;

//	add start date and end date
	public List<Transaction> showTransactions(Integer custId, String accType) throws SQLException;

}