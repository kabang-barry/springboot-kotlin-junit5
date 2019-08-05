package com.jeonguk.web.service

import com.jeonguk.web.domain.dto.BookDto
import com.jeonguk.web.domain.dto.converter.BookDtoConverter
import com.jeonguk.web.domain.entity.Book
import com.jeonguk.web.exception.BadRequestException
import com.jeonguk.web.exception.ResourceNotFoundException
import com.jeonguk.web.repository.AuthorRepository
import com.jeonguk.web.repository.BookRepository
import com.jeonguk.web.util.PageDto
import com.jeonguk.web.util.PaginationDto
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

    /**
     * 읽기전용 트랜잭션(read-only, readOnly)
     *
     * 트랜잭션을 읽기 전용으로 설정할 수 있다.
     * 성능을 최적화하기 위해 사용할 수도 있고 특정 트랜잭션 작업 안에서 쓰기 작업이 일어나는 것을
     * 의도적으로 방지하기 위해 사용할 수도 있다.
     * 트랜잭션을 준비하면서 읽기 전용 속성이 트랜잭션 매니저에게 전달된다.
     * 그에 따라 트랜잭션 매니저가 적절한 작업을 수행한다.
     * 그런데 일부 트랜잭션 매니저의 경우 읽기전용 속성을 무시하고 쓰기 작업을 허용할 수도 있기 때문에 주의해야 한다.
     * 일반적으로 읽기 전용 트랜잭션이 시작된 이후 INSERT, UPDATE, DELETE 같은 쓰기 작업이 진행되면 예외가 발생한다.
     * aop/tx 스키마로 트랜잭션 선언을 할 때는 이름 패턴을 이용해 읽기 전용 속성으로 만드는 경우가 많다.
     * 보통 get이나 find 같은 이름의 메소드를 모두 읽기전용으로 만들어 사용하면 편리하다.
     * @Transactional 의 경우는 각 메소드에 일일이 읽기 전용 지정을 해줘야 한다.
     * read-only 애트리뷰트 또는 readOnly 앨리먼트로 지정한다.
     */
    @Transactional(readOnly = true)
    fun getBook(id: Long): BookDto {
        return bookRepository.findById(id)
                .map { BookDtoConverter.convert(it) }
                .orElse(null)
                ?: throw ResourceNotFoundException("Book $id does not exist")
    }

    @Transactional(readOnly = true)
    fun getBooks(pagination: PaginationDto): PageDto<BookDto> {
        val page = bookRepository.findAll(pagination.toPageable())
        return BookDtoConverter.convert(page)
    }

    @Transactional(readOnly = true)
    fun getAuthorBooks(id: Long): List<Book> {
        return bookRepository.findByAuthorId(id)
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