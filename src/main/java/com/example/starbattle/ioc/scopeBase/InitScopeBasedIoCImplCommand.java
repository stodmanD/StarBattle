package com.example.starbattle.ioc.scopeBase;

import com.example.starbattle.Command;
import com.example.starbattle.ioc.IoC;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class InitScopeBasedIoCImplCommand implements Command {
    @Override
    public void execute() {
        if (Objects.nonNull(ScopeBasedResolveDependencyStrategy.getRoot())) {return;}

        var dependencies = new ConcurrentHashMap<String, Function<Object[], Object>>();

        var scope = new ScopeImpl(
                dependencies,
                new RootScope(IoC.resolve("IoC.Default"))
        );

        dependencies.putIfAbsent(
                "Scopes.Storage",
                args -> new ConcurrentHashMap<String, Function<Object[], Object>>());

        dependencies.putIfAbsent(
                "Scopes.New",
                args -> new ScopeImpl(
                        IoC.resolve("Scopes.Storage"),
                        (ScopeImpl) args[0]
                ));

        dependencies.putIfAbsent(
                "Scopes.Current",
                args -> {
                    var currentScope = ScopeBasedResolveDependencyStrategy.getCurrentScopes().get();
                    if (Objects.nonNull(currentScope)) {
                        return currentScope;
                    } else {
                        return ScopeBasedResolveDependencyStrategy.getDefaultScope();
                    }
                });

        dependencies.putIfAbsent(
                "Scopes.Current.Set",
                args -> new SetScopeInCurrentThreadCommand((ScopeImpl) args[0]));

        dependencies.putIfAbsent(
                "IoC.Register",
                args -> new RegisterIoCsDependencyCommand((String) args[0], (Function<Object[], Object>) args[1]));

        ScopeBasedResolveDependencyStrategy.setRoot(scope);

        IoC.<Command>resolve(
                "IoC.SetupStrategy",
                (BiFunction<String, Object[], Object>) ScopeBasedResolveDependencyStrategy::resolve
        ).execute();

        new SetScopeInCurrentThreadCommand(scope).execute();
    }
}
