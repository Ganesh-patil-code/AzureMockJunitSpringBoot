package in.nit.raghu.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.nit.raghu.exception.DuplicateprimarykeyException;
import in.nit.raghu.exception.EmployeeNotExistException;
import in.nit.raghu.exception.EmployeeNotFoundException;
import in.nit.raghu.exception.EmptyNameException;
import in.nit.raghu.exception.ProductNotFoundException;

@RestControllerAdvice
public class CustomeExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException enf) {
		return new ResponseEntity<String>(enf.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException pnf){
		return new ResponseEntity<String>(pnf.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleEmptyNameException(EmptyNameException ene){
		return new ResponseEntity<String>(ene.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleDuplicateprimarykeyException(DuplicateprimarykeyException dpk){
		return new ResponseEntity<String>(dpk.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleEmployeeNotExistException(EmployeeNotExistException enee){
		return new ResponseEntity<String>(enee.getMessage(),HttpStatus.NOT_FOUND);
	}
	
}