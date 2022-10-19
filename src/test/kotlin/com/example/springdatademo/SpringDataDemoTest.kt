package com.example.springdatademo

import com.example.springdatademo.test.TestService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpringDataDemoTest @Autowired constructor(
    val testService: TestService
) {

    @Test
    fun testJPA() {
        println("Testing JPA!!")
        testService.test()
    }

    @Test
    fun testRedis() {
        println("Testing Redis!!")
        testService.testRedis()
    }

    @Test
    fun test() {
        println("Testing!!")
    }

}
