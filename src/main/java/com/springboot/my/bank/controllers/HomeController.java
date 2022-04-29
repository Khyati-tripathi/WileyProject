/**
 * 
 */
package com.springboot.my.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "index";
	}

}
