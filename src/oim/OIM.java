/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oim;

public class OIM {
    
    static String filename = "D:\\fotoToSort";
    static MetadataReader mdr = new MetadataReader();

    public static void main(String[] args) {        
        
        // readMetadata();
        mdr.readAndAssignMetadata(filename);
    }
}
