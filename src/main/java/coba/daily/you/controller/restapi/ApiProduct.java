package coba.daily.you.controller.restapi;

import coba.daily.you.model.dto.ProductDto;
import coba.daily.you.model.entity.Product;
import coba.daily.you.model.entity.ProductCategory;
import coba.daily.you.repository.ProductRepository;
import coba.daily.you.service.ProductCategoryService;
import coba.daily.you.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ApiProduct {

    @Autowired
    ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    ProductRepository productRepository;

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getListProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtos = productList.stream().map(product -> mapProductToProductDto(product)).collect(Collectors.toList());
//        List<ProductDto> body = productService.listProducts();
        return new ResponseEntity<List<ProductDto>>(productDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Integer id) {
        Product product = productRepository.findById(id).get();
        ProductDto productDto= new ProductDto();
        modelMapper.map(product, productDto);
        modelMapper.map(product.getProductCategory(), productDto);
        productDto.setId(product.getId());
        return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductDto>> getProducts(@PathVariable Integer id) {
        List<ProductDto> body = listProductByCategory(id);
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }

    public List<ProductDto> listProductByCategory(Integer id) {
        List<Product> products = productRepository.cariProductCategory(id);
        List<ProductDto> productDtos = products.stream().map(product -> mapProductToProductDto(product)).collect(Collectors.toList());

        return productDtos;
    }

    @GetMapping("/find/{product}")
    public ResponseEntity<List<ProductDto>> getProducts(@PathVariable String product) {
//        String search= "\\y" +product+"\\y";
        List<ProductDto> body = searchProduct(product);
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }

    public List<ProductDto> searchProduct(String search) {
        List<Product> products = productRepository.searchProduct(search);
        List<ProductDto> productDtos = products.stream().map(product -> mapProductToProductDto(product)).collect(Collectors.toList());

        return productDtos;
    }

    @PostMapping
    public ProductDto editSaveProduct(@RequestBody ProductDto productDto){
        Product product = modelMapper.map(productDto, Product.class);
        product.setIdCategory(productDto.getIdCategory());
        product = productService.saveProductMaterDetail(product);
        ProductDto productDtoDB = mapProductToProductDto(product);

        return productDtoDB;
    }

    private ProductDto mapProductToProductDto(Product product){
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        productDto.setIdCategory(product.getProductCategory().getId());
        productDto.setCategoryName(product.getProductCategory().getCategoryName());
        productDto.setId(product.getId());
        return productDto;
    }
//
//    @PostMapping("/add")
//    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
//        Optional<ProductCategory> optionalProductCategory = productCategoryService.readProductCategory(productDto.getIdCategory());
//        if (!optionalProductCategory.isPresent()) {
//            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
//        }
//        ProductCategory productCategory = optionalProductCategory.get();
//        productService.addProduct(productDto, productCategory);
//        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
//    }


//    @PostMapping("/update/{IDproduct}")
//    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("IDproduct") int idProduct, @RequestBody @Valid ProductDto productDto) {
//        Optional<ProductCategory> optionalProductCategory = productCategoryService.readProductCategory(productDto.getIdCategory());
//        if (!optionalProductCategory.isPresent()) {
//            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
//        }
//        ProductCategory productCategory = optionalProductCategory.get();
//        productService.updateProduct(idProduct, productDto, productCategory);
//        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        productRepository.deleteById(id);
    }

}
