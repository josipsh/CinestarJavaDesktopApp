/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.model;

import hr.movieTheater.xml.DateAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Josip Sadek
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"title", "originalTitle", "description", "duration", "publishedDate", "startDate", "picturePath", "genres", "actors", "directors"})
public class Movie {
    
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE;
    
    @XmlTransient
    private int id;
    private String title;
    private String originalTitle;
    private String description;
    
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate publishedDate;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate startDate;
    
    private int duration;
    private String picturePath;
    
    @XmlElementWrapper
    @XmlElement(name = "genre")
    private List<Genre> genres;
    @XmlElementWrapper
    @XmlElement(name = "actor")
    private List<Actor> actors;
    @XmlElementWrapper
    @XmlElement(name = "director")
    private List<Director> directors;

    public Movie() {
    }

    public Movie(int id, String title, String originalTitle, String description, 
            LocalDate publishedDate, LocalDate startDate, int duration, 
            String picturePath, List<Genre> genre, List<Actor> actors, List<Director> directors) {
        this(title, originalTitle, description, publishedDate, startDate, duration,
                picturePath, genre, actors, directors);
        this.id = id;
    }

    public Movie(String title, String originalTitle, String description,
            LocalDate publishedDate, LocalDate startDate, int duration,  
            String picturePath, List<Genre> genre, List<Actor> actors, List<Director> directors) {
        this.title = title;
        this.originalTitle = originalTitle;
        this.description = description;
        this.publishedDate = publishedDate;
        this.startDate = startDate;
        this.duration = duration;
        this.picturePath = picturePath;
        this.genres = genre;
        this.actors = actors;
        this.directors = directors;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getDuration() {
        return duration;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public List<Genre> getGenre() {
        return genres;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public void setGenre(List<Genre> genre) {
        this.genres = genre;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }
    
    

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title=" + title 
                + ", originalTitle=" + originalTitle + ", description=" 
                + description + ", publishedDate=" + publishedDate + ", startDate=" 
                + startDate + ", duration=" + duration + ", picturePath=" 
                + picturePath + ", genre=" + genres + ", actors=" + actors + ", directors=" 
                + directors + '}';
    }


}
