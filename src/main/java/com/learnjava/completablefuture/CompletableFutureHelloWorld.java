package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureHelloWorld {

    private final HelloWorldService hws;

    public CompletableFutureHelloWorld(HelloWorldService hws) {
        this.hws = hws;
    }

    public static void main(String[] args) {

    }

    public CompletableFuture<String> helloWorld() {
        return CompletableFuture
                .supplyAsync(hws::helloWorld)
                .thenApply(String::toUpperCase);
    }

    public CompletableFuture<String> helloWorldWithSize() {
        return CompletableFuture
                .supplyAsync(hws::helloWorld)
                .thenApply(String::toUpperCase)
                .thenApply(s -> s.length() + " - " + s);
    }
}
