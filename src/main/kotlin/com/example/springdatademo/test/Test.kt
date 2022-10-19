package com.example.springdatademo.test

import javax.persistence.Entity
import org.springframework.data.annotation.Id

@Entity
class Test constructor(
    @Id
    var id: Long,

    var name: String,

    var age: Int,
) {
    constructor(): this(0, "", 0)
}