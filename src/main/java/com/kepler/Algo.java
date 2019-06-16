package com.kepler;

import java.util.Random;

public class Algo {

    public String sayHello() {
        return "Today, your luck number is " + luckNumber();
    }

    private int luckNumber() {
        Random r = new Random();
        return r.nextInt(100);
    }
}
