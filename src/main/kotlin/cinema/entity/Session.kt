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
    val place: CinemaHall
) {

    val tickets = Array(place.rows) { i ->
        Array(place.lines) { j ->
            Ticket(Seat(i, j))
        }
    }

    override fun toString(): String {
        val minuteFormatted = String.format("%02d", time.minute)
        return "${time.dayOfMonth} ${time.month} ${time.hour}:${minuteFormatted}"
    }

}