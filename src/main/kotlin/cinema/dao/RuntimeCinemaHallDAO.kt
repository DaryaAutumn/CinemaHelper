package cinema.dao

import cinema.entity.*
import cinema.exceptions.EmptyListException
import cinema.exceptions.MovieNotExistsException
import java.time.LocalDateTime
import java.util.*

class RuntimeCinemaHallDAO(val hall: CinemaHall) : CinemaHallDAO {

    override fun addMovie(name: String, description: String, vararg actorNames: String) {
        val movie = Movie(name, description)
        for (actor in actorNames) {
            movie.actors.add(actor)
        }
        hall.movies.add(movie)
    }


    override fun addSession(movie: Movie, time: LocalDateTime) {
        val session = Session(hall, movie, time)
        hall.sessions.add(session)
    }

    override fun findMovieByName(name: String): Movie {
        for (movie in hall.movies) {
            if (name == movie.name) {
                return movie;
            }
        }
        throw MovieNotExistsException("Movie with such name doesn't exist!")
    }

    override fun findSessionsByMovie(movie: Movie): MutableList<Session> {
        val sessionsList = mutableListOf<Session>()
        for (session in hall.sessions) {
            if (session.movie.equals(movie)) {
                sessionsList.add(session)
            }
        }
        if (sessionsList.isEmpty())
            throw EmptyListException("No session for such movie!")
        return sessionsList
    }

    override fun findTicketBySession(session: Session, seat: Seat): Ticket {
        return session.tickets[seat.row][seat.line]
    }
}