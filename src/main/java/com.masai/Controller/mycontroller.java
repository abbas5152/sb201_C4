package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.EmployeeException;
import com.masai.model.Employee;
import com.masai.model.EmployeeDTO;
import com.masai.service.Empservimp;

@RestController
@RequestMapping("/Emps")
public class Mycontroller {
	
	@Autowired
	private Empservimp empa;
	
	
	@PostMapping("/employee")
	public  ResponseEntity<Employee> Register(@RequestBody Employee em) throws EmployeeException {
		return new ResponseEntity<Employee>(empa.registerEmployee(em),HttpStatus.CREATED);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> GetByid(@PathVariable Integer id) throws EmployeeException {
		
		return new ResponseEntity<Employee>(empa.getEmployeeById(id),HttpStatus.OK);
		
	}
	
	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> ListofEmp() throws EmployeeException{
		
		return new ResponseEntity<List<Employee>>(empa.getAllEmployeeDetails(),HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Employee> DeleteByid(@PathVariable Integer id) throws EmployeeException {
		
		return new ResponseEntity<Employee>(empa.deleteEmployeeById(id),HttpStatus.OK);
		
	}
	@GetMapping("/byadd/{add}")
	public ResponseEntity<List<Employee>> GetallDeatilsByadd(@PathVariable String add) throws EmployeeException{
		
		return new ResponseEntity<List<Employee>>(empa.getEmployeeDetailsByAddress(add),HttpStatus.ACCEPTED);
	}
	@PutMapping("/employee")
	public  ResponseEntity<Employee> Update(@RequestBody Employee em) throws EmployeeException {
		return new ResponseEntity<Employee>(empa.updateEmployee(em),HttpStatus.CREATED);
	}
	
	@GetMapping("/empdto")
	public ResponseEntity<List<EmployeeDTO>> alldtodetails() throws EmployeeException{
		return new ResponseEntity<List<EmployeeDTO>>(empa.getNameAddressSalaryOfAllEmployee(),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/empbyid/{id}")
	public ResponseEntity<String[]> detailsbyid(@PathVariable Integer id) throws EmployeeException{
		
		return new ResponseEntity<String[]>(empa.getNameAndAddressOfEmplyeeById(id),HttpStatus.ACCEPTED);
		
	}

}
