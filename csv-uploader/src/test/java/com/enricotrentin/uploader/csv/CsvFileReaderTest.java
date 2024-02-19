package com.enricotrentin.uploader.csv;

import com.enricotrentin.uploader.csv.CsvFileReader;
import com.enricotrentin.uploader.csv.CsvLineHandler;
import com.enricotrentin.uploader.csv.CsvLineParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

@ExtendWith(MockitoExtension.class)
public class CsvFileReaderTest {

    @Mock
    CsvLineParser<String> parser;
    @Mock
    CsvLineHandler<String> handler;

    CsvFileReader<String> csvFileReader;

    @BeforeEach
    void setup() {
        csvFileReader = new CsvFileReader<>(parser, handler);
    }

    @Test
    void readCsvFileTest () {
        String path = "./src/test/resources/sample.csv";
        String parsed = "value";

        Mockito.when(parser.parse(Mockito.anyString()))
                .thenReturn(parsed);

        csvFileReader.readCsvFile(path);

        Mockito.verify(parser, Mockito.times(2))
                .parse(Mockito.anyString());
        Mockito.verify(handler, Mockito.times(2))
                .handle(parsed);
    }

    @Test
    void fileNotFoundTest () {
        String path = "non-existing.csv";
        RuntimeException notFound = Assertions.assertThrows(
                RuntimeException.class,
                ()->csvFileReader.readCsvFile(path));
        Assertions.assertEquals("File not found: " + path, notFound.getMessage());
    }
}
