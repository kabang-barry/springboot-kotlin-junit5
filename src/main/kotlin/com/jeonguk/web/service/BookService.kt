package com.jeonguk.web.service

import com.jeonguk.web.domain.dto.BookDto
import com.jeonguk.web.domain.dto.converter.BookDtoConverter
import com.jeonguk.web.domain.entity.Book
import com.jeonguk.web.exception.BadRequestException
import com.jeonguk.web.exception.ResourceNotFoundException
import com.jeonguk.web.repository.AuthorRepository
import com.jeonguk.web.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * null 이 아닌 타입에 대한 의존성 주입을 허용하는 lateinit 키워드
 * 'it' 매개 변수가 하나 뿐인 경우 lambda 의 기본 매개 변수 이름
 * 엘비스 연산자 ?: 왼쪽 값이 null 인 경우 오른쪽 피연산자를 실행합니다.
 * String interpolation. "Book $id does not exist" 에서 $id 는 id 변수 값으로 대체됩니다.
 */
@Service
class BookService {

    @Autowired
    private lateinit var authorRepository: AuthorRepository

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Transactional
    fun getBook(id: Long): BookDto {
        return bookRepository.findById(id)
                .map { BookDtoConverter.convert(it) }
                .orElse(null)
                ?: throw ResourceNotFoundException("Book $id does not exist")
    }

    @Transactional
    fun createBook(dto: BookDto): Long? {

        val authorId = dto.authorId ?: throw BadRequestException("Author id must not be null")

        val author = authorRepository.findById(authorId).orElse(null)
                ?: throw BadRequestException("Author ${dto.authorId} does not exist")

        val book = Book()
        book.title = dto.title
        book.publication = dto.publication
        book.author = author
        return bookRepository.save(book).id
    }

    @Transactional
    fun updateBook(id: Long, dto: BookDto) {

        val book = bookRepository.findById(id).orElse(null)
                ?: throw ResourceNotFoundException("Book $id does not exist")

        book.title = dto.title
        book.publication = dto.publication

        if (dto.authorId != null && dto.authorId == book.author?.id) {
            val author = authorRepository.findById(dto.authorId!!).orElse(null)
                    ?: throw BadRequestException("Author $id does not exist")
            book.author = author
        }

        bookRepository.save(book)
    }

    @Transactional
    fun deleteBook(id: Long) {
        val book = bookRepository.findById(id).orElse(null)
                ?: throw ResourceNotFoundException("Book $id does not exist")
        bookRepository.delete(book)
    }

}