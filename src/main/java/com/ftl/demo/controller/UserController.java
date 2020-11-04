package com.ftl.demo.controller;

import com.ftl.demo.pojo.User;
import com.ftl.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 普普通通controller
 */
@RestController
@RequestMapping("/jetcache")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PutMapping
    public User update(@RequestBody User user) {
        userService.update(user);
        return user;
    }

    @DeleteMapping
    public User delete(@RequestBody User user) {
        userService.delete(user.getId());
        return user;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/map")
    public Map<Long, User> getUserMap() {
        return userService.getUserMap();
    }
}
