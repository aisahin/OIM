/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oim;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OIM {
    
    static String filename = "C:\\workspace\\fotoToSort";
    static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {        
        
        readMetadata();
    }
    
    private static File[] getAllFiles(String path) {
        
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }
    
    private static void readMetadata() {
        
        for(File file : getAllFiles(filename)) {
            
            try {
                
                Metadata metadata = ImageMetadataReader.readMetadata(file);
                print(metadata, "--- OUTPUT SEPARATOR FOR EACH IMAGE: " , file.getName() + "\n");
            }
            
            catch (ImageProcessingException | IOException e) { print(e); }
        }
    }
    
    private static Map<String, String> print(Metadata metadata, String method, String myFile ) { 
        
        Map<String, String> theThings = new HashMap<>();
        System.out.println();
        System.out.println(ANSI_RED + "================================================================================");
        System.out.print(method);
        System.out.println(ANSI_RED + "================================================================================");
        System.out.println();
        
        for (Directory directory : metadata.getDirectories()) {
            
            for (Tag tag : directory.getTags()) {
                
                if (tag.getTagName().equalsIgnoreCase("Date/Time Original") 
                    || tag.getTagName().equalsIgnoreCase("GPS Latitude Ref")
                    || tag.getTagName().equalsIgnoreCase("GPS Latitude")
                    || tag.getTagName().equalsIgnoreCase("GPS Longitude Ref")
                    || tag.getTagName().equalsIgnoreCase("GPS Longitude"))
                {
                    theThings.put(tag.getTagName(), tag.getDescription());
                }
            }
            
            for (String error : directory.getErrors()) {
                
                System.err.println("ERROR: " + error);
            }            
        }
        theThings.forEach((key, value) -> System.out.println(key + " : " + value));
        return theThings;
    }

    private static void print(Exception exception) {
        
        System.err.println("EXCEPTION: " + exception);
    }
}
