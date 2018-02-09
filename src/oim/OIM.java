
package oim;

public class OIM {
    
    static String filename = "C:\\Users\\yerdu\\fotoToSort";
    static MetadataReader mdr = new MetadataReader();
    static Geocode gc = new Geocode();
    static Helper helper = new Helper();
    
    public static void main(String[] args) throws Exception {        
        
        //mdr.readAndAssignMetadata(filename).forEach((key, value) -> System.out.println("--- " + key + " : " + value));
        //mdr.readAndAssignMetadata(filename);
        //gc.GetGeocode(mdr.readAndAssignMetadata(filename));
        gc.sendGet(36.31698888888889, 33.877250000000004);
        //gc.convert(50, 6, 32.71);
        // helper.SplitBySpace("50° 6' 32.71\"");
    }
}
