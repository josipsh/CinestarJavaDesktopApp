/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Josip Sadek
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "director")
public class Director extends Person{
    @XmlTransient
    private final String type = "Director";
    
    public Director() {
    }
    
    public Director(int id, String firstName, String middleName, String lastName) {
        super(id, firstName, middleName, lastName);

    }

    @Override
    public String toString() {
        return super.toString();
    }
//    @Override
//    public String toString() {
//        return super.toString() + " - " + type;
//    }
    
    
    
}
