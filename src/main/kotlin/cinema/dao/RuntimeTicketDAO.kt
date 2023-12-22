package cinema.dao

import cinema.entity.Seat
import cinema.entity.Session
import cinema.entity.Ticket
import cinema.exceptions.TicketException
import java.time.LocalDateTime
import java.util.*

class RuntimeTicketDAO : TicketDAO {
    override fun sellTicket(seat: Seat, session: Session): Ticket {
        val rtSession = RuntimeSessionDAO()
        val ticket = rtSession.findTicketByRow(seat, session)
        if (ticket.isSold){
            throw TicketException(
                "This ticket has already been sold! You cannot sell it twice."
            )
        }
        ticket.isSold = true
        return ticket
    }

    override fun returnTicket(ticket: Ticket) {
        if (ticket.session.time < LocalDateTime.now()) {
            throw TicketException(
                "The session has already begun! You cannot return this ticket."
            )
        }
        if (!ticket.isSold){
            throw TicketException(
                "This ticket hasn't been sold! You cannot return it."
            )
        }
        ticket.isSold = false
    }

    override fun checkTicket(ticket: Ticket) {
        if (!ticket.isSold){
            throw TicketException(
                "This ticket hasn't been sold! You cannot check it."
            )
        }
        if (ticket.isChecked){
            throw TicketException("This ticket has already been checked! You cannot check it twice.")
        }
        ticket.isChecked = true
    }

}