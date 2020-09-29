package com.example.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.*;

/**
 * huize Service Inc
 * All Rights Reserved @2020
 *
 * @author hz19084340
 * 2020/9/22 18:11
 */
public class TestTwo {

    public static final Logger logger = LoggerFactory.getLogger(TestTwo.class);

    public static void main(String[] args) {

        StopWatch stopwatch = new StopWatch("批量导入数据");
        for (int i = 0; i < 100; i++) {
            stopwatch.start(i +":时长");
            logger.info("第{}次 - begin",i+1);
            bulkData();
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("第{}次 - end",i+1);
            stopwatch.stop();
        }
        logger.info(stopwatch.prettyPrint());

//        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().build();
//        ExecutorService pool = new ThreadPoolExecutor(8,10,0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>(1024),namedThreadFactory,new ThreadPoolExecutor.AbortPolicy());
//
//        for (int i = 0; i < 10; i++) {
//            pool.submit(TestTwo::bulkData);
//        }
//        pool.shutdown();


    }



    public static void insertData(){
//        StopWatch stopwatch = new StopWatch("批量导入数据");
//        stopwatch.start();
        String json = HttpRequestUtil.httpRequest("https://randomuser.me/api/?results=100&nat=ch");
        getJson(json);
//        stopwatch.stop();
//        System.out.println(stopwatch.prettyPrint());
    }

    public static String getJson(String response){
        JSONObject jsonObject = JSONObject.parseObject(response);
        if(StringUtils.isEmpty(String.valueOf(jsonObject.get("results")))){
            System.out.println("空数据");
        }
        JSONArray results = JSONArray.parseArray(String.valueOf(jsonObject.get("results")));
        for (int i = 0; i < results.size(); i++) {
            Map<String,String> map = Maps.newHashMap();
            map.put("gender",String.valueOf(results.getJSONObject(i).get("gender")));
            JSONObject nameJsonObject = JSONObject.parseObject(String.valueOf(results.getJSONObject(i).get("name")));
            map.put("first_name",String.valueOf(nameJsonObject.get("first")));
            map.put("last_name",String.valueOf(nameJsonObject.get("last")));

            map.put("email",String.valueOf(results.getJSONObject(i).get("email")));

            JSONObject dobJsonObject = JSONObject.parseObject(String.valueOf(results.getJSONObject(i).get("dob")));
            map.put("birthday",String.valueOf(dobJsonObject.get("date")));
            map.put("age",String.valueOf(dobJsonObject.get("age")));

            map.put("phone",String.valueOf(results.getJSONObject(i).get("phone")));
            map.put("cell",String.valueOf(results.getJSONObject(i).get("cell")));
            map.put("nat",String.valueOf(results.getJSONObject(i).get("nat")));

            JSONObject pictureJsonObject = JSONObject.parseObject(String.valueOf(results.getJSONObject(i).get("picture")));
            map.put("large",String.valueOf(pictureJsonObject.get("large")));
            map.put("medium",String.valueOf(pictureJsonObject.get("medium")));
            map.put("thumbnail",String.valueOf(pictureJsonObject.get("thumbnail")));
            saveToEs(JSON.toJSONString(map));
        }
        return "";
    }


    public static void saveToEs(String json){
        String url = "http://localhost:9200/user/doc";
        String result = HttpRequestUtil.sendPost(url,json,false);
        System.out.println(result);
    }


    public static void bulkData(){
        String response = HttpRequestUtil.httpRequest("https://randomuser.me/api/?results=5000&nat=us");
        if(StringUtils.isEmpty(String.valueOf(response))){
            logger.info("空数据 response:{}",response);
        }
        JSONObject jsonObject = JSONObject.parseObject(response);
        JSONArray results = JSONArray.parseArray(String.valueOf(jsonObject.get("results")));
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < results.size(); i++) {
            Map<String,String> map = Maps.newHashMap();
            map.put("gender",String.valueOf(results.getJSONObject(i).get("gender")));
            JSONObject nameJsonObject = JSONObject.parseObject(String.valueOf(results.getJSONObject(i).get("name")));
            map.put("first_name",String.valueOf(nameJsonObject.get("first")));
            map.put("last_name",String.valueOf(nameJsonObject.get("last")));

            map.put("email",String.valueOf(results.getJSONObject(i).get("email")));

            JSONObject dobJsonObject = JSONObject.parseObject(String.valueOf(results.getJSONObject(i).get("dob")));
            map.put("birthday",String.valueOf(dobJsonObject.get("date")));
            map.put("age",String.valueOf(dobJsonObject.get("age")));

            map.put("phone",String.valueOf(results.getJSONObject(i).get("phone")));
            map.put("cell",String.valueOf(results.getJSONObject(i).get("cell")));
            map.put("nat",String.valueOf(results.getJSONObject(i).get("nat")));

            JSONObject pictureJsonObject = JSONObject.parseObject(String.valueOf(results.getJSONObject(i).get("picture")));
            map.put("large",String.valueOf(pictureJsonObject.get("large")));
            map.put("medium",String.valueOf(pictureJsonObject.get("medium")));
            map.put("thumbnail",String.valueOf(pictureJsonObject.get("thumbnail")));

            stringBuffer.append("{\"index\":{}}\n");
            stringBuffer.append(JSON.toJSONString(map)).append("\n");
        }
        saveToEsBulk(stringBuffer.toString());
    }

    public static void saveToEsBulk(String json){
        String url = "http://localhost:9200/user/doc/_bulk?pretty&pretty";
        String result = HttpRequestUtil.sendPost(url,json,false);
    }
}