package in.nit.raghu.service;

import java.util.List;
import java.util.Optional;

import in.nit.raghu.exception.EmployeeNotExistException;
import in.nit.raghu.model.Employee;

public interface IEmployeeService {

	public Integer saveEmployee(Employee employee);
	public List<Employee> getAllEmployee();
	public Employee getOneEmployee(Integer id);
	public void deleteOneEmployee(Integer id);
	public Boolean updateEmployee(Integer id);
	
}
