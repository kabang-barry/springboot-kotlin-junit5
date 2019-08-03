package com.jeonguk.web.domain.dto.converter

interface IDtoConverter<T, U> {
    fun convert(entity: T): U
}
