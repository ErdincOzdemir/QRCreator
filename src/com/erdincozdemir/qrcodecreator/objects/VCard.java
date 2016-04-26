/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erdincozdemir.qrcodecreator.objects;

import com.itextpdf.text.Document;
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
public class VCard implements Base {
    
    private final String vCardHeader = "BEGIN:VCARD\nVERSION:3.0\n";
    private final String vCardFooter = "END:VCARD";
    
    private int width;
    private int height;
    private String firstName;
    private String lastName;
    private String companyName;
    private String title;
    private String webUrl;
    private List<Address> addresses;
    private List<Email> emails;
    private List<PhoneNumber> phoneNumbers;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
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
        return title;
    }

    public void setTitle(String Title) {
        this.title = Title;
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
        System.out.println(this.toVCardString());
        BarcodeQRCode my_code = new BarcodeQRCode(this.toVCardString(), this.width, this.height, null);

        Document vCard_QR_Code = new Document(new Rectangle(360, 852));
        vCard_QR_Code.open();              
        vCard_QR_Code.add(my_code.getImage());        
        vCard_QR_Code.close();
        /* Create QR Code as a PNG Image file */
        Image qr_awt_image = my_code.createAwtImage(Color.BLACK,Color.WHITE);        
        AWTImageDescriptor converter = new AWTImageDescriptor();
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
        sb.append("İsim: ").append(this.getFirstName()).append("\n");
        sb.append("Soyisim: ").append(this.getLastName()).append("\n");
        if(!this.getCompanyName().isEmpty()) sb.append("Şirket: ").append(this.getCompanyName()).append("\n");
        if(!this.getTitle().isEmpty()) sb.append("Ünvan: ").append(this.getTitle()).append("\n");
        if(!this.getWebUrl().isEmpty()) sb.append("Url: ").append(this.getWebUrl()).append("\n");
        if(this.getAddresses().size() > 0) {
            sb.append("\nAdresler: \n");
            for (Address address : this.addresses) {
                sb.append("\t").append(address).append("\n");
            }
        }
        if(this.getEmails().size() > 0) {
            sb.append("\nEmailler: \n");
            for (Email email : this.emails) {
                sb.append("\t").append(email).append("\n");
            }
        }
        if(this.getPhoneNumbers().size() > 0) {
            sb.append("\nTelefon Numaraları: \n");
            for (PhoneNumber phoneNumber : this.phoneNumbers) {
                sb.append("\t").append(phoneNumber).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toVCardString() {
        StringBuilder vCardContent = new StringBuilder();
        vCardContent.append(String.format("N:%s;%s;;%n", this.lastName, this.firstName));
        vCardContent.append(String.format("FN:%s %s%n", this.lastName, this.firstName));
        if(!this.companyName.isEmpty()) vCardContent.append(String.format("ORG:%s%n", this.companyName));
        if(!this.webUrl.isEmpty()) vCardContent.append(String.format("URL:%s%n", this.webUrl));
        if(!this.title.isEmpty()) vCardContent.append(String.format("TITLE:%s%n", this.title));
        
        for (PhoneNumber phoneNumber : this.phoneNumbers) {
            vCardContent.append(phoneNumber.toVCardString());
        }
        
        for (Address address : this.addresses) {
            vCardContent.append(address.toVCardString());
        }
        
        for (Email email : this.emails) {
            vCardContent.append(email.toVCardString());
        }
        
        return String.format("%s%s%s", this.vCardHeader, vCardContent, this.vCardFooter);
    }
}
