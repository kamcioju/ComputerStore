/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import es.upv.inf.Product;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.PC;


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
    private ObservableList<Product> product_list = FXCollections.observableArrayList();
    private List<Product> productList;
    private Product selectedProduct;
    public void initController(List<Product> p_list)
    {
        this.productList = p_list;
        product_list.addAll(p_list);
        productsTableView.setItems(product_list);
        //this.currentPc = pc;    
       //addComponentsToTableView(product_list);
        
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
        descriptionColumn.prefWidthProperty().bind(productsTableView.widthProperty().divide(2));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        
        priceColumn.prefWidthProperty().bind(productsTableView.widthProperty().divide(4));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
        
        quantityColumn.minWidthProperty().bind(productsTableView.widthProperty().divide(3));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("Stock"));
        // TODO
    }    
    
}
