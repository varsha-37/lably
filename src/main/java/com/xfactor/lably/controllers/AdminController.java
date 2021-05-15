package com.xfactor.lably.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.xfactor.lably.entity.Admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/admin")
public class AdminController {
    
    ArrayList<Admin> admins = new ArrayList<>();
    //localhost.com/admin
    @GetMapping()
    public String hello_world() {
        return "Hello world!!!";
    }
    //localhost.com/admin/hello
    @GetMapping("/hello")
    public String hello() {
        return "Hello from AdminController!!!";
    }
    //localhost.com/admin/greet/Varsha/Poddar
    @GetMapping("/greet/{text}/{text2}")
    public String greet(@PathVariable String text, @PathVariable String text2) {
        return "Greetings from " + text + " "+text2;
    }
    //localhost.com/admin/greet2?name=Varsha&age=21&city=Muz
    @GetMapping("/greet2")
    public String greet2(@RequestParam String name, @RequestParam String age, @RequestParam String city) {
        return "Greetings from " + " " + name + " " + age + " " + city;
    }
    //localhost.com/admin/greet3?name=Varsha&age=21&city=Muz
    @GetMapping("/greet3")
    public HashMap<String, String> greet3(@RequestParam String name, @RequestParam String age,
            @RequestParam String city) {
        HashMap<String, String> resp = new HashMap<>();
        resp.put("name", name);
        resp.put("age", age);
        resp.put("city", city);
        return resp;
    }

    // @GetMapping("/register")
    // public HashMap<String, String> register(@RequestParam String name, @RequestParam String username,
    //         @RequestParam String password ,@RequestParam String department) {
    //     HashMap<String, String> resp = new HashMap<>();
    //     resp.put("name", name);
    //     resp.put("department", department);
    //     resp.put("username", username);
    //     resp.put("password", password);
    //     return resp;
    // }
    @GetMapping("/register")
    public Admin register(@RequestParam String name, @RequestParam String username,
        @RequestParam String password ,@RequestParam String department) {
        Admin admin1 = new Admin();
        admin1.setName(name);
        admin1.setDepartment(department);
        admin1.setPassword(password);
        admin1.setUsername(username);
        return admin1;
    }
    // post API
    @PostMapping("/registerAdmin")
    public Admin registerPost(@RequestBody Admin admin) {
        admin.setId(UUID.randomUUID().toString());
        //save admin to database
        admins.add(admin);
        return admin;
    }

    @GetMapping("/getAllAdmins")
    public ArrayList<Admin> getAllAdmins(){
	    return admins;
    }

    @GetMapping("/getAdminByUserName")
    public Admin getAdminByUserName(@RequestParam String username)
    {
        Admin resAdmin = null;
        for(Admin admin: admins)
        {
            if(admin.getUsername().equalsIgnoreCase(username)){
                resAdmin=admin;
            }
        }
        return resAdmin;
    }
}