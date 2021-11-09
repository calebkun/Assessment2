/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import java.util.*;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.dao.dvdLibraryDao;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.dao.DvdLibraryDaoException;


/**
 *
 * @author calebdiaz
 * 
 * Controller of the app, responsible for coordinating model and view components to carry out
 * CRUD functionality.
 */
public class DvdLibraryController {
    
    // Declarations for member objects from our model, representing composition
    private DvdLibraryView view;
    private dvdLibraryDao dao;
    
    
    /** 
     * Constructor for our controller where dependencies (specific implementations) are injected
     * 
     * @param dao - specific implementation of dvdLibraryDao
     * @param view - specific implementation of DvdLibraryView
    */
    public DvdLibraryController(dvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    /**
     * Calls DvdLibraryView method to print our menu and get the user's selection
     * 
     * @return int indicating user's menu selection
     */
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    /**
     * Uses DAO and view methods to prompt the user for the dvd they wish to edit,
     * the field they wish to edit, and collect the user's edits.
     * 
     * @throws DvdLibraryDaoException 
     */
    private void editDvd() throws DvdLibraryDaoException {
        view.displayEditBanner();
        String title = view.getTitleChoice();
        Dvd prevDvd = dao.getDvd(title);
        if (prevDvd != null){ // execute edit if dvd matching given title exists
            view.displayDvd(prevDvd); // display current info to user before they edit
            int selection = view.printEditMenuAndGetSelection();
            Dvd dvdEdit = view.getEditDvdInfo(prevDvd, selection);
            dao.editDvdInfo(dvdEdit.getTitle(), dvdEdit, prevDvd);
            view.displayEditSuccessBanner();
        } else view.displayDvd(prevDvd); // passes null to displayDvd, triggering error message
    }
    
    /** 
     * Uses DAO and view methods to prompt user for the info of the dvd they wish to add.
     * 
     * @throws DvdLibraryDaoException 
     */
    private void addDvd() throws DvdLibraryDaoException {
        view.displayAddDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayAddSuccessBanner();
    }
    
    /**
     * Uses DAO and view methods to display all dvds in collection.
     * 
     * @throws DvdLibraryDaoException 
     */
    private void listDvds() throws DvdLibraryDaoException {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }
    
    /**
     * Uses DAO and view methods to prompt user for title of dvd they wish to view
     * and display detailed info on the dvd.
     * 
     * @throws DvdLibraryDaoException 
     */
    private void viewDvd() throws DvdLibraryDaoException {
        view.displayDisplayDvdBanner();
        String title = view.getTitleChoice();
        Dvd dvd = dao.getDvd(title);
        view.displayDvd(dvd);
    }
    
    /**
     * Uses DAO and view methods to prompt user for title of the dvd they are searching
     * for and display detailed info on the dvd should it exist.
     * 
     * @throws DvdLibraryDaoException 
     */
    private void searchDvd() throws DvdLibraryDaoException {
        view.displaySearchDvdBanner();
        String title = view.getTitleChoice();
        Dvd dvd = dao.getDvd(title);
        view.displayDvd(dvd);
    }
    
    /**
     * Uses DAO and view methods to prompt user for title of the dvd they wish to remove
     * and then remove the dvd from the collection should it exist.
     * 
     * @throws DvdLibraryDaoException 
     */
    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        String title = view.getTitleChoice();
        Dvd removedDvd = dao.removeDvd(title);
        view.displayRemoveResult(removedDvd);
    }
    
    /**
     * Calls view method to display appropriate message when a user's menu selection does not
     * correspond to a command.
     */
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    /**
     * Calls view method to display goodbye message when user selects to exit.
     */
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    /**
     * run() is the only (non-constructor) method called from the main() method, and
     * contains a while loop that continues until the user provides proper input
     * to exit the loop, allowing the user to provide input and the app to perform the
     * corresponding tasks.
     * 
     */
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDvds();
                        break;
                    case 2:
                        addDvd();
                        break;
                    case 3:
                        viewDvd();
                        break;
                    case 4:
                        editDvd();
                        break;
                    case 5:
                        removeDvd();
                        break;
                    case 6:
                        searchDvd();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (DvdLibraryDaoException e){
            view.displayErrorMessage(e.getMessage());
        }
    }
}
