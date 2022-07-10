package com.aa.controller;

import com.aa.bean.Tour;
import com.aa.bean.Tourthing;
import com.aa.bean.vo.TourVo;
import com.aa.bean.vo.TournumberVo;
import com.aa.bean.vo.User;
import com.aa.feign.UserFeign;
import com.aa.service.TourService;
import com.aa.service.TourthingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tourthing")
@CrossOrigin(origins = "*")
public class TourthingController {
    @Autowired
    TourthingService tourthingService;

    @Autowired
    TourService tourService;

    @Autowired
    UserFeign userFeign;


    @GetMapping("/list")
    public List<TournumberVo> list()
    {
        List<TournumberVo> returnList=new ArrayList<>();
        List<Tourthing> list = tourthingService.list();
        for (Tourthing u:list) {
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
            tournumberVo.setRemark(u.getRemark());
            returnList.add(tournumberVo);

        }
        return returnList;
    }

    @GetMapping(value = "/{id}")
    public Tourthing getInfo(@PathVariable("id") Integer id)
    {
        List<Tourthing> list = tourthingService.list();
        Tourthing user1=new Tourthing();
        for (Tourthing u:list) {
            if(u.getId()==id){
                user1=u;
            }
        }
        return user1;
    }

    @GetMapping(value = "mytourthing/{ccid}")
    public List<TournumberVo> getMyTourThing(@PathVariable("ccid") Integer ccid)
    {
        List<TournumberVo> returnList=new ArrayList<>();
        List<Tourthing> list = tourthingService.list();
        List<Tourthing> list1=new ArrayList();
        for (Tourthing u:list) {
            if (u.getTid()==ccid){
                list1.add(u);
            }
        }
        for (Tourthing u:list1) {
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
            tournumberVo.setRemark(u.getRemark());
            returnList.add(tournumberVo);

        }
        return returnList;
    }


    @PostMapping
    public Tourthing add(@RequestBody Tourthing user)
    {
        boolean save = tourthingService.save(user);
        if(save){
            return user;
        }
        return null;
    }

    @PutMapping
    public Boolean edit(@RequestBody Tourthing user)
    {
        return tourthingService.updateById(user);
    }

    @DeleteMapping("/{ids}")
    public Boolean remove(@PathVariable Integer ids)
    {
        return tourthingService.removeById(ids);
    }
}
