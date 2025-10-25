package com.paoo.shr.core;

@FunctionalInterface
public interface RequiresInitialization {
    void init() throws Exception;
}
