package com.spring.student.util;


import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.hibernate.internal.util.collections.CollectionHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Service
public class RequestUtil {

    private RestTemplate restTemplate;
    @Value("${server.servlet.context-path}")
    private String applicationContext;
    @Value("${server.port}")
    private String serverPort;

    @PostConstruct
    public void setup() {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                        .<ConnectionSocketFactory> create()
                        .register("http", new PlainConnectionSocketFactory()).build();

        BasicHttpClientConnectionManager connectionManager =
                        new BasicHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient httpClient = HttpClients.custom().disableCookieManagement()
                        .setConnectionManager(connectionManager).build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                        new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplate = new RestTemplate(requestFactory);
    }

    public <T> ResponseEntity<T> post(String path, @Nullable Object body,
                    @Nullable Map<String, Object> headers, Class<T> responseType) {
        String url = String.format("http://localhost:%s/%s/%s", serverPort,
                        applicationContext, path);
        HttpEntity httpEntity = getHttpEntity(body, headers);
        return restTemplate.exchange(url, POST, httpEntity, responseType);
    }

    public <T> ResponseEntity<T> put(String path, @Nullable Object body,
                    @Nullable Map<String, Object> headers, Class<T> responseType) {
        String url = String.format("http://localhost:%s/%s/%s", serverPort,
                        applicationContext, path);
        HttpEntity httpEntity = getHttpEntity(body, headers);
        return restTemplate.exchange(url, PUT, httpEntity, responseType);
    }

    public <T> ResponseEntity<T> get(String path, @Nullable Map<String, Object> body,
                    @Nullable Map<String, Object> headers, Class<T> responseType) {
        String url = String.format("http://localhost:%s/%s/%s", serverPort,
                        applicationContext, path);
        HttpEntity httpEntity = getHttpEntity(body, headers);
        return restTemplate.exchange(url, GET, httpEntity, responseType);
    }

    public <T> ResponseEntity<T> delete(String path, @Nullable Map<String, Object> body,
                    @Nullable Map<String, Object> headers, Class<T> responseType) {
        String url = String.format("http://localhost:%s/%s/%s", serverPort,
                        applicationContext, path);
        HttpEntity httpEntity = getHttpEntity(body, headers);
        return restTemplate.exchange(url, DELETE, httpEntity, responseType);
    }

    private HttpEntity getHttpEntity(@Nullable Object body, Map<String, Object> headers) {
        HttpHeaders httpHeaders = getHttpHeaders(headers);
        HttpEntity httpEntity;
        if (body != null) {
            httpEntity = new HttpEntity(body, httpHeaders);
        } else {
            httpEntity = new HttpEntity(httpHeaders);
        }
        return httpEntity;
    }

    private HttpHeaders getHttpHeaders(@Nullable Map<String, Object> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        if (CollectionHelper.isNotEmpty(headers)) {
            headers.forEach((s, o) -> {
                if (o instanceof List) {
                    httpHeaders.put(s, (List<String>) o);
                } else if (o instanceof String) {
                    httpHeaders.add(s, (String) o);
                } else {
                    throw new IllegalArgumentException("this object is not supported");
                }
            });
        }
        return httpHeaders;
    }

}
