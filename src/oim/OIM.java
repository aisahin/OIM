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
import com.google.code.geocoder.Geocoder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
/**
 *
 * @author aliihsan.sahin
 */
public class OIM {
    
    static String filename ="src/oim/foto.jpg";

    public static void main(String[] args)
    {
        File file = new File("src/oim/foto.jpg");

        // There are multiple ways to get a Metadata object for a file

        //
        // SCENARIO 1: UNKNOWN FILE TYPE
        //
        // This is the most generic approach.  It will transparently determine the file type and invoke the appropriate
        // readers.  In most cases, this is the most appropriate usage.  This will handle JPEG, TIFF, GIF, BMP and RAW
        // (CRW/CR2/NEF/RW2/ORF) files and extract whatever metadata is available and understood.
        //
        try {
            
            Metadata metadata = ImageMetadataReader.readMetadata(file);

            print(metadata, "Using ImageMetadataReader\n");
        } 
        
        catch (ImageProcessingException | IOException e) { print(e); }
    }

    /**
     * Write all extracted values to stdout.
     */
    private static void print(Metadata metadata, String method)
    {
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.print(method);
        System.out.println("-------------------------------------------------");
        System.out.println();

        //
        // A Metadata object contains multiple Directory objects
        //
        for (Directory directory : metadata.getDirectories()) {

            //
            // Each Directory stores values in Tag objects
            //
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }

            //
            // Each Directory may also contain error messages
            //
            for (String error : directory.getErrors()) {
                System.err.println("ERROR: " + error);
            }
        }
    }

    private static void print(Exception exception)
    {
        System.err.println("EXCEPTION: " + exception);
    }
}
