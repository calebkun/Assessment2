/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary;
import com.sg.dvdlibrary.controller.DvdLibraryController;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.dao.dvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoFileImpl;

/**
 *
 * @author calebdiaz
 * 
 * Contains our main method through which we enter the application.
 * Specific implementation decisions for our app are included in this file.
 */
public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl(); // initialize implemenation of UserIO
        DvdLibraryView myView = new DvdLibraryView(myIo); // initialize view with myIo
        dvdLibraryDao myDao = new DvdLibraryDaoFileImpl(); // initialize implementation of dvdLibraryDao
        DvdLibraryController controller = new DvdLibraryController(myDao, myView); // initialize controller
        controller.run(); // run app
    }  
}
