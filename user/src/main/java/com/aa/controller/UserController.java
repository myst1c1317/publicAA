package com.aa.controller;

import com.aa.bean.User;
import com.aa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("login")
    public User login(@RequestBody User user){
        System.out.println(user);
        List<User> list = userService.list();
        User user1=new User();
        for (User u:list) {
            String name = u.getName();
            String password = u.getPassword();
            Integer id = u.getId();
            System.out.println(user.getPassword());
            System.out.println(password);
            System.out.println(user.getPassword().equals(password));
            if(user.getName().equals(name)&&user.getPassword().equals(password)){
                System.out.println("2312");
                user1.setId(id);
                user1.setName(name);
                user1.setPassword(password);
                break;
            }
            else {
                user1.setId(-1);
            }
        }
        return user1;
    }


    @GetMapping("/list")
    public List<User> list()
    {
        List<User> list = userService.list();
        return list;
    }

    @GetMapping(value = "/{id}")
    public User getInfo(@PathVariable("id") Integer id)
    {
        List<User> list = userService.list();
        User user1=new User();
        for (User u:list) {
            if(u.getId()==id){
                user1=u;
            }
        }
        return user1;
    }

    @PostMapping
    public User add(@RequestBody User user)
    {
        boolean save = userService.save(user);
        if(save){
            return user;
        }
        return null;
    }

    @PutMapping
    public Boolean edit(@RequestBody User user)
    {
        return userService.updateById(user);
    }

    @DeleteMapping("/{ids}")
    public Boolean remove(@PathVariable Integer ids)
    {
        return userService.removeById(ids);
    }



}
