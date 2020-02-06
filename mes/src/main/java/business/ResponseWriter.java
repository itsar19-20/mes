package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import javax.servlet.http.HttpServletResponse;

public class ResponseWriter {
	
	/*
	 * default constructor
	 */
	public ResponseWriter() {
		
	}
	
	/*
	 * methods
	 */
	public void write( String sourceLocation, HttpServletResponse response) throws IOException {
		
		BufferedReader source = new BufferedReader( new FileReader( sourceLocation)); 
		
		for( String line = source.readLine(); line != null; line = source.readLine()) {
			
			response.getWriter().append( line).flush();
		}		
	
		source.close(); 
	}
	
}
