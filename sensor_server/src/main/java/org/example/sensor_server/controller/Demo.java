package org.example.sensor_server.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class Demo {
    @GetMapping("")
    public Map<String, String> send(){
        Map<String, String> map= new HashMap<>();
        map.put("hello", "hello");
        return map;

    }
}
