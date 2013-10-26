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

import com.melloware.jspiff.jaxp.XspfPlaylist;
import com.melloware.jspiff.jaxp.XspfPlaylistTrackList;
import com.melloware.jspiff.jaxp.XspfTrack;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import static noThreads.DefaultCaller.eradioLinks;
import static noThreads.DefaultCaller.writeToFile;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;


public class Playlist {
	
	//method used on dev stage to quickly get the links from file and not wait for a run execution
	public ArrayList<String> loadFromFile(String linksFile) throws IOException{
    	String inputLine;
		// Open file
        FileInputStream fstream = new FileInputStream(linksFile);
        // Get the object of DataInputStream
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        //Read File Line By Line
        ArrayList<String> links = new ArrayList<>();
        while ((inputLine = br.readLine())!= null){
        			links.add(inputLine);
        }
        in.close();
        return links;
	}
	
	//used only for debugging purposes
	 public static void  writeXmlReadyLinks(String fileName, ArrayList<String> theLinks) throws IOException {
		 for(String link : theLinks) {
			 writeToFile(fileName, org.apache.commons.lang3.StringEscapeUtils.escapeXml(link));
		 }	 
	 }
	//used only for debugging purposes
	 public void makeValidXmlLinks(ArrayList<String> links) throws IOException{
		writeXmlReadyLinks("xml_ready_links.txt", links);
	 }
	 
	 public void createPlaylist(String filePath) throws IOException, DocumentException, SAXException, ParserConfigurationException{
		 String xmlObject, title = null;
		 boolean flag = true;
		 XspfPlaylist playlist = new XspfPlaylist();
		 playlist.setTitle("My Playlist");
		 playlist.setVersion("1");

		 // create track list first
		 XspfPlaylistTrackList tracks = new XspfPlaylistTrackList();
	 
		 for(int i=0; i<eradioLinks.size();i++){
			 if(flag==true){
				 flag=false;
				 title = org.apache.commons.lang3.StringEscapeUtils.escapeXml(eradioLinks.get(i));
			 }else{
				 flag=true;
				 //escape the xml characters of the url
				 xmlObject = org.apache.commons.lang3.StringEscapeUtils.escapeXml(eradioLinks.get(i));
				 
				 // now create track, set title and add to list
				 XspfTrack track = new XspfTrack();
				 track.setTitle(title);
				 track.setLocation(xmlObject);
				 tracks.addTrack(track);  
			 }
		 }
		 // add tracks to playlist
		 playlist.setPlaylistTrackList(tracks);

		 //or use Dom4j to output the playlist
		 File file = new File(filePath);
		 OutputFormat format = OutputFormat.createPrettyPrint();
		 XMLWriter writer = new XMLWriter(new FileWriter(file), format);
		 Document doc = DocumentHelper.parseText(playlist.makeTextDocument());
		 writer.write(doc);
		 writer.close();
	 }
         
         public void createM3uPlaylist(String filePath){
             try {
                 boolean flag=true;
                 writeToFile(filePath, "#EXTM3U:\n");
                 //writeToFile("playlist.m3u8", "\n");
                 for(int i=0; i<eradioLinks.size();i++){
                     if (flag==true){
                         flag=false;
                         String theStation = "#EXTINF: "+eradioLinks.get(i);
                         writeToFile(filePath, theStation);
                     }
                     else{
                         flag=true;
                         writeToFile(filePath, eradioLinks.get(i));
                     }
                 }
             } catch (IOException ex) {
                 Logger.getLogger(Playlist.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
}//end of Class

