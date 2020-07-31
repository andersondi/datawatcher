package br.com.andersondi.datawatcher.Service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.springframework.test.util.AssertionErrors.assertTrue;

class DataReaderTest {
    @TempDir
    static Path tempDir;

    /*@BeforeAll
    static void setupDirectories() throws IOException {
        File testDir = new File( String.valueOf( tempDir ) );

        Path dataInputPath = tempDir.resolve( "out" );
        Path dataInput = dataInputPath.resolve( "data.dat" );
//        Path dataInput = Paths.get("/out/data.dat");

        List< String > lines = Arrays.asList(
                "001ç1234567891234çPedroç50000",
                "001ç3245678865434çPauloç40000.99",
                "002ç2345675434544345çJose da SilvaçRural",
                "002ç2345675433444345çEduardo PereiraçRural",
                "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro",
                "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo"
        );
        Files.write( dataInput, lines );
*/
        /*assertAll(
                () -> assertTrue("File should exist", Files.exists(dataInput)),
                () -> assertLinesMatch(lines, Files.readAllLines(dataInput)));
}
*/
    @Test
    void processFiles() throws IOException {

        Path dataInput = tempDir.resolve( "data.dat" );

        List< String > lines = Arrays.asList(
                "001ç1234567891234çPedroç50000",
                "001ç3245678865434çPauloç40000.99",
                "002ç2345675434544345çJose da SilvaçRural",
                "002ç2345675433444345çEduardo PereiraçRural",
                "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro",
                "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo"
        );
        Files.write( dataInput, lines );
        List< String > readedLines = Files.readAllLines( dataInput );

        assertAll(
                () -> assertTrue("File should exist", Files.exists(dataInput)),
                () -> assertLinesMatch(lines, Files.readAllLines(dataInput)));
    }

    /*@AfterAll
    static void cleanGarbage() {
        File testDir = new File( tempDir + "/data-test" );

        for ( File f : testDir.listFiles() ) {
            f.delete();
        }
        testDir.delete();
        System.out.println(testDir);
    }*/
}