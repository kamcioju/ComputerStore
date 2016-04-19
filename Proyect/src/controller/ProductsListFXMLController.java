/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import es.upv.inf.Product;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    public void initController(List<Product> p_list)
    {
        this.productList = p_list;
        product_list.addAll(p_list);
        productsTableView.setItems(product_list);
        productsTableView.autosize();
        //this.currentPc = pc;    
       //addComponentsToTableView(product_list);
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("Stock"));
        //productsTableView.setColumnResizePolicy((param) -> true );
        productsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // TODO
    }    
    
}
