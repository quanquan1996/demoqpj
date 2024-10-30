package com.example.demoqpj.util;

/**
 * @Author qiupengjun
 * @Date 2023 09 13 15 10
 **/
public class RandomUtil {
    public static void main(String std[]){
        System.out.println(RandomUtil.getRandomString(4));
    }
    public static String getRandomString(int i)
    {
        String theAlphaNumericS;
        StringBuilder builder;

        theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        //create the StringBuffer
        builder = new StringBuilder(i);

        for (int m = 0; m < i; m++) {

            // generate numeric
            int myindex
                    = (int)(theAlphaNumericS.length()
                    * Math.random());

            // add the characters
            builder.append(theAlphaNumericS
                    .charAt(myindex));
        }

        return builder.toString();
    }
}
