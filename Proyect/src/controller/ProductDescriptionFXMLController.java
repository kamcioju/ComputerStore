/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import es.upv.inf.Product;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author qjot
 */
public class ProductDescriptionFXMLController implements Initializable {

    @FXML private Label companyLabel;
    @FXML private Label nameLabel;
    @FXML private Label priceLabel;
    
    private Product product;
    @FXML
    private AnchorPane content;
    @FXML
    private ImageView imageView;
    @FXML
    private Label stockLabel;
    
    public void initController(Product _product) throws FileNotFoundException
    {
        //Image image = new Image("@/cpu.png");
      //  imageView.setImage(image);
        this.product = _product;
        String arr[] = product.getDescription().split(" ", 2);

        companyLabel.setText(arr[0]);
         nameLabel.setText(arr[1]);
         priceLabel.setText(String.valueOf(product.getPrice()));
          stockLabel.setText(String.valueOf(product.getStock()));
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void AddToCart(ActionEvent event) {
    }
    
}
