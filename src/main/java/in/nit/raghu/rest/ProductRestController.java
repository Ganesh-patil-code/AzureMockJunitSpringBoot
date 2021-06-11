package in.nit.raghu.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nit.raghu.exception.DuplicateprimarykeyException;
import in.nit.raghu.exception.EmptyNameException;
import in.nit.raghu.exception.ProductNotFoundException;

import in.nit.raghu.model.Product;
import in.nit.raghu.service.IProductService;

@RestController
@RequestMapping("/product")
public class ProductRestController {

	@Autowired
	private IProductService service;
	
	Integer id=null;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveProduct(@RequestBody Product product) throws DuplicateprimarykeyException{
		id=service.saveProduct(product);
		if(product.getPid()==id) {
			throw new DuplicateprimarykeyException("duplicate primary key");
		}
		if(product.getPname().isEmpty()) {
			throw new EmptyNameException("Empty Product Name");
		}
		return ResponseEntity.ok("Product Saved.."+id);		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProduct(){
		List<Product> list = service.getAllProduct();
		if(list.size()>=1) {
			return ResponseEntity.ok(list);
		}
		else {
			throw new ProductNotFoundException("All Product Not Found..");
		}
	}	
}