package sample.cafekiosk.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.cafekiosk.domain.product.Product;
import sample.cafekiosk.domain.product.ProductSellingType;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllBySellingTypeIn(List<ProductSellingType> forDisplay);
}
