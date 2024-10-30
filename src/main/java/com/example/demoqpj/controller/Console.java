package com.example.demoqpj.controller;

import com.example.demoqpj.entity.Book;
import com.example.demoqpj.entity.BookRepository;
import com.example.demoqpj.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Console {
    @Autowired
    BookRepository bookRepository;
    public void init(){
        Book book;
        for(int i=0;i<5;i++){
            book = new Book();
            book.name = "book name "+i;
            book.author = RandomUtil.getRandomString(10);
            book.detail = RandomUtil.getRandomString(10)+" "+RandomUtil.getRandomString(10)+" "+RandomUtil.getRandomString(10);
            bookRepository.save(book);
        }
    }
}
