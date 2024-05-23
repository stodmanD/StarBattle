package com.example.starbattle.IoC;

import com.example.starbattle.Command;
import com.example.starbattle.ioc.IoC;
import com.example.starbattle.ioc.scopeBase.InitScopeBasedIoCImplCommand;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("реализация многопоточных тестов")
public class IoCMultiThreadTest {

    private static Object rootScope;

    @BeforeAll
    static void init() {
        new InitScopeBasedIoCImplCommand().execute();
        rootScope = IoC.resolve("Scopes.Current");
    }

    @BeforeEach
    void setup() {
        IoC.<Command>resolve(
                "Scopes.Current.Set",
                (Object) IoC.resolve("Scopes.New", rootScope)
        ).execute();
    }

    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void parallelTest1() {
        // given
        var mockedCommand = mock(Command.class);
        doNothing()
                .when(mockedCommand)
                .execute();
        IoC.<Command>resolve(
                        "IoC.Register",
                        "parallelTest1",
                        (Function<Object[], Object>) (args) -> mockedCommand)
                .execute();

        IoC.<Command>resolve("parallelTest1")
                .execute();

        await().atMost(1, SECONDS)
                .untilAsserted(() -> verify(mockedCommand).execute());
        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> IoC.<Command>resolve("parallelTest2")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> IoC.<Command>resolve("parallelTest3"))
        );
    }

    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void parallelTest2() {

        var mockedCommand = mock(Command.class);
        doNothing()
                .when(mockedCommand)
                .execute();
        IoC.<Command>resolve(
                        "IoC.Register",
                        "parallelTest2",
                        (Function<Object[], Object>) (args) -> mockedCommand)
                .execute();


        IoC.<Command>resolve("parallelTest2")
                .execute();

        await().atMost(1, SECONDS)
                .untilAsserted(() -> verify(mockedCommand).execute());
        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> IoC.<Command>resolve("parallelTest1")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> IoC.<Command>resolve("parallelTest3"))
        );
    }

    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void parallelTest3() {
        var mockedCommand = mock(Command.class);
        doNothing()
                .when(mockedCommand)
                .execute();
        IoC.<Command>resolve(
                        "IoC.Register",
                        "parallelTest3",
                        (Function<Object[], Object>) (args) -> mockedCommand)
                .execute();

        IoC.<Command>resolve("parallelTest3")
                .execute();

        await().atMost(1, SECONDS)
                .untilAsserted(() -> verify(mockedCommand).execute());
        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> IoC.<Command>resolve("parallelTest1")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> IoC.<Command>resolve("parallelTest2"))
        );
    }

}
