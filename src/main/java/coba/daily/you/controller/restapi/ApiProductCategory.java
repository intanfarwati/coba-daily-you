package coba.daily.you.controller.restapi;


import coba.daily.you.model.dto.ProductDto;
import coba.daily.you.model.entity.Product;
import coba.daily.you.model.entity.ProductCategory;
import coba.daily.you.repository.ProductCategoryRepository;
import coba.daily.you.service.ProductCategoryService;
import coba.daily.you.util.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/productcategory")
public class ApiProductCategory {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @GetMapping()
    public ResponseEntity<List<ProductCategory>> getProductCategories() {
        List<ProductCategory> body = productCategoryRepository.findAll();
        return new ResponseEntity<List<ProductCategory>>(body, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> editSaveProductCategory(@RequestBody ProductCategory productCategory){
        if (Helper.notNull(readProductCategory(productCategory.getCategoryName()))) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
        }
        createProductCategory(productCategory);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);

//        ProductCategory productCategory = modelMapper.map(productDto, ProductCategory.class);
//        ProductCategory productCategory1 = new ProductCategory();
//        productCategory1.setCategoryName(productDto.getCategoryName());
//        productCategory1 = productCategoryService.saveProductCategoryMaterDetail(productCategory);
//        ProductDto productCategoryDtoDB = mapProductCategoryToProductDto(productCategory1);

//        return productCategoryDtoDB;
    }

    public ProductCategory readProductCategory(String categoryName) {
        return productCategoryRepository.findByCategoryName(categoryName);
    }
    public void createProductCategory(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }

    private ProductDto mapProductCategoryToProductDto(ProductCategory productCategory){
        ProductDto productDto = modelMapper.map(productCategory, ProductDto.class);
      productDto.setId(productCategory.getId());
        return productDto;
    }

//    @PostMapping("/create")
//    public ResponseEntity<ApiResponse> createProductCategory(@Valid @RequestBody ProductCategory productCategory) {
//        if (Helper.notNull(productCategoryService.readProductCategory(productCategory.getCategoryName()))) {
//            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
//        }
//        productCategoryService.createProductCategory(productCategory);
//        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
//    }

//    TODO create an UPDATE method Giridhar

//    @PostMapping("/update/{idCategory}")
//    public ResponseEntity<ApiResponse> updateProductCategory(@PathVariable("idCategory") int idCategory, @Valid @RequestBody ProductCategory productCategory) {
//        // Check to see if the category exists.
//        if (Helper.notNull(productCategoryService.readProductCategory(idCategory))) {
//            // If the category exists then update it.
//            productCategoryService.updateProductCategory(idCategory, productCategory);
//            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
//        }
//
//        // If the category doesn't exist then return a response of unsuccessful.
//        return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        productCategoryRepository.deleteById(id);
    }

}
