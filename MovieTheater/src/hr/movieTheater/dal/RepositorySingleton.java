/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.dal;



/**
 *
 * @author Josip
 */
public class RepositorySingleton {

    private RepositorySingleton() {
    }
    
    private static Repository instance;
    
    public static Repository getInstance(){
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    
    private static void createInstance() {
        instance = RepositoryFactory.getRepository();
    }
    
}
