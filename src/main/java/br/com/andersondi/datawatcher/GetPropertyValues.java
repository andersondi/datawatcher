package br.com.andersondi.datawatcher;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

public class GetPropertyValues {
    String value = "";
    InputStream inputStream;

    public String getPropValues( String propertyKey ) throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream( propFileName );
//            FileInputStream input = new FileInputStream( new File( "dataWatcher/src/main/resources/config.properties" ) );


            if ( inputStream != null ) {
                prop.load( inputStream );
//                prop.load( new InputStreamReader( input, Charset.forName( "UTF-8" ) ) );

            } else {
                throw new FileNotFoundException( "property file '" + propFileName + "' not found in the classpath" );
            }
            value = prop.getProperty( propertyKey );

        } catch ( Exception e ) {
            System.out.println( "Exception: " + e );
        } finally {
            inputStream.close();
        }
        return value;
    }
}

