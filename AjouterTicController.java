/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.PI.gui;

import edu.PI.entities.Ticket;
import edu.PI.services.TicketCRUD;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class AjouterTicController implements Initializable {

    ObservableList<String> types = FXCollections.observableArrayList("GOLD", "SILVER",
            "BRONZE ");

    @FXML
    private TextField tfId_event;
    @FXML
    private TextField tfId_Client;
    @FXML
    private TextField tfPrix;
    @FXML
    private ChoiceBox<String> cbtype;
    private TextField tfId_ticket;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbtype.setItems(types);
        cbtype.setValue("GOLD");
    }

    @FXML
    private void AjouterTicket(ActionEvent event) throws IOException {
        TicketCRUD tc = new TicketCRUD();
        ControleSaisie cs = new ControleSaisie();
        if (tfId_Client.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "veuillez remplir le champ id du client");
        } else if (tfId_event.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "veuillez remplir le champ id de l'evenement");
        } else if (tfPrix.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "veuillez remplir le champ prix");
        } else if (!cs.num(tfId_Client.getText())) {
            JOptionPane.showMessageDialog(null, "L'Id du client est incorrect, pas de caractére!");
        } else if (!cs.num(tfId_event.getText())) {
            JOptionPane.showMessageDialog(null, "L'Id est de l'evenement est incorrect, pas de caractére!");
        } else if (!cs.num(tfPrix.getText())) {
            JOptionPane.showMessageDialog(null, "Le prix est incorrect, pas de caractére!");
        } else {
            Ticket t = new Ticket(cbtype.getValue(),
                    Integer.parseInt(tfPrix.getText()), Integer.parseInt(tfId_event.getText()), Integer.parseInt(tfId_Client.getText()));
            tc.ajouterTicket(t);
            JOptionPane.showMessageDialog(null, "Ticket ajouté ✓");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterTic.fxml"));
            Parent root = loader.load();
            tfPrix.getScene().setRoot(root);
        }
    }

    @FXML
    private void GestionTicket(ActionEvent event
    ) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherTic.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
