package br.com.andersondi.datawatcher.Model;

import java.util.HashMap;

public class ReportModel {
    private HashMap< String, SalesmanModel > listOfSalespeople;
    private HashMap< String, CustomerModel > listOfCustomers;
    private HashMap< String, SaleModel > listOfSales;
    private Integer numberOfDuplicateSalesman;
    private Integer numberOfDuplicateCustomers;
    private Integer numberOfDuplicateSales;
    private Integer numberOfInvalidInputs;
    private SalesmanModel worstSalesman;
    private SaleModel betterSale;

    public ReportModel() {
        listOfSalespeople = new HashMap<>();
        listOfCustomers = new HashMap<>();
        listOfSales = new HashMap<>();
        numberOfDuplicateSalesman = 0;
        numberOfDuplicateCustomers = 0;
        numberOfDuplicateSales = 0;
        numberOfInvalidInputs = 0;
        worstSalesman = getWorstSalesman();
        betterSale = getBetterSale();
    }

    public Integer getNumberOfDuplicateSalesman() {
        return numberOfDuplicateSalesman;
    }

    public void setNumberOfDuplicateSalesman( Integer numberOfDuplicateSalesman ) {
        this.numberOfDuplicateSalesman = numberOfDuplicateSalesman;
    }

    public Integer getNumberOfDuplicateCustomers() {
        return numberOfDuplicateCustomers;
    }

    public void setNumberOfDuplicateCustomers( Integer numberOfDuplicateCustomers ) {
        this.numberOfDuplicateCustomers = numberOfDuplicateCustomers;
    }

    public Integer getNumberOfDuplicateSales() {
        return numberOfDuplicateSales;
    }

    public void setNumberOfDuplicateSales( Integer numberOfDuplicateSales ) {
        this.numberOfDuplicateSales = numberOfDuplicateSales;
    }

    public HashMap< String, SalesmanModel > getListOfSalespeople() {
        return listOfSalespeople;
    }

    public HashMap< String, CustomerModel > getListOfCustomers() {
        return listOfCustomers;
    }

    public HashMap< String, SaleModel > getListOfSales() {
        return listOfSales;
    }

    public SaleModel getBetterSale() {
        return betterSale;
    }

    public SalesmanModel getWorstSalesman() {
        return worstSalesman;
    }

    public void setListOfSalespeople( HashMap< String, SalesmanModel > listOfSalespeople ) {
        this.listOfSalespeople = listOfSalespeople;
    }

    public void setListOfCustomers( HashMap< String, CustomerModel > listOfCustomers ) {
        this.listOfCustomers = listOfCustomers;
    }

    public void setListOfSales( HashMap< String, SaleModel > listOfSales ) {
        this.listOfSales = listOfSales;
    }

    public void setWorstSalesman( SalesmanModel worstSalesman ) {
        this.worstSalesman = worstSalesman;
    }

    public void setBetterSale( SaleModel betterSale ) {
        this.betterSale = betterSale;
    }

    public Integer getNumberOfInvalidInputs() {
        return numberOfInvalidInputs;
    }

    public void setNumberOfInvalidInputs( Integer numberOfInvalidInputs ) {
        this.numberOfInvalidInputs = numberOfInvalidInputs;
    }
}
