package cinema.ui

import cinema.dao.*
import cinema.entity.CinemaHall

interface UIChooser {

    fun start(hall: CinemaHall)

    fun chooseOption(): Options

    fun optionSellTicket(hallDAO: CinemaHallDAO, ticketDAO: TicketDAO, sessionDAO: SessionDAO)

    fun optionReturnTicket(hallDAO: CinemaHallDAO, ticketDAO: TicketDAO)

    fun optionEditData(hallDAO: CinemaHallDAO, movieDAO: MovieDAO, sessionDAO: SessionDAO, hall: CinemaHall)

    fun optionCheckTicket(hallDAO: CinemaHallDAO, ticketDAO: TicketDAO)

    fun optionExit()

}