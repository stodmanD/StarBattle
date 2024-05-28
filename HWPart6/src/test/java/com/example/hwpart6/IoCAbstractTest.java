package com.example.hwpart6;


import com.example.hwpart6.ioc.adapter.InitAdapterGeneratorCommand;
import com.example.hwpart6.ioc.scope_based.InitScopeBasedIoCImplCommand;
import com.example.hwpart6.operations.Fuelable;
import com.example.hwpart6.operations.Movable;
import com.example.hwpart6.operations.Rotatable;
import org.junit.jupiter.api.BeforeAll;

public class IoCAbstractTest {
    @BeforeAll
    static void init() {
        new InitScopeBasedIoCImplCommand().execute();
        new InitAdapterGeneratorCommand(Movable.class).execute();
        new InitAdapterGeneratorCommand(Rotatable.class).execute();
        new InitAdapterGeneratorCommand(Fuelable.class).execute();
    }
}
