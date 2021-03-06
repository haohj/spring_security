package com.hao.security.web.controller;

import com.hao.security.entity.FileInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.io.IoUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/file")
public class FileController {
    private String folder = "D:\\MyCode\\spring_security\\hao-security-demo\\src\\main\\java\\com\\hao\\security\\web\\controller";
    @PostMapping
    @ApiOperation(value = "文件上传接口", notes = "访问此接口可以实现文件上传")
    @ApiImplicitParam(name = "file", value = "使用MultipartFile的实例对象来接收文件数据", required = true, dataTypeClass = MultipartFile.class)
    public FileInfo upload(MultipartFile file) throws Exception {
        System.out.println("上传文件的表单name值为：" + file.getName());
        System.out.println("文件路径为：" + file.getOriginalFilename());
        System.out.println("文件大小为：" + file.getSize());
        File localFile = new File(folder, System.currentTimeMillis() + ".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(folder);
        try (
                // 这是JDK7的特性，关于流的操作，可以写在try后面的圆括号里，这样就无需手动关闭流
                InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
                OutputStream outputStream = response.getOutputStream()
        ) {
            // 设置下载的文件类型
            response.setContentType("application/x-download");
            // 设置下载后的文件名
            response.setHeader("Content-Disposition", "attachment;filename=test.txt");
            IoUtil.copy(inputStream, outputStream);
            // 刷新输出流
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
