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
	public static final  String userAgent = new String("Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
	public static String filePath = null;
	public static ArrayList<String> theUrls = new ArrayList<String>();
	/*
	 * ArrayList eradioLinks holds the final radio stations links along with their titles
	 * in this order:
	 * <Stationtitle>
	 * <StatonUrl>
	 */
	public static ArrayList<String> eradioLinks = new ArrayList<String>();
	
	
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
	        	doc = con.get();
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
