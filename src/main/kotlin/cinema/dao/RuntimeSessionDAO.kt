package cinema.dao

import cinema.entity.Seat
import cinema.entity.Session
import cinema.entity.Ticket
import cinema.ui.Drawer

class RuntimeSessionDAO: SessionDAO {
    override fun drawSessionSeats(session: Session, tool: Drawer) {
        tool.drawSeats(session)
    }

    override fun findTicketByRow(seat: Seat, session: Session): Ticket {
        return session.tickets[seat.row][seat.line]
    }

    override fun isSold(session: Session): Boolean {
        var isAllSold = true
        for (i in 0..<session.tickets.size){
            for (j in 0..<session.tickets[i].size){
                if (!session.tickets[i][j].isSold){
                    return false
                }
            }
        }
        return true
    }


}