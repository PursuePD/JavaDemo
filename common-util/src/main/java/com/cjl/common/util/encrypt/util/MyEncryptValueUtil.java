package com.cjl.common.util.encrypt.util;

import com.cjl.common.util.encrypt.MyEncrypt;
import com.cjl.common.util.encrypt.exception.MyEncryptValueException;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyEncryptValueUtil {

    public static String[] parseFromEncrypt(MyEncrypt myEncrypt){
        String beanRoute = myEncrypt.beanRoute();
	if(StringUtils.isEmpty(beanRoute)){
	    throw new MyEncryptValueException();
	}
	return parseValue(beanRoute);
    }

    public static String[] parseValue(String value){
	String regEx = "^[A-Za-z][A-Za-z.]+$";
	Pattern pattern = Pattern.compile(regEx);
	Matcher matcher = pattern.matcher(value);
	if(!matcher.matches()){
	    throw new MyEncryptValueException();
	}
	String[] str = value.split("\\.");
	return str;
    }


    public static String encrypt(MyEncrypt myEncrypt,String value){
	int start = myEncrypt.start();
	int length = myEncrypt.length();
	if(StringUtils.isEmpty(value)){
	    return null;
	}else if(value.length() <= start){
	    return value;
	}else if(value.length() <= start+length){
	    return value.substring(0,start) + myEncrypt.encryptStr();
	}else{
	    return value.substring(0,start) + myEncrypt.encryptStr() + value.substring(start+length);
	}
    }
}
