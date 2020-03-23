//package com.example.demo
//
//import org.slf4j.LoggerFactory
//import org.springframework.boot.autoconfigure.SpringBootApplication
//import org.springframework.boot.runApplication
//import org.springframework.context.annotation.Bean
//import java.util.function.Function
//
//@SpringBootApplication
//class FunctionConfiguration  {
//
//    @Bean
//    fun uppercase(): Function<String, String> {
//        return Function { value: String -> value.toUpperCase() }
//    }
//
//    companion object {
//        val log = LoggerFactory.getLogger(FunctionConfiguration::class.java)
//    }
//}
//
//fun main(args: Array<String>) {
//    runApplication<FunctionConfiguration>(*args)
//}
//
