package com.aa.controller;

import com.aa.bean.Tour;
import com.aa.bean.Tournumber;
import com.aa.bean.Tourthing;
import com.aa.bean.vo.TourVo;
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
@RequestMapping("/tour")
@CrossOrigin(origins = "*")
public class TourController {

    @Autowired
    TourService tourService;

    @Autowired
    TournumberService tournumberService;

    @Autowired
    TourthingService tourthingService;

    @Autowired
    UserFeign userFeign;

    @GetMapping("/list")
    public List<TourVo> list()
    {
        List<TourVo> returnList=new ArrayList<>();
        List<Tour> list = tourService.list();
        for (Tour u:list) {
            Integer cid = u.getCid();
            User info = userFeign.getInfo(cid);
            String name = info.getName();
            TourVo tourVo=new TourVo();
            tourVo.setId(u.getId());
            tourVo.setCid(u.getCid());
            tourVo.setName(name);
            tourVo.setFtime(u.getFtime());
            tourVo.setIssue(u.getIssue());
            tourVo.setLocation(u.getLocation());
            tourVo.setMoney(u.getMoney());
            tourVo.setTime(u.getTime());
            returnList.add(tourVo);
        }
        return returnList;
    }

    @GetMapping(value = "/{id}")
    public Tour getInfo(@PathVariable("id") Integer id)
    {
        List<Tour> list = tourService.list();
        Tour user1=new Tour();
        for (Tour u:list) {
            if(u.getId()==id){
                user1=u;
            }
        }
        return user1;
    }

    @GetMapping(value = "mytour/{ccid}")
    public List<TourVo> getMyTour(@PathVariable("ccid") Integer ccid)
    {
        List<TourVo> returnList=new ArrayList<>();
        List<Tour> list = tourService.list();
        List<Tour> list1 = new ArrayList<>();
        for (Tour u:list) {
            if(u.getCid()==ccid){
                list1.add(u);
            }
        }
        for (Tour u:list1) {
            Integer cid = u.getCid();
            User info = userFeign.getInfo(cid);
            String name = info.getName();
            TourVo tourVo=new TourVo();
            tourVo.setId(u.getId());
            tourVo.setCid(u.getCid());
            tourVo.setName(name);
            tourVo.setFtime(u.getFtime());
            tourVo.setIssue(u.getIssue());
            tourVo.setLocation(u.getLocation());
            tourVo.setMoney(u.getMoney());
            tourVo.setTime(u.getTime());
            returnList.add(tourVo);
        }
        return returnList;
    }


    @PostMapping
    public Tour add(@RequestBody Tour user)
    {
        //添加团队活动
        boolean save = tourService.save(user);
        //添加队长
        List<Tour> list = tourService.list();
        Tour tour = list.get(list.size() - 1);
        Tournumber tournumber=new Tournumber();
        tournumber.setTid(tour.getId());
        tournumber.setUid(tour.getCid());
        tournumber.setMoney(0);
        boolean save1 = tournumberService.save(tournumber);
        //添加事件
        Tourthing tourthing=new Tourthing();
        tourthing.setMoney(user.getMoney());
        tourthing.setTid(tour.getId());
        tourthing.setUid(tour.getCid());
        tourthing.setRemark("创建团队活动");
        boolean save2 = tourthingService.save(tourthing);

        if(save||save1||save2){
            return user;
        }
        return null;
    }

    @PutMapping
    public Boolean edit(@RequestBody Tour user)
    {
        Tourthing tourthing=new Tourthing();
        tourthing.setMoney(user.getMoney());
        tourthing.setTid(user.getId());
        tourthing.setUid(user.getCid());
        tourthing.setRemark("修改团费为："+user.getMoney());
        tourthingService.save(tourthing);
        return tourService.updateById(user);
    }

    @DeleteMapping("/{ids}")
    public Boolean remove(@PathVariable Integer ids)
    {
        Tour byId = tourService.getById(ids);
        //添加事件
        Tourthing tourthing=new Tourthing();
        tourthing.setTid(ids);
        tourthing.setUid(byId.getCid());
        tourthing.setRemark("删除团队活动");
        boolean save1 = tourthingService.save(tourthing);
        boolean b = tourService.removeById(ids);
        if(save1||b){
            return true;
        }
        return false;
    }
}
