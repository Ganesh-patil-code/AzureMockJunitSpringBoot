package in.nit.raghu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.raghu.exception.EmployeeNotFoundException;
import in.nit.raghu.model.Employee;
import in.nit.raghu.repo.EmployeeRepository;
import in.nit.raghu.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository repo;

	@Override
	public Integer saveEmployee(Employee employee) {
		 return repo.save(employee).getEmpId();
	}

	@Override
	public List<Employee> getAllEmployee() {
		return repo.findAll();
	}

	@Override
	public Employee getOneEmployee(Integer id) {
		return repo.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee Not Exist"));
		/*
		 * Optional<Employee> opt = repo.findById(id); if(opt.isPresent()) return
		 * opt.get(); else throw new EmployeeNotFoundException("Employee Not Exist");
		 */
	}

	@Override
	public void deleteOneEmployee(Integer id) {
		Employee e = getOneEmployee(id);
		repo.delete(e);
	}

	@Override
	public Boolean updateEmployee(Integer id) {
		return repo.existsById(id);
	}
	
	
	
}