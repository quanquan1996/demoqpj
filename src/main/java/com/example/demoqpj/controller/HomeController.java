package com.example.demoqpj.controller;

import com.example.demoqpj.entity.BookRepository;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    BookRepository bookRepository;
    @RequestMapping("/home")
    public String hello(Model model) {
        model.addAttribute("error", "test error");
        Region region = Region.EU_NORTH_1;
        S3Client s3 = S3Client.builder()
                .region(region)
                .build();
        String bucketsInfo = listBuckets(s3);
        model.addAttribute("bucketsInfo", bucketsInfo);
        model.addAttribute("bookList", bookRepository.findAll());
        return "hello";
    }

    public static String listBuckets(S3Client s3) {
        try {
            ListBucketsResponse response = s3.listBuckets();
            List<Bucket> bucketList = response.buckets();
            StringBuilder sb = new StringBuilder();
            bucketList.forEach(bucket -> {
                sb.append("Bucket Name: " + bucket.name());
            });
            return sb.toString();
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            return "get bucket error";
        }
    }
    public static void main(String[] args) {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        /* 配置文件中配置如下的算法 */
        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        /* 配置文件中配置的password */
        standardPBEStringEncryptor.setPassword("quanquanyiyi");
        // 加密
        String jasyptPasswordEN = standardPBEStringEncryptor.encrypt("[_(fS?#nnWSNElmtLyo9]%{7#wKS");
        // 解密
        String jasyptPasswordDE =standardPBEStringEncryptor.decrypt(jasyptPasswordEN);
        System.out.println("加密后密码：" + jasyptPasswordEN);
        System.out.println("解密后密码：" + jasyptPasswordDE);
    }
}
