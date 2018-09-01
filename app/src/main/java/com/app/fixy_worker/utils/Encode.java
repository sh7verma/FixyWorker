package com.app.fixy_worker.utils;

/**
 * Created by dev on 25/4/18.
 */

public class Encode {

    private CryptLib cryptLib;
    private String SHA_KEY = "KEYFORISN";
    private String SHA_IV = "121245457878";

    public Encode() {
        try {
            cryptLib = new CryptLib();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String s) {
        String encryptedString = "";
        try {
            encryptedString = cryptLib.encryptSimple(s, SHA_KEY, SHA_IV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }


    public String decrypt(String s) {
        String decryptedString = "";
        try {
            decryptedString = cryptLib.decryptSimple(s, SHA_KEY, SHA_IV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedString;
    }

}