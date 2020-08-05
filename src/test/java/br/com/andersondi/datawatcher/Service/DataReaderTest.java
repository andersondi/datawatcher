package br.com.andersondi.datawatcher.Service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataReaderTest {
    private static Path dataInput;

    @TempDir
    static Path tempDir;

    DataReaderTest() throws IOException {
    }

    @BeforeAll
    static void setupDirectories() throws IOException {
        dataInput = tempDir.resolve( "data.dat" );

        List< String > lines = Arrays.asList(
                "002ç1345675434544345çJose da SilvaçUrbano",
                "002ç2345675433444345çEduardo PereiraçRural",
                "002ç3345675434544345çJose da SilvaçRural",
                "002ç4345675433444345çEduardo PereiraçRural",
                "002ç5345675434544345çJose da SilvaçRural",
                "002ç6345675433444345çEduardo PereiraçUrbano",
                "002ç7345675434544",
                "345çJose da SilvaçRural",
                "002ç8345675433444345çEduardo PereiraçRural",
                "002ç9345675434544345çJose da SilvaçRural",
                "002ç0145675433444345çEduardo PereiraçRural",
                "002ç2245675434544345çJose da SilvaçRural",
                "002ç2345675433444345çEduardo PereiraçUrbano",
                "002ç2445675434544345çJose da SilvaçRural",
                "002ç2545675433444345çEduardo PereiraçRural",
                "002ç2645675434544345çJose da SilvaçRural",
                "002ç274567543344",
                "4345çEduardo PereiraçRural",
                "002ç2845675434544345çJose da SilvaçRural",
                "002ç2945675433444345çEduardo PereiraçRural",
                "002ç2045675434544345çJose da SilvaçRural",
                "002ç2315675433444345çEduardo PereiraçRural",
                "002ç2325675434544345çJose da SilvaçRural",
                "002ç2335675433444345çEduardo PereiraçRural",
                "002ç2345675434544345çJose da SilvaçRural",
                "002ç2355675433444345çEduardo PereiraçRural",
                "002ç2365675434544345çJose da SilvaçRural",
                "001ç1234567891200çPedroç50",
                "001ç3245678865401çPauloç400.99",
                "001ç1234567891202çPedroç58",
                "001ç3245678865403çPauloç400.99",
                "001ç1234567891204çAntonioç50",
                "001ç3245678865405çPauloç400.99",
                "001ç1234567891206çPedroç780",
                "001ç3245678865407çPauloç600.99",
                "001ç1234567891208çPedroç50",
                "001ç3245678865409çPauloç400.99",
                "001ç1234567891210çPedroç50",
                "001ç3245678865411çPauloç400.99",
                "001ç1234567891212çLuizç50",
                "001ç3245678865413çPauloç400.99",
                "001ç1234567891214çPedroç50",
                "001ç3245678865415çJoseç400.99",
                "001ç1234567891216çLuanç50",
                "001ç3245678865417çPauloç400.99",
                "001ç1234567891218çPedroç50",
                "001ç3245678865419çPauloç400.99",
                "001ç1234567891220çMatheusç50",
                "001ç3245678865421çPauloç400.99",
                "001ç1234567891222çPedroç50",
                "001ç3245678865423çJoaoç400.99",
                "001ç1234567891224çAndreç50",
                "003ç11ç[1-10-1.9,2-30-2.50,3-55-3.10]çPedro",
                "003ç12ç[1-34-10,2-33-2.50,5-30-9.5]çPaulo",
                "003ç13ç[1-10-8,2-30-",
                "2.50,3-55-3.10]çJoao",
                "003ç14ç[1-34-10,2-33-2.50]çAlexandre",
                "003ç15ç[1-10-12.5,2-30-1.50,3-55-3.10]çJoao",
                "003ç0234ç[1-34-10,2-33-2.50,2-33-2.50]çAlexandre",
                "003ç1234ç[1-10-7,2-30-2.50,3-55-3.10]çPedro",
                "003ç0128ç[1-34-10,2-33-2.50,3-33-10.50,4-23",
                "-10.50]çPaulo",
                "003ç1232ç[1-10-15,2-30-2.50,3-55-3.10]çJoao",
                "003ç085ç[1-34-10,2-33-2.50]çAlexandre",
                "003ç1652ç[1-10-12.5,2-30-1.50,3-55-3.10]çJoao",
                "003ç065ç[1-34-10,2-33-2.50,2-33-2.50]çAlexandre",
                "003ç1672ç[1-10-12.5,2-30-1.50,3-55-3.10]çAndre",
                "003ç065ç[1-34-10,2-33-2",
                ".50,2-33-2.50]çAndre",
                "003ç191ç[1-10-10,2-30-2.50,3-55-3.10]çPedro",
                "003ç172ç[1-10-11,2-30-2.50,3-55-3.10]çJoao",
                "003ç065ç[1-34-10,2-33-2.50]çAlexandre",
                "003ç142ç[1-10-12.5,2-30-1.50,3-55-3.10]çJoao",
                "003ç035ç[1-34-10,2-33-2.50,2-33-2.50]çAlexandre",
                "003ç131ç[1-10-8.9,2-30-2.50,3-55-3.10]çPedro",
                "003ç122ç[1-10-9.9,2-30-2.50,3-55-3.10]çJoao",
                "003ç015ç[1-34-10,2-33-2.50]çAlexandre",
                "003ç122ç[1-10-12.5,2-30-1.50,3-55-3.10]çJoao",
                "003ç085ç[1-34-10,2-33-2.50,2-33-2.50]çAlexandre",
                "003ç191ç[3-5-1]çPedro"
        );
        Files.write( dataInput, lines );
    }

    @Test
    void writeTestFile() throws IOException {
        List< String > lines = Files.readAllLines( dataInput);
        assertAll(
                () -> assertTrue( Files.exists( dataInput ) ),
                () -> assertEquals( "data.dat", dataInput.getFileName().toString() ),
                () -> assertEquals( "dat", dataInput.toString().substring( dataInput.toString().lastIndexOf( "." ) + 1 ) ),
                () -> assertLinesMatch( lines, Files.readAllLines( dataInput ) )
        );
    }
}