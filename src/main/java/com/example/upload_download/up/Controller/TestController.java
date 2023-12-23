package com.example.upload_download.up.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file/tranfer")
public class TestController {

    @Value("${BaseUrl}")
    private String uploadDir;

    @ResponseBody
    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file")MultipartFile file) throws IOException {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        List<String> extList = Arrays.asList(".jpg", ".png", ".jpeg", ".gif");
        if (!extList.contains(suffixName)) {
            return "图片格式非法";
        }
        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = UUID.randomUUID().toString().replace("-", "") + suffixName;
        // 返回客户端 文件地址 URL
        String url = "localhost:8080"+"/upload/" + fileName;
        File dest = new File( uploadDir + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        file.transferTo(dest);
        return url;

    }
}
