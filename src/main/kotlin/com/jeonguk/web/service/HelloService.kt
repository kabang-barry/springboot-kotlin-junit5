package com.jeonguk.web.service

import com.jeonguk.web.domain.dto.Hello
import org.springframework.stereotype.Service

@Service
class HelloService {

    fun getHelloBody(id: Long, name: String): Hello {
        return Hello(id, name)
    }

    fun getHello(name: String): String {
        return "Hello $name"
    }

}