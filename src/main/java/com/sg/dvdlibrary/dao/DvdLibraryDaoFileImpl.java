/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.util.*;
import java.io.*;

/** DvdLibraryDaoFileImpl
 * 
 * Implementation of our Data Access Object interface which allows for data
 * persistence using .txt files.
 * 
 * @author calebdiaz
 */
public class DvdLibraryDaoFileImpl implements dvdLibraryDao {
    
    // constants used for file persistence
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    
    // map containing records for our dvds
    private Map<String, Dvd> dvds = new HashMap<>();
    
    /**
     * Responsible for taking a line from our file and parsing the line to
     * extract info needed to create a corresponding Dvd object.
     * 
     * @param dvdAsText
     * @return dvdFromFile
     */
    private Dvd unmarshallDvd(String dvdAsText){
        // dvdAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // Title::10/10/21::Caleb Diaz::DreamWorks::G::Great movie!
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in dvdTokens.
        // Which should look like this:
        // _____________________________________________________
        // |     |        |          |          | |            |
        // |Title|10/10/21|Caleb Diaz|DreamWorks|G|Great movie!|
        // |     |        |          |          | |            |
        // -----------------------------------------------------
        //  [0]     [1]       [2]        [3]    [4]     [5]
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        // Given the pattern above, the title is in index 0 of the array.
        String title = dvdTokens[0];

        // Which we can then use to create a new Dvd object to satisfy
        // the requirements of the Dvd constructor.
        Dvd dvdFromFile = new Dvd(title);

        // However, there are 3 remaining tokens that need to be set into the
        // new dvd object. We do this manually by using the appropriate setters.

        // Index 1 - ReleaseDate
        dvdFromFile.setReleaseDate(dvdTokens[1]);

        // Index 2 - DirectorName
        dvdFromFile.setDirectorName(dvdTokens[2]);

        // Index 3 - Studio
        dvdFromFile.setStudio(dvdTokens[3]);
        
        // Index 4 - MpaaRating
        dvdFromFile.setMpaaRating(dvdTokens[4]);
        
        // Index 5 - UserNote
        dvdFromFile.setUserNote(dvdTokens[5]);

        // We have now created a Dvd!
        return dvdFromFile;
    }
    
    /**
     * Responsible for taking a Dvd object and translating it's member fields
     * into the appropriate format to be stored in our file. The string of the appropriate
     * file is then passed as input to unmarshallDvd()
     * 
     * @param aDvd
     * @return dvdAsText
     */
    private String marshallDvd(Dvd aDvd){
        // We need to turn a Dvd object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // Title::10/10/21::Caleb Diaz::DreamWorks::G::Great movie!

        // We get out each property, and concatenate with our DELIMITER as a kind of spacer. 

        // Start with the title, since that's supposed to be first.
        String dvdAsText = aDvd.getTitle() + DELIMITER;

        // add the rest of the properties in the correct order:
        
        // ReleaseDate
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;
        
        // DirectorName
        dvdAsText += aDvd.getDirectorName() + DELIMITER;
        
        // Studio
        dvdAsText += aDvd.getStudio() + DELIMITER;

        // MpaaRating
        dvdAsText += aDvd.getMpaaRating() + DELIMITER;
        
        // UserNote -- skip delimiter
        dvdAsText += aDvd.getUserNote();

        // We have now turned a student to text! Return it!
        return dvdAsText;
    }

    /**
     * Creates Scanner object to open persistence file if it exists 
     * and parses each line, calling unmarshallDvd() on each to create Dvd objects
     * 
     * @throws DvdLibraryDaoException 
     */
    private void loadLibrary() throws DvdLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "-_- Could not load library data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDvd holds the most recent dvd unmarshalled
        Dvd currentDvd;
        // Go through LIBRARY_FILE line by line, decoding each line into a 
        // Dvd object by calling the unmarshallDvd method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Dvd
            currentDvd = unmarshallDvd(currentLine);

            // Put currentDvd into the map using title as the key
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        // close scanner
        scanner.close();
    }
    
    /**
    * Writes all students in the library out to a LIBRARY_FILE.  See loadLibrary
    * for file format.
    * 
    * @throws ClassRosterDaoException if an error occurs writing to the file
    */
    private void writeLibrary() throws DvdLibraryDaoException {
        // We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    "Could not save DVD data.", e);
        }

        // Write out the Dvd objects to the library file.
        String dvdAsText;
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {
            // turn a Dvd into a String
            dvdAsText = marshallDvd(currentDvd);
            // write the Dvd object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    /**
     * Implementation of DAO interface add record method, which loads current
     * library from file into local dvds hashmap, adds the given dvd to the map, and
     * overwrites the library to reflect added dvd.
     * 
     * @param title of dvd to be added
     * @param dvd object returned from view's getNewDvdInfo method
     * @return newDvd
     * @throws DvdLibraryDaoException 
     */
    @Override
    public Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException {
        loadLibrary();
        Dvd newDvd = dvds.put(title, dvd);
        writeLibrary();
        return newDvd;
    }
    
    /**
     * Implementation of DAO interface get all records method, which loads current
     * library from file into local dvds hashmap and returns an array list of its values.
     * 
     * @return new ArrayList of all dvds in hashmap
     * @throws DvdLibraryDaoException 
     */
    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        loadLibrary();
        return new ArrayList<Dvd>(dvds.values());
    }
    
    /**
     * Implementation of DAO interface get record method, which loads current library
     * from file into local dvds hashmap and gets the dvd with the given title.
     * 
     * @param title of dvd to get 
     * @return dvd matching given title
     * @throws DvdLibraryDaoException 
     */
    @Override
    public Dvd getDvd(String title) throws DvdLibraryDaoException {
        loadLibrary();
        return dvds.get(title);
    }
    
    /**
     * Implementation of DAO interface search record method, which loads current library
     * from file into local dvds hashmap and gets the dvd with the given title.
     * @param title of dvd being searched for
     * @return dvd matching given title
     * @throws DvdLibraryDaoException 
     */
    @Override
    public Dvd searchDvd(String title) throws DvdLibraryDaoException {
        loadLibrary();
        return dvds.get(title);
    }
    
    /**
     * Implementation of DAO interface remove record method, which loads current library
     * from file into local dvds hashmap, removes the record by key (title), and 
     * overwrites the library file to reflect the removed dvd.
     * 
     * @param title of dvd to be removed
     * @return dvd object of removed dvd
     * @throws DvdLibraryDaoException 
     */
    @Override
    public Dvd removeDvd(String title) throws DvdLibraryDaoException {
        loadLibrary();
        Dvd removedDvd = dvds.remove(title);
        writeLibrary();
        return removedDvd;
    }
    
    /**
     * Implementation of DAO interface edit record method, which loads current library
     * from file into local dvds hashmap, removes dvd which user indicated by title,
     * replaces it with a dvd object of the same title with fields reflecting edited info,
     * and overwrites the library file to reflect the edited dvd.
     * 
     * @param title of dvd to be edited
     * @param dvd object with updated info fields
     * @param prevDvd dvd object to be replaced
     * @return
     * @throws DvdLibraryDaoException 
     */
    @Override
    public Dvd editDvdInfo(String title, Dvd dvd, Dvd prevDvd) throws DvdLibraryDaoException {
        loadLibrary();
        dvds.remove(prevDvd.getTitle());
        Dvd newDvd = dvds.put(title, dvd);
        writeLibrary();
        return newDvd;
    }
}
