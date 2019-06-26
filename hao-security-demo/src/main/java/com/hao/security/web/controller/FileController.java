package com.hao.security.web.controller;

import com.hao.security.entity.FileInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("/file")
public class FileController {

    @PostMapping
    public FileInfo upload(MultipartFile file) {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getOriginalFilename());
        return null;
    }
}
