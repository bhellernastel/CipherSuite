package com.nastel.nfc.encrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.IOException;
import java.security.GeneralSecurityException;


public class PBEMD5DESEncryptorDecryptor extends EncryptDecrypt {

    private static final char[] PASSWORD = "nastelAutoPilotM6-898923784628937".toCharArray();
    private static final byte[] SALT = {(byte) 0xde,(byte) 0x33,(byte) 0x10,(byte) 0x12,(byte) 0xde,(byte) 0x33,(byte) 0x10,(byte) 0x12,};


    public static void main(String args[]) throws Exception{
      /*  String o = "test";
        System.out.println("Original Password: "+o);
        String en = encrypt(o);
        System.out.println("Encrypted Password: "+en);
        String d = decrypt(en);
        System.out.println("Decrypted Password: "+d);
*/

    }



    public  boolean isEncrypted(String property){
        try{
            decrypt(property);
            return true;
        }
        catch(Throwable e){
            return false;
        }
    }

    public String encrypt(String property) throws GeneralSecurityException,IOException{
        SecretKeyFactory keyFactory =  SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(getPwdString().toCharArray()));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.ENCRYPT_MODE,key,new PBEParameterSpec(getSalt(),20));
        return base64Encode(pbeCipher.doFinal(property.getBytes()));
    }

    public String decrypt(String property) throws GeneralSecurityException,IOException{
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(getPwdString().toCharArray()));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.DECRYPT_MODE,key,new PBEParameterSpec(getSalt(),20));
        return new String(pbeCipher.doFinal(base64Decode(property)));
    }




}
