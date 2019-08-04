package com.jeonguk.web.config

import com.jeonguk.web.domain.entity.Author
import com.jeonguk.web.domain.entity.Book
import com.jeonguk.web.repository.AuthorRepository
import com.jeonguk.web.repository.BookRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate

@Configuration
class DataInit {

    @Bean
    fun init(authorRepository: AuthorRepository, bookRepository: BookRepository) = CommandLineRunner {
        val stephenKing = Author(name = "Barry")
        authorRepository.save(stephenKing)

        val robinHobb = Author(name = "Allen")
        authorRepository.save(robinHobb)

        bookRepository.save(Book(
                title = "JAVA 8",
                publication = LocalDate.parse("2019-08-01"),
                author = stephenKing
        ))
        bookRepository.save(Book(
                title = "Kotlin",
                publication = LocalDate.parse("2019-08-02"),
                author = stephenKing
        ))

        bookRepository.save(Book(
                title = "L'assassin Royal",
                publication = LocalDate.parse("1998-12-17"),
                author = robinHobb
        ))
    }

}