package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
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

    @Test
    void helloWorldWithSize() {
        CompletableFuture<String> helloWorldCompletableFuture = cfhw.helloWorldWithSize();
        helloWorldCompletableFuture
                .thenAccept(s -> assertEquals("11 - HELLO WORLD", s))
                .join();
    }

    @Test
    void helloWorldMultipleAsyncCalls() {
        String helloWorldCompletableFuture = cfhw.helloWorldWithAsyncCalls();
        assertEquals("HELLO WORLD!", helloWorldCompletableFuture);
    }

    @Test
    void helloWorldMultiple3AsyncCalls() {
        String helloWorldCompletableFuture = cfhw.helloWorldWith3AsyncCalls();
        assertEquals("HELLO WORLD!HI COMPLETABLEFUTURE!", helloWorldCompletableFuture);
    }

    @Test
    void helloWorldMultiple4AsyncCalls() {
        String helloWorldCompletableFuture = cfhw.helloWorldWith4AsyncCalls();
        assertEquals("HELLO WORLD!HI COMPLETABLEFUTURE! BYE!", helloWorldCompletableFuture);
    }

    @Test
    void helloWorldThenComposeTest() {
        startTimer();
        CompletableFuture<String> s = cfhw.helloWorldThenCompose();
        s.thenAccept(text -> assertEquals("HELLO WORLD!", text)).join();
        timeTaken();
    }
}