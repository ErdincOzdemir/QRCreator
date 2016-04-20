/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erdincozdemir.qrcodecreator.main;

import com.erdincozdemir.qrcodecreator.objects.Address;
import com.erdincozdemir.qrcodecreator.objects.Email;
import com.erdincozdemir.qrcodecreator.objects.PhoneNumber;
import com.erdincozdemir.qrcodecreator.objects.VCard;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    private ComboBox cmbAddressType;
    @FXML
    private TextField txtAddressLine;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtCountry;
        
    @FXML
    private ComboBox cmbEmailType;
    @FXML
    private TextField txtEmail;
    
    @FXML
    private ComboBox cmbPhoneType;
    @FXML
    private TextField txtPhone;
    
    @FXML
    private void createQRCode(ActionEvent event) {
        try {
            System.out.println("You clicked me!");
            
            vcard.setFirstName(txtFirstName.getText());
            vcard.setLastName(txtLastName.getText());
            vcard.setCompanyName(txtCompanyName.getText());
            vcard.setTitle(txtTitle.getText());
            vcard.setWebUrl(txtUrl.getText());
            
            FileChooser fileChooser = new FileChooser();
            
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(new Stage());
            vcard.createQRCode(file.getPath());
        } catch (Exception ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void addAddress(ActionEvent event) {
        Address address = new Address();
        if(cmbAddressType.getValue().equals("İş")) {
            address.setAddressType(Address.AddressType.WORK);
        } else if(cmbAddressType.getValue().equals("Ev")) {
            address.setAddressType(Address.AddressType.HOME);
        } else if(cmbAddressType.getValue().equals("Diğer")) {
            address.setAddressType(Address.AddressType.OTHER);
        }
        
        address.setAddressLine(txtAddressLine.getText());
        address.setCity(txtCity.getText());
        address.setCountry(txtCountry.getText());
        this.vcard.getAddresses().add(address);
    }
    
    @FXML
    private void addEmail(ActionEvent event) {
        Email email = new Email();
        if(cmbEmailType.getValue().equals("İş")) {
            email.setEmailType(Email.EmailType.WORK);
        } else if(cmbAddressType.getValue().equals("Kişisel")) {
            email.setEmailType(Email.EmailType.PERSONAL);
        }
        
        email.setEmail(txtEmail.getText());
        this.vcard.getEmails().add(email);
    }
    
    @FXML
    private void addPhoneNumber(ActionEvent event) {
        PhoneNumber phoneNumber = new PhoneNumber();
        if(cmbPhoneType.getValue().equals("İş")) {
            phoneNumber.setPhoneNumberType(PhoneNumber.PhoneNumberType.WORK);
        } else if(cmbPhoneType.getValue().equals("Ev")) {
            phoneNumber.setPhoneNumberType(PhoneNumber.PhoneNumberType.HOME);
        } else if(cmbPhoneType.getValue().equals("Diğer")) {
            phoneNumber.setPhoneNumberType(PhoneNumber.PhoneNumberType.OTHER);
        }
        
        phoneNumber.setPhoneNumber(txtPhone.getText());
        this.vcard.getPhoneNumbers().add(phoneNumber);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
