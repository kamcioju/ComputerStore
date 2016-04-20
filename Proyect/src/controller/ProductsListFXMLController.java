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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.controlsfx.control.RangeSlider;

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

    @FXML
    private AnchorPane content;
    @FXML
    private TableView<Product> productsTableView;
    @FXML
    private TableColumn<Product, Integer> priceColumn;
    @FXML
    private TableColumn<Product, Integer> quantityColumn;
    @FXML
    private TableColumn<Product, String> descriptionColumn;
    @FXML
    private VBox searchBar;
    private ObservableList<Product> tableList = FXCollections.observableArrayList();
    private List<Product> staticProductList;
    private Product selectedProduct;
    Product.Category category;
    RangeSlider hSlider;
    @FXML
    private TextField searchBox;
    @FXML
    private Button showDetalisButton;
    @FXML
    private Button addToCardButton;

    public void setupSlider(ObservableList<Product> p_list) {
        //setup minimum and maximum
        double minimumPrice = p_list.get(0).getPrice();
        double maximumPrice = p_list.get(0).getPrice();
        category = p_list.get(0).getCategory();
        for (Product p : p_list) {

            if (p.getPrice() < minimumPrice) {
                minimumPrice = p.getPrice();
            }
            if (p.getPrice() > maximumPrice) {
                maximumPrice = p.getPrice();
            }
        }

        hSlider = new RangeSlider(minimumPrice, maximumPrice, minimumPrice, maximumPrice);
        hSlider.setShowTickMarks(true);
        hSlider.setShowTickLabels(true);
        hSlider.setBlockIncrement(1);
        searchBar.getChildren().add(hSlider);
        hSlider.highValueProperty().addListener((observable, oldValue, newValue) -> {
            RefreshList();
        });
        hSlider.lowValueProperty().addListener((observable, oldValue, newValue) -> {
            RefreshList();
        });
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            RefreshList();
        });

    }

    public void initController(List<Product> p_list) {
        this.staticProductList = p_list;
        tableList.addAll(this.staticProductList);
        setupSlider(tableList);
        productsTableView.setItems(tableList);
        productsTableView.getSortOrder().add(descriptionColumn);
        showDetalisButton.disableProperty().bind(productsTableView.getSelectionModel().selectedItemProperty().isNull());
        addToCardButton.disableProperty().bind(productsTableView.getSelectionModel().selectedItemProperty().isNull());
       productsTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
    public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
              ShowDetalis(null);  //null will fix everything!        
        }
    }
});
    }

    @FXML
    public void ShowDetalis(ActionEvent event) {
        try {
            selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProductDescriptionFXML.fxml"));
            Parent root = (Parent) loader.load();
            ProductDescriptionFXMLController appController = loader.<ProductDescriptionFXMLController>getController();
            ChangeContent(root);
            appController.initController(selectedProduct);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ChangeContent(AnchorPane loader) {
        Stage search = new Stage();

        AnchorPane aPane = (AnchorPane) loader;
        aPane.autosize();
        this.content.getChildren().clear();
        content.getChildren().clear();
        this.content.setScaleY(1000);

    }

    public void ChangeContent(Parent loader) {
        AnchorPane aPane = (AnchorPane) loader;
        CurrentContent.currentContent.getChildren().clear();
        CurrentContent.currentContent.getChildren().addAll(aPane.getChildren());

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        descriptionColumn.prefWidthProperty().bind(productsTableView.widthProperty().divide(2).subtract(10));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        descriptionColumn.setSortType(TableColumn.SortType.DESCENDING);

        priceColumn.prefWidthProperty().bind(productsTableView.widthProperty().divide(4).subtract(10));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));

        quantityColumn.prefWidthProperty().bind(productsTableView.widthProperty().divide(4));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("Stock"));

    }

    private void RefreshList() {
        tableList.clear();
        for (Product p : staticProductList) {
            if ((p.getPrice() <= hSlider.highValueProperty().doubleValue())
                    && (p.getPrice() >= hSlider.lowValueProperty().doubleValue())
                    && (p.getDescription().contains(searchBox.getCharacters()))) {
                tableList.add(p);
            }
        }
        productsTableView.sort();
    }

    @FXML
    private void AddToCard(ActionEvent event) {
    }

}
