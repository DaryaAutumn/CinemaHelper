import cinema.dao.RuntimeCinemaHallDAO
import cinema.dao.RuntimeMovieDAO
import cinema.dao.RuntimeSessionDAO
import cinema.dao.RuntimeTicketDAO
import cinema.entity.CinemaHall
import cinema.entity.Worker
import cinema.exceptions.UIException
import cinema.ui.ConsoleDrawer
import cinema.ui.ConsolePrinter
import cinema.ui.Options
import java.time.LocalDateTime

fun main(args: Array<String>) {

    var currentWorker: Worker
    val mainHall = CinemaHall(10, 10)

    // init DAOs
    val hallDAO = RuntimeCinemaHallDAO(mainHall)
    val movieDAO = RuntimeMovieDAO()
    val sessionDAO = RuntimeSessionDAO()
    val ticketDAO = RuntimeTicketDAO()
    val consoleDrawer = ConsoleDrawer()
    val consolePrinter = ConsolePrinter(consoleDrawer)

    hallDAO.addMovie(
        "Boy and a bird",
        "Мальчик Махито сильно тоскует по своей матери и решает отправиться в рискованное путешествие в потусторонний мир. В иной реальности исход неминуем, а жизнь обретает новое начало"
    )

    hallDAO.addSession(hallDAO.findMovieByName("Boy and a bird"), LocalDateTime.of(2023, 12, 22, 23, 30))

    try {
        currentWorker = consolePrinter.start()

        var action: Options
        action = consolePrinter.chooseOption()

        while (true) {
            when (action) {
                Options.SELLTICKET ->
                    consolePrinter.optionSellTicket(hallDAO, ticketDAO, sessionDAO)

                Options.RETURNTICKET ->
                    consolePrinter.optionReturnTicket(hallDAO, ticketDAO)

                Options.EDITDATA ->
                    consolePrinter.optionEditData(hallDAO, movieDAO)

                Options.CHECKTICKET ->
                    consolePrinter.optionCheckTicket(hallDAO, ticketDAO)

                Options.EXIT -> {
                    consolePrinter.optionExit()
                }

            }
            action = consolePrinter.chooseOption()
        }


    } catch (e: UIException) {
        println(e.message)
    } catch (e: Exception) {
        println(e.message)
    }


}