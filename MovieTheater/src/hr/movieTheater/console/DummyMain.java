/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.console;

import hr.algebra.factory.ParserFactory;
import hr.movieTheater.dal.Repository;
import hr.movieTheater.dal.RepositoryFactory;
import hr.movieTheater.model.Movie;
import static hr.movieTheater.parsers.rss.MovieParser.parse;
import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josip Sadek
 */
public class DummyMain {
    public static void main(String[] args) {
        Repository data = RepositoryFactory.getRepository();
        try {
            data.createMovies(parse());
            
            //System.out.println(AssetDirectory.getAssetDirectory());
            
            
            //List<Movie> movies = data.getMovies();
            //movies.forEach(System.out::println);
            
//            if (data.getMovie(13).isPresent()) {
//                Optional<Movie> movieForUpdate = data.getMovie(13);
//                
//                movieForUpdate.get().setId(12);
//                data.updateMovie(movieForUpdate.get());
//                
//                //System.out.println(data.getMovie(1));
//                
//                
//            }else{
//                System.out.println("nema retka");
//            }
            
            
            
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(DummyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
