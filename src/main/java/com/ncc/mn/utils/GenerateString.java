package com.ncc.mn.utils;


import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GenerateString {
    public String generate(int leftLimit,int rightLimit,int length,String prefix){
//        int leftLimit = 48; // letter 'a'
//        int rightLimit = 57; // letter 'z'
//        int targetStringLength = 4;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return prefix + generatedString;
    }
}
