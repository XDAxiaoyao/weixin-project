package com.xiaoyao;

import static org.junit.Assert.assertTrue;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void main(String[] args) {
        String content = "test中文";

//随机生成密钥
//         byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
            String keys = "java2203java2203";  //注意 ase算法key值长度一般是8的倍数
        byte[] key = keys.getBytes(StandardCharsets.UTF_8);
//构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);

//加密
        byte[] encrypt = aes.encrypt(content);
        //加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        System.out.println("加密后的是：" + encryptHex);


//解密
        byte[] decrypt = aes.decrypt(encrypt);
//解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        System.out.println("解密后的是：" + decryptStr);


        //解密为字符串
        String decryptStr1 = aes.decryptStr("1aa789bd9f83061b97c764a33b50fcc7", CharsetUtil.CHARSET_UTF_8);
        System.out.println("解密后的是：" + decryptStr1);
    }

}
