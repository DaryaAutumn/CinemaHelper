package cinema.dao

import cinema.entity.Seat
import cinema.entity.Session
import cinema.entity.Ticket
import cinema.exceptions.TicketException
import cinema.serialization.CinemaSerializer
import java.time.LocalDateTime

class RuntimeTicketDAO : TicketDAO {

    override fun sellTicket(seat: Seat, session: Session): Ticket {
        // read from json file
        val sessions = CinemaSerializer.deserializeSessions()
        val rtSession = RuntimeSessionDAO()
        // find a ticket
        val ticket = rtSession.findTicketByRow(
            seat,
            // find session in loaded data
            rtSession.findSessionId(session, sessions)
        )
        if (ticket.isSold) {
            throw TicketException(
                "This ticket has already been sold! You cannot sell it twice."
            )
        }
        ticket.isSold = true
        // save changes
        CinemaSerializer.serializeSessions(sessions)
        return ticket
    }

    override fun returnTicket(ticket: Ticket, session: Session) {
        // read from json file
        val sessions = CinemaSerializer.deserializeSessions()
        val rtSession = RuntimeSessionDAO()
        // check conditions
        if (rtSession.findSessionId(session, sessions).time
            < LocalDateTime.now()
        ) {
            throw TicketException(
                "The session has already begun! You cannot return this ticket."
            )
        }
        if (!ticket.isSold) {
            throw TicketException(
                "This ticket hasn't been sold! You cannot return it."
            )
        }

        ticket.isSold = false
        // save changes
        CinemaSerializer.serializeSessions(sessions)

    }

    override fun checkTicket(ticket: Ticket, session: Session) {
        // read from json file
        val sessions = CinemaSerializer.deserializeSessions()
        val rtSession = RuntimeSessionDAO()
        val rtHall = RuntimeCinemaHallDAO()
        // find a ticket in loaded data
        var t = rtHall.findTicketBySession(
            rtSession.findSessionId(session, sessions),
            ticket.seat
        )
        if (!t.isSold) {
            throw TicketException(
                "This ticket hasn't been sold! You cannot check it."
            )
        }
        if (t.isChecked) {
            throw TicketException("This ticket has already been checked! You cannot check it twice.")
        }
        t.isChecked = true
        // save changes
        CinemaSerializer.serializeSessions(sessions)
    }

}