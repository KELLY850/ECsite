package com.kadaisite.ECsite.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageMapping {
    @GetMapping("/")
    public  String top(){
        return  "/users/index";
    }
}
