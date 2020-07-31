package br.com.andersondi.datawatcher.Service;

import br.com.andersondi.datawatcher.Model.CustomerModel;
import br.com.andersondi.datawatcher.Model.ReportModel;
import br.com.andersondi.datawatcher.Model.SaleModel;
import br.com.andersondi.datawatcher.Model.SalesmanModel;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReportService implements IReportService {
    ReportModel reportModel = new ReportModel();

    @Override
    public SaleModel searchSale( String id ) {
        return reportModel.getListOfSales().get( id );
    }

    @Override
    public SalesmanModel searchSalesman( String cpf ) {
        return reportModel.getListOfSalespeople().get( cpf );
    }

    @Override
    public CustomerModel searchCustomer( String cnpj ) {
        return reportModel.getListOfCustomers().get( cnpj );
    }

    @Override
    public void incrementNumberOfDuplicateSalesman() {
        reportModel.setNumberOfDuplicateSalesman( reportModel.getNumberOfDuplicateSalesman() + 1 );
    }

    @Override
    public void incrementNumberOfDuplicateCustomer() {
        reportModel.setNumberOfDuplicateCustomers( reportModel.getNumberOfDuplicateCustomers() + 1 );
    }

    @Override
    public void incrementNumberOfDuplicateSales() {
        reportModel.setNumberOfDuplicateSales( reportModel.getNumberOfDuplicateSales() + 1 );
    }

    @Override
    public SaleModel findBetterSale( HashMap< String, SaleModel > saleList ) {

        SaleModel mostExpensiveSale = saleList
                .values()
                .stream()
                .reduce( ( a, b ) -> a.getValue() > b.getValue() ? a : b )
                .orElse( null );

        return mostExpensiveSale;
    }


    @Override
    public SalesmanModel findWorstSalesman( HashMap< String, SalesmanModel > salesmanList ) {

        SalesmanModel lesserAmountSold = salesmanList
                .values()
                .stream()
                .reduce( ( a, b ) -> a.getAmountSold() < b.getAmountSold() ? a : b )
                .orElse( null );

        return lesserAmountSold;
    }

    @Override
    public void addSalesman( SalesmanModel salesman ) {
        if ( searchSalesman( salesman.getCpf() ) == null ) {
            reportModel.getListOfSalespeople().put( salesman.getCpf(), salesman );
        } else {
            incrementNumberOfDuplicateSalesman();
        }
    }

    @Override
    public void addCustomer( CustomerModel customer ) {
        if ( searchCustomer( customer.getCnpj() ) == null ) {
            reportModel.getListOfCustomers().put( customer.getCnpj(), customer );
        } else {
            incrementNumberOfDuplicateCustomer();
        }
    }

    @Override
    public void addSale( SaleModel sale ) {
        if ( searchSale( sale.getId() ) == null ) {
            reportModel.getListOfSales().put( sale.getId(), sale );

            String name = sale.getSalesmanName().toLowerCase();
            Collection< SalesmanModel > list = reportModel.getListOfSalespeople().values();
            for ( SalesmanModel s : list ) {
                if ( s.getName().toLowerCase().equals( name ) ) {
                    s.addBilledAmount( sale.getValue() );
                }
            }
        } else {
            incrementNumberOfDuplicateSales();
        }
    }

    @Override
    public void generateReport( String outputPath, String fileName ) {
        SaleModel betterSale = findBetterSale( reportModel.getListOfSales() );
        reportModel.setBetterSale( betterSale );

        SalesmanModel worstSalesman = findWorstSalesman( reportModel.getListOfSalespeople() );
        reportModel.setWorstSalesman( worstSalesman );

        PrintWriter writer = null;

        try {
            String fullPath = outputPath + "/" + fileName.replace( ".dat", ".done.dat" );
            writer = new PrintWriter( fullPath );
            String time = new SimpleDateFormat( "dd MMM yyyy HH:mm:ss" ).format( Calendar.getInstance().getTime() );
            writer.println( MessageFormat.format( "Dados referentes ao arquivo {0}", fileName ) );
            writer.println( MessageFormat.format( "{0}", time ) );
            writer.println( "==============================" );
            writer.println( MessageFormat.format( "Numero de clientes: {0}", reportModel.getListOfCustomers().size() ) );
            writer.println( MessageFormat.format( "Numero de vendedores: {0}", reportModel.getListOfSalespeople().size() ) );
            writer.println( MessageFormat.format( "ID da transacao de maior valor: {0}", reportModel.getBetterSale().getId() ) );
            writer.println( MessageFormat.format( "Vendedor com pior desempenho: {0}", StringUtils.capitalize( reportModel.getWorstSalesman().getName() ) ) );
            writer.println( "\n" );
            writer.println( MessageFormat.format( "Desconsideradas {0} entradas de clientes repetidos", reportModel.getNumberOfDuplicateCustomers() ) );
            writer.println( MessageFormat.format( "Desconsideradas {0} entradas de vendedores repetidos", reportModel.getNumberOfDuplicateSalesman() ) );
            writer.println( "==============================" );

        } catch (
                FileNotFoundException e ) {
            e.printStackTrace();
        } finally {
            if ( writer != null ) {
                writer.close();
            }
        }
    }
}
