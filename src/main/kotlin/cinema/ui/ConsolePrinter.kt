package cinema.ui

import cinema.dao.MovieDAO
import cinema.dao.RuntimeCinemaHallDAO
import cinema.dao.SessionDAO
import cinema.dao.TicketDAO
import cinema.entity.*
import cinema.exceptions.EmptyListException
import cinema.exceptions.MovieNotExistsException
import cinema.exceptions.TicketException
import cinema.exceptions.UIException
import cinema.serialization.CinemaSerializer
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlin.system.exitProcess

class ConsolePrinter(var drawer: Drawer) : UIChooser {

    override fun start(): Worker {
        println("Hello! Enter your name:")
        val name = checkStringInput("Nothing entered. Please enter your name:")
        val worker = Worker(name)
        println("Hi " + worker.name + "!")
        return worker
    }

    override fun chooseOption(): Options {
        print(
            "Choose what you want to do:\n" +
                    "1. PRESS 1 TO SELL TICKET\n" +
                    "2. PRESS 2 TO RETURN TICKET\n" +
                    "3. PRESS 3 TO EDIT SESSIONS OF FILMS DATA\n" +
                    "4. PRESS 4 TO CHECK CUSTOMER TICKET\n" +
                    "5. PRESS 5 TO EXIT\n"
        )
        var keyCode = checkIntInput("Wrong action. Try again:")
        while (keyCode < 1 || keyCode > 5) {
            println("Wrong action. Try again.")
            keyCode = checkIntInput()
        }

        when (keyCode) {
            1 -> {
                return Options.SELLTICKET
            }

            2 -> return Options.RETURNTICKET

            3 ->
                return Options.EDITDATA

            4 ->
                return Options.CHECKTICKET

            5 ->
                return Options.EXIT
        }
        throw UIException("Wrong input!")
    }

    override fun optionSellTicket(hallDAO: RuntimeCinemaHallDAO, ticketDAO: TicketDAO, sessionDAO: SessionDAO) {

        try {
            println(
                "Choose a movie by printing its number. " +
                        "Available movies:"
            )
            val movieName =
                CinemaSerializer.deserializeMovies()[chooseByNumber(CinemaSerializer.deserializeMovies())].name
            val movie = hallDAO.findMovieByName(movieName)
            val sessions = hallDAO.findSessionsByMovie(movie)
            println(
                "Choose a session by printing its number. " +
                        "Available sessions:"
            )

            val session = sessions[chooseByNumber(sessions)]
            if (sessionDAO.isSold(session)) {
                println("All tickets for that session are sold!")
                return
            }
            println("Choose a seat.")
            val seat = chooseSeat(session)

            val ticket = ticketDAO.sellTicket(seat, session)
            println("Here's your ticket:")
            drawer.drawTicket(ticket, session)
            println("Thank you for buying a ticket!")

        } catch (e: MovieNotExistsException) {
            println(e.message)
        } catch (e: EmptyListException) {
            println(e.message)
        } catch (e: TicketException) {
            println(e.message)
        } catch (e: Exception) {
            println(e.message)
        }

    }

    override fun optionReturnTicket(hallDAO: RuntimeCinemaHallDAO, ticketDAO: TicketDAO) {
        try {
            val pair = findTicket(hallDAO)
            ticketDAO.returnTicket(pair.first, pair.second)
            println("Successfully returned!")


        } catch (e: MovieNotExistsException) {
            println(e.message)
        } catch (e: EmptyListException) {
            println(e.message)
        } catch (e: TicketException) {
            println(e.message)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    override fun optionEditData(hallDAO: RuntimeCinemaHallDAO, movieDAO: MovieDAO) {
        println("1. PRESS 1 IF YOU WANT TO EDIT MOVIES INFO")
        println("2. PRESS 2 IF YOU WANT TO EDIT SESSIONS INFO")
        println("2. PRESS 3 IF YOU WANT TO ADD NEW MOVIE")
        println("2. PRESS 4 IF YOU WANT TO ADD NEW SESSION")

        try {
            var keyCode = checkIntInput("Wrong action. Try again:")
            while (keyCode < 1 || keyCode > 4) {
                println("Wrong action. Try again:")
                keyCode = checkIntInput("Wrong action. Try again:")
            }

            when (keyCode) {
                1 -> {
                    println(
                        "Choose a movie by printing its number. " +
                                "Available movies:"
                    )
                    val movieName =
                        CinemaSerializer.deserializeMovies()[chooseByNumber(CinemaSerializer.deserializeMovies())].name
                    val movie = hallDAO.findMovieByName(movieName)
                    editMovie(movie, movieDAO)

                }

                2 -> {
                    println(
                        "Choose a movie by printing its number to edit its sessions. " +
                                "Available movies:"
                    )
                    val movieName =
                        CinemaSerializer.deserializeMovies()[chooseByNumber(CinemaSerializer.deserializeMovies())].name
                    val movie = hallDAO.findMovieByName(movieName)
                    val sessions = hallDAO.findSessionsByMovie(movie)
                    println(
                        "Choose a session by printing its number. " +
                                "Available sessions:"
                    )
                    val session = sessions[chooseByNumber(sessions)]
                    editSessions(session)
                }

                3 -> {
                    println("Enter movie name:")
                    val name = checkStringInput()
                    println("Enter movie description:")
                    val description = checkStringInput()
                    hallDAO.addMovie(name, description)

                    // TODO (add actors)
                }

                4 -> {
                    println(
                        "Choose a movie by printing its number to add new session. " +
                                "Available movies:"
                    )
                    val movieName =
                        CinemaSerializer.deserializeMovies()[chooseByNumber(CinemaSerializer.deserializeMovies())].name
                    val movie = hallDAO.findMovieByName(movieName)
                    // TODO
                    println("Enter session new date and time as [dd mm YYYY HH:mm]:")
                    val line = checkStringInput()
                    val formatter = DateTimeFormatter.ofPattern("dd mm YYYY HH:mm")
                    val date = LocalDateTime.parse(line, formatter)
                    hallDAO.addSession(movie, date)

                }

            }
        } catch (e: MovieNotExistsException) {
            println(e.message)
        } catch (e: EmptyListException) {
            println(e.message)
        } catch (e: DateTimeParseException) {
            println("Wrong data format!")
        } catch (e: Exception) {
            println(e.message)
        }


    }

    override fun optionCheckTicket(hallDAO: RuntimeCinemaHallDAO, ticketDAO: TicketDAO) {
        try {
            val pair = findTicket(hallDAO)
            ticketDAO.checkTicket(pair.first, pair.second)
            println("Ticket checked. Enjoy the movie!")
        } catch (e: MovieNotExistsException) {
            println(e.message)
        } catch (e: EmptyListException) {
            println(e.message)
        } catch (e: TicketException) {
            println(e.message)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    override fun optionExit() {
        print("Exiting program...")
        exitProcess(0)
    }

    private fun checkStringInput(
        errorMessage: String =
            "Nothing entered. Please try again:"
    ): String {
        var line = readlnOrNull()
        while (line == null) {
            println(errorMessage)
            line = readlnOrNull()
        }
        return line
    }

    private fun checkIntInput(
        errorMessage: String =
            "Wrong number entered. Please try again:"
    ): Int {
        var line = checkStringInput()
        while (line.toIntOrNull() == null) {
            println(errorMessage)
            line = checkStringInput()
        }
        return line.toInt()
    }

    private fun chooseSeat(session: Session, toReturn: Boolean = false): Seat {
        drawer.drawSeats(session)
        println("Type a row:")
        var row = checkIntInput()
        println("Type number of seat:")
        var col = checkIntInput()
        while (row <= 0 || row > session.tickets.size ||
            col <= 0 || col > session.tickets[0].size ||
            session.tickets[row - 1][col - 1].isSold == !toReturn
        ) {
            println("Wrong seat! Choose another one:")
            println("Type a row:")
            row = checkIntInput()
            println("Type number of seat:")
            col = checkIntInput()
        }


        return Seat(row - 1, col - 1)
    }

    private fun <T> chooseByNumber(arr: MutableList<T>): Int {
        for (i in 0..<arr.size) {
            println(
                (i + 1).toString() + ": " +
                        arr[i].toString()
            )
        }
        var num = checkIntInput("Wrong number! Try again:")
        while (num > arr.size || num <= 0) {
            println("Wrong number! Try again:")
            num = checkStringInput().toInt()
        }
        return num - 1;
    }

    private fun findTicket(hallDAO: RuntimeCinemaHallDAO): Pair<Ticket, Session> {
        try {
            println(
                "What movie do you have ticket for?"
            )
            val movieName =
                CinemaSerializer.deserializeMovies()[chooseByNumber(CinemaSerializer.deserializeMovies())].name
            val movie = hallDAO.findMovieByName(movieName)
            val sessions = hallDAO.findSessionsByMovie(movie)
            println(
                "What time do you have ticket for?"
            )

            val session = sessions[chooseByNumber(sessions)]
            println("What is your seat?")
            val seat = chooseSeat(session, true)
            val ticket = hallDAO.findTicketBySession(session, seat)
            return Pair(ticket, session)
        } catch (e: MovieNotExistsException) {
            throw e
        } catch (e: EmptyListException) {
            throw e
        } catch (e: Exception) {
            throw e
        }
    }

    private fun editMovie(movie: Movie, movieDAO: MovieDAO) {

        try {
            println("1. PRESS 1 IF YOU WANT TO EDIT MOVIE NAME")
            println("2. PRESS 2 IF YOU WANT TO EDIT MOVIE DESCRIPTION")
            println("3. PRESS 3 IF YOU WANT TO ADD ACTORS TO MOVIE")
            var keyCode = checkIntInput("Wrong action. Try again:")
            while (keyCode < 1 || keyCode > 3) {
                println("Wrong action. Try again:")
                keyCode = checkIntInput("Wrong action. Try again:")
            }

            when (keyCode) {
                1 -> {
                    println("Enter new movie name:")
                    movie.name = checkStringInput()
                }

                2 -> {
                    println("Enter movie new description:")
                    movie.description = checkStringInput()
                }

                3 -> {
                    movieDAO.addActor(checkStringInput(), movie)
                }
            }
        } catch (e: Exception) {
            println(e.message)
        }

    }


    private fun editSessions(session: Session) {
        try {
            println("Enter session new date and time as [day month year hour:minutes]:")
            val line = checkStringInput()
            val formatter = DateTimeFormatter.ofPattern("dd mm YYYY HH:mm")
            val date = LocalDateTime.parse(line, formatter)
            session.time = date
        } catch (e: DateTimeParseException) {
            println("Wrong data format!")
            editSessions(session)
        } catch (e: Exception) {
            println(e.message)
        }

    }

}