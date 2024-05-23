package com.example.starbattle.ioc.scopeBase;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;


public class ScopeImpl implements Scope {

    private ConcurrentMap<String, Function<Object[], Object>> dependencies;
    private Scope parent;

    public ScopeImpl(ConcurrentMap<String, Function<Object[], Object>> dependencies, Scope parent) {
        this.dependencies = dependencies;
        this.parent = parent;
    }

    public ConcurrentMap<String, Function<Object[], Object>> getDependencies() {
        return dependencies;
    }

    public void setDependencies(ConcurrentMap<String, Function<Object[], Object>> dependencies) {
        this.dependencies = dependencies;
    }

    public Scope getParent() {
        return parent;
    }

    public void setParent(Scope parent) {
        this.parent = parent;
    }

    @Override
    public Object resolve(String key, Object[] args) {
        if (dependencies.containsKey(key)) {
            return dependencies
                    .get(key)
                    .apply(args);
        } else {
            return parent.resolve(key, args);
        }
    }
}
