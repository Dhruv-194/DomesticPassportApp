package com.auth0.samples;

public class HelperClass {

    String hcurrentcity, hgoingtocity, hname, hpermaddress, hdob, hgender;

    public HelperClass(String hcurrentcity){

    }

    public HelperClass(String hcurrentcity, String hgoingtocity, String hname, String hpermaddress, String hdob, String hgender) {
        this.hcurrentcity = hcurrentcity;
        this.hgoingtocity = hgoingtocity;
        this.hname = hname;
        this.hpermaddress = hpermaddress;
        this.hdob = hdob;
        this.hgender = hgender;
    }

    public String getHcurrentcity() {
        return hcurrentcity;
    }

    public void setHcurrentcity(String hcurrentcity) {
        this.hcurrentcity = hcurrentcity;
    }

    public String getHgoingtocity() {
        return hgoingtocity;
    }

    public void setHgoingtocity(String hgoingtocity) {
        this.hgoingtocity = hgoingtocity;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getHpermaddress() {
        return hpermaddress;
    }

    public void setHpermaddress(String hpermaddress) {
        this.hpermaddress = hpermaddress;
    }

    public String getHdob() {
        return hdob;
    }

    public void setHdob(String hdob) {
        this.hdob = hdob;
    }

    public String getHgender() {
        return hgender;
    }

    public void setHgender(String hgender) {
        this.hgender = hgender;
    }
}
