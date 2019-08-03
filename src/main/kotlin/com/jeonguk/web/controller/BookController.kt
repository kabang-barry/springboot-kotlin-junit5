package com.jeonguk.web.controller

import com.jeonguk.web.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class BookController {

    @Autowired
    private lateinit var bookService: BookService

    @GetMapping("/books/{id}")
    fun getBook(@PathVariable("id") id: Long) = bookService.getBook(id)

}