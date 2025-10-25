package com.paoo.shr.util;

import kotlin.NotImplementedError;

public class ToDo {

    public static void TODO(String msg) {
        throw new NotImplementedError(msg);
    }

    public static void TODO() {
        throw new NotImplementedError("Not implemented yet!");
    }

}
