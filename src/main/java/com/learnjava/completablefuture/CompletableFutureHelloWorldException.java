package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import com.learnjava.util.CommonUtil;
import com.learnjava.util.LoggerUtil;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.*;

public class CompletableFutureHelloWorldException {

    private final HelloWorldService hws;

    public CompletableFutureHelloWorldException(HelloWorldService hws) {
        this.hws = hws;
    }

    public String helloWorldWith3AsyncCallsWithHandle() {
        startTimer();

        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(hws::hello);
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(hws::world);
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return "Hi CompletableFuture!";
        });
        String helloWord = helloFuture
                .handle((res, ex) -> {
                    if (Objects.nonNull(ex)) {
                        log("Exception is: " + ex.getMessage());
                        return "";
                    }
                    return res;
                })
                .thenCombine(worldFuture, String::concat)
                .handle((res, ex) -> {
                    if (Objects.nonNull(ex)) {
                        log("Exception is: " + ex.getMessage());
                        return "";
                    }
                    return res;
                })
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        stopWatchReset();

        return helloWord;
    }

    public String helloWorldWith3AsyncCallsWithExceptionally() {
        startTimer();

        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(hws::hello);
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(hws::world);
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return "Hi CompletableFuture!";
        });
        String helloWord = helloFuture
                .exceptionally(ex -> {
                    log("Exception is: " + ex.getMessage());
                    return "";
                })
                .thenCombine(worldFuture, String::concat)
                .exceptionally(ex -> {
                    log("Exception is: " + ex.getMessage());
                    return "";
                })
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        stopWatchReset();

        return helloWord;
    }
}
