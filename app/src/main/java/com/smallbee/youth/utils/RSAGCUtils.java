package com.smallbee.youth.utils;





import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;



public class RSAGCUtils {

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * RSA签名，目前服务端未支持
     *
     * @param paramSrc the source to be signed
     * @return
     */
    public static String sign(byte[] data, String PRIVATE_KEY) throws Exception {
        byte[] digest = sha1(data);
        byte[] encryptData = encryptByPrivateKey(digest, PRIVATE_KEY);
        return Base64.encode(encryptData);
    }

    /**
     * RSA验签
     *
     * @param source 签名内容
     * @param sign   签名值
     * @return
     */
    public static boolean verify(String source, String sign, String TFB_PUBLIC_KEY) throws Exception {
        byte[] digest = sha1(source.getBytes("UTF8"));
        byte[] encryptData = decryptByPublicKey(Base64.decode(sign), TFB_PUBLIC_KEY);
        if (Arrays.equals(digest, encryptData)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 生成签名摘要
     *
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static byte[] sha1(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = null;
        md = MessageDigest.getInstance("SHA-1"); // 选择SHA-1，也可以选择MD5
        byte[] digest = md.digest(data); // 返回的是byet[]，要转化为String存储比较方便
        return digest;
    }

    /**
     * RSA加密
     *
     * @param paramstr
     * @return
     */
    public static String encrypt(String paramstr, String TFB_PUBLIC_KEY) {
        try {
            // byte[] cipherData = RSAGCUtils.encryptByPrivateKey(paramstr.getBytes("UTF8"), TFB_PUBLIC_KEY);
            byte[] cipherData = RSAGCUtils.encryptByPublicKey(paramstr.getBytes("UTF8"), TFB_PUBLIC_KEY);

            String result = Base64.encode(cipherData);
            //            System.out.println("加密结果:" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA解密
     *
     * @param cipherData the data to be decrypt
     * @return decryptByPrivateKey
     */
    public static String decrypt(String cipherData, String PRIVATE_KEY) {
        try {
            byte[] cipher = RSAGCUtils.decryptByPrivateKey(Base64.decode(cipherData), PRIVATE_KEY);
            String result = new String(cipher, "UTF8");
            //            System.out.println("解密结果：" + new String(result.getBytes("UTF8"), "UTF8"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <P>
     * 私钥解密
     * </p >
     *
     * @param data       要解密的数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * <p>
     * 公钥解密
     * </p >
     *
     * @param data      要解密的数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * <p>
     * 公钥加密
     * </p >
     *
     * @param data      源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * <p>
     * 私钥加密
     * </p >
     *
     * @param data       源数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    public static void main(String[] args) throws Exception {
        String WALLET_PAY_publicKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMW0OJ0I8gwP9R9xCHsXxyb+i9s7wWK2NneWWVCr2JlaNercpGg1gxLGVhgFlwpcuotOyVjBY7T40Y7LPu/B7+k76ajWZACes174s3d0kk8Hqim7J6DM9icvcqQqJp8DbrFy9R+jzncH37AJMAHedA0Hlb06Hews4cYlWuSwkEcwIDAQAB";
        String publikey = encrypt("123456", WALLET_PAY_publicKEY);//公钥加密
        System.out.println("加密后:" + publikey);


    }
}