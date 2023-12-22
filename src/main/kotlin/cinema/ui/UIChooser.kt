package cinema.ui

import cinema.dao.*
import cinema.entity.Worker

interface UIChooser {

    fun start(): Worker

    fun chooseOption(): Options

    // TODO (change Runtime to DAO)
    fun optionSellTicket(hallDAO: RuntimeCinemaHallDAO, ticketDAO: TicketDAO, sessionDAO: SessionDAO)

    fun optionReturnTicket(hallDAO: RuntimeCinemaHallDAO, ticketDAO: TicketDAO)

    fun optionEditData(hallDAO: RuntimeCinemaHallDAO, movieDAO: MovieDAO)

    fun optionCheckTicket(hallDAO: RuntimeCinemaHallDAO, ticketDAO: TicketDAO)

    fun optionExit()

}