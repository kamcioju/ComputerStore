/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import es.upv.inf.Database;
import es.upv.inf.Product;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.xml.bind.JAXBException;
import model.CartPC;
import model.CurrentContent;
import model.ListPCWrapper;
import model.PC;
import model.PcMarshing;

/**
 *
 * @author kaqq.pl
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane content;
    @FXML
    private ListView ramListView;
    private ObservableList<String> data = null;
    private List<PC> pcList = new ArrayList<PC>();
    private List<Product> product_list = new ArrayList<>();
    private PC defaultPc;
    public static PC currentPc;
    
    public void ChangeContent(Parent loader) {
        Stage search = new Stage();
        Pane aPane = (Pane) loader;
        aPane.autosize();
        ObservableList<Node> nodeList = aPane.getChildren();
        content.getChildren().clear();
        //if(type==1)
        content.getChildren().addAll(aPane.getChildren());
        CurrentContent.currentContent = content;
        CurrentContent.type=2;
//else
        //content.getChildren().addAll(aPane.getChildren());
    }

    
    public AnchorPane GetContent() {
    return content;
    }
    
    public void ShowDefaultPc(String pcName) {
        for (int i = 0; i < pcList.size(); i++) {
            String tempString = pcList.get(i).getPcName();
            if (tempString.equals(pcName)) {
                GoToPcDesc(pcList.get(i));
                break;
            }
        }
    }

    @FXML
    private void GoToPcDescription(ActionEvent event) {
        try {
            Button btn = (Button) event.getSource();
            String pcName = btn.getId();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PcDescriptionFXML.fxml"));
            Parent root = (Parent) loader.load();
            PcDescriptionFXMLController pcController = loader.<PcDescriptionFXMLController>getController();
            ChangeContent(root);
            for (int i = 0; i < pcList.size(); i++) {
                String tempName = pcList.get(i).getPcName().toUpperCase();
                if (tempName.equals(pcName)) {
                    pcController.initController(pcList.get(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GoToPcDesc(PC pc) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PcDescriptionFXML.fxml"));
            Parent root = (Parent) loader.load();
            PcDescriptionFXMLController pcController = loader.<PcDescriptionFXMLController>getController();
            ChangeContent(root);
            pcController.initController(pc);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<PC> GetPcList() {
        content.getChildren().clear();
        return this.pcList;
    }

    @FXML
    private void GoToProductsList(ActionEvent event) {
        try {
            Button btn = (Button) event.getSource();
            //String categoryName = btn.getText();
            String categoryName = btn.getId();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProductsListFXML.fxml"));
            Parent root = (Parent) loader.load();
            ProductsListFXMLController productsController = loader.<ProductsListFXMLController>getController();
            ChangeContent(root);
            product_list = Database.getProductByCategory(Product.Category.valueOf(categoryName.toUpperCase()));
            productsController.initController(product_list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void GoToMainPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindowFXML.fxml"));
            Parent root = (Parent) loader.load();
            MainWindowFXMLController mainController = loader.<MainWindowFXMLController>getController();
            mainController.InitController();
            ChangeContent(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GoToProductDescription(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProductDescriptionFXML.fxml"));
            Parent root = (Parent) loader.load();
            ChangeContent(root);
            ProductDescriptionFXMLController pdController =loader.<ProductDescriptionFXMLController>getController();
            pdController.initController(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            pcList = PcMarshing.unMarshalingDefaultSet().getPcList();
           
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
