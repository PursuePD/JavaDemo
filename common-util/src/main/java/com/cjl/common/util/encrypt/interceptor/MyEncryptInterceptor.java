package com.cjl.common.util.encrypt.interceptor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cjl.common.util.encrypt.MyEncrypt;
import com.cjl.common.util.encrypt.util.MyEncryptValueUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: 小崔
 * @create: 2020-05-15 18:08
 * @Description:
 */
@Aspect
@Component
public class MyEncryptInterceptor {

    @Pointcut(value = "@annotation(com.hzins.channel.tools.encryption.Encrypt)")
    private void encryptPointcut() {

    }
    @Around(value = "encryptPointcut() && @annotation(encrypt)")
    public <T> Object encryptAround(ProceedingJoinPoint point, MyEncrypt myEncrypt)
	    throws Throwable {
	Object result = null;

	try {
	    result = point.proceed();
	} catch (Throwable throwable) {
	    throw throwable;
	}
	JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(result));
	String[] beanRouteAttr = MyEncryptValueUtil.parseFromEncrypt(myEncrypt);
	boolean isCollection = myEncrypt.isCollection();
	if(isCollection){
	    JSONArray jsonArray = getCollection(jsonObject,beanRouteAttr);
	    if(jsonArray != null) {
		for (int i = 0; i < jsonArray.size(); i++) {
		    encryptBean(jsonArray.getJSONObject(i), myEncrypt);
		}
	    }
	}else{
	    jsonObject = getBean(jsonObject,beanRouteAttr);
	    encryptBean(jsonObject, myEncrypt);
	}
	result = JSONObject.parseObject(jsonObject.toJSONString(), myEncrypt.resultClass());
	return result;
    }

    private JSONObject getBean(JSONObject jsonObject,String[] beanRouteArr){
	if(jsonObject == null){
	    return null;
	}
	for (String str : beanRouteArr) {
	    jsonObject = jsonObject.getJSONObject(str);
	    if(jsonObject == null){
		break;
	    }
	}
	return jsonObject;
    }

    private JSONArray getCollection(JSONObject jsonObject,String[] beanRouteArr){
	if(jsonObject == null){
	    return null;
	}
	for(int i = 0; i < beanRouteArr.length; i ++){
	    if(i < beanRouteArr.length - 1){
		jsonObject = jsonObject.getJSONObject(beanRouteArr[i]);
	    }else{
		return jsonObject.getJSONArray(beanRouteArr[i]);
	    }
	}
	return null;
    }

    private void encryptBean(JSONObject jsonObject, MyEncrypt myEncrypt){
	String[] fieldNames = myEncrypt.fieldNames();
	for (String fieldName : fieldNames) {
	    String objValue = jsonObject.getString(fieldName);
	    objValue = MyEncryptValueUtil.encrypt(myEncrypt, objValue);
	    jsonObject.put(fieldName, objValue);
	}
    }

}
