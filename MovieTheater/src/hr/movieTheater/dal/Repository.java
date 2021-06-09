/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.dal;

import hr.movieTheater.customsExceptions.InvalidUserTypeException;
import hr.movieTheater.model.Genre;
import hr.movieTheater.model.Movie;
import hr.movieTheater.model.Person;
import hr.movieTheater.model.User;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


/**
 *
 * @author Josip Sadek
 */
public interface Repository {
    void createMovie(Movie movie) throws SQLException;
    void createMovies(List<Movie> movies) throws SQLException;
    Optional<Movie> getMovie(int id) throws SQLException;
    List<Movie> getMovies(boolean allData) throws SQLException;
    List<Movie> getMoviesFilteredByPerson(Person person) throws SQLException;
    void updateMovie(Movie movie) throws SQLException;
    void deleteMovie(int id) throws SQLException;
    
    List<Person> getPersons() throws SQLException;
    Optional<Person> getPerson(int id) throws SQLException;
    int createPerson(Person person) throws SQLException;
    void updatePerson(Person person) throws SQLException;
    void deletePerson(int id) throws SQLException;
    Optional<Integer> whatRolePersonHas(int idPerson) throws SQLException;
    
    List<Genre> getGenres() throws SQLException;
    void createGenre(Genre genre) throws SQLException;
    
    List<User> getUsers() throws SQLException;
    Optional<User> getUser(String email) throws SQLException;
    int createUser(User user) throws SQLException, InvalidUserTypeException;
    void updateUser(User user) throws SQLException, InvalidUserTypeException;
    Optional<User> isPaswordOk(String userName, String psw) throws SQLException;

    void deleteMovieData() throws SQLException;
}