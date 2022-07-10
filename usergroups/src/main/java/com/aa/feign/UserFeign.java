package com.aa.feign;

import com.aa.bean.vo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service",path = "/user")
public interface UserFeign {

    @GetMapping("/list")
    List<User> list();

    @GetMapping(value = "/{id}")
    User getInfo(@PathVariable("id") Integer id);

    @PostMapping
    User add(@RequestBody User user);

    @PutMapping
    Boolean edit(@RequestBody User user);

    @DeleteMapping("/{ids}")
    Boolean remove(@PathVariable Integer ids);
}
