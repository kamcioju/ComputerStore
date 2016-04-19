/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import es.upv.inf.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author qjot
 */
public class ProductDescriptionFXMLController implements Initializable {

    @FXML private Label companyLabel;
    @FXML private Label nameLabel;
    @FXML private Label priceLabel;
    @FXML private Label stockLabel;
    
    private Product product;
    
    public void initController(Product _product)
    {
        this.product = _product;
//        companyLabel.setText(product.getDescription());
  //      nameLabel.setText(product.getDescription());
   //     priceLabel.setText(String.valueOf(product.getPrice()));
    //    companyLabel.setText(String.valueOf(product.getStock()));
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
    
}
