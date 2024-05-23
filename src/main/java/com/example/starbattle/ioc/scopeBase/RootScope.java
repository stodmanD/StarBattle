package com.example.starbattle.ioc.scopeBase;

import lombok.RequiredArgsConstructor;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class RootScope implements Scope {

    private final BiFunction<String, Object[], Object> strategy;

    @Override
    public Object resolve(String key, Object[] args) {
        return strategy.apply(key, args);
    }
}
