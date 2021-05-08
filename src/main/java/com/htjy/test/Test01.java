package com.htjy.test;

import java.io.Console;

public class Test01 {
    public static void main(String[] args) {
        String head = "ttt";
        String h = "lll";
        System.out.println(h != (h=head));
        System.out.println(h);
    }
}
