package cinema.entity

import java.time.LocalDateTime

data class Session(
    val place: CinemaHall,
    val movie: Movie,
    var time: LocalDateTime
) {

    val tickets = Array(place.rows) { i ->
        Array(place.lines) { j ->
            Ticket(Seat(i, j), this)
        }
    }

    override fun toString(): String {
        return "${time.dayOfMonth} ${time.month} ${time.hour}:${time.minute}"
    }

}