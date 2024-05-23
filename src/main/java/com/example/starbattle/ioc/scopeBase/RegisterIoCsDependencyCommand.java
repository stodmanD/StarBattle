package com.example.starbattle.ioc.scopeBase;

import com.example.starbattle.Command;
import com.example.starbattle.Exeptions.CommandException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.function.Function;

@RequiredArgsConstructor
public class RegisterIoCsDependencyCommand implements Command {

    private final String key;
    private final Function<Object[], Object> strategy;

    @Override
    @SneakyThrows
    public void execute() {
        try {
            ScopeBasedResolveDependencyStrategy
                    .getCurrentScopes()
                    .get()
                    .getDependencies()
                    .putIfAbsent(key, strategy);
        } catch (Exception ex) {
            throw new CommandException("Dependency registration error");
        }
    }
}
