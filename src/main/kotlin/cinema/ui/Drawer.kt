package cinema.ui

import cinema.entity.Session
import cinema.entity.Ticket

interface Drawer {

    fun drawSeats(session: Session)

    fun drawTicket(ticket: Ticket)

}