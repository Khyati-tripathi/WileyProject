package com.springboot.my.bank.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.my.bank.models.Admin;
import com.springboot.my.bank.models.Customer;
import com.springboot.my.bank.repository.AdminRepository;
import com.springboot.my.bank.repository.CustomerRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping()
	public String adminIndex(Model model) {
		model.addAttribute("admin",new Admin());
		return "admin";
	}

	@PostMapping("/validate")
	public String getAdminById(Admin admin) {
			try {
				List<Admin> adminList = adminRepository.getAdminById(admin.getAdminId());
				if(adminList.isEmpty()) {
					return "redirect:/admin";
				}else if (adminList.size() == 1) {
					return"redirect:/admin/"+admin.getAdminId();
				}
			} catch (SQLException e) {
				return "redirect:/admin";
			}
			return null;
	}
	
	@GetMapping("/{id}")
	public String adminPage(@PathVariable Integer id,Model model) {
		model.addAttribute("adminId",id);
		try {
			List<Customer> list = adminRepository.showCustomers();
			model.addAttribute("customers",list);
		} catch (SQLException e) {
			return "admin-loggedin";
		}
		return "admin-loggedin";
	}

	@GetMapping("/customer/delete")
	public String deleteCustomer(@RequestParam Integer customerId,@RequestParam Integer adminId) {
		try {
			System.out.println("customer id" + customerId);
			if(customerRepository.deleteCustomerById(customerId)) {
				
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return "redirect:/admin/"+adminId;
	}

	@GetMapping("/customer/update")
	public String update(@RequestParam Integer customerId,@RequestParam Integer adminId,Model model) {
		model.addAttribute("adminId",adminId);
		try {
			Customer customer = customerRepository.getCustomerById(customerId);
			model.addAttribute("customer", customer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "update-customer";
	}
	@PostMapping("/updateCustomer")
	public String updateCustomer(Customer customer,@RequestParam Integer adminId) {
		System.out.print("\n\n****************"+customer.getAddress().toString()+"\n\n\n\n****************");
		try {
			customerRepository.updateCustomerById(customer.getCustomerId(), customer.getName(), customer.getAddress().toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/"+adminId;		
	}

}
