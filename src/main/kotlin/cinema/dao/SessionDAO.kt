package cinema.dao

import cinema.entity.Seat
import cinema.entity.Session
import cinema.entity.Ticket
import cinema.ui.Drawer

interface SessionDAO {

    fun drawSessionSeats(session: Session, tool: Drawer)

    fun findTicketByRow(seat: Seat, session: Session): Ticket

    fun isSold(session: Session): Boolean

}