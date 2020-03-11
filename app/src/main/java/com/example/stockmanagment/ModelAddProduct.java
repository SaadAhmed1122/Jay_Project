package com.example.stockmanagment;

import android.graphics.Bitmap;

public class ModelAddProduct {

    int pro_id;
    Bitmap pro_images;
    String pro_name;
    int retail_price;
    int Quantity_packet;
    Float Quantity_Darzan;
    Float Whole_price_drzan;
    Float Whole_prize_cartoon;
    Float discount_pice;
    Float Scheme;
    String company_name;
    String Coupen;
    Float NetAmount_p;
    Float NetAmount_d;
    int extra_discount1;
    int extra_discount2;
    int extra_discount3;
    String City;
    Float finalamount_p;
    Float finalamount_d;

    public ModelAddProduct(Bitmap pro_images, String pro_name, int retail_price,int quantity_packet, Float quantity_Darzan, Float whole_price_drzan, Float whole_prize_cartoon, Float discount_pice, Float scheme, String coupen, Float netAmount_p, Float netAmount_d, int extra_discount1, int extra_discount2, int extra_discount3, String city, Float finalamount_p, Float finalamount_d,String cp_id) {
        this.pro_images = pro_images;
        this.pro_name = pro_name;
        this.retail_price = retail_price;
        Quantity_packet = quantity_packet;
        Quantity_Darzan = quantity_Darzan;
        Whole_price_drzan = whole_price_drzan;
        Whole_prize_cartoon = whole_prize_cartoon;
        this.discount_pice = discount_pice;
        Scheme = scheme;
        Coupen = coupen;
        NetAmount_p = netAmount_p;
        NetAmount_d = netAmount_d;
        this.extra_discount1 = extra_discount1;
        this.extra_discount2 = extra_discount2;
        this.extra_discount3 = extra_discount3;
        City = city;
        this.finalamount_p = finalamount_p;
        this.finalamount_d = finalamount_d;
        this.company_name = cp_id;

    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public Bitmap getPro_images() {
        return pro_images;
    }

    public void setPro_images(Bitmap pro_images) {
        this.pro_images = pro_images;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public int getRetail_price() {
        return retail_price;
    }

    public void setRetail_price(int retail_price) {
        this.retail_price = retail_price;
    }

    public int getQuantity_packet() {
        return Quantity_packet;
    }

    public void setQuantity_packet(int quantity_packet) {
        Quantity_packet = quantity_packet;
    }

    public Float getQuantity_Darzan() {
        return Quantity_Darzan;
    }

    public void setQuantity_Darzan(Float quantity_Darzan) {
        Quantity_Darzan = quantity_Darzan;
    }

    public Float getWhole_price_drzan() {
        return Whole_price_drzan;
    }

    public void setWhole_price_drzan(Float whole_price_drzan) {
        Whole_price_drzan = whole_price_drzan;
    }

    public Float getWhole_prize_cartoon() {
        return Whole_prize_cartoon;
    }

    public void setWhole_prize_cartoon(Float whole_prize_cartoon) {
        Whole_prize_cartoon = whole_prize_cartoon;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Float getDiscount_pice() {
        return discount_pice;
    }

    public void setDiscount_pice(Float discount_pice) {
        this.discount_pice = discount_pice;
    }

    public Float getScheme() {
        return Scheme;
    }

    public void setScheme(Float scheme) {
        Scheme = scheme;
    }

    public String getCoupen() {
        return Coupen;
    }

    public void setCoupen(String coupen) {
        Coupen = coupen;
    }

    public Float getNetAmount_p() {
        return NetAmount_p;
    }

    public void setNetAmount_p(Float netAmount_p) {
        NetAmount_p = netAmount_p;
    }

    public Float getNetAmount_d() {
        return NetAmount_d;
    }

    public void setNetAmount_d(Float netAmount_d) {
        NetAmount_d = netAmount_d;
    }

    public int getExtra_discount1() {
        return extra_discount1;
    }

    public void setExtra_discount1(int extra_discount1) {
        this.extra_discount1 = extra_discount1;
    }

    public int getExtra_discount2() {
        return extra_discount2;
    }

    public void setExtra_discount2(int extra_discount2) {
        this.extra_discount2 = extra_discount2;
    }

    public int getExtra_discount3() {
        return extra_discount3;
    }

    public void setExtra_discount3(int extra_discount3) {
        this.extra_discount3 = extra_discount3;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public Float getFinalamount_p() {
        return finalamount_p;
    }

    public void setFinalamount_p(Float finalamount_p) {
        this.finalamount_p = finalamount_p;
    }

    public Float getFinalamount_d() {
        return finalamount_d;
    }

    public void setFinalamount_d(Float finalamount_d) {
        this.finalamount_d = finalamount_d;
    }
}
