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
public class MovieRoleFromDataBase {
    
    private int idMovieRole;
    private String name;

    public MovieRoleFromDataBase(int idMovieRole, String name) {
        this.idMovieRole = idMovieRole;
        this.name = name;
    }

    public int getIdMovieRole() {
        return idMovieRole;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MovieRole{" + "idMovieRole=" + idMovieRole + ", name=" + name + '}';
    }
    
    
}
