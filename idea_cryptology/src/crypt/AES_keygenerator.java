package crypt;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AES_keygenerator {
    public static void main(String[] args) {
        AES_keygenerator aes_keygenerator=new AES_keygenerator();
        byte[] newkey = {(byte) 0xAA, (byte) 0, (byte) 0, (byte) 0,
                (byte) 0, (byte) 0x33, (byte) 0, (byte) 0,
                (byte) 0, (byte) 0, (byte) 0, (byte) 0,
                (byte) 0, (byte) 0, (byte) 0, (byte) 0};
//        chushi1=AES_keygenerator.getKey("hhh");
        String aesa=AES_keygenerator.parseByte2HexStr(newkey);
        byte[] aesbyte=AES_keygenerator.parseHexStr2Byte(aesa);
        System.out.println(aesa);
        for(byte x:aesbyte)
        {
            System.out.printf("%02x",x);
        }
        try{
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, new SecureRandom("HHHHH".getBytes()));
            //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            System.out.println();
            for(byte x:raw)
            {
                System.out.printf("%02x",x);
            }
        } catch (Exception e) {
            throw new RuntimeException("初始化密钥出现异常");
        }
    }
    public static String encrypt(String bef_aes, String password) {
        byte[] byteContent = null;

        try {
            byteContent = bef_aes.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return encrypt(byteContent, password);
    }

    public static String encrypt(byte[] content, String password) {
        SecretKey secretKey;

        try {
            secretKey = getKey(password);
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(1, key);
            byte[] result = cipher.doFinal(content);
            String aft_aes = parseByte2HexStr(result);
            return aft_aes;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String aft_aes, String password) {
        byte[] content;

        try {
            content = parseHexStr2Byte(aft_aes);
            SecretKey secretKey = getKey(password);
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, key);
            byte[] result = cipher.doFinal(content);
            String bef_aes = new String(result);
            return bef_aes;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; ++i) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) hex = '0' + hex;
            sb.append(hex);//.toUpperCase()可以大写
        }

        return sb.toString();
    }

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

    public static SecretKey getKey(String strKey) {
        KeyGenerator _generator;

        try {
            _generator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(strKey.getBytes());
            _generator.init(128, secureRandom);

            return _generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("初始化密钥出现异常");
        }
    }
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
