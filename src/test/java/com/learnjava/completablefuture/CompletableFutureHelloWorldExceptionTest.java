package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompletableFutureHelloWorldExceptionTest {

    @Mock
    HelloWorldService hws = mock(HelloWorldService.class);

    @InjectMocks
    CompletableFutureHelloWorldException hwcfe;

    @Test
    void helloWorldWith3AsyncCallsWithHandle() {

        when(hws.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(hws.world()).thenCallRealMethod();

        String result = hwcfe.helloWorldWith3AsyncCallsWithHandle();

        assertEquals(" WORLD!HI COMPLETABLEFUTURE!", result);
    }
}