package com.htjy.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternLearn {
    public static void main(String[] args) {
        System.out.println("starting test....");

        String str = "lily is 5 year now";

        Pattern pattern = Pattern.compile("\\s+");
        String[] rst = pattern.split(str);
        for (String ele :rst) {
            System.out.println(ele);
        }

        pattern = Pattern.compile("lily is 5 years now");
        System.out.println( pattern.matcher(str).matches());

        pattern = Pattern.compile("\\b\\w+\\b");
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            System.out.println(m.group());
        }

    }
}
