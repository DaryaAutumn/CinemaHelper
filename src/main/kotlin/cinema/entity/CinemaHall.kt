package cinema.entity

import cinema.serialization.CinemaSerializer
import kotlinx.serialization.Serializable

@Serializable
data class CinemaHall(
    val rows: Int,
    val lines: Int
) {

    var sessions: MutableList<Session> = mutableListOf()//= CinemaSerializer.deserializeSessions()
    var movies: MutableList<Movie> = CinemaSerializer.deserializeMovies()


}