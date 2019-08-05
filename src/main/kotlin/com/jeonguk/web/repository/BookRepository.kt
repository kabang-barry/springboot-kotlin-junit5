package com.jeonguk.web.repository

import com.jeonguk.web.domain.entity.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long> {
    fun findByAuthorId(id: Long): List<Book>
}