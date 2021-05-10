package coba.daily.you.repository;

import coba.daily.you.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductRepository extends JpaRepository<Product, Integer> {


    @Query(value = "SELECT * FROM t_product where id_category =?1", nativeQuery = true)
    List<Product> cariProductCategory(Integer id);

    @Query(value = "SELECT * FROM t_product where product_name ~* ?1", nativeQuery = true)
    List<Product> searchProduct(String search);
}
