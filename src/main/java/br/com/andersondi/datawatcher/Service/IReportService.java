package br.com.andersondi.datawatcher.Service;

import br.com.andersondi.datawatcher.Model.CustomerModel;
import br.com.andersondi.datawatcher.Model.SaleModel;
import br.com.andersondi.datawatcher.Model.SalesmanModel;

import java.util.HashMap;
import java.util.Map;

public interface IReportService {
    SaleModel searchSale( String id );

    SalesmanModel searchSalesman( String cpf );

    CustomerModel searchCustomer( String cnpj );

    void addSalesman( SalesmanModel salesman );

    void addCustomer( CustomerModel customer );

    void addSale( SaleModel sale );

    void incrementNumberOfDuplicateSalesman();

    void incrementNumberOfDuplicateCustomer();

    void incrementNumberOfDuplicateSales();

    SaleModel findBetterSale( HashMap<String, SaleModel> saleList );

    SalesmanModel findWorstSalesman( HashMap<String, SalesmanModel> salesmanList );

    void generateReport( String outputPath, String fileName );
}
