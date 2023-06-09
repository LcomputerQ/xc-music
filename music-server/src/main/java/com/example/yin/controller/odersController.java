package com.example.yin.controller;

import com.example.yin.dao.ordersMapper;
import com.example.yin.domain.Dynamics;
import com.example.yin.domain.orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class odersController {
    @Autowired
    private ordersMapper ordersMapper;
    @ResponseBody
    @RequestMapping(value = "/orders/all", method = RequestMethod.GET)
    public List<orders> ordersAll(){
        return ordersMapper.ordersAll();
    }
    @ResponseBody
    @RequestMapping(value = "/orders/byid", method = RequestMethod.GET)
    public List<orders> ordersById(@RequestParam("user_id")Integer user_id){
        return ordersMapper.ordersById(user_id);
    }
    @ResponseBody
    @RequestMapping(value = "/orders/del", method = RequestMethod.GET)
    public int ordersDelId(@RequestParam("id")Integer id){
        return ordersMapper.ordersdel(id);
    }
}
