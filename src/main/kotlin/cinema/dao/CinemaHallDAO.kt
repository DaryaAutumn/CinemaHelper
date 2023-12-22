package cinema.dao

import cinema.entity.Movie
import cinema.entity.Seat
import cinema.entity.Session
import cinema.entity.Ticket
import java.time.LocalDateTime

interface CinemaHallDAO {

    fun addMovie(name: String, description: String, vararg actorNames: String)

    fun addSession(movie: Movie, time: LocalDateTime)

    fun findMovieByName(name: String): Movie

    fun findSessionsByMovie(movie: Movie): MutableList<Session>

    fun findTicketBySession(session: Session, seat: Seat): Ticket

}