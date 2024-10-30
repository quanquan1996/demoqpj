package com.example.demoqpj.entity;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author qiupengjun
 * @Date 2022 08 12 17 00
 **/
public interface BookRepository extends JpaRepository<Book, Long> {
}
