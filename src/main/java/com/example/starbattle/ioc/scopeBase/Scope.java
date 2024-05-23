package com.example.starbattle.ioc.scopeBase;

public interface Scope {
    Object resolve(String key, Object[] args);
}
