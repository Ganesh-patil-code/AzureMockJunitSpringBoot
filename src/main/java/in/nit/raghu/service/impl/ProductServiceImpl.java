package in.nit.raghu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.raghu.model.Product;
import in.nit.raghu.repo.ProductRepository;
import in.nit.raghu.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository prepo;

	@Override
	public Integer saveProduct(Product product) {
		return prepo.save(product).getPid();
	}

	@Override
	public List<Product> getAllProduct() {
		return prepo.findAll();
	}

}
