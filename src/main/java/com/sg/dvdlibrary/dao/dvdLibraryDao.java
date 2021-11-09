/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;
import com.sg.dvdlibrary.dto.Dvd;
import java.util.*;

/**
 *
 * @author calebdiaz
 */
public interface dvdLibraryDao {
    /**
     * Adds the given Dvd to the library and associates it with the given
     * student id. If there is already a student associated with the given
     * student id it will return that student object, otherwise it will
     * return null.
     *
     * @param studentId id with which student is to be associated
     * @param student student to be added to the roster
     * @return the Student object previously associated with the given  
     * student id if it exists, null otherwise
     */
    Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException;

    /**
     * Returns a List of all students in the roster.
     *
     * @return List containing all students in the roster.
     */
    List<Dvd> getAllDvds() throws DvdLibraryDaoException;

    /**
     * Returns the student object associated with the given student id.
     * Returns null if no such student exists
     *
     * @param studentId ID of the student to retrieve
     * @return the Student object associated with the given student id,  
     * null if no such student exists
     */
    Dvd getDvd(String title) throws DvdLibraryDaoException;
    
    /**
     * Returns the student object associated with the given student id.
     * Returns null if no such student exists
     *
     * @param studentId ID of the student to retrieve
     * @return the Student object associated with the given student id,  
     * null if no such student exists
     */
    Dvd searchDvd(String title) throws DvdLibraryDaoException;

    /**
     * Removes from the roster the student associated with the given id.
     * Returns the student object that is being removed or null if
     * there is no student associated with the given id
     *
     * @param studentId id of student to be removed
     * @return Student object that was removed or null if no student
     * was associated with the given student id
     */
    Dvd removeDvd(String title) throws DvdLibraryDaoException;
    
    
    /**
     * Updates the info of the dvd with the given title.
     * Returns the a Dvd object with the updated info or null if 
     * there is no dvd associated with the given title.
     *
     * @param title title of the movie whose info is to be updated
     * @param dvd object created from edit
     * @return String with the info that was updated, or null if no dvd
     * was associated with the given title
     */
    Dvd editDvdInfo(String title, Dvd dvd, Dvd prevDvd) throws DvdLibraryDaoException;
    
    
    
}
