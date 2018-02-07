
package oim;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class MetadataReader {    
    
    private String _imageName;
    private Date _date;
    private String _GPSLatitudeRef;
    private String _GPSLatitude;
    private String _GPSLongitudeRef;
    private String _GPSLongitude;
            
    private Metadata metadata;
    private String imageName;
    private final HashMap<String, HashMap<String, String>> outerMap = new HashMap<>();
    private final HashMap<String, String> innerMap = new HashMap<>();
    
    // default constructor.
    public MetadataReader(){
    
    }
    
    // get images from specified directory.
    private static File[] getAllFiles(String path) {
        
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }    
    
    // read metadata for each image and add them to a HashMap of HashMap.
    public HashMap<String, HashMap<String, String>> readAndAssignMetadata(String path) {
        
        for(File file : getAllFiles(path)) {
            
            imageName = file.getName();
            
            try {
                
                metadata = ImageMetadataReader.readMetadata(file);
        
                for (Directory directory : metadata.getDirectories()) {

                    for (Tag tag : directory.getTags()) {

                        if (tag.getTagName().equalsIgnoreCase("Date/Time Original") 
                            || tag.getTagName().equalsIgnoreCase("GPS Latitude Ref")
                            || tag.getTagName().equalsIgnoreCase("GPS Latitude")
                            || tag.getTagName().equalsIgnoreCase("GPS Longitude Ref")
                            || tag.getTagName().equalsIgnoreCase("GPS Longitude"))
                        {
                            
                            innerMap.put(tag.getTagName(), tag.getDescription());
                        }
                    }

                    for (String error : directory.getErrors()) {

                        System.err.println("ERROR: " + error);
                    }            
                }
                
                outerMap.put(imageName, innerMap);
            }
            
            catch (ImageProcessingException | IOException e) { print(e); }
        }
                
        outerMap.forEach((key, value) -> System.out.println("--- " + key + " : " + value));
        
        return outerMap;
    }
    
    // print error while reading metadata of an image.
    private static void print(Exception exception) {
        
        System.err.println("EXCEPTION: " + exception);
    }
}
