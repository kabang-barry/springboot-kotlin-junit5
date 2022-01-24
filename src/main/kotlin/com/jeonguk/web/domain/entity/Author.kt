package com.jeonguk.web.domain.entity

import javax.persistence.*

@Entity
@Table(name = "author")
class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null
)
