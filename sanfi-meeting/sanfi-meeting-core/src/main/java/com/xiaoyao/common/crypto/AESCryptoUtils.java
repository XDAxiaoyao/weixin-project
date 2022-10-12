package com.xiaoyao.common.crypto;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

import java.nio.charset.StandardCharsets;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/11 21:58
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description: TODO HUTOOL工具类 AES 对称加密算法
 */
public class AESCryptoUtils {
    /**
     * 1.类常量（成员变量）
     * 2.配置文件application.yml
     * 3.数据库（mysql）系统全局配置（密钥）
     * 4.redis（数字字典） 系统全局。
     */
    private static final String KEYS = "java2203java2203";

    /**
     * 加密AES
     * @param content 输入的文本
     * @return 加密后的字符串
     */

    public static String encryptMethod(String content){
        byte[] key = KEYS.getBytes(StandardCharsets.UTF_8);
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);

        //加密
        byte[] encrypt = aes.encrypt(content);
        //加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        return encryptHex;
    }


    /**
     * TODO 解密AES
     * @param encrypt 加密后的密文
     * @return 解密后的明文
     */
    public static String decryptMethod(String encrypt){
        byte[] key = KEYS.getBytes(StandardCharsets.UTF_8);
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        //解密
        byte[] decrypt = aes.decrypt(encrypt);
        //解密为字符串
        String decryptStr = aes.decryptStr(encrypt, CharsetUtil.CHARSET_UTF_8);
        return decryptStr;
    }

    public static void main(String[] args) {
        String encryStr = AESCryptoUtils.encryptMethod("java2203java2203");
        System.out.println(encryStr);
        String decryStr = AESCryptoUtils.decryptMethod(encryStr);
        System.out.println(decryStr);

    }

}
