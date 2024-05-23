package com.example.starbattle.IoC;

import com.example.starbattle.Command;
import com.example.starbattle.ioc.IoC;
import com.example.starbattle.ioc.scopeBase.InitScopeBasedIoCImplCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class IoCTest {

    private static Object rootScope;

    @BeforeEach
    void setup() {
        new InitScopeBasedIoCImplCommand().execute();
        rootScope = IoC.resolve("Scopes.Current");
    }

    @Test
    void shouldIoC_CanRegisterNewDependency() {
        var mockedCommand = mock(Command.class);
        doNothing()
                .when(mockedCommand)
                .execute();

        IoC.<Command>resolve(
                        "IoC.Register",
                        "Queue.Log.Size",
                        (Function<Object[], Object>) (args) -> mockedCommand)
                .execute();

        IoC.<Command>resolve("Queue.Log.Size")
                .execute();
        await().atMost(1, SECONDS)
                .untilAsserted(() -> verify(mockedCommand).execute());
    }

    @Test
    void shouldIoC_CanCreateNewScope() {

        var newScope = IoC.resolve("Scopes.New", rootScope);
        var currentScope = IoC.resolve("Scopes.Current");
        assertAll(
                () -> assertEquals(rootScope, currentScope),
                () -> assertNotEquals(currentScope, newScope)
        );
    }

    @Test
    void shouldIoC_CanChangeCurrentScope() {

        var newScope = IoC.resolve("Scopes.New", rootScope);
        IoC.<Command>resolve("Scopes.Current.Set", newScope)
                .execute();

        var currentScope = IoC.resolve("Scopes.Current");
        assertEquals(currentScope, newScope);
    }
}
