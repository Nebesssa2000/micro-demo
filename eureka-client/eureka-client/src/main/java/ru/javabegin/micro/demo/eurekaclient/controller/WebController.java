package ru.javabegin.micro.demo.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class WebController {

    @Value("${eureka.instance.instance-id}")
    private String id;
//
//    @Value("${tmpVar}")
//    private int tmpVar;

    @GetMapping("/web")
    public String web(){
        return "webAPI" + id;
    }
}
