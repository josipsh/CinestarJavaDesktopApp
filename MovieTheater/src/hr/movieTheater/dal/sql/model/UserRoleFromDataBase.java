/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.dal.sql.model;

/**
 *
 * @author Josip Sadek
 */
public class UserRoleFromDataBase {
    
    private final int idUserRole;
    private final String name;

    /**
     *
     * @param idUserRole
     * @param name
     */
    public UserRoleFromDataBase(int idUserRole, String name) {
        this.idUserRole = idUserRole;
        this.name = name;
    }

    public int getIdUserRole() {
        return idUserRole;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserRole{" + "idUserRole=" + idUserRole + ", name=" + name + '}';
    }
    
    
    
}
