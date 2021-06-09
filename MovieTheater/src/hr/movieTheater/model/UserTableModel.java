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
public class UserTableModel extends AbstractTableModel{

    String[] COLUMN_NAMES = {"Id korisnika", "Email", "Tip", "Datum kreiranja računa"};
    
    List<User> users;

    public UserTableModel(List<User> users) {
        this.users = users;
    }
    
    public void setUsers(List<User> users) {
        this.users = users;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
            case 0:
                return users.get(rowIndex).getIdClient();
            case 1:
                return users.get(rowIndex).getEmail();
            case 2:
                return users.get(rowIndex).getUserType().get();
            case 3:
                return users.get(rowIndex).getAddDate().format(User.DATE_FORMAT);
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
        }
        return super.getColumnClass(columnIndex);
    }
    
}
