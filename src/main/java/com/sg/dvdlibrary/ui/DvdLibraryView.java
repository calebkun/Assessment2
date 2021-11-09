/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;
import com.sg.dvdlibrary.dto.Dvd;
import java.util.*;

/**
 * Class for our view, which contains member methods and calls UserIO methods 
 * to handle interactions with our user.
 * 
 * @author calebdiaz
 */
public class DvdLibraryView {
    
    // UserIO member used to display to and get from the user information.
    private UserIO io;
    
    /**
     * Constructor which initializes io with specific implementation passed as parameter
     * to keep the view class agnostic to any implementations.
     * 
     * @param io 
     */
    public DvdLibraryView(UserIO io) {
        this.io = io;
    }
    
    /**
     * Takes in a dvd object and displays remove message,
     * or, if null, a message indicating that no such dvd exists.
     * 
     * @param dvd that was removed
     */
    public void displayRemoveResult(Dvd dvd) {
        if(dvd != null){
          io.print("DVD successfully removed.");
        }else{
          io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    // Banner to accompany remove result
    public void displayRemoveDvdBanner () {
        io.print("=== Remove DVD ===");
    }
    
    /**
     * Takes in a dvd object and displays all of its member fields to user,
     * or, if null, a message indicating that no such dvd exists.
     * 
     * @param dvd to be displayed
     */
    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getDirectorName());
            io.print(dvd.getStudio());
            io.print(dvd.getMpaaRating());
            io.print(dvd.getUserNote());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    // Banner to accompany display info
    public void displayDisplayDvdBanner () {
        io.print("=== Display DVD ===");
    }
    
    // Banner to accompany search result
    public void displaySearchDvdBanner () {
        io.print("=== Search DVD ===");
    }
    
    /**
     * Prompts user to enter title of dvd.
     * 
     * @return string containing title of dvd
     */
    public String getTitleChoice() {
        return io.readString("Please enter the DVD Title.");
    }
    
    /**
     * Takes in a list of dvd objects and prints out some info for each using format
     * method of String object
     * 
     * @param dvdList 
     */
    public void displayDvdList(List<Dvd> dvdList) {
        for (Dvd currentDvd : dvdList) {
            String dvdInfo = String.format("%s : %s %s",
                  currentDvd.getTitle(),
                  currentDvd.getReleaseDate(),
                  currentDvd.getMpaaRating());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    // Banner to accompany display all dvd objects
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }
    
    /**
     * Method to print menu of dvd info to edit and collect user input.
     * 
     * @return int indicating user's menu selection
     */
    public int printEditMenuAndGetSelection() {
        io.print("Which field will you edit?");
        io.print("1. Release Date");
        io.print("2. Director Name");
        io.print("3. Studio");
        io.print("4. MPAA Rating");
        io.print("5. Note");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    /**
     * Takes dvd object to be edited and selection of field to edit, copies all
     * fields from parameter dvd to a new dvd object, and then uses a 
     * switch statement to prompt user and edit appropriate field.
     * 
     * @param dvd to be edited
     * @param selection
     * @return currentDvd edited dvd object
     */
    public Dvd getEditDvdInfo(Dvd dvd, int selection) {
        
        Dvd currentDvd = new Dvd(dvd.getTitle());
        currentDvd.setReleaseDate(dvd.getReleaseDate());
        currentDvd.setDirectorName(dvd.getDirectorName());
        currentDvd.setStudio(dvd.getStudio());
        currentDvd.setMpaaRating(dvd.getMpaaRating());
        currentDvd.setUserNote(dvd.getUserNote());
        
        switch (selection) {
            
            case 1:
                String releaseDate = io.readString("Please enter Release Date");
                currentDvd.setReleaseDate(releaseDate);
                break;
            case 2:
                String directorName = io.readString("Please enter Director Name");
                currentDvd.setDirectorName(directorName);
                break;
            case 3:
                String studio = io.readString("Please enter Studio");
                currentDvd.setStudio(studio);
                break;
            case 4:
                String mpaaRating = io.readString("Please enter Mpaa Rating");
                currentDvd.setMpaaRating(mpaaRating);
                break;
            case 5:
                String userNote = io.readString("Please enter a Note");
                currentDvd.setUserNote(userNote);
                break;
            case 6:
                break;
        }
        return currentDvd;
    }
    
    // Banner to accompany edit method
    public void displayEditBanner() {
        io.print("=== Edit DVD ===");
    }
    
    // Banner to indicate succesful editing
    public void displayEditSuccessBanner() {
        io.readString(
                "DVD successfully edited.  Please hit enter to continue");
    }
    
    /**
     * Prompts user to provide information for each field of dvd object and 
     * then creates and initializes new dvd object.
     * 
     * @return currentDvd
     */
    public Dvd getNewDvdInfo() {
        String title = io.readString("Please enter Title");
        String releaseDate = io.readString("Please enter Release Date");
        String directorName = io.readString("Please enter Director Name");
        String studio = io.readString("Please enter Studio");
        String mpaaRating = io.readString("Please enter Mpaa Rating");
        String userNote = io.readString("Please enter a Note");
        
        Dvd currentDvd = new Dvd(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setUserNote(userNote);
        return currentDvd;
    }

    // Banner to accompany call to adddvd method
    public void displayAddDvdBanner() {
        io.print("=== Add DVD ===");
    }
    
    // Banner to indicate succesful addition
    public void displayAddSuccessBanner() {
        io.readString(
                "DVD successfully added.  Please hit enter to continue");
    }
    
    /**
     * Prints main menu to user and prompts for selection.
     * 
     * @return int indicating menu selection
     */
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVDs in Collection");
        io.print("2. Add DVD to Collection");
        io.print("3. Display DVD Info");
        io.print("4. Edit DVD Info");
        io.print("5. Remove DVD from Collection");
        io.print("6. Search DVD");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }
    
    // Displays error message
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    // Displays exit message
    public void displayExitBanner() {
        io.print("Good Bye!");
    }

    // Displays unknown command message
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command");
    }
}
