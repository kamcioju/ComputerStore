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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
            Parent root = (Parent) loader.load();
            AnchorPane aPane = (AnchorPane) root;
            MainWindowFXMLController mainController = loader.<MainWindowFXMLController>getController();
            mainController.InitController();
            CurrentContent.currentContent = aPane;
            CurrentContent.type = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.show();
          
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
