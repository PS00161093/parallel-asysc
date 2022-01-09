package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureHelloWorldTest {

    HelloWorldService hws = new HelloWorldService();
    CompletableFutureHelloWorld cfhw = new CompletableFutureHelloWorld(hws);

    @Test
    void helloWorld() {
        CompletableFuture<String> helloWorldCompletableFuture = cfhw.helloWorld();
        helloWorldCompletableFuture
                .thenAccept(s -> assertEquals("HELLO WORLD", s))
        .join();
    }
}