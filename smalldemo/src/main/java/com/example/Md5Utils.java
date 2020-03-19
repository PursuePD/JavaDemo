package com.example;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: 小崔
 * @create: 2020-03-19 11:20
 * @Description:
 */
public class Md5Utils {
    private static char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static MessageDigest messagedigest = null;

    public Md5Utils() {
    }

    public static String getFileMD5String(File file) throws IOException {
	FileInputStream in = new FileInputStream(file);
	FileChannel ch = in.getChannel();
	MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
	messagedigest.update(byteBuffer);
	in.close();
	return bufferToHex(messagedigest.digest());
    }

    public static String getMD5String(String s) {
	return getMD5String(s.getBytes());
    }

    public static String getMD5String(byte[] bytes) {
	synchronized(messagedigest) {
	    messagedigest.update(bytes);
	    return bufferToHex(messagedigest.digest());
	}
    }

    private static String bufferToHex(byte[] bytes) {
	return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte[] bytes, int m, int n) {
	StringBuffer stringbuffer = new StringBuffer(2 * n);
	int k = m + n;

	for(int l = m; l < k; ++l) {
	    appendHexPair(bytes[l], stringbuffer);
	}

	return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
	char c0 = hexDigits[(bt & 240) >> 4];
	char c1 = hexDigits[bt & 15];
	stringbuffer.append(c0);
	stringbuffer.append(c1);
    }

    public static boolean checkPassword(String password, String md5PwdStr) {
	String s = getMD5String(password);
	return s.equals(md5PwdStr);
    }

    public static Object byteToObject(byte[] bytes) {
	Object obj = null;

	try {
	    ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
	    ObjectInputStream oi = new ObjectInputStream(bi);
	    obj = oi.readObject();
	    bi.close();
	    oi.close();
	} catch (Exception var4) {
	    var4.printStackTrace();
	}

	return obj;
    }

    public static byte[] objectToByte(Object obj) {
	byte[] bytes = null;
	ByteArrayOutputStream bo = null;
	ObjectOutputStream oo = null;

	try {
	    bo = new ByteArrayOutputStream();
	    oo = new ObjectOutputStream(bo);
	    oo.writeObject(obj);
	    bytes = bo.toByteArray();
	} catch (Exception var13) {
	    var13.printStackTrace();
	} finally {
	    try {
		bo.close();
		oo.close();
	    } catch (IOException var12) {
		var12.printStackTrace();
	    }

	}

	return bytes;
    }

    public static String fileHash(byte[] data) {
	String result = null;

	for(int i = 0; i < data.length; ++i) {
	    result = result + Integer.toString((data[i] & 255) + 256, 16).substring(1);
	}

	return result;
    }

    static {
	try {
	    messagedigest = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException var1) {
	    var1.printStackTrace();
	}

    }
}

