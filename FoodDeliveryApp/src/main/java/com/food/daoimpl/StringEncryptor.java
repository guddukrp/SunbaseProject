package com.food.daoimpl;

import java.util.Base64;

public class StringEncryptor {

//    public static void main(String[] args) {
//        String password = "123";
//        
//        String t1 = encrypt(password);
//        String t2= decrypt(t1);
//
//        System.out.println(t1+"   "+t2);
//    }
    public static String encrypt(String password){
        byte[] encrypt = Base64.getEncoder().encode(password.getBytes());
        return new String(encrypt);
    }
    public static String decrypt(String password){
        byte[] encrypt = password.getBytes();
        byte[] decrypt = Base64.getDecoder().decode(encrypt);
        return new String(decrypt);
    }
}

