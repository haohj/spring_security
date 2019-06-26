package com.hao.security.web.controller;

import com.hao.security.entity.FileInfo;
import org.apache.commons.io.IOUtils;
import org.omg.CORBA.portable.OutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController("/file")
public class FileController {
    private String folder = new Object() {
        public String getPath() {
            return this.getClass().getClassLoader().getResource("").getPath();
        }
    }.getPath();
    @PostMapping
    public FileInfo upload(MultipartFile file) throws Exception {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getOriginalFilename());
        File localFile = new File(folder, System.currentTimeMillis() + ".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void donwload(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
             ServletOutputStream outputStream = response.getOutputStream();
        ) {
            response.setHeader("Content-Disposition", "attachment;filename=test.txt");
            response.setContentType("application/x-download");

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }
}
