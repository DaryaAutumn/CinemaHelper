package cinema.entity

import kotlinx.serialization.Serializable

@Serializable
data class CinemaHall(
    val rows: Int,
    val lines: Int
)