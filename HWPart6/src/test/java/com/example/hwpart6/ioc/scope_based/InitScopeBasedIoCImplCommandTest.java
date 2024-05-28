package com.example.hwpart6.ioc.scope_based;


import com.example.hwpart6.ioc.IoC;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InitScopeBasedIoCImplCommandTest {

    @Test
    void shouldInitScopeBasedIoC(){
        // when
        new InitScopeBasedIoCImplCommand().execute();

        // then
        assertNotNull(IoC.resolve("Scopes.Current"));
    }
}