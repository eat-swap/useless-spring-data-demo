package com.example.springdatademo.test

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TestDao: JpaRepository<Test, Long> {
    fun findByName(name: String): Test?
    fun findByNameAndAge(name: String, age: Int): Test?

    @Query("SELECT DISTINCT age " +
            "FROM Test " +
            "WHERE name NOT IN :n")
    fun findDistinctAgeAndNameNotIn(n: List<String>): List<Int>
}