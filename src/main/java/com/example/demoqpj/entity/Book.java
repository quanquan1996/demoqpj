package com.example.demoqpj.entity;

import lombok.Data;

import jakarta.persistence.*;

/**
 * @Author qiupengjun
 * @Date 2023 06 08 15 44
 **/
@Data
@Entity
@Table(name = "book", schema = "demo", catalog = "")
public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public long id;
    @Basic
    @Column(name = "name")
    public String name;
    @Basic
    @Column(name = "author")
    public String author;
    @Basic
    @Column(name = "detail")
    public String detail;

}
