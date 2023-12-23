package cinema.ui

import cinema.entity.Session
import cinema.entity.Ticket

class ConsoleDrawer : Drawer {

    val white = "\u001b[0m"
    val red = "\u001b[31m"
    val green = "\u001b[32m"
    val yellow = "\u001b[33m"

    override fun drawSeats(session: Session) {
        for (i in 0..<session.tickets.size) {
            for (j in 0..<session.tickets[i].size) {
                val iFormat = String.format("%02d", i + 1)
                val jFormat = String.format("%02d", j + 1)
                if (session.tickets[i][j].isSold) {
                    print("$red[$iFormat $jFormat]$white  ")
                } else {
                    print("$green[$iFormat $jFormat]$white  ")
                }
            }
            println()
        }
    }

    override fun drawTicket(ticket: Ticket, session: Session) {
        print(yellow)
        println("---------------")
        println(session.movie.name)
        println(session.toString())
        println("seat: ${ticket.seat}")
        println("---------------")
        print(white)
    }

}