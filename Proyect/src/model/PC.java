/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import es.upv.inf.*;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author qjot
 */
@XmlRootElement
public class PC {

    @XmlElement(name = "Product")
    private List<Product> productsList = new ArrayList<Product>();

    //Constructors
    public PC() {
        this.totalPrice = 0.0;
    }

    public List<Product> GetProductsByCategory(Product.Category category) {
        List<Product> tempList = new ArrayList<Product>();
        for (Product product : productsList) {
              if(product.getCategory().equals(category))
                      {
                          tempList.add(product);
                      }
        }
        return tempList;
    }

    public List<Product> getProductList() {
        return productsList;
    }

    public void setProductList(List<Product> Components) {
        this.productsList = Components;
    }

    /*private Product motherboard;
    private Product cpu;
    private Product computerCase;
    private List<Product> ramList = new ArrayList<Product>();
    private List<Product> gpuList = new ArrayList<Product>();
    private List<Product> hardDriveList= new ArrayList<Product>();
    private List<Product> optionalComponents = new ArrayList<Product>();   
     */
    private double totalPrice;
    private String pcName;

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName.toUpperCase();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addProduct(Product product) {
        this.productsList.add(product);
    }

    public void addProductList(List<Product> productList) {
        this.productsList.addAll(productList);
    }
}
