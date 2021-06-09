/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.xml;

import hr.movieTheater.model.Movie;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Josip
 */
@XmlRootElement(name = "theater")
@XmlAccessorType(XmlAccessType.FIELD)
public class Theater {
    
    @XmlElement(name = "movie")
    private List<Movie> movies;

    public Theater() {
    }

    public Theater(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getPerson() {
        return movies;
    }
}
