package com.jeonguk.web.service

import com.jeonguk.web.domain.entity.Author
import com.jeonguk.web.repository.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorService {

    @Autowired
    private lateinit var authorRepository: AuthorRepository

    fun getAuthor(id: Long): Author {
        return authorRepository.findById(id).map { it }.orElse(null)
    }

    fun getAuthors(): List<Author> = authorRepository.findAll()

}