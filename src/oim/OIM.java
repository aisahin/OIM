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

public class OIM {
    
    static String filename = "D:\\fotoToSort";
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
                print(metadata, "--- OUTPUT SEPARATOR FOR EACH IMAGE: " + file.getName() + "\n");
            }
            
            catch (ImageProcessingException | IOException e) { print(e); }
        }
    }
    
    private static void print(Metadata metadata, String method) {
        
        System.out.println();
        System.out.println(ANSI_RED + "================================================================================");
        System.out.print(method);
        System.out.println(ANSI_RED + "================================================================================");
        System.out.println();
        
        for (Directory directory : metadata.getDirectories()) {
            
            for (Tag tag : directory.getTags()) {
                
                //if (tag.getDirectoryName().equalsIgnoreCase("GPS"))
                
                System.out.printf("DIRECTORY NAME: %s *** TAG NAME: %s *** DESCRIPTION: %s \n",
                                   tag.getDirectoryName(), tag.getTagName(), tag.getDescription()
                                   );
            }
            
            for (String error : directory.getErrors()) {
                
                System.err.println("ERROR: " + error);
            }
        }
    }

    private static void print(Exception exception) {
        
        System.err.println("EXCEPTION: " + exception);
    }
}
