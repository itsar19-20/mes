package itsar.mes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import itsar.mes.service.Constants;

import static org.assertj.core.api.Assertions.*; 


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WebLayerTests {

	@LocalServerPort	
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void shouldContainString() throws Exception {

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",	String.class)).contains("<html","</html>");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/login",	String.class)).contains("<html","</html>");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/quadro",	String.class)).contains("<html","</html>");

		//GREET CONTROLLER
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/greeting",	String.class)).contains("{");
		
		//AUTH CONTROLLER
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/login?nome=Giulio&password=123", null, String.class)).contains("{");
	
		//SCADA CONTROLLER
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/linea?codice=001",	String.class)).contains("{");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/linea?codice=002",	String.class)).contains("{");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/linea?codice=003",	String.class)).contains("{");

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/linea/001/stato",	String.class)).contains("{");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/linea/002/stato",	String.class)).contains("{");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/linea/003/stato",	String.class)).contains("{");
		
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/linea/001?stato=" + Constants.AVVIATA.getStato(), null, String.class)).isNotNull();
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/linea/001?stato=" + Constants.FERMA.getStato(), null, String.class)).isNotNull();
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/linea/001?stato=" + Constants.PAUSA.getStato(), null, String.class)).isNotNull();
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/linea/001?stato=" + Constants.ERRORE.getStato(), null, String.class)).isNotNull();

		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/linea/002?stato=" + Constants.AVVIATA.getStato(), null, String.class)).isNotNull();
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/linea/002?stato=" + Constants.FERMA.getStato(), null, String.class)).isNotNull();
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/linea/002?stato=" + Constants.PAUSA.getStato(), null, String.class)).isNotNull();
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/linea/002?stato=" + Constants.ERRORE.getStato(), null, String.class)).isNotNull();
		
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/linea/003?stato=" + Constants.AVVIATA.getStato(), null, String.class)).isNotNull();
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/linea/003?stato=" + Constants.FERMA.getStato(), null, String.class)).isNotNull();
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/linea/003?stato=" + Constants.PAUSA.getStato(), null, String.class)).isNotNull();
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/linea/003?stato=" + Constants.ERRORE.getStato(), null, String.class)).isNotNull();
	
	}
	
}
