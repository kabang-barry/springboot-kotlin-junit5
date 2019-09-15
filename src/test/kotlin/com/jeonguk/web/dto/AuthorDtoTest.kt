package com.jeonguk.web.dto

import com.jeonguk.web.domain.dto.AuthorDto
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class AuthorDtoTest {

    private val log = LoggerFactory.getLogger(AuthorDtoTest::class.java)

    @Test
    fun `Dto with test`() {
        val authorDto = getAuthor()
        authorDto.id = 1L
        authorDto.name = "jeonguk"

        /**
         * 수신 객체 람다 내부에서 수신 객체의 함수를 사용하지 않고
         * 수신 객체 자신을 다시 반환 하려는 경우에 apply 를 사용합니다.
         * 수신 객체 의 프로퍼티 만을 사용하는 대표적인 경우가 객체의 초기화 이며,
         * 이곳에 apply 를 사용합니다.
         */
        val authorDtoApply = AuthorDto().apply {
            id = 1L
            name = "jeonguk"
        }
        log.info("apply ===================")
        log.info("authorDto {}", authorDtoApply)

        /**
         * Non-nullable (Null 이 될수 없는) 수신 객체 이고
         * 결과가 필요하지 않은 경우에만 with 를 사용합니다.
         */
        with(authorDto) {
            log.info("with ===================")
            log.info("id {}",id)
            log.info("name {}", name)
        }

        /**
         * 수신 객체 람다가 전달된 수신 객체를 전혀 사용 하지 않거나
         * 수신 객체의 속성을 변경하지 않고 사용하는 경우 also 를 사용합니다.
         */
        val authorAlso = getAuthor().also {
            log.info("also ===================")
            log.info("id {}", it.id)
            log.info("name {}", it.name)
        }

        log.info("authorAlso id {}", authorAlso.id)
        log.info("authorAlso name {}", authorAlso.name)

        /**
         * 지정된 값이 null 이 아닌 경우에 코드를 실행해야 하는 경우.
         * Nullable 객체를 다른 Nullable 객체로 변환하는 경우.
         * 단일 지역 변수의 범위를 제한 하는 경우.
         */
        val nullableAuthorLet = getNullableAuthor()?.let {
            log.info("let ===================")
            // null 이 아닐때만 실행됩니다.
            log.info("author id {}", it.id)
            log.info("author name {}", it.name)
        }

    }

    private fun getAuthor(): AuthorDto {
        return AuthorDto(1L, "jeonguk")
    }

    private fun getNullableAuthor(): AuthorDto? {
        return AuthorDto(1L, "jeonguk")
    }

}
