package com.example.amazonclone.Service;


import com.example.amazonclone.Model.Merchant;
import com.example.amazonclone.Model.MerchantStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    private ArrayList<MerchantStock> merchantStocks = new ArrayList<>();
    private final MerchantService merchantService;
    private final ProductService productService;


    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public int addMerchantStock(MerchantStock merchantStock) {

        boolean checkMerchantId = false;
        for (int i = 0; i < merchantService.getMerchants().size(); i++) {
            if (merchantService.getMerchants().get(i).getId().equals(merchantStock.getMerchantId())) {
                checkMerchantId = true;
                break;
            }
        }
        if (!checkMerchantId) {
            return 0;
        }


        boolean checkProductId = false;
        for (int i = 0; i < productService.getProducts().size(); i++) {
            if (productService.getProducts().get(i).getId().equals(merchantStock.getProductId())) {
                checkProductId = true;
                break;
            }
        }
        if (!checkProductId) {
            return 1;
        }

        merchantStocks.add(merchantStock);
        return 2;
    }



    public int updateMerchantStock(String id, MerchantStock updatedStock) {
        boolean checkMerchantId = false;
        boolean checkProductId = false;

        for (int i = 0; i < merchantService.getMerchants().size(); i++) {
            if (merchantService.getMerchants().get(i).getId().equals(updatedStock.getMerchantId())) {
                checkMerchantId = true;
                break;
            }
        }
        if (!checkMerchantId) {
            return 0;
        }

        for (int i = 0; i < productService.getProducts().size(); i++) {
            if (productService.getProducts().get(i).getId().equals(updatedStock.getProductId())) {
                checkProductId = true;
                break;
            }
        }
        if (!checkProductId) {
            return 1;
        }

        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equals(id)) {
                merchantStocks.set(i, updatedStock);
                return 2;
            }
        }

        return 3;
    }



    public boolean deleteMerchantStock(String id){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equals(id)) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

    //11
    public int addToStock (String idProduct,String idMerchant,int amount){
        boolean checkMerchantId = false;
        boolean checkProductId = false;

        for (int i = 0; i < merchantStocks.size(); i++) {

            if (merchantStocks.get(i).getMerchantId().equals(idMerchant)) {
                checkMerchantId = true;
            }
            if (merchantStocks.get(i).getProductId().equals(idProduct)) {
                checkProductId = true;
            }

            if (checkMerchantId && checkProductId) {
                merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() + amount);
                return 2;
            }
        }

        if (!checkMerchantId) {
            return 0;
        }
        if (!checkProductId) {
            return 1;
        }

        return 2;
    }


    //method 3 for endpoint 3
    public ArrayList<Merchant> findMerchantsByProductAndCity(String productId, String city) {
        ArrayList<Merchant> result = new ArrayList<>();
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getProductId().equals(productId)) {
                for (int j = 0; j < merchantService.getMerchants().size(); j++) {
                    if (merchantService.getMerchants().get(j).getCity().equalsIgnoreCase(city) && merchantService.getMerchants().get(j).getId().equals(merchantStocks.get(i).getMerchantId())) {
                        result.add(merchantService.getMerchants().get(j));
                    }
                }
            }


        }
        return result;
    }
}
