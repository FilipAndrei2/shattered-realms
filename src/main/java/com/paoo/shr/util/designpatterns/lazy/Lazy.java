package com.paoo.shr.util.designpatterns.lazy;

import java.io.FileNotFoundException;

public class Lazy<T> {
    private Supplier<T> supplier;
    private volatile T value;

    public Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
        this.value = null;
    }

    public T getValue() throws FileNotFoundException {
        if (this.value == null) {
            synchronized (this){
                if (this.value == null) {
                    value = supplier.get();
                    supplier = null;
                }
            }
        }
        return value;
    }
}
