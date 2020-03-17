package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseWriter {
	
	
	private static Logger log = LoggerFactory.getLogger(ResponseWriter.class);
	
	/*
	 * methods
	 */
	public void write( String sourceLocation, HttpServletResponse response) {
		
		BufferedReader source = null;
		
		try { 
			
			source = new BufferedReader( new FileReader( sourceLocation)); 
			
			for( String line = source.readLine(); line != null; line = source.readLine()) {
				
				response.getWriter().append( line).flush();
			}		
			
			source.close();
	
		}catch( IOException e) {
			
			log.debug("business: ResponseWriter: write(): IOException");
			if( source != null ){
				
				try {
					
					source.close();
				
				}catch( IOException ee) {
					
					log.debug("business: ResponseWriter: write(): IOException: .close() error");
				}
			}
		}
	}
	
}
