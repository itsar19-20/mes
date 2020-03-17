package business;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import controllers.LineaController;

public class RequestManager {

	private static Logger log = LoggerFactory.getLogger(RequestManager.class);
	
	private static RequestManager instance;
	
	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	
	private RequestManager() {
		
	}
	
	public static RequestManager getInstance() {
    	
    	if( instance == null)
    		instance = new RequestManager();
    	
		return instance;
    }
	
	
	public String requestGET( String endpoint) throws IOException {
		
		try {
		
			GenericUrl url = new GenericUrl( endpoint);
	        HttpRequest request = HTTP_TRANSPORT.createRequestFactory().buildGetRequest(url);
	        HttpResponse response = request.execute();
	        log.debug("business: NetworkManager: requestGET(): " + response.getStatusCode()); 
	
	        InputStream streamIn = response.getContent();
	        String result = "";  
	        
	        int ch;
	        while ((ch = streamIn.read()) != -1) {
	            
	        	Character c = (char) ch;
	        	result += c.toString(); 
	        }
	        response.disconnect();
	        
	        return result; 
	        
		}catch( IOException e) {
			
			log.debug("business: RequestManager: requestGET():IOException");
			return null; 
		}
	}
}
