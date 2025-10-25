package com.paoo.shr.engine.core;

@FunctionalInterface
public interface RequiresInitialization {
    void init() throws Exception;
}
