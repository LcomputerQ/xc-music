package com.example.yin.controller;

import com.example.yin.domain.Journaling;
import com.example.yin.service.impl.JournalingServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JournalingControll {
    @Autowired
    JournalingServiceImpl journaling;
//    前台访问
    @ResponseBody
    @RequestMapping(value = "/journaling/all", method = RequestMethod.GET)
    public Journaling All(){
        //get请求为例
//        GetMethod getMethod = new GetMethod(" http://localhost:8080/get/getBooks");
//        HttpClient httpClient = new  HttpClient();
//        try {
////            log.debug("开始发送get请求");
//            httpClient.executeMethod(getMethod);
//            String result = getMethod.getResponseBodyAsString();
////            log.debug("获取的结果为" + result);
//        }catch (Exception e){
//
//        }
//        finally {
//            getMethod.releaseConnection();
////            log.debug("已释放链接");
//        }
        return journaling.SelectJournaling();
    }
//    后台访问
@ResponseBody
@RequestMapping(value = "/journaling/data", method = RequestMethod.GET)
    public Journaling data(){
        return journaling.getJournaling();
    }
}
