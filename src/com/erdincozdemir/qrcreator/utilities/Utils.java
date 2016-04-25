/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erdincozdemir.qrcreator.utilities;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author erdinc.ozdemir
 */
public class Utils {
    public static void showAlertMessage(Alert.AlertType type, String header, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
    }
    
    public static Optional<ButtonType> showOptionalAlert(String header, String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }
    
    public static boolean isNumeric(String str) {  
      try {  
        double d = Double.parseDouble(str);  
      } catch(NumberFormatException ex) {  
          return false;  
      }
      return true;  
    }
    
    public static boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
}
