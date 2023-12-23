package com.example.upload_download.up.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/a")
public class Controller2 {

    @GetMapping("b")
    public String b() {
        return "哈哈哈哈";
    }
}
