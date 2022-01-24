package com.jeonguk.web.domain.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "book")
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String? = null,
    var publication: LocalDate? = null,
    @ManyToOne(targetEntity = Author::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    var author: Author? = null
)