package cinema.dao

import cinema.entity.Movie

interface MovieDAO {

    fun addActor(name: String, movie : Movie)

}