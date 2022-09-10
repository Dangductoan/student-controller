package com.cybersoft.demoSpring.controller;

import com.cybersoft.demoSpring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private List<User> list = new ArrayList<>();
    public List<User> addUser(@RequestParam("fullname") String fullname,
                              @RequestParam("age") int age,
                              @RequestParam("gender") boolean gender) {
        User user = new User();
        user.setFullname(fullname);
        user.setAge(age);
        user.setGender(gender);

        list.add(user);

        return list;

    }
    //seo,lam dep link ,
    @GetMapping("/{id}/{username}/detail-page")
    public User getDetailUser(@PathVariable("id") int id ,@PathVariable("username") String username) {
        User user = new User();
        System.out.println("User id" + id + "username" + username);
        return user;
    }
    //?tenthamso=giatricuathamgso
    @GetMapping("/detail-page")
    public User getDetail(@RequestParam("id") int id,@RequestParam("username") String username) {
        User user = new User();
        System.out.println("User id" + id + "username" + username);
        return user;
    }
   @PostMapping(value = "/add-user"
           ,consumes = "application/x-www-form-urlencoded"
           ,produces = "application/json")
    public User addUser(@RequestParam("id") int id,@RequestParam("username") String username) {
        User user = new User();
        System.out.println("User id" + id + "username" + username);
        return user;
    }
    @PostMapping("/add-user-file")
    public User addUserFile(
            @RequestParam("file")MultipartFile file,
            @RequestParam("id") int id,
            @RequestParam("username") String username
            ) {
        User user = new User();
        System.out.println("User id" + id + "username" + username + file);
        return user;

    }
    @PostMapping("/add-json")
    public ResponseEntity<User> addUserJson(
           @RequestBody User user
    ) {

        System.out.println("User" + user.getFullname());
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

}
