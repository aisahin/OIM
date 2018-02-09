
package oim;

public class Helper {

    public Helper() { }

    public double convert(double degrees, double minutes, double seconds){
        
        double conversion;
        conversion = (degrees) + (minutes * 1/60 ) + (seconds * 1/60 * 1/60);
        System.out.println(conversion);
        return conversion;
    }
    
    public void SplitBySpace(String strToSplit){
        
        double degree, minutes, seconds;
        String[] items = strToSplit.split("\\s");
        
        degree = Double.parseDouble(items[0].replaceAll("([^\\p{ASCII}])|(\')|(\")", ""));
        minutes = Double.parseDouble(items[1].replaceAll("([^\\p{ASCII}])|(\')|(\")", ""));
        seconds = Double.parseDouble(items[2].replaceAll("([^\\p{ASCII}])|(\')|(\")", ""));
        convert(degree, minutes, seconds);
    }
}
