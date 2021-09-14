package com.nastel.nfc.encrypt;

public class EncryptDecryptMain {

    public static void main(String args[]) throws Exception{

        String pwd = "test123";

        EncryptDecrypt edes = EncryptDecryptFactory.getEncryptDecryptMethod("DES").setPwd(SecretClass.DESPASS).setSalt(SecretClass.DESSALT);
        System.out.println("Password: "+pwd);
        String encpwd = edes.encrypt(pwd);
        System.out.println("Password encrypted using DES: "+encpwd);
        String decpwd = edes.decrypt(encpwd);
        System.out.println("Password decrypted using DES: "+decpwd);


        EncryptDecrypt eaes = EncryptDecryptFactory.getEncryptDecryptMethod("AES").setPwd(SecretClass.AESPWDKEY);
        System.out.println("Password: "+pwd);
        String encpwdaes = eaes.encrypt(pwd);
        System.out.println("Password encrypted using AES: "+encpwdaes);
        String decpwdaes = eaes.decrypt(encpwdaes);
        System.out.println("Password decrypted using AES: "+decpwdaes);


    }


}
