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
public class PersonTableModel extends AbstractTableModel {

    String[] COLUMN_NAMES = {"Id", "Ime", "Srednje ime", "Prezime"};
    
    private List<Person> persons;

    public PersonTableModel(List<Person> persons) {
        this.persons = persons;
    }
    
    public void setData(List<Person> persons){
        this.persons = persons;
        fireTableDataChanged();
    }
    
    
    @Override
    public int getRowCount() {
        return persons.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return persons.get(rowIndex).getId();
            case 1:
                return persons.get(rowIndex).getFirstName();
            case 2:
                return persons.get(rowIndex).getMiddleName();
            case 3:
                return persons.get(rowIndex).getLastName();
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
