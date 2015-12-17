package pa.iscde.metrix;

import java.io.FileWriter;
import java.io.IOException;

public class HtmlFileWrite {
	
		//Delimiter used in HTML file
		private static final String BEGIN_HTML = "<html>";
		private static final String END_HTML = "</html>";
		private static final String BEGIN_HEAD = "<head>";
		private static final String END_HEAD = "</head>";
		private static final String BEGIN_BODY = "<body>";
		private static final String END_BODY = "</body>";
		
		
		

		public static void writeHtmlFile(String fileName) {
			
			
			
			FileWriter fileWriter = null;
					
			try {
				fileWriter = new FileWriter(fileName);
				
				
				//Write a HTML file
								
					
					fileWriter.append(BEGIN_HTML);
					fileWriter.append(BEGIN_HEAD);
					fileWriter.append(END_HEAD);
					fileWriter.append(BEGIN_BODY);
					fileWriter.append("DADOS");
					fileWriter.append(END_BODY);
					fileWriter.append(END_HTML);
					
			
				
				System.out.println("HTML file was created successfully !!!");
				
			} catch (Exception e) {
				System.out.println("Error in HTMLFileWriter !!!");
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
