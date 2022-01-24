package com.jeonguk.web.service

import com.jeonguk.web.domain.dto.BookDto
import com.jeonguk.web.domain.entity.Author
import com.jeonguk.web.domain.entity.Book
import com.jeonguk.web.exception.ResourceNotFoundException
import com.jeonguk.web.repository.AuthorRepository
import com.jeonguk.web.repository.BookRepository
import com.nhaarman.mockito_kotlin.any
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.hasItems
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.slf4j.LoggerFactory
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDate
import java.util.*

@ExtendWith(SpringExtension::class)
class BookServiceTest {

    private val log = LoggerFactory.getLogger(BookServiceTest::class.java)

    @Mock
    private lateinit var authorRepository: AuthorRepository

    @Mock
    private lateinit var bookRepository: BookRepository

    @InjectMocks
    private lateinit var bookService: BookService

    @Test
    fun `test Get Book`() {
        /* Given */
        val bookId = 1L
        `when`(bookRepository.findById(bookId)).thenReturn(Optional.of(Book(id = bookId)))

        /* When */
        val result = bookService.getBook(bookId)

        /* Then */
        assertEquals(bookId, result.id)
    }

    @Test
    fun `test Get Book When Book Is Not Found`() {
        /* Given */
        val bookId = 2L
        `when`(bookRepository.findById(bookId)).thenReturn(Optional.empty())

        /* When */
        //bookService.getBook(bookId)
        val thrown = assertThrows(ResourceNotFoundException::class.java) { bookService.getBook(bookId) }

        /* Then */
        assertThat(thrown.message, `is`("Book $bookId does not exist"))
    }


    @Test
    fun `test Get Author Books`() {
        /* Given */
        val author = Author(1L, "barry")

        val bookList = arrayListOf(
            Book(
                id = 1L,
                title = "Java 8",
                publication = LocalDate.parse("2019-08-01"),
                author = author
            ),
            Book(
                id = 2L,
                title = "Kotlin",
                publication = LocalDate.parse("2019-08-02"),
                author = author
            )
        )

        `when`(author.id?.let { bookRepository.findByAuthorId(it) }).thenReturn(bookList)

        /* When */
        val result = author.id?.let { bookService.getAuthorBooks(it) }

        /* Then */
        assertThat(
            result, hasItems(
                BookDto(1, "Java 8", publication = LocalDate.parse("2019-08-01"), authorId = 1, authorName = "barry"),
                BookDto(2, "Kotlin", publication = LocalDate.parse("2019-08-02"), authorId = 1, authorName = "barry")
            )
        )
    }

    @Test
    fun testCreateBook() {
        /* Given */
        val bookId = 2L
        val authorId = 5L

        `when`(bookRepository.save(any<Book>())).thenReturn(Book(id = bookId))
        `when`(authorRepository.findById(5)).thenReturn(Optional.of(Author(id = authorId)))

        /* When */
        val result = bookService.createBook(BookDto(authorId = authorId))

        /* Then */
        assertEquals(bookId, result)
    }

    @Test
    fun testUpdateBook() {
        /* Given */
        val bookId = 1L
        val book = Book(id = bookId)
        `when`(bookRepository.findById(bookId)).thenReturn(Optional.of(book))

        /* When */
        bookService.updateBook(bookId, BookDto())

        /* Then */
        verify(bookRepository).findById(bookId)
    }

}