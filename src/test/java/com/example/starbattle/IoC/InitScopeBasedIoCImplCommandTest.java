package com.example.starbattle.IoC;

import com.example.starbattle.ioc.IoC;
import com.example.starbattle.ioc.scopeBase.InitScopeBasedIoCImplCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InitScopeBasedIoCImplCommandTest {

    @DisplayName("проверка работы класса InitScopeBasedIoCImplCommand")
    @Test
    void shouldInitScopeBasedIoC(){
        // when
        new InitScopeBasedIoCImplCommand().execute();

        // then
        assertNotNull(IoC.resolve("Scopes.Current"));
    }
}
