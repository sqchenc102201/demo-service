package com.it.demo.controller.test;

import com.it.demo.service.TestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;


    @GetMapping("/hello")
    @ApiOperation("访问测试")
    public String hello() {
        return "hello test world!";
    }

    @PostMapping("upload")
    @ApiOperation("文件上传")
    public String uploadFile(@RequestBody MultipartFile file) throws IOException {
        testService.uploadFile(file);
        return "success";
    }


}
