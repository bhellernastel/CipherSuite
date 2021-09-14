package com.nastel.nfc.encrypt;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Arrays;

public class AesEncryptorDecryptor extends EncryptDecrypt {

    private static final String AES_ALGORITHM = "AES/ECB/NoPadding";
    private static final String AES_KEY_TYPE  = "AES";
    private static final int    AES_BLOCK_SIZE = 16;





    public String encrypt(String property) throws GeneralSecurityException {

        Key keySpec = new SecretKeySpec(getPwdBytes(), AES_KEY_TYPE);
        Cipher c = Cipher.getInstance(AES_ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, keySpec);

        if (property.length() > 255)
            throw new GeneralSecurityException("specified input string exceed maximum supported length of 255");

        // Determine length of string to be encrypted:
        //    - add 1 to encode length of original string into string to be encrypted
        //    - string to be encrypted must be padded to length that's a multiple of block size
        int strLen = 1 + property.length();
        int nEncLen = (strLen/AES_BLOCK_SIZE*AES_BLOCK_SIZE)
                + (strLen%AES_BLOCK_SIZE != 0 ? AES_BLOCK_SIZE : 0);

        byte[] strBytes = new byte[nEncLen];

        // encode length of original string into first byte of encoded string
        strBytes[0] = (byte)property.length();

        // add original string to encoded string
        System.arraycopy(property.getBytes(), 0, strBytes, 1, property.length());

        // pad encoded string with blanks if necessary
        if (nEncLen > strLen)
            Arrays.fill(strBytes, strLen, nEncLen, (byte)' ');

        // perform encryption
        byte[] encVal = c.doFinal(strBytes);

// return base64-encoding of encrypted value
        return new String(base64Encode(encVal));



    }

    public String decrypt(String property) throws GeneralSecurityException, IOException {

        Key keySpec = new SecretKeySpec(getPwdBytes(), AES_KEY_TYPE);
        Cipher c = Cipher.getInstance(AES_ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, keySpec);

    // Base64-decode value to get raw encrypted value
        byte[] encVal = base64Decode(property);

        // perform decryption
        String decStr = new String(c.doFinal(encVal));

        // extract length of original string from first byte of encoded string
        int origLen = decStr.getBytes()[0];

        // extract original string from encoded string
        return decStr.substring(1, 1+origLen);
    }
}
