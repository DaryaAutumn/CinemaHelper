package cinema.entity

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.time.LocalDateTime

@Serializable
data class Session(
    val movie: Movie,
    @Contextual
    var time: LocalDateTime,
    @Transient
    val place: CinemaHall = CinemaHall(10, 10),
) {

    val tickets = Array(place.rows) { i ->
        Array(place.lines) { j ->
            Ticket(Seat(i, j))
        }
    }

    override fun toString(): String {
        return "${time.dayOfMonth} ${time.month} ${time.hour}:${time.minute}"
    }

}