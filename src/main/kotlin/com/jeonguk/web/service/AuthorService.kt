package com.jeonguk.web.service

import com.jeonguk.web.domain.entity.Book
import com.jeonguk.web.repository.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorService {

    @Autowired
    private lateinit var authorRepository: AuthorRepository

    fun getAuthorBooks(id: Long): List<Book> {
        val author = authorRepository.findById(id)
        return author.get().books
    }

}