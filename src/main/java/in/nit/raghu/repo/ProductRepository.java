package in.nit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.raghu.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
