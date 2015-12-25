package com.wxcrm.util;

import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ThreeDes 
{
	private static final Key key = getKey("oilchemKey");
	
    private static Key getKey(String strKey)
    {
        Key key = null;
        try 
        {
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            generator.init(new SecureRandom(strKey.getBytes()));
            key = generator.generateKey();
            generator = null;
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return key;
    }
    
    public static String encode(String strMing)
    {
    	byte[] byteMing = null;
        byte[] byteMi = null;
        BASE64Encoder encoder = new BASE64Encoder();
        String strMi = null;
        try 
        {
            byteMing = strMing.getBytes("utf-8");
            byteMi = getEncode(byteMing);
            strMi = encoder.encode(byteMi);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
        	byteMing = null;
        	byteMi = null;
            encoder = null;
        }
        return strMi;
    }
    
    public static String decode(String strMi)
    {
    	byte[] byteMi = null;
        byte[] byteMing = null;
        BASE64Decoder decoder = new BASE64Decoder();
        String strMing = null;
        try 
        {
            byteMi = decoder.decodeBuffer(strMi);
            byteMing = getDecode(byteMi);
            strMing = new String(byteMing, "utf-8");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        finally
        {
        	byteMi = null;
        	byteMing = null;
        	decoder = null;
        }
        return strMing;
    }
    
    private static byte[] getEncode(byte[] byt)
    {
        byte[] byteFina = null;
        Cipher cipher = null;
        try 
        {
        	cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byteFina = cipher.doFinal(byt);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            cipher = null;
        }
        return byteFina; 
    }
    
    private static byte[] getDecode(byte[] byt)
    {
        byte[] byteFina = null;
        Cipher cipher = null;
        try 
        {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byteFina = cipher.doFinal(byt);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            cipher = null;
        }
        return byteFina;
    }
}