package coba.daily.you.service;

import coba.daily.you.model.entity.Product;
import coba.daily.you.repository.ProductCategoryRepository;
import coba.daily.you.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public Product saveProductMaterDetail(Product product){
        product= productRepository.save(product);
        product.get
    }
}
