package com.jeonguk.web.domain.dto.converter

import com.jeonguk.web.domain.dto.BookDto
import com.jeonguk.web.domain.entity.Book
import com.jeonguk.web.util.IDtoConverter

/**
 * 엔티티는 BookDtoConverter 싱글톤에 의해 dto로 변환됩니다.
 */
object BookDtoConverter : IDtoConverter<Book, BookDto> {
    override fun convert(entity: Book): BookDto =
            BookDto(
                    id = entity.id,
                    title = entity.title,
                    publication = entity.publication,
                    authorId = entity.author?.id,
                    authorName = entity.author?.name ?: ""
            )
}