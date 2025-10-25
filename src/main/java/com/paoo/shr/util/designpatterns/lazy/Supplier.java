package com.paoo.shr.util.designpatterns.lazy;

import java.io.FileNotFoundException;

@FunctionalInterface
public interface Supplier<T> {
    T get() throws FileNotFoundException;
}
