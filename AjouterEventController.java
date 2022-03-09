/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.PI.gui;

import edu.PI.entities.Evenement;
import edu.PI.services.EvenementCRUD;
import edu.PI.utils.ControleSaisie;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class AjouterEventController implements Initializable {
            EvenementCRUD ec = new EvenementCRUD();


    ObservableList<String> types = FXCollections.observableArrayList("musique", "Fête foraine",
            "Industriel", "Street Art", "Cinéma", "Gastronomie");
    
    @FXML
    private TextField tfemplacement;
    @FXML
    private TextField tfnom_event;
    @FXML
    private Button tfajouter;
    @FXML
    private TextField tfnombre_participants;
    private TextField tftheme;
    @FXML
    private TextField tfdescription;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private ChoiceBox<String> cbtype;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbtype.setItems(types);
        cbtype.setValue("Cinéma");
        // TODO
    }

    @FXML
    private void ajouterevent(ActionEvent event) throws IOException {
        EvenementCRUD ec = new EvenementCRUD();

        LocalDate ldd = dateDebut.getValue();
        LocalDate ldd1 = dateFin.getValue();
        Date dateDD = Date.valueOf(ldd);
        Date dateDF = Date.valueOf(ldd1);

        ControleSaisie cs = new ControleSaisie();
        
        if (ec.listeevent1(tfnom_event.getText(), tfemplacement.getText(), Integer.parseInt(tfnombre_participants.getText()) )== true)  {
            JOptionPane.showMessageDialog(null, "Cet Evenement existe déja !");
        } else

        if (tfnom_event.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "veuillez remplir le champ nom");
        } else if (tfemplacement.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "veuillez remplir le champ emplacement");
        } else if (tfnombre_participants.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "veuillez remplir le champ nombre de participants");
        } else if (!cs.testnomprenom(tfnom_event.getText())) {
            JOptionPane.showMessageDialog(null, "Le nom de cet evenement est incorrect");
        } else if (!cs.testEmplacement(tfemplacement.getText())) {
            JOptionPane.showMessageDialog(null, "L'emplacement saisie est incorrect");
        } else {
            Evenement e = new Evenement(tfnom_event.getText(), Integer.parseInt(tfnombre_participants.getText()),
                    dateDD, dateDF, tfemplacement.getText(), tfdescription.getText(),
                    cbtype.getValue());
            ec.ajouterEvenement(e);
            JOptionPane.showMessageDialog(null, "Evenement ajouté ✓");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEvent.fxml"));
            Parent root = loader.load();
            tfnom_event.getScene().setRoot(root);
        }

//        int nbr_participant = Integer.parseInt(tfnombre_participants.getText());
//        String description = tfdescription.getText();
////        
//        
//        Evenement e = new Evenement(nom_event, nbr_participant, date_debut, dete_fin, emplacement, description, theme);
//        EvenementCRUD ec = new EvenementCRUD();
//        ec.ajouterEvenement(e);
    }

    @FXML
    private void GestionEvent(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherEvent.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
