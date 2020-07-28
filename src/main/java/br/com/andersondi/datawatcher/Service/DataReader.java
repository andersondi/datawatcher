package br.com.andersondi.datawatcher.Service;

import br.com.andersondi.datawatcher.GetPropertyValues;
import br.com.andersondi.datawatcher.Model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.getProperty;


public class DataReader {
    GetPropertyValues properties;
    private final String HOMEPATH = getProperty( "user.home" );
    private final String MAIN_DELIMITER;
    private final String PRIMARY_DELIMITER;
    private final String SECONDARY_DELIMITER;
    private final String TARGET_EXTETION;
    private final String INPUT_PATH;
    private final String OUTPUT_PATH;
    private final String PROCESSED_FILE_PATH;
    private final String REJECTED_FILE_PATH;
    private final Integer SALESMAN_ID;
    private final Integer CUSTOMER_ID;
    private final Integer SALES_ID;

    private List< File > files;

    public DataReader() throws IOException {
        properties = new GetPropertyValues();
        MAIN_DELIMITER = properties.getPropValues( "mainDelimiter" );
        PRIMARY_DELIMITER = properties.getPropValues( "primaryDelimiter" );
        SECONDARY_DELIMITER = properties.getPropValues( "secondaryDelimiter" );
        TARGET_EXTETION = properties.getPropValues( "targetExtension" );
        INPUT_PATH = HOMEPATH + properties.getPropValues( "inputPath" );
        OUTPUT_PATH = HOMEPATH + properties.getPropValues( "outputPath" );
        PROCESSED_FILE_PATH = HOMEPATH + properties.getPropValues( "processedFilePath" );
        REJECTED_FILE_PATH = HOMEPATH + properties.getPropValues( "rejectedFilePath" );
        SALESMAN_ID = 1;
        CUSTOMER_ID = 2;
        SALES_ID = 3;

        processFiles();
    }

    private void readFiles() {
        FileFilter filter = file -> file.getName().endsWith( TARGET_EXTETION );

        File dir = new File( INPUT_PATH );

        files = Arrays.asList( dir.listFiles( filter ) );
        System.out.println( "Foram lidos " + files.size() + " arquivos validos" );
    }

    private void processFiles() {
        readFiles();

        for ( File file : files ) {
            try (
                    BufferedReader reader = new BufferedReader( new FileReader( file ) )
            ) {
                ReportService fileReport = new ReportService();
                for ( String line = reader.readLine(); line != null; line = reader.readLine() ) {
                    String delims = "[ç]+";
                    String[] tokens = line.split( delims );
                    Integer id = Integer.parseInt( tokens[ 0 ] );
                    if ( isSalesman( id ) ) {
                        parseSalesman( tokens, fileReport );
                    } else if ( isCustomer( id ) ) {
                        parseCustomer( tokens, fileReport );
                    } else if ( isSale( id ) ) {
                        parseSale( tokens, fileReport );
                    }
                }

                fileReport.generateReport( OUTPUT_PATH, file.getName() );
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }

    private void parseSale( String[] line, ReportService fileReport ) {
        String input = line[ 2 ];
        String[] result = input.substring( 1, input.length() - 1 ).split( PRIMARY_DELIMITER );

        List< ItemModel > listOfItems = new ArrayList<>();

        for ( String str : result ) {
            String[] itemParameters = str.split( SECONDARY_DELIMITER );
            ItemModel newItem = new ItemModel(
                    itemParameters[ 0 ],
                    Integer.parseInt( itemParameters[ 1 ] ),
                    Double.parseDouble( itemParameters[ 2 ] )
            );
            listOfItems.add( newItem );
        }
        SaleModel sale = new SaleModel( line[ 1 ], line[ 3 ], listOfItems );
        fileReport.addSale( sale );
    }

    private void parseCustomer( String[] line, ReportService fileReport ) {
        String cnpj = line[ 1 ];
        String name = line[ 2 ];
        String businessArea = line[ 3 ];
        CustomerModel customer = new CustomerModel( cnpj, name, businessArea );
        fileReport.addCustomer( customer );
    }

    private void parseSalesman( String[] line, ReportService fileReport ) {
        String cpf = line[ 1 ];
        String name = line[ 2 ];
        Double salary = Double.parseDouble( line[ 3 ] );
        SalesmanModel salesman = new SalesmanModel( cpf, name, salary );
        fileReport.addSalesman( salesman );
    }

    private boolean isSale( Integer id ) {
        return id.equals( SALES_ID );
    }

    private boolean isSalesman( Integer id ) {
        return id.equals( SALESMAN_ID );
    }

    private boolean isCustomer( Integer id ) {
        return id.equals( CUSTOMER_ID );
    }

}
