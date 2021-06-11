package in.nit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.raghu.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee	, Integer> {

}
