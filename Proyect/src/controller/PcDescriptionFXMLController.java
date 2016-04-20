/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import es.upv.inf.Product;
import java.net.URL;
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
public class PcDescriptionFXMLController implements Initializable {

    @FXML
    private TableView<Product> productsTableView;
    @FXML
    private TableColumn<Product, Integer> priceColumn;
    @FXML
    private TableColumn<Product, Integer> quantityColumn;
    @FXML
    private TableColumn<Product, String> descriptionColumn;
    @FXML
    private TableColumn<Product, String> categoryColumn;

    private ObservableList<Product> product_list = FXCollections.observableArrayList();
    private PC currentPc;

    public void initController(PC pc) {
        this.currentPc = pc;
        addComponentsToTableView(product_list);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));

    }

    private void addComponentsToTableView(ObservableList<Product> product_list) {

        if (!currentPc.getProductList().isEmpty()) {
            product_list.addAll(currentPc.getProductList());
        }
        /*
        if(currentPc.getMotherboard()!=null)
        {
        product_list.add(currentPc.getMotherboard());    
        }
        if(currentPc.getCpu()!=null)
        {
        product_list.add(currentPc.getCpu());    
        }
        if(!currentPc.getGpuList().isEmpty())
        {
        product_list.addAll(currentPc.getGpuList());
        }
        if(!currentPc.getHardDriveList().isEmpty())
        {
        product_list.addAll(currentPc.getHardDriveList());    
        }
         if(!currentPc.getRamList().isEmpty())
        {
        product_list.addAll(currentPc.getRamList());    
        }
        if(currentPc.getComputerCase()!=null)
        {
        product_list.add(currentPc.getComputerCase());    
        }
           if(!currentPc.getOptionalComponents().isEmpty())
        {
        product_list.addAll(currentPc.getOptionalComponents());    
        }*/

        productsTableView.setItems(product_list);

    }

}
