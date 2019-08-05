package com.jeonguk.web.service

import com.jeonguk.web.repository.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorService {

    @Autowired
    private lateinit var authorRepository: AuthorRepository

    fun getAuthor(id: Long) = authorRepository.findById(id)

}