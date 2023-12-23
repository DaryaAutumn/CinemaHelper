package cinema.dao

import cinema.entity.Seat
import cinema.entity.Session
import cinema.entity.Ticket
import cinema.ui.Drawer
import java.time.LocalDateTime

interface SessionDAO {

    fun changeSessionTime(time : LocalDateTime, session: Session)

    fun drawSessionSeats(session: Session, tool: Drawer)

    fun findTicketByRow(seat: Seat, session: Session): Ticket

    fun isSold(session: Session): Boolean

    fun findSessionId(session: Session, sessions: MutableList<Session>): Session

}