package cinema.dao

import cinema.entity.Movie

class RuntimeMovieDAO : MovieDAO {

    override fun addActor(name: String, movie: Movie) {
        movie.actors.add(name)
    }
}