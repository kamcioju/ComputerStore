/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Console;
import java.io.IOException;
import static java.lang.System.console;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CurrentContent;
import model.PC;

/**
 * FXML Controller class
 *
 * @author qjot
 */
public class MainWindowFXMLController implements Initializable {

        @FXML
        private AnchorPane contentMainView;
        private List<PC> pcList = new ArrayList<>();
   
        public void InitController()
        {
                  try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLDocument.fxml"));
            Parent root = (Parent) loader.load();
            FXMLDocumentController pcController = loader.<FXMLDocumentController>getController();
            pcList= pcController.GetPcList();
         
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    
    @FXML
    private void ShowDefaultPc(ActionEvent event) {
        try {
            Button btn = (Button) event.getSource();
            String pcName = btn.getId();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLDocument.fxml"));
            Parent root = (Parent) loader.load();
            FXMLDocumentController pcController = loader.<FXMLDocumentController>getController();
           // delete pcList= pcController.GetPcList();
            pcController.ShowDefaultPc(pcName.toUpperCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    
        public void ShowDefaultPc(String pcName)
    {       
        for (int i=0; i<pcList.size();i++)
        {
            String tempString = pcList.get(i).getPcName();
            if(tempString.equals(pcName))
            {
                GoToPcDesc(pcList.get(i));
                break;
            }
                  
        }
    }
        
          public void GoToPcDesc(PC pc)
    {
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
               
        private void ChangeContent(Parent loader) {
        Stage search = new Stage();
        AnchorPane aPane = (AnchorPane) loader;
        aPane.autosize(); 
        contentMainView = CurrentContent.currentContent;
        contentMainView.getChildren().clear();
        //contentMainView.getChildren().addAll(aPane.getChildren());
        contentMainView.getChildren().add(aPane);
    
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
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
