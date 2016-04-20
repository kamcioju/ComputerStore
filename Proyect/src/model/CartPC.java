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
 *a
 * @author qjot
 */
public class CartPC {

    public static PC currentPC = new PC();

    public static boolean addProduct(Product product) {
        Product.Category cat = product.getCategory();
        String catString = product.getCategory().toString();
        List<Product> productCategoryList = currentPC.GetProductsByCategory(cat);
        if(CheckAvailability(productCategoryList, catString))
        {
            currentPC.addProduct(product);
            return true;
        }
        return false;
    }
    public static boolean CheckAvailability(List<Product> productList, String catString)
    {
                if (!productList.isEmpty()) {
            //SPEAKER, HDD, HDD_SSD, POWER_SUPPLY, DVD_WRITER, RAM, SCREEN, MULTIREADER, MOTHERBOARD, CPU, MOUSE, GPU, KEYBOARD, CASE, FAN
            switch (catString) {
                case "MOTHERBOARD":
                case "CPU":
                case "CASE":
                case "POWER_SUPPLY":
                    return false;
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

        }
        else 
        {
            return true;
        }
        return false;
    }
    public static boolean CheckQuantiy(List<Product> productList, int quantity) {
        if (productList.size() <= quantity) {
            return true;
        } else {
            return false;
        }
    }
}
