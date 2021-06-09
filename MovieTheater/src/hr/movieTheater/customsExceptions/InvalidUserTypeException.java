/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.customsExceptions;

/**
 *
 * @author Josip
 */
public class InvalidUserTypeException extends Exception{
    private final String message;

    public InvalidUserTypeException(String message) {
        this.message = message;
    }
    
}
