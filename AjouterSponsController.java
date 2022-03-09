/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.PI.gui;

import edu.PI.entities.Sponsor;
import edu.PI.services.SponsorCRUD;
import edu.PI.utils.ControleSaisie;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class AjouterSponsController implements Initializable {

    ObservableList<String> types = FXCollections.observableArrayList("Matériel", "Immatériel");

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfNumero;
    private TextField tfType_sponsor;
    @FXML
    private TextField tfId_event;
    @FXML
    private Button tfAjouter_sponsor;
    @FXML
    private ChoiceBox<String> cbType_aide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbType_aide.setItems(types);
        cbType_aide.setValue("Matériel");
        // TODO
    }

    @FXML
    private void ajouterspons(ActionEvent event) throws IOException {
        SponsorCRUD sc = new SponsorCRUD();
        ControleSaisie cs = new ControleSaisie();
        if (!cs.testnomprenom(tfNom.getText())) {
            JOptionPane.showMessageDialog(null, "Le Nom de ce sponsor est incorrect");
        } else if (!cs.testnomprenom(tfPrenom.getText())) {
            JOptionPane.showMessageDialog(null, "Le Prenom de ce sponsor est incorrect");
        } else if (!cs.GSM(tfNumero.getText())) {
            JOptionPane.showMessageDialog(null, "Le Numero de telephone de ce sponsor est incorrect");
        } else {
            Sponsor s = new Sponsor(tfNom.getText(), tfPrenom.getText(), Integer.parseInt(tfNumero.getText()),
                    cbType_aide.getValue(), Integer.parseInt(tfId_event.getText()));
            sc.ajouterSponsor(s);
            JOptionPane.showMessageDialog(null, "Sponsor ajouté ✓");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterSpons.fxml"));
            Parent root = loader.load();
            tfNom.getScene().setRoot(root);
        }
//            String nom_sponsor = tfNom.getText();
//            String prenom_sponsor = tfPrenom.getText();
//            int num_sponsor = Integer.parseInt(tfNumero.getText());
//            String type_sponsor = tfType_sponsor.getText();
//            int id_event = Integer.parseInt(tfId_event.getText());
//
//            Sponsor s = new Sponsor(nom_sponsor, prenom_sponsor, num_sponsor, type_sponsor, id_event);
//            
//            sc.ajouterSponsor(s);

    }

    @FXML
    private void GestionSponsor(ActionEvent event
    ) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherSpons.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
