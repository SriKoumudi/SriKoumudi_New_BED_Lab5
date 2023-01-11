package net.javaguides.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;

@Controller
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	// Home page navigation url
	@GetMapping("/employees")
	public String ListEmployees(Model model) {
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "employees";
	}

	// add employee navigation url
	@GetMapping("/employees/new")
	public String Addemp(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "addemployee";
	}

	@PostMapping("/employees")
	public String SaveEmp(@ModelAttribute("employee") Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/employees";
	}

	@GetMapping("/employees/edit/{id}")
	public String editEmp(@PathVariable Long id, Model model) {
		model.addAttribute("employee", employeeService.getById(id));
		return "editemp";

	}

	@PostMapping("/employees/{id}")
	public String UpdateEmp(@PathVariable Long id, @ModelAttribute("employee") Employee employee, Model model) {
		Employee empexisting = employeeService.getById(id);
		empexisting.setId(id);
		empexisting.setFirstName(employee.getFirstName());
		empexisting.setLastName(employee.getLastName());
		empexisting.setEmail(employee.getEmail());
		employeeService.updateemployee(empexisting);
		return "redirect:/employees";
	}

	@GetMapping("/employees/{id}")
	public String delete(@PathVariable long id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/employees";
	}

}
