package com.enricotrentin.uploader.csv;

public interface CsvLineHandler<T> {

    void handle (T parsed);

}
