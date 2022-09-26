package com.masai.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.exception.EmployeeException;
import com.masai.model.Employee;
import com.masai.model.EmployeeDTO;



@Repository
public interface EmpDao extends JpaRepository<Employee,Integer> {
	

	public List<Employee> findByAddress(String address) throws EmployeeException;
	

	@Query("select new com.masai.model.EmployeeDTO(s.empName, s.salary,s.address) from Employee s")
	public List<EmployeeDTO> getNameAddressSalaryOfAllEmployee() throws EmployeeException;
	

	@Query("select s.empName,s.address from Employee s where s.empId=?1")
	public String[] getNameAndAddressOfEmplyeeById(Integer empId)throws
	EmployeeException;


}
