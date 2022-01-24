package com.jeonguk.web.controller

import com.jeonguk.web.Application
import com.jeonguk.web.domain.dto.Hello
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [Application::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerRestTemplateTest(
    @Autowired private val testRestTemplate: TestRestTemplate
) {

    @Test
    fun `when Called then Should Return Hello With Name`() {
        val result = testRestTemplate.getForEntity("/api/hello/jeonguk", Hello::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
        assertEquals(result?.body, Hello(1L, "Hello jeonguk"))
    }

    @Test
    fun `when Called then Should Return Hello With Parameter Name`() {
        val result = testRestTemplate.getForEntity("/api/name/jeonguk", String::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
        assertEquals(result?.body, "Hello jeonguk")
    }

}