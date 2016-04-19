/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import es.upv.inf.Database;
import es.upv.inf.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.PC;

/**
 *
 * @author kaqq.pl
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button motherboardButton;
    @FXML
    private AnchorPane content;
    @FXML
    private ListView ramListView;
    private ObservableList<String> data = null;
    private List<PC> pcList = new ArrayList<>();
    private List<Product> product_list = new ArrayList<>();

    private void ChangeContent(Parent loader) {
        Stage search = new Stage();
        AnchorPane aPane = (AnchorPane) loader;
        content.getChildren().clear();
        content.getChildren().add(aPane);

    }

    @FXML
    private void GoToPcDescription(ActionEvent event) {
        try {

            String pcName = event.getSource().toString();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PcDescriptionFXML.fxml"));
            Parent root = (Parent) loader.load();
            PcDescriptionFXMLController pcController = loader.<PcDescriptionFXMLController>getController();
            ChangeContent(root);
            pcController.initController(pcList.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        
     @FXML
     private void GoToProductsList(ActionEvent event)
     {
         try
         {
             Button btn = (Button) event.getSource(); 
             String categoryName = btn.getText();
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProductsListFXML.fxml"));
             Parent root = (Parent) loader.load();
             ProductsListFXMLController productsController = loader.<ProductsListFXMLController>getController();
            ChangeContent(root);
            product_list = Database.getProductByCategory(Product.Category.MOTHERBOARD);
            productsController.initController(product_list);
        } catch (IOException e) {
            e.printStackTrace();
        }
         }
     

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindowFXML.fxml"));
        //     ChangeContent(loader);
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        PC standardPC = new PC();
        standardPC.setMotherboard(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(0));
        standardPC.setComputerCase(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(0));
        standardPC.setCpu(Database.getProductByCategory(Product.Category.CPU).get(0));
        standardPC.setGpuList(Database.getProductByCategory(Product.Category.GPU).subList(0, 2));
        standardPC.setHardDriveList(Database.getProductByCategory(Product.Category.HDD).subList(0, 2));
        standardPC.setRamList(Database.getProductByCategory(Product.Category.RAM).subList(0, 1));
        standardPC.setOptionalComponents(Database.getProductByCategory(Product.Category.MOUSE).subList(0, 2));

        pcList.add(standardPC);
    }

}
