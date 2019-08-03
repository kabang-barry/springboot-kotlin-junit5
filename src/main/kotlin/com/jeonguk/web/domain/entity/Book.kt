package com.jeonguk.web.domain.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "book")
data class Book(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var title: String? = null,
        var publication: LocalDate? = null,
        @ManyToOne
        var author: Author? = null
)