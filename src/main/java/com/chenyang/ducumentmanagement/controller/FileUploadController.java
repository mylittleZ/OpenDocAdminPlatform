package com.chenyang.ducumentmanagement.controller;

import com.chenyang.ducumentmanagement.pojo.Result;
import com.chenyang.ducumentmanagement.s3.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@RestController
public class FileUploadController {

    private final S3Service s3Service;

    @Autowired
    public FileUploadController(S3Service s3Service) {
        this.s3Service = s3Service;
    }
    @Value("${aws.s3.buckets.name}")
    private String bucketName;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();
        // 确保文件名唯一，防止文件覆盖
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 将MultipartFile转换为字节流
        byte[] fileContent = file.getBytes();

        // 上传到S3
        s3Service.putObject(bucketName, fileName, fileContent);

        // 构建文件在S3中的URL（确保bucket的权限是公开的）
        String fileUrl = "https://"+ bucketName + ".s3.amazonaws.com/" + fileName;
        return Result.success(fileUrl);
    }

}
