package com.paoo.shr.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector2Tests {

    @Test
    void additionBetweenTwoNullShouldRaiseException(){
        assertThrows(IllegalArgumentException.class, ()->{
            Vector2 v1 = null;
            Vector2 v2 = null;
            Vector2.Add(v1, v2);
        });
    }

    @Test
    void subtractionBetweenTwoNullShouldRaiseException(){
        assertThrows(IllegalArgumentException.class, ()->{
            Vector2 v1 = null;
            Vector2 v2 = null;
            Vector2.Sub(v1, v2);
        });
    }

    @Test
    void multiplicationOfNullShouldRaiseException(){
        assertThrows(IllegalArgumentException.class, ()->{
            Vector2 v1 = null;
            Vector2.Mul(v1, 3.14f);
        });
    }

    @Test
    void divisionOfNullShouldRaiseException(){
        assertThrows(IllegalArgumentException.class, ()->{
            Vector2 v1 = null;
            Vector2.Div(v1, 3.14f);
        });
    }

    @Test
    void divisionWithZeroShouldRaiseException(){
        assertThrows(ArithmeticException.class, ()->{
            Vector2 v1 = new  Vector2(45.0f, 66.4f);
            Vector2.Div(v1, 0);
        });
    }
}