package com.aa.controller;



import com.aa.bean.Tour;
import com.aa.bean.Tournumber;
import com.aa.bean.Tourthing;
import com.aa.bean.vo.TournumberVo;
import com.aa.bean.vo.User;
import com.aa.feign.UserFeign;
import com.aa.service.TourService;
import com.aa.service.TournumberService;
import com.aa.service.TourthingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tournumber")
@CrossOrigin(origins = "*")
public class TournumberController {
    @Autowired
    TournumberService tournumberService;

    @Autowired
    TourService tourService;

    @Autowired
    TourthingService tourthingService;

    @Autowired
    UserFeign userFeign;

    @GetMapping("/list")
    public List<TournumberVo> list()
    {
        List<TournumberVo> returnList=new ArrayList<>();
        List<Tournumber> list = tournumberService.list();
        for (Tournumber u:list) {
            //获取队伍信息
            Integer tid = u.getTid();
            System.out.println(u.getTid());
            List<Tour> listTour = tourService.list();
            Tour tour1=new Tour();
            for (Tour tour:listTour) {
                if(tour.getId()==tid){
                    tour1=tour;
                }
            }
            System.out.println(tour1);
            Integer cid = tour1.getCid();
            Integer money = tour1.getMoney();
            //获取用户信息
            Integer uid = u.getUid();
            User info = userFeign.getInfo(uid);
            //获取队长信息
            User info1 = userFeign.getInfo(cid);
            String cname = info1.getName();
            //封装
            TournumberVo tournumberVo=new TournumberVo();
            tournumberVo.setCid(cid);
            tournumberVo.setCname(cname);
            tournumberVo.setId(u.getId());
            tournumberVo.setIssue(tour1.getIssue());
            tournumberVo.setLocation(tour1.getLocation());
            tournumberVo.setMoney(u.getMoney());
            tournumberVo.setTid(u.getTid());
            tournumberVo.setTime(tour1.getTime());
            tournumberVo.setUid(u.getUid());
            tournumberVo.setUname(info.getName());
            tournumberVo.setElsemoney(u.getElsemoney());
            returnList.add(tournumberVo);
        }
        return returnList;
    }

    @GetMapping(value = "/{id}")
    public Tournumber getInfo(@PathVariable("id") Integer id)
    {
        List<Tournumber> list = tournumberService.list();
        Tournumber user1=new Tournumber();
        for (Tournumber u:list) {
            if(u.getId()==id){
                user1=u;
            }
        }
        return user1;
    }


    @GetMapping(value = "mytournumber/{ccid}")
    public List<TournumberVo> getMyTourNumber(@PathVariable("ccid") Integer ccid)
    {
        List<TournumberVo> returnList=new ArrayList<>();
        List<Tournumber> list = tournumberService.list();
        List<Tournumber> list1=new ArrayList<>();
        for (Tournumber u:list) {
            if (u.getTid()==ccid){
                list1.add(u);
            }
        }
        for (Tournumber u:list1) {
            //获取队伍信息
            Integer tid = u.getTid();
            List<Tour> listTour = tourService.list();
            Tour tour1=new Tour();
            for (Tour tour:listTour) {
                if(u.getTid()==tid){
                    tour1=tour;
                }
            }

            Integer cid = tour1.getCid();
            //获取用户信息
            Integer uid = u.getUid();
            User info = userFeign.getInfo(uid);
            //获取队长信息
            User info1 = userFeign.getInfo(cid);
            String cname = info1.getName();
            //封装
            TournumberVo tournumberVo=new TournumberVo();
            tournumberVo.setCid(cid);
            tournumberVo.setCname(cname);
            tournumberVo.setId(u.getId());
            tournumberVo.setIssue(tour1.getIssue());
            tournumberVo.setLocation(tour1.getLocation());
            tournumberVo.setMoney(u.getMoney());
            tournumberVo.setTid(u.getTid());
            tournumberVo.setTime(tour1.getTime());
            tournumberVo.setUid(u.getUid());
            tournumberVo.setUname(info.getName());
            tournumberVo.setNeedmoney(tour1.getMoney());
            tournumberVo.setElsemoney(u.getElsemoney());
            returnList.add(tournumberVo);
        }
        return returnList;
    }


    @PostMapping
    public Tournumber add(@RequestBody Tournumber user)
    {
        Tournumber tournumber=new Tournumber();
        List<Tournumber> list = tournumberService.list();
        boolean contains = false;
        for (Tournumber u:list) {
            if(u.getTid()==user.getTid()){
                if (u.getUid()==user.getUid()){
                    contains=true;
                }
            }
        }
        if (!contains)
        {
            boolean save = tournumberService.save(user);

            //添加事件
            Tourthing tourthing=new Tourthing();
            tourthing.setMoney(user.getMoney());
            tourthing.setTid(user.getTid());

            tourthing.setUid(user.getUid());
            tourthing.setRemark(user.getUid()+"加入团队活动");
            boolean save2 = tourthingService.save(tourthing);
            if(save||save2){
                return user;
            }
        }
        else {
            tournumber.setId(-1);
        }
        return tournumber;
    }

    @PutMapping
    public Boolean edit(@RequestBody Tournumber user)
    {
        int a1=user.getMoney();
        int b1=user.getElsemoney();
        Integer id = user.getId();
        Tournumber byId = tournumberService.getById(id);
        Integer money = byId.getMoney();
        int a=money+user.getMoney();
        user.setMoney(a);
        Integer elsemoney = byId.getElsemoney();
        int b=elsemoney+user.getElsemoney();
        user.setElsemoney(b);

        //添加事件
        Tourthing tourthing=new Tourthing();
        tourthing.setMoney(a1+b1);
        tourthing.setTid(user.getTid());
        tourthing.setUid(user.getUid());
        tourthing.setRemark("缴纳团费"+a1+";个人追加"+b1);
        boolean save2 = tourthingService.save(tourthing);

        return tournumberService.updateById(user);
    }

    @DeleteMapping("/{ids}")
    public Boolean remove(@PathVariable Integer ids)
    {
        return tournumberService.removeById(ids);
    }
}
