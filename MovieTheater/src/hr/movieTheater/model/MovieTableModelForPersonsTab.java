/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Josip
 */
public class MovieTableModelForPersonsTab extends AbstractTableModel {

    String[] COLUMN_NAMES = {"Id", "Naziv", "Originalni naziv", "Datum objave"};
    
    private List<Movie> movies;

    public MovieTableModelForPersonsTab(List<Movie> movies) {
        this.movies = movies;
    }
    
    public void setData(List<Movie> movies){
        this.movies = movies;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return movies.get(rowIndex).getId();
            case 1:
                return movies.get(rowIndex).getTitle();
            case 2:
                return movies.get(rowIndex).getOriginalTitle();
            case 3:
                return movies.get(rowIndex).getPublishedDate().format(Movie.DATE_FORMAT);
            default:
                throw new RuntimeException("No such column!");
        }
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
                return String.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
    
    
    
}
