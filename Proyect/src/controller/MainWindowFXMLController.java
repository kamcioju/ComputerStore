/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author qjot
 */
public class MainWindowFXMLController implements Initializable {

    
    @FXML
    private void ShowDefaultPc(ActionEvent event) {
        try {
            Button btn = (Button) event.getSource();
            String pcName = btn.getId();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLDocument.fxml"));
            Parent root = (Parent) loader.load();
            FXMLDocumentController pcController = loader.<FXMLDocumentController>getController();
            pcController.ShowDefaultPc(pcName.toUpperCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
