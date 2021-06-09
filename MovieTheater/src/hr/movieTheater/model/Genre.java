/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Josip Sadek
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Genre implements Comparable<Genre>{
    @XmlTransient
    private int id;
    @XmlElement(name = "genreName")
    private String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
//    @Override
//    public String toString() {
//        return "Genre{" + "id=" + id + ", name=" + name + '}';
//    }

    @Override
    public int compareTo(Genre o) {
        return Integer.valueOf(o.getId()).compareTo(Integer.valueOf(id));
    }
    
    
}
