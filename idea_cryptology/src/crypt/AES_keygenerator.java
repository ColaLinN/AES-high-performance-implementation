package crypt;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AES_keygenerator {
    public static void main(String[] args) {
    }

    //用于加密测试速度对比
    public static String encrypt1(byte[] content, Cipher cipher) {
        SecretKey secretKey;

        try {
            byte[] result = cipher.doFinal(content);
            String aft_aes = parseByte2HexStr(result);
            return aft_aes;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    //用于将字节串转换为十六进制字符串
    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; ++i) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) hex = '0' + hex;
            sb.append(hex);//.toUpperCase()可以大写
        }

        return sb.toString();
    }
    //用于将十六进制字符串转换为字节串
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;

        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; ++i) {
            int value = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 2), 16);
            result[i] = (byte)value;
        }

        return result;
    }
    //用于生成密钥
    public static String KeyGenerator_A(String seed){
        try {
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            String seedA=seed;
            secureRandom.setSeed(seedA.getBytes());
            keygen.init(128, secureRandom);
            SecretKey original_key=keygen.generateKey();
            byte [] raw=original_key.getEncoded();
            String random_key=AES_keygenerator.parseByte2HexStr(raw);
            return  random_key;
        } catch (Exception e) {
        throw new RuntimeException("生成密钥出现异常");
        }
    }
}
