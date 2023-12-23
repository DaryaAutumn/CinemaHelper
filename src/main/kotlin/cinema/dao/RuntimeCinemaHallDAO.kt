package cinema.dao

import cinema.entity.*
import cinema.exceptions.EmptyListException
import cinema.exceptions.MovieNotExistsException
import cinema.serialization.CinemaSerializer
import java.time.LocalDateTime

class RuntimeCinemaHallDAO : CinemaHallDAO {

    override fun addMovie(name: String, description: String, vararg actorNames: String) {
        try {
            val movie = Movie(name, description)
            for (actor in actorNames) {
                movie.actors.add(actor)
            }
            val movies = CinemaSerializer.deserializeMovies()
            movies.add(movie)
            CinemaSerializer.serializeMovies(movies)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    override fun addSession(movie: Movie, time: LocalDateTime, hall: CinemaHall) {
        try {
            val session = Session(movie, time, hall)
            val sessions = CinemaSerializer.deserializeSessions()
            sessions.add(session)
            CinemaSerializer.serializeSessions(sessions)
        } catch (e: Exception) {
            println(e.message)
        }

    }

    override fun findMovieByName(name: String): Movie {
        val movies = CinemaSerializer.deserializeMovies()
        for (movie in movies) {
            if (name == movie.name) {
                return movie;
            }
        }
        throw MovieNotExistsException("Movie with such name doesn't exist!")
    }

    override fun findSessionsByMovie(movie: Movie): MutableList<Session> {
        val sessionsList = mutableListOf<Session>()
        for (session in CinemaSerializer.deserializeSessions()) {
            if (session.movie.equals(movie) && session.time > LocalDateTime.now()) {
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