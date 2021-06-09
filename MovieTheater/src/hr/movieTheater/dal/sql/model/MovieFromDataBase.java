/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.dal.sql.model;

import java.time.LocalDate;

/**
 *
 * @author Josip Sadek
 */
public class MovieFromDataBase {
    private final int idMovie;
    private final String title;
    private final String originalTitle;
    private final String description;
    private final int duration;
    private final LocalDate publisheddate;
    private final LocalDate startDate;
    private final String picturePath;

    /**
     *
     * @param idMovie
     * @param title
     * @param originalTitle
     * @param description
     * @param duration
     * @param publisheddate
     * @param startDate
     * @param trailerUrl
     * @param picturePath
     */
    public MovieFromDataBase(int idMovie, String title, String originalTitle, 
            String description, int duration, LocalDate publisheddate, LocalDate startDate, 
            String picturePath) {
        this.idMovie = idMovie;
        this.title = title;
        this.originalTitle = originalTitle;
        this.description = description;
        this.duration = duration;
        this.publisheddate = publisheddate;
        this.startDate = startDate;
        this.picturePath = picturePath;
    }

    public int getIdMovie() {
        return idMovie;
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

    public int getDuration() {
        return duration;
    }

    public LocalDate getPublisheddate() {
        return publisheddate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getPicturePath() {
        return picturePath;
    }

    @Override
    public String toString() {
        return "Movie{" + "idMovie=" + idMovie + ", title=" + title + '}';
    }
    
}
