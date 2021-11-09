/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

/**
 * Exception wrapper class which inherits from Exception class and is meant to
 * disguise exceptions that can be thrown within the program to hide implementation
 * details from user.
 * 
 * @author calebdiaz
 */
public class DvdLibraryDaoException extends Exception {
    
    /** 
     * Constructor for exception object which handles issues that are not caused by another
     * exception, such as violations of business/program logic. The message is passed to the 
     * super class's constructor.
     * 
     * @param message provided by method creating exception object.
     */
    public DvdLibraryDaoException(String message) {
        super(message);
    }
    
    /**
     * Constructor for exception object which handles exceptions caused by issues in underlying 
     * implementation. Original exception is caught and passed to the super class's constructor along with a
     * message.
     * 
     * @param message provided by method creating exception object
     * @param cause exception, referred to by super class, caught by method creating the exception object
     */
    public DvdLibraryDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
