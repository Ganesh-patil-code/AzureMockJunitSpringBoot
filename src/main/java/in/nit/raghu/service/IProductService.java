package in.nit.raghu.service;

import java.util.List;

import in.nit.raghu.model.Product;

public interface IProductService {

	public Integer saveProduct(Product product);
	public List<Product> getAllProduct();	
}
