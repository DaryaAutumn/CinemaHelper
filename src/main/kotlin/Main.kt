import cinema.dao.RuntimeCinemaHallDAO
import cinema.dao.RuntimeMovieDAO
import cinema.dao.RuntimeSessionDAO
import cinema.dao.RuntimeTicketDAO
import cinema.entity.CinemaHall
import cinema.entity.Movie
import cinema.entity.Session
import cinema.exceptions.UIException
import cinema.serialization.CinemaSerializer
import cinema.ui.ConsoleDrawer
import cinema.ui.ConsolePrinter
import cinema.ui.Options
import java.time.LocalDateTime

fun main(args: Array<String>) {


    val mainHall = CinemaHall(10, 10)

    // init DAOs
    val hallDAO = RuntimeCinemaHallDAO()
    val movieDAO = RuntimeMovieDAO()
    val sessionDAO = RuntimeSessionDAO()
    val ticketDAO = RuntimeTicketDAO()
    val consoleDrawer = ConsoleDrawer()
    val consolePrinter = ConsolePrinter(consoleDrawer)

//    val m = mutableListOf(
//        Movie(
//            "The boy and the heron",
//            "The movie follows the story of a young boy, Mahito, who after the death of his mother, flees 1940s Tokyo for an estate in the countryside. Once there, he struggles to adjust to his new life with his father, his new stepmother (his mother’s younger sister), and several elderly ladies."
//        ),
//        Movie(
//            "Interstellar",
//            "Interstellar is about Earth’s last chance to find a habitable planet before a lack of resources causes the human race to go extinct. The film’s protagonist is Cooper (Matthew McConaughey), a former NASA pilot who is tasked with leading a mission through a wormhole to find a habitable planet in another galaxy."
//        ),
//        Movie(
//            "Bohemian Rhapsody",
//            "The story chronicles lead singer Freddie Mercury's tempestuous journey from an outcast immigrant struggling to find his place in a rejecting society to his becoming a beloved and world-famous artist."
//        )
//    )
//
//    var s = mutableListOf(
//        Session(m[0], LocalDateTime.of(2023, 12, 23, 21, 0), mainHall),
//        Session(m[0], LocalDateTime.of(2023, 12, 24, 23, 0), mainHall),
//        Session(m[1], LocalDateTime.of(2023, 12, 24, 19, 0), mainHall),
//        Session(m[1], LocalDateTime.of(2023, 12, 24, 17, 0), mainHall),
//        Session(m[2], LocalDateTime.of(2023, 12, 24, 15, 0), mainHall),
//        Session(m[2], LocalDateTime.of(2023, 12, 24, 13, 0), mainHall),
//    )
//
//    CinemaSerializer.serializeMovies(m)
//    CinemaSerializer.serializeSessions(s)


    try {
        consolePrinter.start(mainHall)

        var action: Options
        action = consolePrinter.chooseOption()

        while (true) {
            when (action) {
                Options.SELLTICKET ->
                    consolePrinter.optionSellTicket(hallDAO, ticketDAO, sessionDAO)

                Options.RETURNTICKET ->
                    consolePrinter.optionReturnTicket(hallDAO, ticketDAO)

                Options.EDITDATA ->
                    consolePrinter.optionEditData(hallDAO, movieDAO, sessionDAO, mainHall)

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