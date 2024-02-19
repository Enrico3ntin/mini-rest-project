package com.enricotrentin.uploader.customer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.enricotrentin.uploader.csv.CsvLineParser;

import java.io.IOException;

public class CustomerParser implements CsvLineParser<String> {

    private final static CsvSchema csvSchema = CsvSchema.builder()
            .addColumn("reference")
            .addColumn("name")
            .addColumn("addressLine1")
            .addColumn("addressLine2")
            .addColumn("town")
            .addColumn("county")
            .addColumn("country")
            .addColumn("postcode")
            .build();

    @Override
    public String parse(String line) {
        ObjectReader inputReader = new CsvMapper().reader(csvSchema);
        try {
            return inputReader
                    .readValue(line, JsonNode.class)
                    .toString();

        } catch (IOException e) {
            throw new RuntimeException("Could not parse csv line: " + line);
        }
    }
}
