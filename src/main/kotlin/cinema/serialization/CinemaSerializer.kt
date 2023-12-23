package cinema.serialization

import cinema.entity.Movie
import cinema.entity.Session
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.*
import java.nio.file.Files
import java.nio.file.Paths

abstract class CinemaSerializer {
    companion object {

        private val path = "resources/json/"
        private val moviePath = "${path}movies.json"
        private val sessionPath = "${path}sessions.json"
        private val workerPath = "${path}workers.json"
        fun serializeMovies(movies: MutableList<Movie>) {
            val jsonArray = jacksonObjectMapper().writeValueAsString(movies)
            try {
                File(moviePath).bufferedWriter().use { out ->
                    out.write(jsonArray)
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }

        fun serializeSessions(sessions: MutableList<Session>) {
            val mapper = jacksonObjectMapper()
            mapper.registerKotlinModule()
            mapper.registerModule(JavaTimeModule())
            val jsonArray = mapper.writeValueAsString(sessions)

            try {
                File(sessionPath).bufferedWriter().use { out ->
                    out.write(jsonArray)
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }

        fun deserializeMovies(): MutableList<Movie> {

            val encoded = Files.readAllBytes(Paths.get(moviePath))
            val content = String(encoded, Charsets.UTF_8)

            return jacksonObjectMapper().readValue<MutableList<Movie>>(content)

        }

        fun deserializeSessions(): MutableList<Session> {

            val mapper = jacksonObjectMapper()
            mapper.registerKotlinModule()
            mapper.registerModule(JavaTimeModule())

            val encoded = Files.readAllBytes(Paths.get(sessionPath))
            val content = String(encoded, Charsets.UTF_8)

            return mapper.readValue<MutableList<Session>>(content)

        }


    }
}