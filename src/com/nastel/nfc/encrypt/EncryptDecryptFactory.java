package com.nastel.nfc.encrypt;

public class EncryptDecryptFactory {

    public static EncryptDecrypt getEncryptDecryptMethod(String m){
        if (m.equalsIgnoreCase("DES")){
            return new PBEMD5DESEncryptorDecryptor();
        }
        else if(m.equalsIgnoreCase("AES")){
            return new AesEncryptorDecryptor();
        }
        return null;
    }


}
