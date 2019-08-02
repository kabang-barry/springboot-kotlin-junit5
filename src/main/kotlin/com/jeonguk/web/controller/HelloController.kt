package com.jeonguk.web.controller

import com.jeonguk.web.domain.dto.Hello
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
@RequestMapping("/api/hello")
class HelloController {

    val counter = AtomicLong()

    @GetMapping("/{name}")
    fun greeting(@PathVariable("name") name: String): Hello = Hello(counter.incrementAndGet(), "Hello, $name")

}