/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erdincozdemir.qrcodecreator.main;

import com.erdincozdemir.qrcodecreator.objects.VCard;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author erdinc.ozdemir
 */
public class MainScreenController implements Initializable {
    
    
    private VCard vcard = new VCard();
    
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtCompanyName;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtUrl;
    
    @FXML
    private void createQRCode(ActionEvent event) {
        System.out.println("You clicked me!");
        
        vcard.setFirstName(txtFirstName.getText());
        vcard.setLastName(txtLastName.getText());
        vcard.setCompanyName(txtCompanyName.getText());
        vcard.setTitle(txtTitle.getText());
        vcard.setWebUrl(txtUrl.getText());
        System.out.println(vcard.getLastName());
    }
    
    @FXML
    private void addAddress(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
