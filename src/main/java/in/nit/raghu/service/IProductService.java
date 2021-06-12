package in.nit.raghu.service;

import java.util.List;

import in.nit.raghu.model.Product;

public interface IProductService {

	public Integer saveProduct(Product product);
	public List<Product> getAllProduct();	
	public Product getOneProduct(Integer id);
	public void deleteOneProduct(Integer id);
	public Boolean updateProduct(Integer id);
}
