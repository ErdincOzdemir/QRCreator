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
public class PhoneNumber {
    public enum PhoneNumberType {
        WORK, HOME, OTHER
    }
    
    private PhoneNumberType phoneNumberType;
    private String phoneNumber;

    public PhoneNumberType getPhoneNumberType() {
        return phoneNumberType;
    }

    public void setPhoneNumberType(PhoneNumberType phoneNumberType) {
        this.phoneNumberType = phoneNumberType;
    }    
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public String toString() {
        return this.getPhoneNumberType().toString() + " - " + this.getPhoneNumber();
    }
}
