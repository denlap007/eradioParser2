/**
	eradioParser: This program extracts the radio station 
	links along with their names, found on http://e-radio.gr, 
	and creates a playlist.
    
	Copyright (C) 2012  Lappas Dionysis
    
    This file is part of eradioParser.

    eradioParser is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    eradioParser is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>
    
    You may contact the author at: dio@freelabs.net
 */
package noThreads;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public final class ProcessCla {
	private String filePath = null;
	private ArrayList<String> theUrls = new ArrayList<String>();

	
	/**
	 * @param filePath
	 * @throws IOException
	 */
	public void processFile(String filePath) throws IOException {
    	String inputLine;
        // Open file
        FileInputStream fstream = new FileInputStream(filePath);
        // Get the object of DataInputStream
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        //Read File Line By Line
        while ((inputLine = br.readLine())!= null){
        	theUrls.add(inputLine);
        }
        
        System.out.print("\n*** "+theUrls.size() +" urls loaded successfully from file! "+"***\n");
		in.close();
	}
	
	
	/**
	 * @param args
	 */
	public void processStrings(String[] args) {
		for(String anArg :args) {
	        try {
				@SuppressWarnings("unused")
				URL link = new URL(anArg);
			} catch (MalformedURLException e) {
				System.out.print("ERROR: "+anArg+" is not a valid url!\n");
				continue;
			}
			theUrls.add(anArg);   	
		}	
		System.out.print("\n*** "+theUrls.size() +" urls loaded successfully from terminal! "+"***\n");		
	}
	
	
	/**
	 * Setters and Getters
	 */
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		filePath = this.filePath;
	}

	public void setTheUrls(ArrayList<String> theUrls) {
		this.theUrls = theUrls;
	}

	public ArrayList<String> getTheUrls() {
		return theUrls;
	}

	public void addToTheUrls(String link) {
		theUrls.add(link);
	}
}
