package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService employeeService; 
	
	//Since only one constructor, @Autowired is optional 
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService; 
	}
	
	
	//add mapping for "/list"	
	@GetMapping("/list")
	public String listEmployees(Model theModel)
	{ 
		List<Employee> theEmployees = employeeService.findAll(); 
		theModel.addAttribute("employees", theEmployees);
		return "employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		employeeService.save(theEmployee);
		
		//use redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdatae(@RequestParam("employeeId") int theId,
									 Model theModel) {
		
		 Employee theEmployee = employeeService.findById(theId);
		 
		theModel.addAttribute("employee", theEmployee);
		//use redirect to prevent duplicate submissions
		return "employees/employee-form";
	}
	
	@GetMapping("/showFormForDelete")
	public String showFormForDelete(@RequestParam("employeeId") int theId) {
		
		employeeService.deleteById(theId);
		
		//use redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}


}
