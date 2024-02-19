package com.enricotrentin.uploader.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;

@ExtendWith(MockitoExtension.class)
public class CustomerUploaderTest {

    @Spy
    CustomerUploader customerUploader;
    @Mock
    HttpClient httpClient;

    @Test
    void testHandle () throws IOException, InterruptedException {
        String parsed = "{\"key\":\"value\"}";
        Mockito.when(customerUploader.getHttpClient())
                .thenReturn(httpClient);

        customerUploader.handle(parsed);

        Mockito.verify(httpClient, Mockito.times(1))
                .send(Mockito.any(), Mockito.any());
    }

    @Test
    void testHandleIOException () throws IOException, InterruptedException {
        String parsed = "{\"key\":\"value\"}";
        Mockito.when(customerUploader.getHttpClient())
                .thenReturn(httpClient);
        Mockito.when(httpClient.send(Mockito.any(), Mockito.any()))
                .thenThrow(new IOException());

        RuntimeException thrown = Assertions.assertThrows(
                RuntimeException.class,
                ()->customerUploader.handle(parsed));

        Assertions.assertTrue(thrown.getMessage().startsWith(
                "Could not send customer to Address Book API:"));
    }

    @Test
    void testHandleInterruptedExecption () throws IOException, InterruptedException {
        String parsed = "{\"key\":\"value\"}";
        Mockito.when(customerUploader.getHttpClient())
                .thenReturn(httpClient);
        Mockito.when(httpClient.send(Mockito.any(), Mockito.any()))
                .thenThrow(new InterruptedException());

        RuntimeException thrown = Assertions.assertThrows(
                RuntimeException.class,
                ()->customerUploader.handle(parsed));

        Assertions.assertTrue(thrown.getMessage().startsWith(
                "Request to Address Book API was interrupted:"));
    }

}
