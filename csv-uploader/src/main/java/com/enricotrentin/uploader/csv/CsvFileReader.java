package com.enricotrentin.uploader.csv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class CsvFileReader <T> {

    private final CsvLineParser<T> parser;
    private final CsvLineHandler<T> handler;

    public void readCsvFile (String path) {
        log.info("reading file {}", path);
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();

            while (line != null) {
                processLine(line);
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + path);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while reading the file: " + e.getMessage());
        }
    }

    private void processLine (String line) {
        try {
            T parsed = parser.parse(line);
            handler.handle(parsed);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
    }

}
