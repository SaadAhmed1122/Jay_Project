package com.example.stockmanagment;

public class Companymodel {
    int company_id;
    String comapny_name;

    public Companymodel() {
    }

    public Companymodel(String comapny_name) {
        this.comapny_name = comapny_name;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getComapny_name() {
        return comapny_name;
    }

    public void setComapny_name(String comapny_name) {
        this.comapny_name = comapny_name;
    }
}
