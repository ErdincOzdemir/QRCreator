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
import com.erdincozdemir.qrcodecreator.utilities.Utils;
import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author erdinc.ozdemir
 */
public class MainScreenController implements Initializable {
    
    
    private VCard vcard = new VCard();
    
    @FXML
    private TextField txtWidth;
    
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
    private ListView lstAddresses;
        
    @FXML
    private TextField txtEmail;
    @FXML
    private ListView lstEmailAddresses;
    
    @FXML
    private ComboBox cmbPhoneType;
    @FXML
    private TextField txtPhone;
    @FXML
    private ListView lstPhoneNumbers;
    
    @FXML
    private void openSettings(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/erdincozdemir/qrcodecreator/main/SettingsScreen.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Ayarlar");
            stage.setScene(new Scene(root1));  
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void createQRCode(ActionEvent event) {
        try {
            
            
            StringBuilder formErrors = new StringBuilder();
            
            if(txtWidth.getText().isEmpty() || !Utils.isNumeric(txtWidth.getText())) {
                formErrors.append("Lütfen geçerli bir genişlik girin!\n");
            }
            
            if(txtFirstName.getText().isEmpty()) {
                formErrors.append("Lütfen isim girin!\n");
            }        
            if(txtLastName.getText().isEmpty()) {
                formErrors.append("Lütfen soyisim girin!\n");
            }

            if(!formErrors.toString().equals("")) {
                Utils.showAlertMessage(Alert.AlertType.ERROR, "Devam edebilmek için aşağıdaki hataları düzeltin", "Hata", formErrors.toString());
                return;
            }
            
            vcard.setWidth(Integer.valueOf(txtWidth.getText()));            
            vcard.setHeight(Integer.valueOf(txtWidth.getText()));
            vcard.setFirstName(txtFirstName.getText());
            vcard.setLastName(txtLastName.getText());
            vcard.setCompanyName(txtCompanyName.getText());
            vcard.setTitle(txtTitle.getText());
            vcard.setWebUrl(txtUrl.getText());
            
            Optional<ButtonType> result = Utils.showOptionalAlert(vcard.getSummary(), "Emin misiniz?", vcard.getDetail());
                if(result.get() == ButtonType.OK) {

                FileChooser fileChooser = new FileChooser();

                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
                fileChooser.getExtensionFilters().add(extFilter);
                File file = fileChooser.showSaveDialog(new Stage());
                vcard.createQRCode(file.getPath());
            }            
        } catch (Exception ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void addAddress(ActionEvent event) {
        Address address = new Address();
        
        StringBuilder formErrors = new StringBuilder();
        if(cmbAddressType.getValue() == null) {
            formErrors.append("Lütfen tip seçiniz!\n");
        }        
        if(txtAddressLine.getText().isEmpty()) {
            formErrors.append("Lütfen adres girin!\n");
        }        
        if(txtCity.getText().isEmpty()) {
            formErrors.append("Lütfen şehir girin!\n");
        }     
        if(txtCountry.getText().isEmpty()) {
            formErrors.append("Lütfen ülke girin!\n");
        } 
        
        if(!formErrors.toString().equals("")) {
            Utils.showAlertMessage(Alert.AlertType.ERROR, "Devam edebilmek için aşağıdaki hataları düzeltin", "Hata", formErrors.toString());
            return;
        }
        
        
        if(cmbAddressType.getValue().equals("İş")) {
            address.setAddressType(Address.AddressType.WORK);
        } else if(cmbAddressType.getValue().equals("Ev")) {
            address.setAddressType(Address.AddressType.HOME);
        }
        
        address.setAddressLine(txtAddressLine.getText());
        address.setCity(txtCity.getText());
        address.setCountry(txtCountry.getText());
        this.vcard.getAddresses().add(address);
        ObservableList<Address> list = FXCollections.observableArrayList(this.vcard.getAddresses());
        lstAddresses.setItems(list);
    }
    
    @FXML
    private void addEmail(ActionEvent event) {
        Email email = new Email();
        
        StringBuilder formErrors = new StringBuilder();       
        if(txtEmail.getText().isEmpty()) {
            formErrors.append("Lütfen email girin!\n");
        }        
        if(!Utils.isValidEmailAddress(txtEmail.getText())) {
            formErrors.append("Lütfen geçerli bir email adresi girin!\n");
        }
        
        if(!formErrors.toString().equals("")) {
            Utils.showAlertMessage(Alert.AlertType.ERROR, "Devam edebilmek için aşağıdaki hataları düzeltin", "Hata", formErrors.toString());
            return;
        }
       
        
        email.setEmail(txtEmail.getText());
        this.vcard.getEmails().add(email);
        ObservableList<Email> list = FXCollections.observableArrayList(this.vcard.getEmails());
        lstEmailAddresses.setItems(list);
    }
    
    @FXML
    private void addPhoneNumber(ActionEvent event) {
        PhoneNumber phoneNumber = new PhoneNumber();
        StringBuilder formErrors = new StringBuilder();
        if(cmbPhoneType.getValue() == null) {
            formErrors.append("Lütfen tip seçiniz!\n");
        }        
        if(txtPhone.getText().isEmpty()) {
            formErrors.append("Lütfen telefon numarası girin!\n");
        }        
        if(!Utils.isNumeric(txtPhone.getText())) {
            formErrors.append("Lütfen geçerli bir telefon numarası girin!\n");
        }
        
        if(!formErrors.toString().equals("")) {
            Utils.showAlertMessage(Alert.AlertType.ERROR, "Devam edebilmek için aşağıdaki hataları düzeltin", "Hata", formErrors.toString());
            return;
        }
        
        if(cmbPhoneType.getValue().equals("İş")) {
            phoneNumber.setPhoneNumberType(PhoneNumber.PhoneNumberType.WORK);
        } else if(cmbPhoneType.getValue().equals("Ev")) {
            phoneNumber.setPhoneNumberType(PhoneNumber.PhoneNumberType.HOME);
        }
        
        phoneNumber.setPhoneNumber(txtPhone.getText());
        this.vcard.getPhoneNumbers().add(phoneNumber);
        ObservableList<PhoneNumber> list = FXCollections.observableArrayList(this.vcard.getPhoneNumbers());
        lstPhoneNumbers.setItems(list);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
