package com.example.yin.controller;

import com.example.yin.domain.Send;
import com.example.yin.service.impl.SendServeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SendController {
    @Autowired
    private SendServeImpl sendServe;
    @ResponseBody
    @RequestMapping(value = "/send/all", method = RequestMethod.GET)
    public List<Send> All(@RequestParam("uid")Integer uid, @RequestParam("frid") Integer frid){
     return sendServe.mySendAll(uid, frid);
    }
    @ResponseBody
    @RequestMapping(value = "/send/add", method = RequestMethod.GET)
    public int add(@RequestParam("uid")Integer uid, @RequestParam("frid") Integer frid ,@RequestParam("mycontent") String mycontent){
        int flag = 0 ;
        flag = sendServe.addMySend(uid, frid, mycontent);
        sendServe.addfrSend(frid,uid,mycontent);
        return flag;
    }

}
