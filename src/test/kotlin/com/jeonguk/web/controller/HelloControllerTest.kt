package com.jeonguk.web.controller

import com.jeonguk.web.Application
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest(classes = [Application::class])
@AutoConfigureMockMvc
class HelloControllerTest(
        @Autowired private val mockMvc: MockMvc
) {

    @Test
    fun `api hello GET should returns 200`() {
        mockMvc.perform(
                get("/api/hello/jeonguk")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(print())
    }

}