package com.aa.controller;

import com.aa.bean.Tour;
import com.aa.bean.vo.User;
import com.aa.feign.UserFeign;
import com.aa.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    UserFeign userFeign;

    @Autowired
    TourService tourService;

    @RequestMapping("/list")
    public String list(){
        List<Tour> list1 = tourService.list();
        List<User> list = userFeign.list();
        return list1+":"+list;
    }
}
