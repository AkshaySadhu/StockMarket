package com.example.demo.CompanyController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.Company;
import com.example.demo.Model.IPO;
import com.example.demo.CompanyService.CompanyService;
//@org.springframework.web.bind.annotation.RestController
@RestController
public class CompanyController 
{
	@Autowired
	private CompanyService companyService;
	
	@PostMapping("/save-company")
	@Transactional
	public String registercompany(@RequestBody Company company) {
		companyService.saveCompany(company);
		return "Details of  "+company.getCompanyName()+"added successfully!";
	}
	
	@GetMapping("/all-company")
	public Iterable<Company> showAllCompany() {
		return companyService.showAllCompany();
	}
	
	@GetMapping("/delete/{companyName}")
	public Iterable<Company> deleteCompany(@PathVariable String companyName){
		return companyService.deleteCompanyByname(companyName);
	}
	
	@GetMapping("/search/{companyName}")
	public Company searchCompany(@PathVariable String companyName) {
		return companyService.findByCompanyname(companyName);
	}
	@PostMapping("/ipo-entry")
	@Transactional
	public String companyipo(@RequestBody IPO ipo, HttpServletRequest request) {
		companyService.saveipo(ipo);
		return "Details of  "+ipo.getCompanyName()+" IPO added successfully!";
	}
	
	@GetMapping("/searchipo/{companyName}")
	public IPO searchCompanyipo(@PathVariable String companyName) {
		return companyService.findipoByCompanyname(companyName);
	}
	@GetMapping("/deleteipo/{companyName}")
	public Iterable<IPO> deleteCompanyipo(@PathVariable String companyName){
		return companyService.deleteCompanyipoByname(companyName);
	}
	@PutMapping("/updateipo/{id}")
	public String updateipo(@RequestBody IPO ipo, @PathVariable Long id)
	{
		return companyService.updateipo(id,ipo);
	}
}
