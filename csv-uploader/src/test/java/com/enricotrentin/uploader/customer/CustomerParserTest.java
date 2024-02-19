package com.enricotrentin.uploader.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerParserTest {

    private final CustomerParser customerParser = new CustomerParser();

    @Test
    void parseTest () {
        String input = getTestCsv();
        String expected = getTestJson();

        String parsed = customerParser.parse(input);

        Assertions.assertEquals(expected, parsed);
    }

    private String getTestCsv () {
        return "sholmes,Sherlock Holmes,221b Baker street,Marylebone," +
                "London,Greater London,United Kingdom,NW1 6XE";
    }

    private String getTestJson () {
        return "{\"reference\":\"sholmes\",\"name\":\"Sherlock Holmes\"," +
                "\"addressLine1\":\"221b Baker street\",\"addressLine2\":\"Marylebone\"," +
                "\"town\":\"London\",\"county\":\"Greater London\"," +
                "\"country\":\"United Kingdom\",\"postcode\":\"NW1 6XE\"}";
    }

}
