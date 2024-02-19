package com.enricotrentin.uploader;

import com.enricotrentin.uploader.csv.CsvFileReader;
import com.enricotrentin.uploader.customer.CustomerParser;
import com.enricotrentin.uploader.customer.CustomerUploader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvUploaderApplication {

    public static void main (String[] args) {
        if (args.length < 1) {
            log.error("Missing required argument: csv file path");
            return;
        }
        processInput(args[0]);
    }

    private static void processInput (String path) {
        CustomerParser customerParser = new CustomerParser();
        CustomerUploader customerUploader = new CustomerUploader();
        CsvFileReader <String> fileReader = new CsvFileReader<>(customerParser, customerUploader);

        try {
            fileReader.readCsvFile(path);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
    }

}
