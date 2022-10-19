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

    companion object {
        private const val CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

        fun randomString(): String {
            return (0..10).map { CHARS.random() }.joinToString("")
        }
    }

    fun test() {
        val sd = testDao.save(with(Test()) {
            name = randomString()
            age = (18..30).random()
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