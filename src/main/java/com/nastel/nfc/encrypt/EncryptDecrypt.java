package com.nastel.nfc.encrypt;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.security.GeneralSecurityException;

public abstract class EncryptDecrypt {

    private byte[] salt=null;
    private String pwd=null;
    private byte[] pwdbytes = null;


    public abstract String encrypt(String property) throws GeneralSecurityException,IOException;
    public abstract String decrypt(String property) throws GeneralSecurityException,IOException;

    public String base64Encode(byte[] bytes){
        return new String(Base64.encodeBase64(bytes));
    }

    public byte[] base64Decode(String property) throws IOException { return Base64.decodeBase64(property.getBytes()); }

    public EncryptDecrypt setSalt(byte[] s){salt=s;return this;}
    public EncryptDecrypt setPwd(String p){pwd=p;return this;}
    public EncryptDecrypt setPwd(byte[] b){pwdbytes = b;return this;}

    public String getPwdString(){return pwd;}
    public byte[] getPwdBytes(){return pwdbytes;}
    public byte[] getSalt(){return salt;}

}
