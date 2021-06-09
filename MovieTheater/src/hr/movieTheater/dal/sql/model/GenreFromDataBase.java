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
public class GenreFromDataBase {
    private final int idGenre;
    private final String name;
    

    /**
     *
     * @param idGenre
     * @param name
     */
    public GenreFromDataBase(int idGenre, String name) {
        this.idGenre = idGenre;
        this.name = name;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
