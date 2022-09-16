package edu.jsu.mcis.cs310;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class ConverterTest {
    
    private String csvString;
    private String jsonString;

    @Before
    public void setUp() {
        
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        
        // PART I: Get CSV Data
        
        // Create StringBuilder for CSV Data
        
        StringBuilder csv = new StringBuilder();
        
        // Open CSV Resource File
        
        try {
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(loader.getResourceAsStream("resources" + File.separator + "grades.csv")));
            
            String line;
            
            // Append CSV Lines to StringBuilder
            
            while((line = reader.readLine()) != null) {
                csv.append(line).append('\n');
            }
            
        }
        catch(IOException e) { e.printStackTrace(); }
        
        // Get CSV String
        
        csvString = csv.toString().trim();
        
        // PART II: Get JSON Data
        
        // Create StringBuilder for JSON Data
        
        StringBuilder json = new StringBuilder();
        
        // Open JSON Resource File
        
        try {
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(loader.getResourceAsStream("resources" + File.separator + "grades.json")));
            
            String line;
            
            // Append JSON Lines to StringBuilder
            
            while((line = reader.readLine()) != null) {
                json.append(line).append('\n');
            }
            
        }
        catch(IOException e) { e.printStackTrace(); }
        
        // Get JSON String
        
        jsonString = json.toString().trim();
        
    }
    
    @Test
    public void testConvertCSVtoJSON() {
        
        assertEquals(jsonString, Converter.csvToJson(csvString));
        
    }

    @Test
    public void testConvertJSONtoCSV() {
        
        assertEquals(csvString, Converter.jsonToCsv(jsonString));
        
    }
	
    @Test
    public void testConvertJSONtoCSVtoJSON(){
        
        String csv = Converter.jsonToCsv(jsonString);
        String json = Converter.csvToJson(csv);
        assertEquals(jsonString, json);

    }
	
    @Test
    public void testConvertCSVtoJSONtoCSV(){
        
        String json = Converter.csvToJson(csvString);
        String csv = Converter.jsonToCsv(json);
        assertEquals(csvString, csv);
        
    }    
}