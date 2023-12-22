package cinema.entity

data class Ticket(
    val seat: Seat,
    val session: Session
) {
    var isChecked: Boolean = false
    var isSold: Boolean = false

}