/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import es.upv.inf.Database;
import static es.upv.inf.Database.getProductByCategoryAndPrice;
import es.upv.inf.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.controlsfx.control.RangeSlider;




/**
 * FXML Controller class
 *
 * @author qjot
 */
public class ProductsListFXMLController implements Initializable {
  @FXML private TableView<Product> productsTableView;
    @FXML private TableColumn<Product, Integer> priceColumn;
    @FXML private TableColumn<Product, Integer> quantityColumn;
    @FXML private TableColumn<Product, String> descriptionColumn; 
    @FXML private Button showButton;
    @FXML private VBox searchBar;
    private ObservableList<Product> product_list = FXCollections.observableArrayList();
    private List<Product> productList;
    private Product selectedProduct;
    Product.Category category;
    RangeSlider hSlider;
   
    
    
    public void setupSlider(ObservableList<Product>  p_list){
        
        //setup minimum and maximum
        double minimumPrice=p_list.get(0).getPrice();
        double maximumPrice=p_list.get(0).getPrice();
        category =p_list.get(0).getCategory();
        for(Product p: p_list)
        {
            
            if(p.getPrice()<minimumPrice)
            {minimumPrice=p.getPrice();}
            if(p.getPrice()>maximumPrice)
            {maximumPrice=p.getPrice();}
        }
                  RefreshList(category,minimumPrice,minimumPrice);
        hSlider = new RangeSlider(minimumPrice, maximumPrice, minimumPrice, maximumPrice);
        hSlider.setShowTickMarks(true);
        hSlider.setShowTickLabels(true);
        hSlider.setBlockIncrement(1);
        searchBar.getChildren().add(hSlider);
        hSlider.highValueProperty().addListener((observable, oldValue, newValue) -> {
        product_list.clear();
        for(Product p: productList)
        {
            if(p.getPrice()<=newValue.doubleValue())
                product_list.add(p);
        }
        productsTableView.sort();
     // product_list.addAll(Database.getProductByCategoryAndPrice(category, minimumPrice, maximumPrice, true));
});
         hSlider.lowValueProperty().addListener((observable, oldValue, newValue) -> {
         product_list.clear();
             for(Product p: productList)
        {
        if(p.getPrice()>=newValue.doubleValue())
                product_list.add(p);
        }
             productsTableView.sort();
     // product_list.addAll(Database.getProductByCategoryAndPrice(category, minimumPrice, maximumPrice, true));
});
    }
    public void RefreshList(Product.Category category,double minimumPrice,double maximumPrice){
      
      
    }
    public void initController(List<Product> p_list)
    {
        this.productList = p_list;
        product_list.addAll(p_list);
        setupSlider(product_list);
        productsTableView.setItems(product_list);
        //this.currentPc = pc;    
       //addComponentsToTableView(product_list)
    }
     @FXML 
    public void GoToProductDescription(ActionEvent event)
    {
        try
        {
        selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLDocument.fxml"));
        
        Parent root = (Parent) loader.load();
        FXMLDocumentController appController = loader.<FXMLDocumentController>getController();
        appController.GoToProductDescription(selectedProduct);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        
        descriptionColumn.prefWidthProperty().bind(productsTableView.widthProperty().divide(2).subtract(10));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        
        priceColumn.prefWidthProperty().bind(productsTableView.widthProperty().divide(4).subtract(10));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
        
        quantityColumn.prefWidthProperty().bind(productsTableView.widthProperty().divide(4));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("Stock"));
       
       // Bottom.widthProperty().bind(productsTableView.widthProperty()); 
        //Bottom.xProperty().bind(productsTableView.layoutXProperty());
        // TODO
    }    
    
}
