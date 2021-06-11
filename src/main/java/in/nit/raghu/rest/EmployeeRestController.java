package in.nit.raghu.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.nit.raghu.exception.EmployeeNotExistException;
import in.nit.raghu.exception.EmployeeNotFoundException;
import in.nit.raghu.model.Employee;
import in.nit.raghu.service.IEmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

	@Autowired
	private IEmployeeService service;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveEmployee(
			@RequestBody Employee employee)
	{
		Integer id = service.saveEmployee(employee);
		return ResponseEntity.ok("Employee Saved.."+id);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		List<Employee> list = service.getAllEmployee();
		if(list.size()>=1){
		return ResponseEntity.ok(list);
		}
		else
		{
			throw new EmployeeNotFoundException("All Employee Not Exist");
		}	
	}
	
	@RequestMapping(value = "/one/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getOneEmployee(@PathVariable Integer id){
		Employee e = service.getOneEmployee(id);
		return ResponseEntity.ok(e);
	}
	
	@RequestMapping(value = "/remove/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id){
		service.deleteOneEmployee(id);
		return ResponseEntity.ok("Employee Deleted");
	}
	
	@RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
	public ResponseEntity<String> updateEmployee(@PathVariable Integer id,@RequestBody Employee employee) throws EmployeeNotExistException{
		ResponseEntity<String> resp = null;
		Boolean exist =  service.updateEmployee(employee.getEmpId());
		if(exist) {
			service.saveEmployee(employee);
			resp = new ResponseEntity<String>("updated..",HttpStatus.OK);
		}
		else {
			throw new EmployeeNotExistException("employee not exist for update");
		}
		return resp;
	}
	
	
	
}