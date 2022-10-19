package com.example.springdatademo.test

import javax.persistence.*

@Entity
class Test constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    var name: String,

    var age: Int,
) {
    constructor(): this(0, "", 0)
}