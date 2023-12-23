package cinema.dao

import cinema.entity.Seat
import cinema.entity.Session
import cinema.entity.Ticket
import cinema.serialization.CinemaSerializer
import cinema.ui.Drawer
import java.time.LocalDateTime

class RuntimeSessionDAO : SessionDAO {
    override fun changeSessionTime(time: LocalDateTime, session: Session) {
        val sessions = CinemaSerializer.deserializeSessions()
        val s = findSessionId(session, sessions)
        s.time = time
        CinemaSerializer.serializeSessions(sessions)
    }

    //user interface
    override fun drawSessionSeats(session: Session, tool: Drawer) {
        tool.drawSeats(session)
    }

    override fun findTicketByRow(seat: Seat, session: Session): Ticket {
        return session.tickets[seat.row][seat.line]
    }

    override fun isSold(session: Session): Boolean {
        // if there is at least one not taken seat
        for (i in 0..<session.tickets.size) {
            for (j in 0..<session.tickets[i].size) {
                if (!session.tickets[i][j].isSold) {
                    return false
                }
            }
        }
        return true
    }

    // finds session id in collection of sessions
    override fun findSessionId(session: Session, sessions: MutableList<Session>): Session {
        var ind = 0
        for (i in 0..<sessions.size) {
            if (sessions[i].equals(session)) {
                ind = i
            }
        }
        return sessions[ind]
    }


}