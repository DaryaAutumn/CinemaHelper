package cinema.dao

import cinema.entity.Seat
import cinema.entity.Session
import cinema.entity.Ticket

interface TicketDAO {

    fun sellTicket(seat: Seat, session: Session): Ticket

    fun returnTicket(ticket: Ticket)

    fun checkTicket(ticket: Ticket)

}