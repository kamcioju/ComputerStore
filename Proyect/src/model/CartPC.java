/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import es.upv.inf.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * a
 *
 * @author qjot
 */
public class CartPC {

    public static PC currentPC = new PC();

    public static String addProduct(Product product) {
        Product.Category cat = product.getCategory();
        String catString = product.getCategory().toString();
        List<Product> productCategoryList = currentPC.GetProductsByCategory(cat);
        int checkMessage = CheckAvailability(productCategoryList, product, catString);
        switch(checkMessage){
            
            case 1: return "Stock is empty.";
            case 2: return "You can not add more this type of product.";
            case 3: currentPC.addProduct(product); 
                    return "Product added to cart!";
            
        }
        return "Try again";
    }

    public static int CheckAvailability(List<Product> productList, Product product, String catString) {
        int count = 0;
        for (Product thisProduct : productList) {
            if (thisProduct.equals(product)) {
                count++;
            }
        }
        if (product.getStock() <= count) {
            return 1;
        }
        if (!productList.isEmpty()) {
            //SPEAKER, HDD, HDD_SSD, POWER_SUPPLY, DVD_WRITER, RAM, SCREEN, MULTIREADER, MOTHERBOARD, CPU, MOUSE, GPU, KEYBOARD, CASE, FAN
            switch (catString) {
                case "MOTHERBOARD":
                case "CPU":
                case "CASE":
                case "POWER_SUPPLY":
                    return 2; //full cart
                case "HDD":
                case "HDD_SDD":
                case "RAM":
                case "FAN":
                    return CheckQuantiy(productList, 4);
                case "SCREEN":
                case "MOUSE":
                case "GPU":
                case "DVD_WRITER":
                case "KEYBOARD":
                    return CheckQuantiy(productList, 2);

            }

        } else {
            return 3; //You can add product
        }
        return 2; //full cart
    }

    public static int CheckQuantiy(List<Product> productList, int quantity) {
        if (productList.size() < quantity) {
            return 3; //You can add product
        } else {
            return 2; //full cart
        }
    }
}
