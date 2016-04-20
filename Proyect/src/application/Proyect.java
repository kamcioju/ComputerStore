/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import controller.MainWindowFXMLController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.CurrentContent;

/**
 *
 * @author kaqq.pl
 */
public class Proyect extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindowFXML.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            
            AnchorPane aPane = (AnchorPane) root;
            MainWindowFXMLController mainController = loader.<MainWindowFXMLController>getController();
            mainController.InitController();
            CurrentContent.currentContent = aPane;
            CurrentContent.type = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        BorderPane root = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
        Node temp= root.getChildren().get(2);
         
       AnchorPane cont = (AnchorPane) temp;
        cont.getChildren().clear();
        cont.getChildren().addAll(CurrentContent.currentContent.getChildren());
        Parent p = root;
        Scene scene = new Scene(p);     
        CurrentContent.currentContent=cont;
        stage.setScene(scene);
        stage.setMinWidth(810);
        stage.setMinHeight(620);
        stage.show();
          
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
