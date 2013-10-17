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


/**
 * This program accepts command line arguments. 
 * The command line arguments supported are: 
 * 1. File Location. Location of file on disk i.e. C://a.txt or /Usr/home/a.txt
 * 2. String(s). The Strings are of the form of a Url. i.e. http://www.e-radio.gr/locations/athens.asp
 **/
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.xml.sax.SAXException;

public class theMain {

	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ParserConfigurationException 
	 * @throws SAXException 
	 * @throws DocumentException 
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException, InterruptedException, DocumentException, SAXException, ParserConfigurationException{
		//variables that hold time in msec, in order to calculate
		//how much time lasts a program execution
		long total_time=0, startTime=0, endTime=0;
		startTime = System.currentTimeMillis();
		
		/*
		 * Print GPL message
		 */
		System.out.println("\nEradioParser v1.1 Copyright (C) 2012 Lappas Dionysis \n"+
				"This program comes with ABSOLUTELY NO WARRANTY. This is free software,\n" +
				"and you are welcome to redistribute it under certain conditions; \n" +
				"details: http://www.gnu.org/licenses/gpl.txt \n");

		
		/*
		 * This structure will hold all the file names used on this project.
		 * Disk files are used to store the output and check data validity 
		 * during different steps of program's execution (debugging). 
		 * WILL BE REMOVED IN THE FUTURE.
		 */
		
		ArrayList<String> diskFiles = new ArrayList<String>();
		
		/*
		 * Create an object of Class ProcessCla in order to process
		 * command line arguments if necessary. 
		 */
		ProcessCla claObject = new ProcessCla();
		
		/*
		 * Create an object of class DefautlCaller.
		 * The caller object is accessed from all object's classes
		 * as it holds all the static methods along with 
		 * the filePath, the first urls loaded and the final 
		 * eradio station links and their names.
		 */
		DefaultCaller caller = new DefaultCaller();
		
		/*
		 * process command line arguments
		 * IF no command line argument inserted load the MENU
		 * else If there exists only one (1) argument then
		 * --if it ends with .txt it's the filaPath of a file on disk 
		 * --else consider it String
		 * else there exist more arguments, Strings (string links)
		 * Save the arguments to variables
		 */
		if(args.length==0) {
			Menu menuObject = new Menu();
			menuObject.createMenu();
		}else if(args.length==1){
			claObject = new ProcessCla();
			if(args[0].endsWith(".txt")) {
				claObject.processFile(args[0]);
				DefaultCaller.setFilePath(claObject.getFilePath());
			}
			else {
				claObject.processStrings(args);	
				DefaultCaller.setTheUrls(claObject.getTheUrls());
			}
		}else{
			claObject.processStrings(args);		
			DefaultCaller.setTheUrls(claObject.getTheUrls());
		}
		
		/*
		 * Create an object of Class ParseLevel0 in order to get 
		 * the codes of the radio stations
		 */
		ParseLevel0 pl0 = new ParseLevel0();
		pl0.parseCodes();	
		
		/*
		 * Create an object of Class ParseLevel1. Parse the codes to GetFirstLinks method
		 * and get the first links of the radio stations. From the extracted links
		 * get the tiles with the getTitles method.
		 */
		
		ParseLevel1 pl1 = new ParseLevel1();
		pl1.getFirstLinks(pl0.getCodes());
		pl1.getStationTitles();
		
		ParseLevel2 pl2 = new ParseLevel2();
		pl2.getSecondLinks(pl1.getStationLinks1());
		pl2.getFinalLinks(pl2.getStationLinks2(), pl1.getTitles());
		
		Playlist p = new Playlist();
		p.createPlaylist();
		
		endTime = System.currentTimeMillis();
		total_time = total_time + (endTime-startTime);
		
		//CLEANUP CODE, delete unnecessary files
		diskFiles.add(pl1.getLinksFileName());
		diskFiles.add(pl1.getTitlesFileNme());
		diskFiles.add(pl2.getEradioLinksFileName());
		diskFiles.add(pl2.getLinks2FileName());
		for(String name : diskFiles){
			File a = new File(name);
			a.delete();
		}
		
		System.out.println("\n\nRUN SUMMARY:\n" +
				"Playlist successfully generated! \n"+
				"Elapsed time: "+ total_time+" msec\n"+
				"Parsed: "+
				pl1.getStationLinks1().size()+
				" station links. \n"+
				"Valid links: " +
				DefaultCaller.eradioLinks.size()/2+
				"/"+
				pl1.getStationLinks1().size()+
				"\nProgram Exiting...");
	}
}
