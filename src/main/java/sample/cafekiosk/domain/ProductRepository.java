package sample.cafekiosk.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sample.cafekiosk.domain.product.Product;
import sample.cafekiosk.domain.product.ProductSellingType;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllBySellingTypeIn(List<ProductSellingType> forDisplay);

    List<Product> findAllByProductNumberIn(List<String> productNumbers);

    @Query(value = "select product_number from product order by id desc limit 1", nativeQuery = true)
    String findLatestProductNumber();
}
