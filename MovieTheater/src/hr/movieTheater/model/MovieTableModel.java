/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.model;

import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Josip
 */
public class MovieTableModel extends AbstractTableModel {
    
    
    
    String[] COLUMN_NAMES = {"Id", "Naziv", "Orginalni naziv", "Trajanje", "Datum objavljivanja", "Datum od kada je dostupno", "Putanja do slike"};
    
    List<Movie> movies;
    public MovieTableModel(List<Movie> movies) {
        this.movies = movies;
    }
    
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        switch (columnIndex) {
            case 0:
                return movies.get(rowIndex).getId();
            case 1:
                return movies.get(rowIndex).getTitle();
            case 2:
                return movies.get(rowIndex).getOriginalTitle();
            case 3:
                return movies.get(rowIndex).getDuration() == 0 ? "nema ispravnog podatka" : movies.get(rowIndex).getDuration();
            case 4:
                return movies.get(rowIndex).getPublishedDate().format(Movie.DATE_FORMAT);
            case 5:
                return movies.get(rowIndex).getStartDate().format(Movie.DATE_FORMAT);
            case 6:
                return movies.get(rowIndex).getPicturePath();
            default:
                throw new RuntimeException("No such column!");
        }
    }
    

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Integer.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
        }
        return super.getColumnClass(columnIndex);
    }
    
    
    
    
}
