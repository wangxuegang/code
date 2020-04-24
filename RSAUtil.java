package utils;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * RSAUtil工具类
 * @author wangxuegang
 */
public class RSAUtil {
	
    /**
     * 用来维护每个用户的公私匙
     */
    public static ConcurrentHashMap keysMap ;

    /**
     * 随机生成密钥对
     */
    public static Map<Integer, String> genKeyPair() {
    	
    	//private static final Logger logger = Logger.getLogger(RSAUtil.class);
    	
        Map<Integer, String> keyMap = new HashMap<Integer, String>();  //用于封装随机产生的公钥与私钥

        try {
            // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

            // 初始化密钥对生成器，密钥大小为96-1024位
            keyPairGen.initialize(1024,new SecureRandom());
            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥


            // 得到公钥字符串
            String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
            // 得到私钥字符串
            String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));

            // 将公钥和私钥保存到Map
            keyMap.put(0,publicKeyString);  //0表示公钥
            keyMap.put(1,privateKeyString);  //1表示私钥
        } catch (Exception e) {
            //logger.info("生成公钥私钥异常："+e.getMessage());
            return null;
        }

        return keyMap;
    }
    /**
     * RSA公钥加密
     * @param str  需要加密的字符串
     * @param publicKey  公钥
     * @return 公钥加密后的内容
     */
    public static String encrypt( String str, String publicKey ){
        String outStr=null;
        try {
            //base64编码的公钥
            byte[] decoded = Base64.decodeBase64(publicKey);
            RSAPublicKey pubKey = (RSAPublicKey)KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
            //RSA加密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        } catch (Exception e) {
            //logger.info("使用公钥加密【"+str+"】异常："+e.getMessage());
        }
        return outStr;
    }

    /**
     * RSA私钥解密
     * @param str  加密字符串
     * @param privateKey  私钥
     * @return 私钥解密后的内容
     */
    public static String decrypt(String str, String privateKey){
        String outStr=null;
        try {
            //64位解码加密后的字符串
            byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
            //base64编码的私钥
            byte[] decoded = Base64.decodeBase64(privateKey);
            RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
            //RSA解密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            outStr = new String(cipher.doFinal(inputByte));
        } catch (Exception e) {
            //logger.info("使用私钥解密异常："+e.getMessage());
        }
        return outStr;
    }
    
    public static void main(String[] args) {
    	
    	
//		前端用crypto-js进行加密，npm i jsencrypt
//    	import JSEncrypt from 'jsencrypt';
//    	const encrypt = new JSEncrypt();
//    	encrypt.setPublicKey('你的公钥');
//    	password = encrypt.encrypt(‘你的密码’);// 加密后的字符串
    	
//    	Gson go = new Gson();
//    	System.out.println(go.toJson(RSAUtil.genKeyPair()));
    	
//    	{"0":"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgA6IoAudhnqPhOjGxsU/54v3XtEmquY08iURxmBw+ocjrVhpSpiFZ3ueYOccoqc2PhHXXymrftd5EpAgWp3upmWHCnajFSx5oy8SwBDFQidZjAeinnBh+H6FvjP8wufK4YE17AiH5kHvsNeRp/6aj4J8CqK3YWoSqXvURWAeHUQIDAQAB","1":"MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKADoigC52Geo+E6MbGxT/ni/de0Saq5jTyJRHGYHD6hyOtWGlKmIVne55g5xyipzY+EddfKat+13kSkCBane6mZYcKdqMVLHmjLxLAEMVCJ1mMB6KecGH4foW+M/zC58rhgTXsCIfmQe+w15Gn/pqPgnwKordhahKpe9RFYB4dRAgMBAAECgYAU0cQ0boRKeEicUmUK2KYuPjGG8kcvdmsA+F82ZHMwVv58WDyeik0Gk3EQplvaV5WOWc63X/fd2Zzs02bWA76cEjFAtGcEVj1pWVEbVx0fF60P8J2PwADeNccpj55D3Z/Agy4af/FTTJhsvUr2d1cuzjODFMeWAAg9QPrAQKn8wQJBAOx64NABEDi4AJrZEcji/rxZzTN9LpwdH9KVh57uwA9PNrjHeYrLJNUv5Y++B6x7e/Su+7FU5ppnBGxGn1xgJ1kCQQCtOO+furggXjF3+C19ypsIec382Wc9Y5G9YPLst+t9tPH/81tC7O9kz6Iz9EENE7vzMYcAomKs/APC02VNGNi5AkBpzNwSE7e3OJOEtANh9jTz/dVx6NrWm60mISJJOBYTg2Q/LXeyYgq7mq9BLUrvn1uo8DTJdOurPtXav4oC4T4ZAkBQeaYhsA6AwyH6WWtRCIKUInqHYL0s8QgxUkwpm5ylLx5KydV5NzhUvn3d4zkhiSyFZFoS+l4bfY2Fws5KTiPJAkA9LpzNnYAo0E4hYySaIow7c/UH3azJoGZH0CoHzZwg0yMxHUFLc0jrkyRXjODCkFtKeywz7NgwNGw0dhaGrfxd"}
    	
//    	String data = RSAUtil.encrypt("test", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgA6IoAudhnqPhOjGxsU/54v3XtEmquY08iURxmBw+ocjrVhpSpiFZ3ueYOccoqc2PhHXXymrftd5EpAgWp3upmWHCnajFSx5oy8SwBDFQidZjAeinnBh+H6FvjP8wufK4YE17AiH5kHvsNeRp/6aj4J8CqK3YWoSqXvURWAeHUQIDAQAB");
//    	System.out.println(RSAUtil.decrypt(data, "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKADoigC52Geo+E6MbGxT/ni/de0Saq5jTyJRHGYHD6hyOtWGlKmIVne55g5xyipzY+EddfKat+13kSkCBane6mZYcKdqMVLHmjLxLAEMVCJ1mMB6KecGH4foW+M/zC58rhgTXsCIfmQe+w15Gn/pqPgnwKordhahKpe9RFYB4dRAgMBAAECgYAU0cQ0boRKeEicUmUK2KYuPjGG8kcvdmsA+F82ZHMwVv58WDyeik0Gk3EQplvaV5WOWc63X/fd2Zzs02bWA76cEjFAtGcEVj1pWVEbVx0fF60P8J2PwADeNccpj55D3Z/Agy4af/FTTJhsvUr2d1cuzjODFMeWAAg9QPrAQKn8wQJBAOx64NABEDi4AJrZEcji/rxZzTN9LpwdH9KVh57uwA9PNrjHeYrLJNUv5Y++B6x7e/Su+7FU5ppnBGxGn1xgJ1kCQQCtOO+furggXjF3+C19ypsIec382Wc9Y5G9YPLst+t9tPH/81tC7O9kz6Iz9EENE7vzMYcAomKs/APC02VNGNi5AkBpzNwSE7e3OJOEtANh9jTz/dVx6NrWm60mISJJOBYTg2Q/LXeyYgq7mq9BLUrvn1uo8DTJdOurPtXav4oC4T4ZAkBQeaYhsA6AwyH6WWtRCIKUInqHYL0s8QgxUkwpm5ylLx5KydV5NzhUvn3d4zkhiSyFZFoS+l4bfY2Fws5KTiPJAkA9LpzNnYAo0E4hYySaIow7c/UH3azJoGZH0CoHzZwg0yMxHUFLc0jrkyRXjODCkFtKeywz7NgwNGw0dhaGrfxd"));
	}
}
