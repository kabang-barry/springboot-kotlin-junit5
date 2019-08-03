package com.jeonguk.web.controller

import com.jeonguk.web.domain.dto.Hello
import com.jeonguk.web.service.HelloService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HelloControllerMockitoTest(
        @Autowired private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var helloService: HelloService

//    @BeforeEach
//    fun setMockOutput() {
//       given(helloService.getHello("jeonguk")).willReturn("Hello jeonguk")
//    }

    @Test
    fun `when Service Called then Should Return Hello With Parameter Name`() {
        given(helloService.getHello("jeonguk")).willReturn("Hello jeonguk")
        assertEquals("Hello jeonguk", helloService.getHello("jeonguk"))
    }

    @Test
    fun `when Called then Should Return Hello With Parameter Name`() {
        val body = "Hello jeonguk"

        mockMvc.perform(MockMvcRequestBuilders.get("/api/name/jeonguk")
                .contentType(MediaType.TEXT_PLAIN)
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(MockMvcResultHandlers.print())
    }

    /**
     * BDD Style
     */
    @Test
    fun `when Called then Should Return Hello With Hello Object`() {
        val body = Hello(1L, "jeonguk")

        // given
        //doReturn(Hello(1L, "jeonguk")).`when`(helloService).getHelloBody(1L, "jeonguk")
        given(helloService.getHelloBody(1L, "jeonguk")).willReturn(body)

        // when
        val actions = mockMvc.perform(MockMvcRequestBuilders.get("/api/hello/jeonguk")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
        // then
        actions
                .andExpect(status().isOk)
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("name").value("jeonguk"))
    }

}