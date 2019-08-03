package com.jeonguk.web.controller

import com.jeonguk.web.domain.dto.Hello
import com.jeonguk.web.service.HelloService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class HelloController(
        @Autowired private val helloService: HelloService
) {

    @GetMapping("/hello/{name}")
    fun greeting(@PathVariable("name") name: String): Hello = helloService.getHelloBody(1L, name)

    @GetMapping("/name/{name}")
    fun hello(@PathVariable("name") name: String) = helloService.getHello(name)

}