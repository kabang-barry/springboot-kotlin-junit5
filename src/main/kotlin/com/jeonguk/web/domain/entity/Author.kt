package com.jeonguk.web.domain.entity

import javax.persistence.*

@Entity
@Table(name = "author")
data class Author(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var name: String? = null,
        @OneToMany
        var books: List<Book> = mutableListOf()
)
