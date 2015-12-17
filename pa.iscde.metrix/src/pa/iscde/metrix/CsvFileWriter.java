package pa.iscde.metrix;



import java.io.FileWriter;
import java.io.IOException;



public class CsvFileWriter {
	
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	

	public static void writeCsvFile(String fileName) {
		
		
		
		FileWriter fileWriter = null;
				
		try {
			fileWriter = new FileWriter(fileName);
			
			
			//Write a CSV file
							
				
				fileWriter.append("HHHH");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append("IIII");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append("PPPP");
				fileWriter.append(NEW_LINE_SEPARATOR);
		
			
			System.out.println("CSV file was created successfully !!!");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
	}
}
