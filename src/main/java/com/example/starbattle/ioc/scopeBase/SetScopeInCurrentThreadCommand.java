package com.example.starbattle.ioc.scopeBase;

import com.example.starbattle.Command;

public class SetScopeInCurrentThreadCommand implements Command {

    private final ScopeImpl scope;

    public SetScopeInCurrentThreadCommand(ScopeImpl scope) {
        this.scope = scope;
    }

    @Override
    public void execute() {
        ScopeBasedResolveDependencyStrategy.getCurrentScopes().set(scope);
    }
}
