package com.example.amazonclone.Service;

import com.example.amazonclone.Model.Product;
import com.example.amazonclone.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    ArrayList<User> users= new ArrayList<>();
    private final ProductService productService;
    private final MerchantService merchantService;
    private final MerchantStockService merchantStockService;



    public ArrayList<User> getUser(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }

    public boolean updateUser(String id, User user){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id)){
                users.set(i,user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)){
                users.remove(i);
                return true;
            }
        }
        return false;
    }



    public int buyProduct(String userId, String productId, String merchantId) {
        boolean checkUserId = false;
        boolean checkMerchantId = false;
        boolean checkProductId = false;
        boolean checkMerchantStock = false;
        boolean checkOutOfBalance = false;
        double price = 0;

        for (int i = 0; i < productService.getProducts().size(); i++) {
            if (productService.getProducts().get(i).getId().equals(productId)) {
                checkProductId = true;
                price = productService.getProducts().get(i).getPrice();

            }

        }
        if (!checkProductId) {
            return 0;
        }
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(userId)) {
                checkUserId = true;
                if (users.get(i).getBalance()>=price){
                    checkOutOfBalance = true;
                }
            }
        }

        if (!checkUserId) {
            return 1;
        }
        if (!checkOutOfBalance) {
            return 2;
        }

        for (int i = 0; i < merchantService.getMerchants().size(); i++) {
            if (merchantService.getMerchants().get(i).getId().equals(merchantId)) {
                checkMerchantId = true;
            }
        }
        if (!checkMerchantId) {
            return 3;
        }

        for (int i = 0; i < merchantStockService.getMerchantStocks().size(); i++) {
            if (merchantStockService.getMerchantStocks().get(i).getMerchantId().equals(merchantId)) {
                if (merchantStockService.getMerchantStocks().get(i).getStock()>=1) {
                    checkMerchantStock = true;
                }
            }
        }

        if (!checkMerchantStock) {
            return 4;
        }

        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < merchantStockService.getMerchantStocks().size(); j++) {
                double totalprice = price-applyDiscount(userId,price);
                users.get(i).setBalance(users.get(i).getBalance()-totalprice);
                merchantStockService.getMerchantStocks().get(j).setStock(merchantStockService.getMerchantStocks().get(j).getStock()-1);
                users.get(i).setCounter(users.get(i).getCounter()+1);
            }

        }
        return 5;
    }

    public double applyDiscount(String userId, double price) {
        double discount = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(userId)) {
                if (users.get(i).getCounter()==0) {
                    discount = price *(5/100);
                    return discount;
                }
                if(users.get(i).isPrime()){
                    discount = price *(10/100);
                }
            }
        }

        return discount;
    }


    // method for endpoint 1
    public int primeSubscription(String userId) {
        boolean checkUserId = false;
        boolean checkBalance = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(userId)) {
                checkUserId = true;
            }
            if (users.get(i).getBalance()>=16){
                checkBalance = true;
            }
            if (checkUserId && checkBalance) {
                users.get(i).setBalance(users.get(i).getBalance()-16);
                users.get(i).setPrime(true);
                return 3;
            }
        }
        if (!checkUserId) {
            return 1;
        }
        if (checkBalance) {
            return 2;
        }
        return 3;

    }













//    //1 endpoint
//    public ArrayList addToWishList(String idUser,String idProdect){
//        for (int i = 0; i < users.size(); i++) {
//            for (int j = 0; j < productService.getProducts().size(); j++) {
//                if (productService.getProducts().get(j).getId().equals(idProdect)){
//                  wishList.add(productService.getProducts().get(j));
//                }
//
//            }
//        }
//        return wishList;
//    }
}
