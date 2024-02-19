package com.enricotrentin.uploader.customer;

import com.enricotrentin.uploader.csv.CsvLineHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class CustomerUploader implements CsvLineHandler<String> {

    private final static URI ADDRESS_BOOK_ENDPOINT = URI.create("http://localhost:8080/customer");

    @Override
    public void handle(String parsed) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(ADDRESS_BOOK_ENDPOINT)
                .POST(HttpRequest.BodyPublishers.ofString(parsed))
                .header("Content-Type","application/json")
                .build();

        try {
            getHttpClient().send(request, HttpResponse.BodyHandlers.discarding());
        } catch (IOException e) {
            throw new RuntimeException("Could not send customer to Address Book API: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException("Request to Address Book API was interrupted: " + e.getMessage());
        }
    }

    protected HttpClient getHttpClient() {
        return HttpClient.newHttpClient();
    }
}
