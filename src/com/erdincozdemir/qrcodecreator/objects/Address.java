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
public class Address implements Base {

    private final String vCardString = "ADR;TYPE=%s:;;%s;%s;%s%nLABEL;TYPE=%s:%s%n";
    
    public enum AddressType {
        WORK, HOME
    }
    
    private AddressType addressType;
    private String addressLine;
    private String city;
    private String country;

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    @Override
    public String toString() {
        return this.getAddressType() + " - " + this.getAddressLine() + " " + this.getCity() + " " + this.getCountry();
    }
    
    @Override
    public String toVCardString() {
        return String.format(this.vCardString, this.addressType, this.addressLine, this.city, this.country, this.addressType, (this.addressLine + " " + this.city + " " + this.country));
    }
}
