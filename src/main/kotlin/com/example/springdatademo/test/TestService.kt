package com.example.springdatademo.test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class TestService @Autowired constructor(
    val testDao: TestDao,
    val srt: StringRedisTemplate,
) {
    fun test() {
        val sd = testDao.save(with(Test()) {
            name = "haha"
            age = 18
            this
        })
        println("Saved as ID = ${sd.id}")
        println(testDao.count())

        val t = testDao.findByName("haha") ?: throw Exception("not found")
        println(t)

        testDao.findDistinctAgeAndNameNotIn(arrayListOf("xiaosi")).forEach {
            println(it)
        }
    }

    fun testRedis() {
        srt.opsForValue().set("k", "v", 1, TimeUnit.SECONDS)
        val x = srt.opsForValue()["foo"] ?: "Not Found"
        println(x)

        srt.opsForList().rightPush("haha", "xiaosi")
        srt.opsForList().leftPop("haha")?.let {
            println("OK! String = $it")
        }
    }
}