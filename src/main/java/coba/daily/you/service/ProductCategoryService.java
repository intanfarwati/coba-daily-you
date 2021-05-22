package coba.daily.you.service;


import coba.daily.you.model.entity.ProductCategory;
import coba.daily.you.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface ProductCategoryService {
    ProductCategory saveProductCategoryMaterDetail (ProductCategory productCategory);

//    private final ProductCategoryRepository productCategoryRepository;

//    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
//        this.productCategoryRepository = productCategoryRepository;
//    }
//
//    public List<ProductCategory> listProductCategories() {
//        return productCategoryRepository.findAll();
//    }
//
//    public void createProductCategory(ProductCategory productCategory) {
//        productCategoryRepository.save(productCategory);
//    }
//
//    public ProductCategory readProductCategory(String categoryName) {
//        return productCategoryRepository.findByCategoryName(categoryName);
//    }
//
//    public Optional<ProductCategory> readProductCategory(Integer idCategory) {
//        return productCategoryRepository.findById(idCategory);
//    }
//
//    public void updateProductCategory(Integer idCategory, ProductCategory newProductCategory) {
//        ProductCategory productCategory = productCategoryRepository.findById(idCategory).get();
//        productCategory.setCategoryName(newProductCategory.getCategoryName());
////        productCategory.setProducts(newProductCategory.getProducts());
//
//
//        productCategoryRepository.save(productCategory);
//    }
}
