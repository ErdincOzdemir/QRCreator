/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erdincozdemir.qrcodecreator.objects;

/**
 *
 * @author erdinc.ozdemir
 */
public class Email implements Base {
    
    private final String vCardString = "EMAIL;TYPE=INTERNET:%s%n";
    
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return this.getEmail();
    }

    @Override
    public String toVCardString() {
        return String.format(this.vCardString, this.email);
    }     
}
