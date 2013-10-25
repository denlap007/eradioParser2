/**
	eradioParser: This program guides the user through 
        a graphical user interface to create playlists 
        from the stations registered on http://e-radio.gr
    
	Copyright (C) 2013  Lappas Dionysis
    
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

import static gui.TheGui.jTextArea1;
import static gui.TheGui.newline;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class DefaultCaller {
	public static final int MAX_CONNECTION_ATTEMPTS  = 5;
	public static final  String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)";
	public static String filePath = null;
	public static ArrayList<String> theUrls = new ArrayList<>();
	/*
	 * ArrayList eradioLinks holds the final radio stations links along with their titles
	 * in this order:
	 * <Stationtitle>
	 * <StatonUrl>
	 */
	public static ArrayList<String> eradioLinks = new ArrayList<>();
	
	
	/**
	 * @param theUrl
	 * @param conAttempts
	 * @return
	 */
	public static Document parseUrl(String theUrl, int conAttempts){
        boolean threw = false;
        Document doc = null;
        if(conAttempts==MAX_CONNECTION_ATTEMPTS)
        	return null;
        else{
			Connection con = Jsoup.connect(theUrl).userAgent(userAgent);
	        try {
	        	doc = con.get();//doc = Jsoup.connect(theUrl).get();
	        } catch (IOException e) {
	        	print("%s  THREW EXCEPTION BUT handled", theUrl);
	            threw = true;
	        }   
	        if(threw==true){
	        	doc = parseUrl(theUrl, conAttempts+1);
	        }	        
	        return doc;	
        }
	}
	
	
	/**
	 * Print method
	 * @param msg
	 * @param args
	 */
	public static void print(String msg, Object... args) {
		jTextArea1.append(String.format(msg, args) + newline);
	}
	
	
	/**
	 * @param fileName
	 * @param contents
	 * @throws IOException
	 */
	public static void writeToFile(String fileName, String contents) throws IOException{
		OutputStreamWriter writer = new OutputStreamWriter(
				new FileOutputStream(fileName, true), "UTF-8");
		BufferedWriter fbw = new BufferedWriter(writer);
		fbw.write(contents);
		fbw.newLine();
		fbw.close();
	}
	
	
	/**
	 * @param fileName
	 * @param theLinks
	 * @throws IOException
	 */
	public static void  writeLinksToFile(String fileName, ArrayList<String> theLinks) throws IOException {
		for(String link : theLinks) {
			writeToFile(fileName, link);
		}	 
	}
	 
	 
	/**
	 * @param aList
	 */
	public static void debug(ArrayList<Integer> aList){
		for(int a : aList)
			print("code: " + a); 	
	}
	   
	
	/**
	 * @param aList
	 */
	public static void debug2(ArrayList<String> aList){
		for(String a : aList)
			print(a);	
	}

	public static String getFilePath() {
		return filePath;
	}

	public static void setFilePath(String filePath) {
		DefaultCaller.filePath = filePath;
	}

	public static ArrayList<String> getTheUrls() {
		return theUrls;
	}

	public static void setTheUrls(ArrayList<String> theUrls) {
		DefaultCaller.theUrls = theUrls;
	}

	public static ArrayList<String> getEradioLinks() {
		return eradioLinks;
	}

	public static void setEradioLinks(ArrayList<String> eradioLinks) {
		DefaultCaller.eradioLinks = eradioLinks;
	}
	
	
}//end of Class
