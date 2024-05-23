package com.example.starbattle.ioc.scopeBase;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.function.Supplier;
@RequiredArgsConstructor

public class ScopeBasedResolveDependencyStrategy {
    private static ScopeImpl root;
    private static final Supplier<Object> defaultScope = () -> root;
    private static ThreadLocal<ScopeImpl> currentScopes = new ThreadLocal<>();

    public static ScopeImpl getRoot() {
        return root;
    }

    public static void setRoot(ScopeImpl root) {
        ScopeBasedResolveDependencyStrategy.root = root;
    }

    public static Supplier<Object> getDefaultScope() {
        return defaultScope;
    }

    public static ThreadLocal<ScopeImpl> getCurrentScopes() {
        return currentScopes;
    }

    private static void setCurrentScopes(ThreadLocal<ScopeImpl> currentScopes) {
        ScopeBasedResolveDependencyStrategy.currentScopes = currentScopes;
    }

    public static Object resolve(String key, Object[] args) {
        if (key.equals("Scopes.Root")) {
            return root;
        } else {
            var scope = currentScopes.get();

            if (Objects.isNull(scope)) {
                scope = (ScopeImpl) defaultScope.get();
            }

            return scope.resolve(key, args);
        }
    }
}
