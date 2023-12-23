import cinema.dao.RuntimeCinemaHallDAO
import cinema.dao.RuntimeMovieDAO
import cinema.dao.RuntimeSessionDAO
import cinema.dao.RuntimeTicketDAO
import cinema.entity.CinemaHall
import cinema.entity.Movie
import cinema.entity.Session
import cinema.entity.Worker
import cinema.exceptions.UIException
import cinema.ui.ConsoleDrawer
import cinema.ui.ConsolePrinter
import cinema.ui.Options
import java.time.LocalDateTime

fun main(args: Array<String>) {

    val currentWorker: Worker
    val mainHall = CinemaHall(10, 10)

    // init DAOs
    val hallDAO = RuntimeCinemaHallDAO()
    val movieDAO = RuntimeMovieDAO()
    val sessionDAO = RuntimeSessionDAO()
    val ticketDAO = RuntimeTicketDAO()
    val consoleDrawer = ConsoleDrawer()
    val consolePrinter = ConsolePrinter(consoleDrawer)

    val m = mutableListOf(
        Movie("The boy and the heron", "1"),
        Movie("Interstellar", "2"),
        Movie("Bohemian Rhapsody", "3")
    )

    var s = mutableListOf(
        Session(m[0], LocalDateTime.of(2023, 12, 23, 21, 0), mainHall),
        Session(m[0], LocalDateTime.of(2023, 12, 23, 23, 0), mainHall),
        Session(m[1], LocalDateTime.of(2023, 12, 23, 19, 0), mainHall),
        Session(m[1], LocalDateTime.of(2023, 12, 23, 17, 0), mainHall),
        Session(m[2], LocalDateTime.of(2023, 12, 23, 15, 0), mainHall),
        Session(m[2], LocalDateTime.of(2023, 12, 23, 14, 0), mainHall),
    )

    //CinemaSerializer.serializeMovies(m)
    //CinemaSerializer.serializeSessions(s)



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