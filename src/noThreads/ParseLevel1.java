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

import java.io.IOException;
import java.util.ArrayList;
import static noThreads.DefaultCaller.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseLevel1 {
	private ArrayList<String> stationLinks1 = new ArrayList<>();
	private ArrayList<String> unProsessedLinks = new ArrayList<>();
	private ArrayList<String> titles = new ArrayList<String>();
	private String linksFileName = new String("theLinks_1.txt");
	private String unProcLinksFileName = new String("Unprocessed_links_probably_BAD.txt");
	private String titlesFileNme = new String("titles.txt");

	
	/**
	 * 
	 * @param theCodes
	 * @throws IOException
	 */
	 public void getFirstLinks(ArrayList<Integer> theCodes) throws IOException{
		 boolean linkFound =false;
	        String temp = null;
	    	String theUrl = "http://e-radio.gr/player/player.asp?sID=";
	    	Document doc;

			for(int code : theCodes){	
				linkFound=false;
	        	theUrl = "http://e-radio.gr/player/player.asp?sID="+code;

				doc = parseUrl(theUrl, 0);
				if (doc!=null){
			        Elements media = doc.select("[src]");
			        print("Fetching %s -->  ", theUrl);
			            
			        for (Element src : media){
			        	if (src.tagName().equals("iframe")==true){
			        		temp = src.attr("abs:src");
			        		if(temp.contains("playerX")==true){	
			        			linkFound=true;
			        			temp = temp.replace(" ", "%");
			        			stationLinks1.add(temp);
								break;//link found no need to check another src on this url
			        		}
			        	}else if (src.tagName().equals("embed")==true){
			        		linkFound=true;
			        		temp =  src.attr("abs:src");
			        		temp = temp.replace(" ", "%");
			        		stationLinks1.add(temp);
			        		break;//link found no need to check another src on this url
			        	}  		
			        }//end nested for 
			        if(linkFound==false) {
						print("Unprocessed, no iframe - embed tag for url: %s", theUrl);
						unProsessedLinks.add(theUrl);
						continue;		
			        }
				}else{
					print("Unprocessed, no connection for url: %s", theUrl);
					unProsessedLinks.add(theUrl);
					continue;								
				}
	        }//end outer for		
			print("Unprosessed Links: %s",  unProsessedLinks.size());
			print("Processed Links: %s", stationLinks1.size());
			//write all the links to the disk
			writeLinksToFile(linksFileName, stationLinks1);
			writeLinksToFile(unProcLinksFileName, unProsessedLinks);
	 }//end method 	
	 

	 /**
	  * 
	  * @throws IOException
	  */
	 public void getStationTitles() throws IOException{
		 int start=0;
		 int end=0;
		 String title;
		 
		 for(String stationLink : stationLinks1){
			 if(stationLink.endsWith(".asx")==true){
				 start = stationLink.lastIndexOf("/")+1;
				 end = stationLink.lastIndexOf(".");
				 title = stationLink.substring(start, end);
				 titles.add(title);
				 writeToFile(titlesFileNme, title);				 
			 }else{
				 start = stationLink.indexOf("title=")+6;
				 end = stationLink.indexOf("&codecID");
				 if (start!=(6-1) && end!=(-1-2)){
					 title = stationLink.substring(start, end);
					 title = title.replace("%", " ");
					 print("Station Title: "+ title);
					 titles.add(title);
					 writeToFile("titles.txt", title);
				 }else{
					 titles.add("NO TITLE");
					 writeToFile(titlesFileNme, "NO TITLE");
				 }				 
			 }		 
		 }//end for
		 print("Size of Links: %s", stationLinks1.size());
		 print("Size of Titles: %s", titles.size());
	 }//end method	


	 /**
	  * Setters and Getters
	  */
	public ArrayList<String> getStationLinks1() {
		return stationLinks1;
	}


	public void setStationLinks1(ArrayList<String> stationLinks1) {
		this.stationLinks1 = stationLinks1;
	}


	public ArrayList<String> getUnProsessedLinks() {
		return unProsessedLinks;
	}


	public void setUnProsessedLinks(ArrayList<String> unProsessedLinks) {
		this.unProsessedLinks = unProsessedLinks;
	}


	public String getLinksFileName() {
		return linksFileName;
	}


	public void setLinksFileName(String linksFileName) {
		this.linksFileName = linksFileName;
	}


	public String getUnProcLinksFileName() {
		return unProcLinksFileName;
	}


	public void setUnProcLinksFileName(String unProcLinksFileName) {
		this.unProcLinksFileName = unProcLinksFileName;
	}


	public void setTitles(ArrayList<String> titles) {
		this.titles = titles;
	}
	
	public ArrayList<String> getTitles() {
		return titles;
	}


	public String getTitlesFileNme() {
		return titlesFileNme;
	}


	public void setTitlesFileNme(String titlesFileNme) {
		this.titlesFileNme = titlesFileNme;
	}



	
	 
	 
}//end of Class
