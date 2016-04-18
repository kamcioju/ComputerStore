/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import es.upv.inf.Database;
import es.upv.inf.Product;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.PC;

/**
 *
 * @author kaqq.pl
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private ListView ramListView;
    private ObservableList<String> data = null;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Database db = new Database(); //TODO singleton
        PC defaultPc = new PC();
        List<Product> ramList;
        ramList = Database.getProductByCategory(Product.Category.RAM);
        ArrayList<String> myData = new ArrayList<String>();
        for(int i = 0; i < ramList.size();i++)
        {
            myData.add(ramList.get(i).getDescription());
        }
        
        data = FXCollections.observableArrayList(myData);
        ramListView.setItems(data);
        
        // TODO
    }    
    
}
