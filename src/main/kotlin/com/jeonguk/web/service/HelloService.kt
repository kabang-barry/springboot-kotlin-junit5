package com.jeonguk.web.service

import org.springframework.stereotype.Service

@Service
class HelloService {

    fun getHello(name: String): String {
        return "Hello $name"
    }

}