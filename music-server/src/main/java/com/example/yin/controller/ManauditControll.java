package com.example.yin.controller;

import com.example.yin.dao.FriendsMapper;
import com.example.yin.domain.Manaudit;
import com.example.yin.service.impl.ManauditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManauditControll {
    @Autowired
    private ManauditServiceImpl manauditService;
    @Autowired
    private FriendsMapper friendsMapper;
//    发送审核
    @ResponseBody
    @RequestMapping(value = "/audit/add", method = RequestMethod.GET)
    public int Addaudit(@RequestParam("myid")Integer myid,@RequestParam("pyid")Integer pyid){
        int flag = 0;
        flag =  manauditService.SendAudit(myid, pyid);
        int bool =   manauditService.ConsentAudit(pyid,myid);
        flag +=bool;
        return flag;
    }
    //    查看审核
    @ResponseBody
    @RequestMapping(value = "/audit/all", method = RequestMethod.GET)
    public List<Manaudit> auditAll(@RequestParam("myid")Integer myid){
        return manauditService.AuditAll(myid);
    }
    //通过审核 或者 没有通过
    @ResponseBody
    @RequestMapping(value = "/audit/get", method = RequestMethod.GET)
    public int auditAll(@RequestParam("myid")Integer myid,@RequestParam("pyid")Integer pyid,@RequestParam("type")Integer type){
//        如果同意则成功加上好友
        if(type==2){
          friendsMapper.addfris(myid,pyid);
          friendsMapper.addfri(pyid,myid);
        }
        return manauditService.getApproved(myid, pyid, type);
    }
}
