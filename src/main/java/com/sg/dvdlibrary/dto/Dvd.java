/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dto;

/**
 * DTO for our dvd construct, implemented with relevant data field and 
 * member mutator methods.
 * 
 * @author calebdiaz
 */
public class Dvd {
    private String title;
    private String releaseDate;
    private String directorName;
    private String studio;
    private String mpaaRating;
    private String userNote;

    /**
     * Constructor which initializes object with title, meant to be
     * unique key which is set at instantiation and unable to be edited.
     * 
     * @param title of dvd
     */
    public Dvd(String title) {
        this.title = title;
    }

    /**
     * getter method for title member field.
     * 
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * getter method releaseDate member field.
     * 
     * @return releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * setter method for releaseDate member field.
     * 
     * @param releaseDate
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * getter method for directorName member field.
     * 
     * @return directorName
     */
    public String getDirectorName() {
        return directorName;
    }
    
    /**
     * setter method for directorName member field.
     * 
     * @param directorName 
     */
    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    /**
     * getter method for studio member field.
     * 
     * @return studio
     */
    public String getStudio() {
        return studio;
    }
    
    /**
     * setter method for studio member field.
     * 
     * @param studio 
     */
    public void setStudio(String studio) {
        this.studio = studio;
    }

    /**
     * getter method for mpaaRating member field.
     * 
     * @return mpaaRating
     */
    public String getMpaaRating() {
        return mpaaRating;
    }
    
    /**
     * setter method for mpaaRating member field.
     * 
     * @param mpaaRating 
     */
    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }   
    
    /**
     * getter method for userNote member field.
     * 
     * @return userNote
     */
    public String getUserNote(){
        return userNote;
    }
    
    /**
     * setter method for userNote member field.
     * 
     * @param userNote 
     */
    public void setUserNote(String userNote){
        this.userNote = userNote;
    }
}
