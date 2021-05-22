package coba.daily.you.service;

import coba.daily.you.model.entity.ProductCategory;
import coba.daily.you.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory saveProductCategoryMaterDetail(ProductCategory productCategory){
     productCategory = productCategoryRepository.save(productCategory);
     return productCategory;
    }
}
