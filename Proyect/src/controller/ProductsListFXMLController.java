/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import es.upv.inf.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CurrentContent;
import model.PC;


/**
 * FXML Controller class
 *
 * @author qjot
 */
public class ProductsListFXMLController implements Initializable {
    @FXML private AnchorPane content;
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
       //addComponentsToTableView(product_list)
    }
     @FXML 
    public void GoToProductDescription(ActionEvent event)
    {
        try
        {
        selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProductDescriptionFXML.fxml"));
        Parent root = (Parent) loader.load();
        ProductDescriptionFXMLController appController = loader.<ProductDescriptionFXMLController>getController();
        ChangeContent(root);
        
        
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/view/FXMLDocument.fxml"));
        Parent mainRoot = (Parent) mainLoader.load();
        FXMLDocumentController mainController = mainLoader.<FXMLDocumentController>getController();
        content = mainController.GetContent();
        ChangeContent(root);
        //appController.GoToProductDescription(selectedProduct);
        
        
        //appController.GoToProductDescription(selectedProduct);
        appController.initController(selectedProduct);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
       public void ChangeContent(Parent loader) {
        Stage search = new Stage();
        AnchorPane aPane = (AnchorPane) loader;
        aPane.autosize();
        content = CurrentContent.currentContent;
        content.getChildren().clear();
        //if(type==1)
        //content.getChildren().add(aPane);
        //else
        content.getChildren().addAll(aPane);
        
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
