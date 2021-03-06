package br.com.andersondi.datawatcher.Model;

public class CustomerModel {
    private String cnpj;
    private String name;
    private String businessArea;

    public CustomerModel(String cnpj, String name, String businessArea) {
        this.cnpj = cnpj;
        this.name = name.toLowerCase();
        this.businessArea = businessArea.toLowerCase();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }
}
