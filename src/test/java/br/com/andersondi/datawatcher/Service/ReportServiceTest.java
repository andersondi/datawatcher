package br.com.andersondi.datawatcher.Service;

import br.com.andersondi.datawatcher.Model.CustomerModel;
import br.com.andersondi.datawatcher.Model.ItemModel;
import br.com.andersondi.datawatcher.Model.SaleModel;
import br.com.andersondi.datawatcher.Model.SalesmanModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportServiceTest {
    @TempDir
    static Path tempDir;

    @BeforeAll
    static void setupDirectories() {
        File testDir = new File( tempDir + "/data-test/out" );
        System.out.println( "BeforeAll " + testDir );

        testDir.mkdirs();
    }

    @Test
    void searchForASaleUsingAnExistentId() {
        ReportService fileReport = new ReportService();

        List< ItemModel > list = new ArrayList<>() {
            {
                add( new ItemModel( "1", 10, 100.0 ) );
                add( new ItemModel( "2", 30, 2.5 ) );
                add( new ItemModel( "3", 40, 3.10 ) );
            }
        };
        SaleModel sale = new SaleModel( "10", "Pedro", list );
        fileReport.addSale( sale );
        assertEquals( sale, fileReport.searchSale( "10" ) );
    }

    @Test
    void searchForASaleUsingAnNonExistentId() {
        ReportService fileReport = new ReportService();

        List< ItemModel > list = new ArrayList<>() {
            {
                add( new ItemModel( "1", 10, 100.0 ) );
                add( new ItemModel( "2", 30, 2.5 ) );
                add( new ItemModel( "3", 40, 3.10 ) );
            }
        };
        SaleModel sale = new SaleModel( "10", "Pedro", list );
        fileReport.addSale( sale );
        assertNotEquals( sale, fileReport.searchSale( "11" ) );
    }

    @Test
    void searchForASalesmanUsingAnExistentCpf() {
        ReportService fileReport = new ReportService();
        SalesmanModel salesman = new SalesmanModel( "12345678900", "Pedro", 50000.0 );
        fileReport.addSalesman( salesman );
        assertEquals( salesman, fileReport.searchSalesman( "12345678900" ) );
    }

    @Test
    void searchForASalesmanUsingAnNonExistentCpf() {
        ReportService fileReport = new ReportService();
        SalesmanModel salesman = new SalesmanModel( "12345678900", "Pedro", 50000.0 );
        fileReport.addSalesman( salesman );
        assertNotEquals( salesman, fileReport.searchSalesman( "12345678901" ) );
    }

    @Test
    void searchForACustomerUsingAnExistentCnpj() {
        ReportService fileReport = new ReportService();
        CustomerModel customer = new CustomerModel( "2345675434544345", "Jose", "Rural" );
        fileReport.addCustomer( customer );
        assertEquals( customer, fileReport.searchCustomer( "2345675434544345" ) );
    }

    @Test
    void searchForACustomerUsingAnNonExistentCnpj() {
        ReportService fileReport = new ReportService();
        CustomerModel customer = new CustomerModel( "2345675434544345", "Jose", "Rural" );
        fileReport.addCustomer( customer );
        assertNotEquals( customer, fileReport.searchCustomer( "2345675434544340" ) );
    }

    @Test
    void ifNumberOfDuplicateSalesmanIs1() {
        ReportService fileReport = new ReportService();
        SalesmanModel salesman = new SalesmanModel( "12345678900", "Pedro", 50000.0 );
        fileReport.addSalesman( salesman );
        fileReport.addSalesman( salesman );
        assertEquals( 1, fileReport.reportModel.getNumberOfDuplicateSalesman() );
    }

    @Test
    void ifNumberOfDuplicateSalesmanIs2() {
        ReportService fileReport = new ReportService();
        SalesmanModel salesman1 = new SalesmanModel( "12345678900", "Pedro", 50000.0 );
        SalesmanModel salesman2 = new SalesmanModel( "12345678901", "Ana", 55000.0 );
        fileReport.addSalesman( salesman1 );
        fileReport.addSalesman( salesman1 );
        fileReport.addSalesman( salesman2 );
        fileReport.addSalesman( salesman2 );
        assertEquals( 2, fileReport.reportModel.getNumberOfDuplicateSalesman() );
    }

    @Test
    void ifNumberOfDuplicateCustomerIs1() {
        ReportService fileReport = new ReportService();
        CustomerModel customer = new CustomerModel( "2345675434544345", "Jose", "Rural" );
        fileReport.addCustomer( customer );
        fileReport.addCustomer( customer );
        assertEquals( 1, fileReport.reportModel.getNumberOfDuplicateCustomers() );
    }

    @Test
    void ifNumberOfDuplicateCustomerIs2() {
        ReportService fileReport = new ReportService();
        CustomerModel customer1 = new CustomerModel( "2345675434544345", "Jose", "Rural" );
        CustomerModel customer2 = new CustomerModel( "2345675434544346", "Maria", "Comercial" );
        fileReport.addCustomer( customer1 );
        fileReport.addCustomer( customer1 );
        fileReport.addCustomer( customer2 );
        fileReport.addCustomer( customer2 );
        assertEquals( 2, fileReport.reportModel.getNumberOfDuplicateCustomers() );
    }

    @Test
    void ifNumberOfDuplicateSalesIs1() {
        ReportService fileReport = new ReportService();

        List< ItemModel > list = new ArrayList<>() {
            {
                add( new ItemModel( "1", 10, 100.0 ) );
                add( new ItemModel( "2", 30, 2.5 ) );
                add( new ItemModel( "3", 40, 3.10 ) );
            }
        };
        SaleModel sale = new SaleModel( "10", "Pedro", list );
        fileReport.addSale( sale );
        fileReport.addSale( sale );
        assertEquals( 1, fileReport.reportModel.getNumberOfDuplicateSales() );
    }

    @Test
    void ifNumberOfDuplicateSalesIs2() {
        ReportService fileReport = new ReportService();

        List< ItemModel > list = new ArrayList<>() {
            {
                add( new ItemModel( "1", 10, 100.0 ) );
                add( new ItemModel( "2", 30, 2.5 ) );
                add( new ItemModel( "3", 40, 3.10 ) );
            }
        };
        SaleModel sale1 = new SaleModel( "10", "Pedro", list );
        SaleModel sale2 = new SaleModel( "11", "Ana", list );
        fileReport.addSale( sale1 );
        fileReport.addSale( sale1 );
        fileReport.addSale( sale2 );
        fileReport.addSale( sale2 );
        assertEquals( 2, fileReport.reportModel.getNumberOfDuplicateSales() );
    }

    @Test
    void findBetterSale() {
        ReportService fileReport = new ReportService();

        List< ItemModel > list1 = new ArrayList<>() {
            {
                add( new ItemModel( "1", 10, 100.0 ) );
                add( new ItemModel( "2", 30, 2.5 ) );
                add( new ItemModel( "3", 40, 3.10 ) );
            }
        };

        List< ItemModel > list2 = new ArrayList<>() {
            {
                add( new ItemModel( "1", 10, 100.0 ) );
                add( new ItemModel( "2", 30, 50.0 ) );
                add( new ItemModel( "3", 40, 10.0 ) );
            }
        };
        SaleModel sale1 = new SaleModel( "1", "Pedro", list1 );
        SaleModel sale2 = new SaleModel( "2", "Ana", list2 );
        fileReport.addSale( sale1 );
        fileReport.addSale( sale2 );
        assertEquals( sale2, fileReport.findBetterSale( fileReport.reportModel.getListOfSales() ) );
    }

    @Test
    void findWorstSalesman() {
        ReportService fileReport = new ReportService();
        SalesmanModel salesman1 = new SalesmanModel( "12345678900", "Pedro", 50000.0 );
        SalesmanModel salesman2 = new SalesmanModel( "12345678901", "Ana", 55000.0 );
        fileReport.addSalesman( salesman1 );
        fileReport.addSalesman( salesman2 );

        List< ItemModel > list1 = new ArrayList<>() {
            {
                add( new ItemModel( "1", 10, 100.0 ) );
                add( new ItemModel( "2", 30, 2.5 ) );
                add( new ItemModel( "3", 40, 3.10 ) );
            }
        };

        List< ItemModel > list2 = new ArrayList<>() {
            {
                add( new ItemModel( "1", 10, 100.0 ) );
                add( new ItemModel( "2", 30, 50.0 ) );
                add( new ItemModel( "3", 40, 10.0 ) );
            }
        };
        SaleModel sale1 = new SaleModel( "1", "Pedro", list1 );
        SaleModel sale2 = new SaleModel( "3", "Ana", list2 );
        fileReport.addSale( sale1 );
        fileReport.addSale( sale2 );
        assertEquals( salesman1, fileReport.findWorstSalesman( fileReport.reportModel.getListOfSalespeople() ) );
    }

    @Test
    void addingSalesman() {
        ReportService fileReport = new ReportService();

        SalesmanModel salesman1 = new SalesmanModel( "12345678900", "Pedro", 50000.0 );
        SalesmanModel salesman2 = new SalesmanModel( "12345678901", "Ana", 55000.0 );
        fileReport.addSalesman( salesman1 );
        fileReport.addSalesman( salesman2 );

        assertAll( "adding",
                () -> assertEquals( salesman1, fileReport.searchSalesman( "12345678900" ) ),
                () -> assertEquals( salesman2, fileReport.searchSalesman( "12345678901" ) ),
                () -> assertEquals( 0, fileReport.reportModel.getNumberOfDuplicateSalesman() )
        );
    }

    @Test
    void addCustomer() {
        ReportService fileReport = new ReportService();

        CustomerModel customer1 = new CustomerModel( "2345675434544345", "Jose", "Rural" );
        CustomerModel customer2 = new CustomerModel( "2345675434544346", "Maria", "Comercial" );
        fileReport.addCustomer( customer1 );
        fileReport.addCustomer( customer2 );

        assertAll( "adding",
                () -> assertEquals( customer1, fileReport.searchCustomer( "2345675434544345" ) ),
                () -> assertEquals( customer2, fileReport.searchCustomer( "2345675434544346" ) ),
                () -> assertEquals( 0, fileReport.reportModel.getNumberOfDuplicateCustomers() )
        );
    }

    @Test
    void addSale() {
        ReportService fileReport = new ReportService();

        List< ItemModel > list1 = new ArrayList<>() {
            {
                add( new ItemModel( "1", 10, 100.0 ) );
                add( new ItemModel( "2", 30, 2.5 ) );
                add( new ItemModel( "3", 40, 3.10 ) );
            }
        };

        List< ItemModel > list2 = new ArrayList<>() {
            {
                add( new ItemModel( "1", 10, 100.0 ) );
                add( new ItemModel( "2", 30, 50.0 ) );
                add( new ItemModel( "3", 40, 10.0 ) );
            }
        };
        SaleModel sale1 = new SaleModel( "1", "Pedro", list1 );
        SaleModel sale2 = new SaleModel( "2", "Ana", list2 );
        fileReport.addSale( sale1 );
        fileReport.addSale( sale2 );

        assertAll( "adding",
                () -> assertEquals( sale1, fileReport.searchSale( "1" ) ),
                () -> assertEquals( sale2, fileReport.searchSale( "2" ) ),
                () -> assertEquals( 0, fileReport.reportModel.getNumberOfDuplicateSales() )
        );
    }

    @Test
    void generateReport() {
    }

    @AfterAll
    static void cleanGarbage() {
        File testDir = new File( tempDir + "/data-test" );

        for ( File f : testDir.listFiles() ) {
            f.delete();
        }
        testDir.delete();
    }
}