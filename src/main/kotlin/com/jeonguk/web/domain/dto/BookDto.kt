package com.jeonguk.web.domain.dto

import java.time.LocalDate

data class BookDto(
        var id: Long? = null,
        var title: String? = null,
        var publication: LocalDate? = null,
        var authorId: Long? = null,
        var authorName: String? = null
)