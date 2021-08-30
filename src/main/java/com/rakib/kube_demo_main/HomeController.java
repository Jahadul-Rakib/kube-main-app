package com.rakib.kube_demo_main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api/main")
public class HomeController {

    @Autowired
    ConfigConstant config;

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getData() throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println(config.getBaseURL());
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(config.getBaseURL() + "/api/child"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        System.out.println(URI.create(config.getBaseURL() + "/api/child"));
        CompletableFuture<HttpResponse<String>> response = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
        return ResponseEntity.ok().body(result);
    }

}
