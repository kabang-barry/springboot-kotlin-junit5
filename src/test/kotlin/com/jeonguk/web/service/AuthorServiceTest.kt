package com.jeonguk.web.service

import com.jeonguk.web.domain.entity.Author
import com.jeonguk.web.repository.AuthorRepository
import com.nhaarman.mockito_kotlin.given
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class AuthorServiceTest {

    @InjectMocks
    private lateinit var authorService: AuthorService

    @Mock
    private lateinit var authorRepository: AuthorRepository

    @Test
    fun `모든 author를 가져오는 서비스 메소드 테스트`() {

        // given
        val author1 = Author()
        author1.id = 1L
        author1.name = "jeonguk"

        val author2 = Author()
        author2.id = 2L
        author2.name = "barry"

        given(authorRepository.findAll()).willReturn(listOf(author1, author2))

        // when
        val authors = authorService.getAuthors()

        // then
        assertEquals(2, authors.size)
        assertEquals(1L, authors[0].id)

    }

}