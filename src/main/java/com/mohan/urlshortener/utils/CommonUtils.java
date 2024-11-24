package com.mohan.urlshortener.utils;

import static com.mohan.urlshortener.constants.CommonConstants.BASE;
import static com.mohan.urlshortener.constants.CommonConstants.BASE62;

public class CommonUtils {

    public static String toBase62(int number) {
        if (number == 0) return "0";
        StringBuilder result = new StringBuilder();
        while (number > 0) {
            int remainder = number % BASE;
            result.append(BASE62.charAt(remainder));
            number /= BASE;
        }
        return result.reverse().toString();
    }

    public static int fromBase62(String base62) {
        int result = 0;
        for (int i = 0; i < base62.length(); i++) {
            char c = base62.charAt(i);
            int value = BASE62.indexOf(c);
            if (value == -1) {
                throw new IllegalArgumentException("Invalid character in Base62 string");
            }
            result = result * BASE + value;
        }
        return result;
    }


}
