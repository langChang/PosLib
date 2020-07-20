package com.nhsoft.poslib.utils;

import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {

    public static final String DEFAULT_CHARSET_NAME = "UTF-8";
    private static final char[] DIGITS_LOWER;
    private static final char[] DIGITS_UPPER;
    private final Charset charset=Charset.forName("UTF-8");

    public static byte[] decodeHex(String data)  {
        return decodeHex(data.toCharArray());
    }

    public static byte[] decodeHex(char[] data)  {
        int len = data.length;
        if ((len & 1) != 0) {
            return null;
        } else {
            byte[] out = new byte[len >> 1];
            int i = 0;

            for(int j = 0; j < len; ++i) {
                int f = toDigit(data[j], j) << 4;
                ++j;
                f |= toDigit(data[j], j);
                ++j;
                out[i] = (byte)(f & 255);
            }

            return out;
        }
    }

    public static char[] encodeHex(byte[] data) {
        return encodeHex(data, true);
    }

    public static char[] encodeHex(ByteBuffer data) {
        return encodeHex(data, true);
    }

    public static char[] encodeHex(byte[] data, boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    public static char[] encodeHex(ByteBuffer data, boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    protected static char[] encodeHex(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];
        int i = 0;

        for(int var5 = 0; i < l; ++i) {
            out[var5++] = toDigits[(240 & data[i]) >>> 4];
            out[var5++] = toDigits[15 & data[i]];
        }

        return out;
    }

    protected static char[] encodeHex(ByteBuffer data, char[] toDigits) {
        return encodeHex(data.array(), toDigits);
    }

    public static String encodeHexString(byte[] data) {
        return new String(encodeHex(data));
    }

    public static String encodeHexString(byte[] data, boolean toLowerCase) {
        return new String(encodeHex(data, toLowerCase));
    }

    public static String encodeHexString(ByteBuffer data) {
        return new String(encodeHex(data));
    }

    public static String encodeHexString(ByteBuffer data, boolean toLowerCase) {
        return new String(encodeHex(data, toLowerCase));
    }

    protected static int toDigit(char ch, int index)  {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            return  0;
        } else {
            return digit;
        }
    }


    public byte[] decode(byte[] array)  {
        return decodeHex((new String(array, this.getCharset())).toCharArray());
    }

    public byte[] decode(ByteBuffer buffer)  {
        return decodeHex((new String(buffer.array(), this.getCharset())).toCharArray());
    }

    public Object decode(Object object)  {
        if (object instanceof String) {
            return this.decode((Object)((String)object).toCharArray());
        } else if (object instanceof byte[]) {
            return this.decode((byte[])((byte[])object));
        } else if (object instanceof ByteBuffer) {
            return this.decode((ByteBuffer)object);
        } else {
            try {
                return decodeHex((char[])((char[])object));
            } catch (ClassCastException var3) {

            }
        }
        return null;
    }

    public byte[] encode(byte[] array) {
        return encodeHexString(array).getBytes(this.getCharset());
    }

    public byte[] encode(ByteBuffer array) {
        return encodeHexString(array).getBytes(this.getCharset());
    }

    public Object encode(Object object) {
        byte[] byteArray = new byte[0];
        if (object instanceof String) {
            byteArray = ((String)object).getBytes(this.getCharset());
        } else if (object instanceof ByteBuffer) {
            byteArray = ((ByteBuffer)object).array();
        } else {
            try {
                byteArray = (byte[])((byte[])object);
            } catch (ClassCastException var4) {

            }
        }

        return encodeHex(byteArray);
    }

    public Charset getCharset() {
        return this.charset;
    }

    public String getCharsetName() {
        return this.charset.name();
    }

    public String toString() {
        return super.toString() + "[charsetName=" + this.charset + "]";
    }

    static {
        DIGITS_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        DIGITS_UPPER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    //***************************************************************************************************************

//    protected static Logger LOG = LoggerFactory.getLogger(StringEncryptUtil.class);
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";



    public static byte[] initSecretKey() {
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException var2) {
            var2.printStackTrace();
            return new byte[0];
        }

        kg.init(128);
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }

    public static String initSecretKeyStr() {
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException var2) {
            var2.printStackTrace();
            return "";
        }

        kg.init(128);
        SecretKey secretKey = kg.generateKey();
        return new String(encodeHex(secretKey.getEncoded()));
    }

    private static Key toKey(byte[] key) {
        return new SecretKeySpec(key, "AES");
    }

    public static byte[] encrypt(byte[] data, Key key) throws Exception {
        return encrypt(data, key, "AES/ECB/PKCS5Padding");
    }

    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        return encrypt(data, key, "AES/ECB/PKCS5Padding");
    }

    public static String encryptStr(String data, String key) {
        try {
            return new String(encodeHex(encrypt(data.getBytes(),decodeHex(key.toCharArray()), "AES/ECB/PKCS5Padding")));
        } catch (Exception var3) {
            Log.d(var3.getMessage(), var3.toString());
            return data;
        }
    }

    public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
        Key k = toKey(key);
        return encrypt(data, k, cipherAlgorithm);
    }

    public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(1, key);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        return decrypt(data, key, "AES/ECB/PKCS5Padding");
    }

    public static String decryptStr(String data, String key) {
        try {
            return new String(decrypt(decodeHex(data.toCharArray()), decodeHex(key.toCharArray()), "AES/ECB/PKCS5Padding"));
        } catch (Exception var3) {
//            LOG.error(var3.getMessage(), var3);
            return data;
        }
    }

    public static byte[] decrypt(byte[] data, Key key) throws Exception {
        return decrypt(data, key, "AES/ECB/PKCS5Padding");
    }

    public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
        Key k = toKey(key);
        return decrypt(data, k, cipherAlgorithm);
    }

    public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(2, key);
        return cipher.doFinal(data);
    }

    private static String showByteArray(byte[] data) {
        if (null == data) {
            return null;
        } else {
            StringBuffer sb = new StringBuffer("{");
            byte[] var2 = data;
            int var3 = data.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                byte b = var2[var4];
                sb.append(b).append(",");
            }

            sb.deleteCharAt(sb.length() - 1);
            sb.append("}");
            return sb.toString();
        }
    }
}
