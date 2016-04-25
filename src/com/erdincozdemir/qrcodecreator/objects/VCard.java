/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erdincozdemir.qrcodecreator.objects;

import java.io.FileOutputStream; 

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph; 
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.Rectangle;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.media.jai.operator.AWTImageDescriptor; //Java advanced Imaging library

/**
 *
 * @author erdinc.ozdemir
 */
public class VCard {
    private String firstName;
    private String lastName;
    private String companyName;
    private String Title;
    private String webUrl;
    private List<Address> addresses;
    private List<Email> emails;
    private List<PhoneNumber> phoneNumbers;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public VCard() {
        this.addresses = new ArrayList<>();
        this.emails = new ArrayList<>();
        this.phoneNumbers = new ArrayList<>();
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public void createQRCode(String path) throws Exception {
        BarcodeQRCode my_code = new BarcodeQRCode(this.toString(), 1, 1, null);
		
		
        Document vCard_QR_Code = new Document(new Rectangle(360, 852));
        vCard_QR_Code.open();              
        vCard_QR_Code.add(my_code.getImage());        
        vCard_QR_Code.close();
        /* Create QR Code as a PNG Image file */
        Image qr_awt_image = my_code.createAwtImage(Color.BLACK,Color.WHITE);        
        AWTImageDescriptor converter=new AWTImageDescriptor();
        try {           
            ImageIO.write(converter.create(qr_awt_image,null), "png",new File(path));        
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getSummary() {
        return this.getAddresses().size() + " adet adres, " + this.getEmails().size() + " adet email, " + this.getPhoneNumbers().size() + " adet telefon numarası bulunan bir kişi kartı oluşturulacak.";
    }
    
    public String getDetail() {
        StringBuilder sb = new StringBuilder();
        sb.append("İsim: " + this.getFirstName() + "\n");
        sb.append("Soyisim: " + this.getLastName()+ "\n");
        if(!this.getCompanyName().isEmpty()) sb.append("Şirket: " + this.getCompanyName()+ "\n");
        if(!this.getTitle().isEmpty()) sb.append("Ünvan: " + this.getTitle()+ "\n");
        if(!this.getWebUrl().isEmpty()) sb.append("Url: " + this.getWebUrl()+ "\n");
        if(this.getAddresses().size() > 0) {
            sb.append("\nAdresler: \n");
            for (Address address : this.addresses) {
                sb.append("\t" + address + "\n");
            }
        }
        if(this.getEmails().size() > 0) {
            sb.append("\nEmailler: \n");
            for (Email email : this.emails) {
                sb.append("\t" + email + "\n");
            }
        }
        if(this.getPhoneNumbers().size() > 0) {
            sb.append("\nTelefon Numaraları: \n");
            for (PhoneNumber phoneNumber : this.phoneNumbers) {
                sb.append("\t" + phoneNumber + "\n");
            }
        }
        return sb.toString();
    }
}
