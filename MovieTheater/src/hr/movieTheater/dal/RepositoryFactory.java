/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.dal;

import hr.movieTheater.dal.sql.SQLRepository;

/**
 *
 * @author Josip Sadek
 */
public class RepositoryFactory {

    private RepositoryFactory() {
    }

    public static Repository getRepository() {
        return new SQLRepository();
    }
    
}
