package cinema.entity

import kotlinx.serialization.Serializable

@Serializable
data class Ticket(
    val seat: Seat,
    //val session: Session
) {
    var isChecked: Boolean = false
    var isSold: Boolean = false

}