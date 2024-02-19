package com.enricotrentin.uploader.csv;

public interface CsvLineParser<T> {

    T parse (String line);

}
