package com.jeonguk.web.controller

import com.jeonguk.web.domain.dto.BookDto
import com.jeonguk.web.service.BookService
import com.jeonguk.web.util.PageDto
import com.jeonguk.web.util.PaginationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class BookController {

    @Autowired
    private lateinit var bookService: BookService

    @GetMapping("/books/{id}")
    fun getBook(@PathVariable("id") id: Long) = bookService.getBook(id)

    @GetMapping("/books")
    fun getBooks(@Valid pagination: PaginationDto): PageDto<BookDto> = bookService.getBooks(pagination)

    @GetMapping("/author/books/{id}")
    fun getAuthorBooks(@PathVariable("id") id: Long) = bookService.getAuthorBooks(id)

    @PostMapping("/books")
    fun createBook(dto: BookDto): Long? = bookService.createBook(dto)

    @PostMapping("/books/{id}")
    fun updateBook(@PathVariable("id") id: Long, dto: BookDto) = bookService.updateBook(id, dto)

    @DeleteMapping("/books/{id}")
    fun deleteBook(@PathVariable("id") id: Long) = bookService.deleteBook(id)

}