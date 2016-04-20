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
 *
 * @author qjot
 */
public class CartPC  {
    
    public static PC currentPC = new PC();
    
    public static boolean addProduct(Product product)
    {
        Product.Category cat = product.getCategory();
        String catString = product.getCategory().toString();
        List<Product> productCategoryList = currentPC.GetProductsByCategory(cat);
        if(!productCategoryList.isEmpty())
        {
             //SPEAKER, HDD, HDD_SSD, POWER_SUPPLY, DVD_WRITER, RAM, SCREEN, MULTIREADER, MOTHERBOARD, CPU, MOUSE, GPU, KEYBOARD, CASE, FAN

            if(catString.equals("MOTHERBOARD"))
                    {
                        return false;
                    }
        }
        return true;
    }
}
