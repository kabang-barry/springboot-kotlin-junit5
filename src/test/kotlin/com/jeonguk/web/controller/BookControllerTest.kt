package com.jeonguk.web.controller

import com.jeonguk.web.Application
import com.jeonguk.web.domain.dto.BookDto
import com.jeonguk.web.domain.dto.converter.BookDtoConverter
import com.jeonguk.web.domain.entity.Author
import com.jeonguk.web.domain.entity.Book
import com.jeonguk.web.service.BookService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.`when`
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [Application::class])
@AutoConfigureMockMvc
class BookControllerTest {

    private val log = LoggerFactory.getLogger(BookControllerTest::class.java)

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var bookService: BookService

    @BeforeEach
    fun printApplicationContext() {
        log.info("beanDefinitionNames print start ===========================")
        Arrays.stream(applicationContext.beanDefinitionNames)
            .map { name -> applicationContext.getBean(name).javaClass.name }
            .sorted()
            .forEach { log.info(it) }
        log.info("beanDefinitionNames print end ==============================")
    }

    @Test
    fun `api hello GET should returns 200`() {
        mockMvc.perform(
            get("/api/books/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
            .andDo(print())
    }

    @Test
    fun `when Called then Should Return Book`() {
        // Geven
        `when`(bookService.getBook(eq(1L)))
            .thenReturn(expectedBook())

        // when
        val actions = mockMvc.perform(
            get("/api/books/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
            .andDo(print())

        // then
        actions
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("id").value(1L))
            .andExpect(MockMvcResultMatchers.jsonPath("title").value("Kotlin"))
    }

    fun expectedBook(): BookDto {
        val author = Author(id = 1L, name = "jeonguk")
        val book = Book(
            id = 1L,
            title = "Kotlin",
            publication = LocalDate.parse("2019-08-05"),
            author = author
        )
        return BookDtoConverter.convert(book)
    }

}