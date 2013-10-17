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

import static noThreads.DefaultCaller.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Menu { 
	private int choice=-1;
	private final String URL= new String("http://e-radio.gr");

	public void createMenu() throws IOException, InterruptedException {
		Document doc = null;
		BufferedReader br = null;
		
		System.out.print("******************** Menu Options ******************** "+
				"\n1. Get a playlist for all the stations at <e-radio.gr>" +
				"\n2. View the available station Categories and get a playlist." +
				"\n3. View the available station Locations and get a playlist." +
				"\n4. View the station Ratings (Top) and get a playlist."+
				"\n5. Exit."+
				"\n\n"+
				"Please make a choice (1-5): ");
		 br = new BufferedReader(new InputStreamReader(System.in));

	     try {
	    	 choice = Integer.parseInt(br.readLine());
	     } catch (IOException e) {
	    	 System.out.println("Error!");
	    	 System.exit(1);
	     }
	     
		switch (choice) {
		case(1):	//GET all the e-radio location links (in order to get all the links)
			doc = parseUrl(URL, 0);
		
			if(doc==null){
				print("No connection to the server! Exiting...");
				System.exit(1);
			}
			
			Elements links = doc.select("div[id=paneContainer]").select("a[href*=/locations/]");
			
			for(Element link : links)
				theUrls.add(link.attr("abs:href"));
			 System.out.println("...Processing <All e-radio> station links");
			 break;
			
		case(2):	//Get CATEGORIES
			doc = parseUrl(URL, 0);
		
			if(doc==null){
				print("No connection to the server! Exiting...");
				System.exit(1);
			}
			
			Elements categoryLinks = doc.select("div[id=paneContainer]").select("a[href*=/categories/]");
			
			System.out.println("E-radio stations available categories: " +
					"\n");
			for(int i=0; i< categoryLinks.size();i++){
				System.out.println(i+1+
						".  "+
						StringEscapeUtils.unescapeHtml4(categoryLinks.get(i).html()));
			}
			System.out.print("\n" +
					"Please make a choise (1-"+
					categoryLinks.size()+
					"): ");
			
			 br = new BufferedReader(new InputStreamReader(System.in));
		     try {
		    	 choice = Integer.parseInt(br.readLine());
		     } catch (IOException e) {
		    	 System.out.println("Error!");
		    	 System.exit(1);
		     }
		     if(choice<=categoryLinks.size() && choice>=1){
		    	 theUrls.add(categoryLinks.get(choice-1).attr("abs:href"));
		    	 System.out.println("...Processing the <"+
		    			 StringEscapeUtils.unescapeHtml4(categoryLinks.get(choice-1).html())+
		    			 "> category");
		     }
		     else{
		    	 System.out.println("Wrong selection...");
		    	 System.out.println("Exiting program...");
		    	 System.exit(1);
		     }
		     
		     break;
		     
		case(3)://Get LOCATIONS
			doc = parseUrl(URL, 0);
		
			if(doc==null){
				print("No connection to the server! Exiting...");
				System.exit(1);
			}
			
			Elements locationLinks = doc.select("div[id=paneContainer]").select("a[href*=/locations/]");
			
			System.out.println("E-radio stations available locations: " +
					"\n");
			for(int i=0; i< locationLinks.size();i++){
				System.out.println(i+1+
						".  "+
						StringEscapeUtils.unescapeHtml4(locationLinks.get(i).html()));
			}
			System.out.print("\n" +
					"Please make a choise (1-"+
					locationLinks.size()+
					"): ");
			
			 br = new BufferedReader(new InputStreamReader(System.in));
		     try {
		    	 choice = Integer.parseInt(br.readLine());
		     } catch (IOException e) {
		    	 System.out.println("Error!");
		    	 System.exit(1);
		     }
		     if(choice<=locationLinks.size() && choice>=1){
		    	 theUrls.add(locationLinks.get(choice-1).attr("abs:href"));
		    	 System.out.println("...Processing <"+
		    			 StringEscapeUtils.unescapeHtml4(locationLinks.get(choice-1).html())+
		    			 "> locatino");
		     }else{
		    	System.out.println("Wrong selection!");
				System.out.println("Exiting program...");
				System.exit(1); 
		     }

		     break;
			
		case(4):
			final int YEARLY_RATING = 10;
			doc = parseUrl(URL, 0);
			
			if(doc==null){
				print("No connection to the server! Exiting...");
				System.exit(1);
			}
			
			Elements ratingsMenu= doc.select("div[class=menuFly]").select("li").select("a[class=hide]");
			
			print("\nStations ratings: \n");
			
			for(int i=0; i< ratingsMenu.size();i++){
				System.out.println(i+1+
						".  "+
						StringEscapeUtils.unescapeHtml4(ratingsMenu.get(i).html()));
			}
			System.out.print("\n" +
					"Please make a choise (1-"+
					ratingsMenu.size()+
					"): ");
			
			 br = new BufferedReader(new InputStreamReader(System.in));
		     try {
		    	 choice = Integer.parseInt(br.readLine());
		     } catch (IOException e) {
		    	 System.out.println("Error!");
		    	 System.exit(1);
		     }
		     
		     
		     /*
		      * The html of the Ratings menu processed 
		      * has this structure:
		      * <div>
		      * 	<ul>
		      * 		<li>
		      * 			<ul>
		      * 				...
		      * 			</ul>
		      * 		</li>
		      * 		...
		      * 	</ul>
		      * </div>
		      */
		     if(choice<=ratingsMenu.size() && choice>=1){
		    	 //Get the DIV element with class "menuFly"
		    	 Elements div = doc.select("div[class=menuFly]") ;
		    	 //div Elements list has only one element. So get the children of div
		    	 Elements ul = div.get(0).children();
		    	 //ul Elements list has only one element. So get the children of ul
		    	 Elements li = ul.get(0).children();
	    			
		    	 //remove blank elements
		    	 for(int j=0; j< li.size();j++){
		    		 if(li.get(j).hasText()==false)
		    			 li.remove(li.get(j));
		    	 }
		    	 
		    	 //get the title of user choice and print it out
				print("\n%s", StringEscapeUtils.unescapeHtml4(ratingsMenu.get(choice-1).html())+"\n");
				//check if there is a sub-menu
				Elements ulTag = li.get(choice-1).select("ul");
				if(ulTag.hasText()==true){
					Elements subMenu = ulTag.select("li").select("a[href]");
					
					//print the sub-menu
					for(int j=0; j<subMenu.size(); j++)
						print("%s.  %s ",j+1, StringEscapeUtils.unescapeHtml4(subMenu.get(j).html()));

					System.out.print("\n" + "Please make a choise (1-"+ subMenu.size() + "): ");
					
					//read user input
					 br = new BufferedReader(new InputStreamReader(System.in));
				     try {
				    	 choice = Integer.parseInt(br.readLine());
				     } catch (IOException e) {
				    	 System.out.println("Error!");
				    	 System.exit(1);
				    	 }
				     
				     if(choice<=subMenu.size() && choice>=1){
				    	 theUrls.add(subMenu.get(choice-1).attr("abs:href"));
				    	 System.out.println("...Processing the <"+
				    			 StringEscapeUtils.unescapeHtml4(subMenu.get(choice-1).html())+
				    			 "> category");
				     }else{
				    	 System.out.println("Wrong selection!");
				    	 System.out.println("Exiting program...");
				    	 System.exit(1);
				     }
				}else{
					if(choice==YEARLY_RATING){
						String  url = li.get(choice-1).select("a[href").attr("abs:href");
						doc = parseUrl(url, 0);
						
						if(doc!=null){
							Elements yearTopSubMenu = doc.select("div[id=maintabsid]").select("a[href]");
						
							//print the sub-menu
							for(int i=0; i<yearTopSubMenu.size(); i++)
								print("%s.  %s",i+1, StringEscapeUtils.unescapeHtml4(yearTopSubMenu.get(i).html()));

							System.out.print("\n" + "Please make a choise (1-" + yearTopSubMenu.size() + "): ");
						
							//read user input
							br = new BufferedReader(new InputStreamReader(System.in));
							try {
								choice = Integer.parseInt(br.readLine());
							} catch (IOException e) {
								System.out.println("Error!");
								System.exit(1);
								}
							
							if(choice<=yearTopSubMenu.size() && choice>=1){
								if(choice==1){
									theUrls.add(yearTopSubMenu.get(choice-1).attr("abs:href"));
									print("...Processing the <"+
											StringEscapeUtils.unescapeHtml4(yearTopSubMenu.get(choice-1).html())+
											"> category");
								}else if(choice==2){
									String link= yearTopSubMenu.get(choice-1).attr("abs:href");
									doc = parseUrl(link, 0);
									
									//print menu title
									print("\n%s", StringEscapeUtils.unescapeHtml4(yearTopSubMenu.get(choice-1).html())+"\n");
									
									if(doc!=null){
										Elements elem = doc.select("select[id=selectoption]").select("option[value]");
										ArrayList<Integer> nums = new ArrayList<Integer>();
										
										for(int i=0; i<elem.size(); i++ ){
											//get the select category values and print the sub-menu
											int num = Integer.parseInt(elem.get(i).attr("value"));								
											//add them to list
											nums.add(num);
											print("%s.  %s", i+1, StringEscapeUtils.unescapeHtml4(elem.get(i).html().replace("Select category: ", "")));
											}
										
										System.out.print("\n" + "Please make a choise (1-" + elem.size() + "): ");
										
										//read user input
										br = new BufferedReader(new InputStreamReader(System.in));
										try {
											choice = Integer.parseInt(br.readLine());
										} catch (IOException e) {
											System.out.println("Error!");
											System.exit(1);
											}	
										if(choice<=elem.size() && choice>=1){
											int num = nums.get(choice-1);
											String added = "max=100&id="+num+"&";
											String newlink = link.replace("max=100&", added);

											//print("\nlink: %s", newlink); DEBUG print
											
											theUrls.add(newlink);
											System.out.println("...Processing the <"+
													StringEscapeUtils.unescapeHtml4(elem.get(choice-1).html().replace("Select category: ", ""))+
													"> category");
											print(elem.get(choice-1).select("a[href]").attr("abs:href"));
										}else{
											System.out.println("Wrong selection!");
											System.out.println("Exiting program...");
											System.exit(1);
										}		
									}else{
										System.out.println("ERROR: Cannot get links from server!");
										System.out.println("Exiting program...");
										System.exit(1);
									}
								}else{
									String link= yearTopSubMenu.get(choice-1).attr("abs:href");
									doc = parseUrl(link, 0);
									
									//print menu title
									print("\n%s", StringEscapeUtils.unescapeHtml4(yearTopSubMenu.get(choice-1).html())+"\n");
									
									if(doc!=null){
										Elements elem = doc.select("select[id=selectoption]").select("option[value]");
										ArrayList<Integer> nums = new ArrayList<Integer>();
										
										for(int i=0; i<elem.size(); i++ ){
											//get the select category values and print the sub-menu
											int num = Integer.parseInt(elem.get(i).attr("value"));								
											//add them to list
											nums.add(num);
											print("%s.  %s", i+1, StringEscapeUtils.unescapeHtml4(elem.get(i).html().replace("Select location: ", "")));
											}
										
										System.out.print("\n" + "Please make a choise (1-" + elem.size() + "): ");
										
										//read user input
										br = new BufferedReader(new InputStreamReader(System.in));
										try {
											choice = Integer.parseInt(br.readLine());
										} catch (IOException e) {
											System.out.println("Error!");
											System.exit(1);
											}	
										if(choice<=elem.size() && choice>=1){
											int num = nums.get(choice-1);
											String[] linkParts = link.split("&", 4);
											String finalLink = linkParts[0]+"&"+linkParts[1]+"&"+"id="+num+"&"+linkParts[3];

											//print("\nlink: %s \n link2: %s \n link3: %s \n link: %s \nsize: %s", linkParts[0], linkParts[1], linkParts[2], linkParts[3], linkParts.length); // DEBUG print
											//print(finalLink);
											
											theUrls.add(finalLink);
											System.out.println("...Processing the <"+
													StringEscapeUtils.unescapeHtml4(elem.get(choice-1).html().replace("Select category: ", ""))+
													"> category");
											print(elem.get(choice-1).select("a[href]").attr("abs:href"));
										}else{
											System.out.println("Wrong selection!");
											System.out.println("Exiting program...");
											System.exit(1);
										}		
									}else{
										System.out.println("ERROR: Cannot get links from server!");
										System.out.println("Exiting program...");
										System.exit(1);
									}									
								}
							}else{
								System.out.println("Wrong selection!");
								System.out.println("Exiting program...");
								System.exit(1);
							}	
						}else{
							System.out.println("ERROR: Cannot get links from server!");
							System.out.println("Exiting program...");
							System.exit(1);
						}
					}else{
						theUrls.add(li.get(choice-1).select("a[href").attr("abs:href"));
						System.out.println("...Processing the <"+
								StringEscapeUtils.unescapeHtml4(ratingsMenu.get(choice-1).html())+
								"> category");
						print(li.get(choice-1).select("a[href]").attr("abs:href") );	
					}
				}
		     }else{
		    	 System.out.println("Wrong selection!");
		    	 System.out.println("Exiting program...");
		    	 System.exit(1);
		     }
		     break;
		     
		case(5):
			System.out.println("Exiting program...");
			System.exit(0);
			break;

		default:
			System.out.println("Invalid choice! Exiting...");
			System.exit(1);
			break;

		}
	}//end method
}//end Class
