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
package gui;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;
import javax.xml.parsers.ParserConfigurationException;
import static noThreads.DefaultCaller.*;
import noThreads.*;
import org.apache.commons.lang3.StringEscapeUtils;
import org.dom4j.DocumentException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

/**
 *
 * @author Dio
 */
public class TheGui extends javax.swing.JFrame {
    public static  final String newline = "\n";
    private enum PlaylistType {M3U, XSPF};
    private PlaylistType playlistType;
    private String filePath;
    //private boolean playlistType =false; //default for xspff, true fro m3u

    /**
     * Creates new form TheGui
     */
    public TheGui() {
        playlistType = PlaylistType.XSPF;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Eradioparser v2.0 - create playlists");
        JDialogClosed = false;
    }
    
    private void reinitialize(){
        playlistType = PlaylistType.XSPF;
        theUrls.clear();
        eradioLinks.clear();
        jRadioButton1.setEnabled(true);
        jRadioButton2.setEnabled(true);
        jRadioButton3.setEnabled(true);
        jRadioButton4.setEnabled(true);
        jButton1.setEnabled(true);
        jMenu2.setEnabled(true);
        jTextArea1.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Main Options");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jRadioButton1.setText("<html><b>Get</b> a playlist for all the stations at <b>e-radio.gr</b>.</html>");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jRadioButton2.setText("<html><b>View</b> the available <b>station Categories</b> and get a playlist.</html>");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jRadioButton3.setText("<html><b>View</b> the available <b>station Locations</b> and get a playlist.</html>");

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jRadioButton4.setText("<html><b>View</b> the station<b> Locations & Categories</b> and create a custom playlist.</html>");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setVerifyInputWhenFocusTarget(false);

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DefaultCaret caret = (DefaultCaret)jTextArea1.getCaret();  caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Program run messages");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/runIcon2.png"))); // NOI18N
        jButton1.setText("Run");
        jButton1.setIconTextGap(10);
        jButton1.setMaximumSize(new java.awt.Dimension(65, 30));
        jButton1.setMinimumSize(new java.awt.Dimension(65, 30));
        jButton1.setPreferredSize(new java.awt.Dimension(65, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setText("EradioParser v2.0");

        jMenuBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jMenu1.setText("File");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/exitIcon.png"))); // NOI18N
        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Options");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButtonMenuItem1);
        jRadioButtonMenuItem1.setText("Create .m3u playlist");
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jRadioButtonMenuItem1);

        buttonGroup2.add(jRadioButtonMenuItem2);
        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("Create .xspf playlist");
        jRadioButtonMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jRadioButtonMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("About");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/InfoIcon.png"))); // NOI18N
        jMenuItem2.setText("Info");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel1))
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 219, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(104, 104, 104)))
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(3, 3, 3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
        private class MenuOption1 implements Runnable{

        @Override
        public void run() {

            DefaultCaller caller = new DefaultCaller();
            final String URL= "http://e-radio.gr";
            Document doc = parseUrl(URL, 0);
            
            if(doc==null){
                JOptionPane.showMessageDialog(TheGui.this, "No connection to the server! Try again later! Exiting...", "No connection", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
            
            filePath = saveAs();
            
            if (filePath!=null){
                Elements links = doc.select("div[id=paneContainer]").select("a[href*=/locations/]");
                for(Element link : links)
                    theUrls.add(link.attr("abs:href"));
                
                jTextArea1.append("...Processing All <e-radio> station links" + newline);
                
                Thread thr = new Thread(new CoreCode());
                thr.start();
            }else{
                    jRadioButton1.setEnabled(true);
                    jRadioButton2.setEnabled(true);
                    jRadioButton3.setEnabled(true);
                    jRadioButton4.setEnabled(true);
                    jButton1.setEnabled(true);
                    jMenu2.setEnabled(true);                   
                }
        }
        }
        
        
    private class MenuOption2 implements Runnable{

        @Override
        public void run() {
            DefaultCaller caller = new DefaultCaller();
            final String URL= "http://e-radio.gr";
            Document doc = parseUrl(URL, 0);
                     
            //Get CATEGORIES
            if(doc==null){
                JOptionPane.showMessageDialog(TheGui.this, "No connection to the server! Try again later! Exiting...", "No connection", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
			
            Elements categoryLinks = doc.select("div[id=paneContainer]").select("a[href*=/categories/]");
            
            ArrayList<String> categories = new ArrayList<>();
            
            for(int i=0; i< categoryLinks.size();i++)
                categories.add(StringEscapeUtils.unescapeHtml4(categoryLinks.get(i).html()));

            
            String[] theCategories = new String[categories.size()];
            theCategories = categories.toArray(theCategories);
            String input = (String) JOptionPane.showInputDialog(TheGui.this, "Please select a station category",
        "Station categories", JOptionPane.QUESTION_MESSAGE, null, theCategories, theCategories[0]);
            
            if(input==null){
                jRadioButton1.setEnabled(true);
                jRadioButton2.setEnabled(true);
                jRadioButton3.setEnabled(true);
                jRadioButton4.setEnabled(true);
                jButton1.setEnabled(true);
             }
            else{
                filePath = saveAs();
                
                if (filePath!=null){
                    int choice = categories.indexOf(input);
                    print("category choice and input" + choice +" "+input);
                    
                    theUrls.add(categoryLinks.get(choice).attr("abs:href"));
                    //JOptionPane.showMessageDialog(TheGui.this, "Processing category <" + StringEscapeUtils.unescapeHtml4(categoryLinks.get(choice).html()) + ">.");

                    print("...Processing category <" +
                            StringEscapeUtils.unescapeHtml4(categoryLinks.get(choice).html()) 
                            +">.");
                    
                    Thread thr = new Thread(new CoreCode());
                    thr.start();
                }else{
                    jRadioButton1.setEnabled(true);
                    jRadioButton2.setEnabled(true);
                    jRadioButton3.setEnabled(true);
                    jRadioButton4.setEnabled(true);
                    jButton1.setEnabled(true);
                    jMenu2.setEnabled(true);                   
                }
            }
        }
    }
    
    
    private class MenuOption3 implements Runnable{

        @Override
        public void run() {
            DefaultCaller caller = new DefaultCaller();
            final String URL= "http://e-radio.gr";
            Document doc = parseUrl(URL, 0);
            //GET locations
            if(doc==null){
                JOptionPane.showMessageDialog(TheGui.this, "No connection to the server! Try again later! Exiting...", "No connection", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }

            
            Elements locationLinks = doc.select("div[id=paneContainer]").select("a[href*=/locations/]");
	
            ArrayList<String> locations = new ArrayList<>();
            
            for(int i=0; i< locationLinks.size();i++)
                locations.add(StringEscapeUtils.unescapeHtml4(locationLinks.get(i).html()));

            
            String[] theLocations = new String[locations.size()];
            theLocations = locations.toArray(theLocations);
            String input = (String) JOptionPane.showInputDialog(TheGui.this, "Please select a station location",
        "Station locations", JOptionPane.QUESTION_MESSAGE, null, theLocations, theLocations[0]);
            
            if(input==null){
                jRadioButton1.setEnabled(true);
                jRadioButton2.setEnabled(true);
                jRadioButton3.setEnabled(true);
                jRadioButton4.setEnabled(true);
                jButton1.setEnabled(true);
             }
            else{
                filePath = saveAs();
                
                if (filePath!=null){
                    int choice = locations.indexOf(input);
                    
                    theUrls.add(locationLinks.get(choice).attr("abs:href"));
                    
                    //JOptionPane.showMessageDialog(TheGui.this, "Processing location <" + StringEscapeUtils.unescapeHtml4(locationLinks.get(choice).html()) + ">.");

                    print("...Processing location <"+
                            StringEscapeUtils.unescapeHtml4(locationLinks.get(choice).html())
                            +">.");
                    Thread thr = new Thread(new CoreCode());
                    thr.start();
                }else{
                    jRadioButton1.setEnabled(true);
                    jRadioButton2.setEnabled(true);
                    jRadioButton3.setEnabled(true);
                    jRadioButton4.setEnabled(true);
                    jButton1.setEnabled(true);
                    jMenu2.setEnabled(true);                   
                }             
            }
        }
    }
    
    
    private class MenuOption4 implements Runnable{

        @Override
        public void run() {
            DefaultCaller caller = new DefaultCaller();
            final String URL= "http://e-radio.gr";
            Document doc = parseUrl(URL, 0);
            if(doc==null){
                JOptionPane.showMessageDialog(TheGui.this, "No connection to the server! Try again later! Exiting...", "No connection", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
            
            //get the locations
            Elements locationLinks = doc.select("div[id=paneContainer]").select("a[href*=/locations/]");
            ArrayList<String> locations = new ArrayList<>();
            
            for(int i=0; i< locationLinks.size();i++)
                locations.add(StringEscapeUtils.unescapeHtml4(locationLinks.get(i).html()));
            
            String[] theLocations = new String[locations.size()];
            theLocations = locations.toArray(theLocations);
            
            //get the categories 
            Elements categoryLinks = doc.select("div[id=paneContainer]").select("a[href*=/categories/]");
            ArrayList<String> categories = new ArrayList<>();
            
            for(int i=0; i< categoryLinks.size();i++)
                categories.add(StringEscapeUtils.unescapeHtml4(categoryLinks.get(i).html()));

            
            String[] theCategories = new String[categories.size()];
            theCategories = categories.toArray(theCategories);

            CustomDialog custom = new CustomDialog(TheGui.this, true, theLocations, theCategories, locationLinks, categoryLinks);
            custom.setVisible(true);
            
            if (JDialogClosed==true){
                jRadioButton1.setEnabled(true);
                jRadioButton2.setEnabled(true);
                jRadioButton3.setEnabled(true);
                jRadioButton4.setEnabled(true);
                jButton1.setEnabled(true); 
                jMenu2.setEnabled(true);
                theUrls.clear();
            }
            else{
                filePath = saveAs();
                if (filePath!=null){
                    Thread thr = new Thread(new CoreCode());
                    thr.start();
                }else{
                    jRadioButton1.setEnabled(true);
                    jRadioButton2.setEnabled(true);
                    jRadioButton3.setEnabled(true);
                    jRadioButton4.setEnabled(true);
                    jButton1.setEnabled(true);
                    jMenu2.setEnabled(true);   
                    theUrls.clear();
                }
            }   
        }
    }
    
    public class OpenFileFilter extends FileFilter {

    String description = "";
    String fileExt = "";

    public OpenFileFilter(String extension) {
        fileExt = extension;
    }

    public OpenFileFilter(String extension, String typeDescription) {
        fileExt = extension;
        description = typeDescription;
    }

    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        return (f.getName().toLowerCase().endsWith(fileExt));
    }

    @Override
    public String getDescription() {
        return description;
    }
    
    public String getFileExt() {
        return fileExt;
    }
}
    
    private String saveAs(){
        final JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(false);
        fc.setAcceptAllFileFilterUsed(false);
        OpenFileFilter m3u_filter = new OpenFileFilter(".m3u", "*.m3u, Playlist in m3u format");
        OpenFileFilter xspf_filter = new OpenFileFilter(".xspf", "*.xspf, Playlist in xspf format");
        
        
        if (playlistType == PlaylistType.M3U)
            fc.addChoosableFileFilter(new OpenFileFilter(".m3u", "*.m3u, Playlist in m3u format"));
        else
            fc.addChoosableFileFilter(new OpenFileFilter(".xspf", "*.xspf, Playlist in xspf format"));

        
        int returnVal = fc.showSaveDialog(this);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            filePath = file.getPath();
	                
	    jTextArea1.append("Saving: " + file.getName() + "." + newline);            
            if (playlistType == PlaylistType.M3U)
                return file.getPath()+".m3u";
            else
                return file.getPath()+".xspf";
      

        }else {
	    jTextArea1.append("Save command cancelled by user." + newline);
            return null;
        }
    }
      
    
    public class CoreCode implements Runnable{

        @Override
        public void run() {
            try {
                coreActions();
            } catch (IOException ex) {
                Logger.getLogger(TheGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        private void coreActions() throws IOException{  
            ArrayList<String> diskFiles = new ArrayList<>();    
            //variables that hold time in msec, in order to calculate
            //how much time lasts a program execution
            long total_time=0, startTime, endTime;
            startTime = System.currentTimeMillis();
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
            try {
                pl2.getSecondLinks(pl1.getStationLinks1());
                pl2.getFinalLinks(pl2.getStationLinks2(), pl1.getTitles());
            } catch (IOException ex) {
                Logger.getLogger(TheGui.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Playlist p = new Playlist();
            
            switch (playlistType) {
            case M3U:
                p.createM3uPlaylist(filePath);
                break;
                    
            case XSPF:
                try {
                    p.createPlaylist(filePath);
                } catch (    IOException | DocumentException | SAXException | ParserConfigurationException ex) {
                    Logger.getLogger(TheGui.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
                 
		
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
		
            jTextArea1.append("\n\nRUN SUMMARY:\n" +
            "Playlist successfully generated! \n"+
				"Elapsed time: "+ total_time+" msec\n"+
				"Parsed: "+
				pl1.getStationLinks1().size()+
				" station links. \n"+
				"Valid links: " +
				DefaultCaller.eradioLinks.size()/2+
				"/"+
				pl1.getStationLinks1().size());
            
            JOptionPane.showMessageDialog(TheGui.this, "Playlist created to: " 
                    + newline 
                    + filePath 
                    + newline
                    +"Parsed: "
                    +pl1.getStationLinks1().size()
                    +" station links. \n"
                    +"Valid links: " 
                    +DefaultCaller.eradioLinks.size()/2
                    +"/"
                    +pl1.getStationLinks1().size());   
            reinitialize();
    }
        
    }

        
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jMenu2.setEnabled(false);
        
        if(jRadioButton1.isSelected()){
             Thread t = new Thread(new MenuOption1());
             t.start();      
           
            jRadioButton1.setEnabled(false);
            jRadioButton2.setEnabled(false);
            jRadioButton3.setEnabled(false);
            jRadioButton4.setEnabled(false);
            jButton1.setEnabled(false);
        }
        else if(jRadioButton2.isSelected()){
            Thread t = new Thread(new MenuOption2());
             t.start();

            jRadioButton1.setEnabled(false);
            jRadioButton2.setEnabled(false);
            jRadioButton3.setEnabled(false);
            jRadioButton4.setEnabled(false);
            jButton1.setEnabled(false);
        }
        else if(jRadioButton3.isSelected()){
            Thread t = new Thread(new MenuOption3());
             t.start();

            jRadioButton1.setEnabled(false);
            jRadioButton2.setEnabled(false);
            jRadioButton3.setEnabled(false);
            jRadioButton4.setEnabled(false);
            jButton1.setEnabled(false);            
            
        }
        else if(jRadioButton4.isSelected()){
            jRadioButton1.setEnabled(false);
            jRadioButton2.setEnabled(false);
            jRadioButton3.setEnabled(false);
            jRadioButton4.setEnabled(false);
            jButton1.setEnabled(false);   
            
            Thread t = new Thread(new MenuOption4());
            t.start();            
        }
        else{
            jMenu2.setEnabled(true);
            JOptionPane.showMessageDialog(this, "You need to make a choice!");
        }
        
     

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JOptionPane.showMessageDialog(this, "EradioParser v2.0 "
                +newline
                +"Copyright (C) 2013 Lappas Dionysis "
                +newline
                +"Released under the GNU General Public License v3.0");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButtonMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem1ActionPerformed
        playlistType = PlaylistType.M3U;
    }//GEN-LAST:event_jRadioButtonMenuItem1ActionPerformed

    private void jRadioButtonMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem2ActionPerformed
         playlistType = PlaylistType.XSPF;
    }//GEN-LAST:event_jRadioButtonMenuItem2ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed

    public javax.swing.JTextArea getTextArea1(){
        return jTextArea1;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TheGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TheGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TheGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TheGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TheGui().setVisible(true);
            }
        });
    }
    public static boolean JDialogClosed;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
