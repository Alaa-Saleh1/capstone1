package com.example.amazonclone.Controller;

import com.example.amazonclone.ApiResponse.ApiResponse;
import com.example.amazonclone.Model.Product;
import com.example.amazonclone.Model.User;
//import com.example.amazonclone.Service.ProdectService;
import com.example.amazonclone.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/amazon-product")
public class ProductController {


    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity<?> getProduct(){
        ArrayList product = productService.getProducts();
        return ResponseEntity.status(200).body(product);
    }

    //تعديل
    @PostMapping("/add")
    public ResponseEntity<?> addProudect(@RequestBody @Valid Product product, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean idAdded = productService.addProduct(product);
        if (idAdded) {
            return ResponseEntity.status(200).body(new ApiResponse("Product has been added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("check Id of category"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody @Valid Product product, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        int isUpdate = productService.updateProduct(id,product);
        if (isUpdate == 0) {
            return ResponseEntity.status(400).body(new ApiResponse("Category does not exist"));
        }
        if (isUpdate == 1) {
            return ResponseEntity.status(200).body(new ApiResponse("Product updated successfully."));
        }
        return ResponseEntity.status(400).body("Product not found");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        boolean isDeleted = productService.deleteProduct(id);

        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Product has been deleted successfully"));
        }
        return ResponseEntity.status(400).body("Product not found");

    }

    //endpoint 2
    @GetMapping("/newly-added/{fromDate}")
    public ResponseEntity<?> getNewlyAddedProducts(@PathVariable LocalDate fromDate) {

        ArrayList<Product> newlyAddedProducts = productService.getNewlyAddedProducts(fromDate);

        if (newlyAddedProducts.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("No products found"));
        }
        return ResponseEntity.status(200).body(newlyAddedProducts);
    }





    //endpoint 5
    @GetMapping("/searchByName/{name}")
    public ResponseEntity<?> searchProductsByName(@PathVariable String name) {
        ArrayList products = productService.searchProductsByName(name);
        if (products.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("No products found"));
        }
        return ResponseEntity.status(200).body(products);
    }

    //endpoint 4
    @GetMapping("/filter-price/{min}/{max}")
    public ResponseEntity<?> filterProductsByPriceRange(@PathVariable double min, @PathVariable double max) {
        ArrayList result = productService.filterProductsByPriceRange(min, max);
        if (result.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("No products found"));
        }
        return ResponseEntity.status(200).body(result);
    }

}
