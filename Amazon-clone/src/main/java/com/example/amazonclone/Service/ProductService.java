package com.example.amazonclone.Service;

import com.example.amazonclone.Model.Category;
import com.example.amazonclone.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {

    private ArrayList<Product> products = new ArrayList<>();
    private final CategoryService categoryService;


    public ArrayList<Product> getProducts() {
        return products;
    }


    public boolean addProduct(Product product) {
        for (int i = 0; i < categoryService.getCategories().size(); i++) {
            if (product.getCategoryID().equals(categoryService.getCategories().get(i).getId())) {
                products.add(product);
                return true;
            }
        }
        return false;
    }

    public int updateProduct(String id, Product updatedProduct) {
            boolean checkcategory = false;
            for (Category category : categoryService.getCategories()) {
                if (updatedProduct.getCategoryID().equals(category.getId())) {
                    checkcategory = true;
                    break;
                }
            }
            if (!checkcategory) {
                return 0;
            }
            for (int j = 0; j < products.size(); j++) {
                if (products.get(j).getId().equals(id)) {
                    products.set(j, updatedProduct);
                    return 1;
                }
            }
            return 2;
        }




    public boolean deleteProduct(String id){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)){
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    //method 2 for endpoint 2
    public ArrayList<Product> getNewlyAddedProducts(LocalDate fromDate) {
        ArrayList<Product> newlyAddedProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getDateAdded().isAfter(fromDate)) {
                newlyAddedProducts.add(product);
            }
        }
        return newlyAddedProducts;
    }


    public ArrayList searchProductsByName(String name) {
        ArrayList result = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                result.add(product);
            }
        }
        return result;
    }

    public ArrayList filterProductsByPriceRange(double minPrice, double maxPrice) {
        ArrayList result = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getPrice() >= minPrice && products.get(i).getPrice() <= maxPrice) {
                result.add(products.get(i));
            }
        }
        return result;
    }



}

