package com.chenyang.ducumentmanagement.controller;

import com.chenyang.ducumentmanagement.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        //把文件的内容存储到本地磁盘上
        String originalFilename = file.getOriginalFilename();
        //确保filename唯一，防止文件覆盖
        String fileName = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File("/Users/zhaochenyang/Desktop/files/"+fileName));
        return Result.success("url");
    }

}
