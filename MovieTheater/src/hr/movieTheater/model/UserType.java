/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.model;

import java.util.Optional;

/**
 *
 * @author Josip
 */
public enum UserType {
    ADMIN(1),
    CLIENT(2);
    
    private final int id;
    
    UserType(int id){
        this.id = id;
    }
    
    public static Optional<UserType> getUserType(int id){
        for (UserType u : values()) {
            if (Integer.valueOf(id).equals(Integer.valueOf(u.id))) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }
    public static Optional<Integer> getUserTypeId(UserType type){
        for (UserType u : values()) {
            if (u.equals(type)) {
                return Optional.of(Integer.valueOf(u.id));
            }
        }
        return Optional.empty();
    }
}
