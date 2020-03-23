package com.kapresoft.demo

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringBootConfiguration
import org.springframework.cloud.function.context.FunctionalSpringApplication
import org.springframework.context.annotation.Bean
import java.util.function.Function

@SpringBootConfiguration
class Application : Function<FindMovieRequest, MovieInfo> {

    override fun apply(request: FindMovieRequest): MovieInfo {
        log.debug("\nFindMovieRequest: {}", toJsonString(request))
        val movieInfo = movieFinder().findCharacterFromMovie(request.characterName)
        log.debug("\nMovie found: {}", toJsonString(movieInfo))
        return movieInfo
    }

    @Bean
    fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true)
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        return objectMapper
    }

    @Bean
    fun movieFinder(): MovieFinder {
        return MovieFinder()
    }

    private fun toJsonString(o: Any): String? {
        try {
            return objectMapper().writeValueAsString(o)
        } catch (e: JsonProcessingException) {
            log.info("Failed to write object to json: {}", e.message, e)
        }
        return null
    }

    companion object {
        private val log = LoggerFactory.getLogger(Application::class.java)

        @JvmStatic
        fun main(args: Array<String>) {
            FunctionalSpringApplication.run(Application::class.java, *args)
        }
    }

}