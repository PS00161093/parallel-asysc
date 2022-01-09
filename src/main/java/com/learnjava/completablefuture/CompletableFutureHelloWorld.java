package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;

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

    public String helloWorldWithAsyncCalls() {
        startTimer();

        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(hws::hello);
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(hws::world);
        String helloWord = helloFuture
                .thenCombine(worldFuture, String::concat)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();

        return helloWord;
    }

    public String helloWorldWith3AsyncCalls() {
        startTimer();

        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(hws::hello);
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(hws::world);
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return "Hi CompletableFuture!";
        });
        String helloWord = helloFuture
                .thenCombine(worldFuture, String::concat)
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();

        return helloWord;
    }

    public String helloWorldWith4AsyncCalls() {
        startTimer();

        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(hws::hello);
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(hws::world);
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return "Hi CompletableFuture!";
        });
        CompletableFuture<String> bye = CompletableFuture.supplyAsync(() -> " Bye!");
        String helloWord = helloFuture
                .thenCombine(worldFuture, String::concat)
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                .thenCombine(bye, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();

        return helloWord;
    }

    public CompletableFuture<String> helloWorldThenCompose() {
        return CompletableFuture
                .supplyAsync(hws::hello)
                .thenCompose((previous) -> hws.worldFuture(previous))
                .thenApply(String::toUpperCase);
    }
}
