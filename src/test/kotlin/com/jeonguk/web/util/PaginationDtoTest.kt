package com.jeonguk.web.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class PaginationDtoTest {

    @Test
    fun testToPaginateWithPageZero() {
        val pagination = PaginationDto(page = 0, size = 2, sort = null)
        val pageable = pagination.toPageable()

        assertEquals(0, pageable.pageNumber)
    }

    @Test
    fun testToPaginateWithSizeZero() {
        val pagination = PaginationDto(page = 1, size = 0, sort = null)
        val pageable = pagination.toPageable()

        assertEquals(10, pageable.pageSize)
    }

    @Test
    fun testToPaginateWithMaxSize() {
        val pagination = PaginationDto(page = 1, size = 30, sort = null)
        val pageable = pagination.toPageable(20)

        assertEquals(20, pageable.pageSize)
    }

}