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
public class MoviePersonFromDataBase {
    
    private int idMoviePerson;
    private int personDataID;
    private int movieRoleID;
    private int movieID;

    public MoviePersonFromDataBase(int idMoviePerson, int personDataID, int movieRoleID, int movieID) {
        this.idMoviePerson = idMoviePerson;
        this.personDataID = personDataID;
        this.movieRoleID = movieRoleID;
        this.movieID = movieID;
    }

    public int getIdMoviePerson() {
        return idMoviePerson;
    }

    public int getPersonDataID() {
        return personDataID;
    }

    public int getMovieRoleID() {
        return movieRoleID;
    }

    public int getMovieID() {
        return movieID;
    }

    @Override
    public String toString() {
        return "MoviePerson{" + "idMoviePerson=" + idMoviePerson + ", personDataID=" + personDataID + ", movieRoleID=" + movieRoleID + ", movieID=" + movieID + '}';
    }
    
    
    
}
