/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import es.upv.inf.Database;
import es.upv.inf.Product;
import java.io.Console;
import java.io.IOException;
import static java.lang.System.console;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CartPC;
import model.CurrentContent;
import model.PC;
import model.PcMarshing;

/**
 * FXML Controller class
 *
 * @author qjot
 */
public class MainWindowFXMLController implements Initializable {

    @FXML
    private AnchorPane contentMainView;
    private List<PC> pcList = new ArrayList<>();
    @FXML
    private Button button1;

    public void InitController() {
        button1.setStyle("-fx-background-image: url('/img/Gaming-PC.jpg')");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLDocument.fxml"));
            Parent root = (Parent) loader.load();
            FXMLDocumentController pcController = loader.<FXMLDocumentController>getController();
            pcList = pcController.GetPcList();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ChangeContent(Parent loader) {
        Stage search = new Stage();
        AnchorPane aPane = (AnchorPane) loader;
        aPane.autosize();

        CurrentContent.currentContent.getChildren().clear();
        CurrentContent.currentContent.getChildren().addAll(aPane.getChildren());

    }

    @FXML
    private void GoToPcDescription(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PcDescriptionFXML.fxml"));
            Parent root = (Parent) loader.load();
            PcDescriptionFXMLController pcController = loader.<PcDescriptionFXMLController>getController();
            ChangeContent(root);

            if (event != null) {
                Button btn = (Button) event.getSource();
                String pcName = btn.getId();
                for (int i = 0; i < pcList.size(); i++) {
                    String tempName = pcList.get(i).getPcName().toUpperCase();
                    if (tempName.equals(pcName)) {
                        CartPC.currentPC = pcList.get(i);
                    }
                }
            }
            pcController.initController(CartPC.currentPC);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void GoToShop(ActionEvent event) {
        try {
             CurrentContent.button1.set(true);
           CurrentContent.button2.set(true);
           CurrentContent.button3.set(true);
           CurrentContent.button4.set(true);
           CurrentContent.button5.set(true);
           CurrentContent.button6.set(true);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProductsListFXML.fxml"));
            Parent root = (Parent) loader.load();
            ProductsListFXMLController productsController = loader.<ProductsListFXMLController>getController();
            ChangeContent(root);
            List<Product> product_list = Database.getProductByCategory(Product.Category.MOTHERBOARD);
            PC newPc = new PC();
            CartPC.currentPC = newPc;
            productsController.initController(product_list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void LoadConfiguration() {
        List<PC> userPcs = new ArrayList<PC>();
        userPcs = PcMarshing.LoadPcConfiguration();
        if (!userPcs.isEmpty()) {
            List<String> choices = new ArrayList<>();
            for (PC pc : userPcs) {
                choices.add(pc.getPcName());
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
            dialog.setTitle("Load Configuration");
            dialog.setHeaderText("Your current cart will be removed!");
            dialog.setContentText("Choose a configuration");
            Optional<String> result = dialog.showAndWait();
// Before Java 8
            if (result.isPresent()) {
                for (PC pc : userPcs) {
                    if (pc.getPcName() == result.get()) {
                        CartPC.currentPC = pc;
                        GoToPcDescription(null);
                    }
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
