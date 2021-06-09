/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.dal.sql.model;

/**
 *
 * @author Josip Sadek
 */
public class MovieGenreFromDataBase {
    private final int idMovieGenre;
    private final int genreID;
    private final int movieID;

    /**
     *
     * @param idMovieGenre
     * @param genreID
     * @param movieID
     */
    public MovieGenreFromDataBase(int idMovieGenre, int genreID, int movieID) {
        this.idMovieGenre = idMovieGenre;
        this.genreID = genreID;
        this.movieID = movieID;
    }

    public int getIdMovieGenre() {
        return idMovieGenre;
    }

    public int getGenreID() {
        return genreID;
    }

    public int getMovieID() {
        return movieID;
    }

    @Override
    public String toString() {
        return "MovieGenre{" + "idMovieGenre=" + idMovieGenre + ", genreID=" + genreID + ", movieID=" + movieID + '}';
    }
    
    
}
