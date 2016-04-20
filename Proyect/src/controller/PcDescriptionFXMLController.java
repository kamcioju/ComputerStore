/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import es.upv.inf.Product;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.CurrentContent;
import model.PC;
import model.PcMarshing;

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
    @FXML TextField configurationField;

    private ObservableList<Product> product_list = FXCollections.observableArrayList();
    private PC currentPc;
    
    @FXML
    private Button showDetalisButton;
    @FXML
    private AnchorPane content;
    @FXML
    private TextField searchBox;
    @FXML
    private Button addToCardButton1;

    public void initController(PC pc) {
        this.currentPc = pc;
        addComponentsToTableView(product_list);
        productsTableView.getSortOrder().add(descriptionColumn);
        showDetalisButton.disableProperty().bind(productsTableView.getSelectionModel().selectedItemProperty().isNull());
        addToCardButton1.disableProperty().bind(productsTableView.getSelectionModel().selectedItemProperty().isNull());
        productsTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
    public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
              ShowDetalis(null);  //null will fix everything!        
        }
    }
});
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
        quantityColumn.prefWidthProperty().bind(productsTableView.widthProperty().divide(3));
        descriptionColumn.prefWidthProperty().bind(productsTableView.widthProperty().divide(2).subtract(10));
        quantityColumn.prefWidthProperty().bind(productsTableView.widthProperty().divide(3));
        categoryColumn.prefWidthProperty().bind(productsTableView.widthProperty().divide(3));
    }

    private void addComponentsToTableView(ObservableList<Product> product_list) {

        if (!currentPc.getProductList().isEmpty()) {
            product_list.addAll(currentPc.getProductList());
        }
        productsTableView.setItems(product_list);
   }

    @FXML private void SaveConfiguration(ActionEvent ecent)
    {
        String configurationName = configurationField.getText();
        currentPc.setPcName(configurationName);
        PcMarshing.marshalingUserConfiguration(currentPc);
    }
    @FXML
    private void RemoveFromCard(ActionEvent event) {
        
        
        //to to!
    }

     @FXML
    public void ShowDetalis(ActionEvent event) {
        try {
            Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProductDescriptionFXML.fxml"));
            Parent root = (Parent) loader.load();
            ProductDescriptionFXMLController appController = loader.<ProductDescriptionFXMLController>getController();
            ChangeContent(root);
            appController.initController(selectedProduct);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ChangeContent(Parent loader) {
        AnchorPane aPane = (AnchorPane) loader;
        CurrentContent.currentContent.getChildren().clear();
        CurrentContent.currentContent.getChildren().addAll(aPane.getChildren());
    }

}
