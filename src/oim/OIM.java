
package oim;

public class OIM {
    
    static String filename = "D:\\fotoToSort";
    static MetadataReader mdr = new MetadataReader();

    public static void main(String[] args) {        
        
        //mdr.readAndAssignMetadata(filename).forEach((key, value) -> System.out.println("--- " + key + " : " + value));
        mdr.readAndAssignMetadata(filename);
    }
}
