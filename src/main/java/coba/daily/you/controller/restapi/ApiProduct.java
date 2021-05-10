package coba.daily.you.controller.restapi;

import coba.daily.you.model.dto.ProductDto;
import coba.daily.you.model.entity.Product;
import coba.daily.you.model.entity.ProductCategory;
import coba.daily.you.repository.ProductRepository;
import coba.daily.you.service.ProductCategoryService;
import coba.daily.you.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ApiProduct {

    @Autowired
    ProductService productService;

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    ProductRepository productRepository;

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> body = productService.listProducts();
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Integer id) {
        Product product = productRepository.findById(id).get();
        ProductDto productDto= new ProductDto(product);
        return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductDto>> getProducts(@PathVariable Integer id) {
        List<ProductDto> body = productService.listProductByCategory(id);
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }

    @GetMapping("/find/{product}")
    public ResponseEntity<List<ProductDto>> getProducts(@PathVariable String product) {
//        String search= "\\y" +product+"\\y";
        List<ProductDto> body = productService.searchProduct(product);
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
        Optional<ProductCategory> optionalProductCategory = productCategoryService.readProductCategory(productDto.getIdCategory());
        if (!optionalProductCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        ProductCategory productCategory = optionalProductCategory.get();
        productService.addProduct(productDto, productCategory);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }


    @PostMapping("/update/{IDproduct}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("IDproduct") int idProduct, @RequestBody @Valid ProductDto productDto) {
        Optional<ProductCategory> optionalProductCategory = productCategoryService.readProductCategory(productDto.getIdCategory());
        if (!optionalProductCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        ProductCategory productCategory = optionalProductCategory.get();
        productService.updateProduct(idProduct, productDto, productCategory);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        productRepository.deleteById(id);
    }

}
