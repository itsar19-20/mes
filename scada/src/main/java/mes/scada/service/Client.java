package mes.scada.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import mes.scada.model.StatoStazione;
import reactor.core.publisher.Mono;

@Service
public class Client {

	private static Logger log = LoggerFactory.getLogger(Client.class);
		
	private final WebClient webClient;
	
	public Client() {
		
		this.webClient = WebClient.create("http://localhost:8080");
	}
	
	@Async
	@Scheduled(fixedRate = 5000)
	public void lineaUno() {
		
		this.webClient.get().uri("/aggiorna?codice=001").exchange().block(); 
	}
	
	@Async
	@Scheduled(fixedRate = 5000)
	public void lineaDue() {
		
		this.webClient.get().uri("/aggiorna?codice=002").exchange().block(); 
	}
	
	@Async
	@Scheduled(fixedRate = 5000)
	public void lineaTre() {
		
		this.webClient.get().uri("/aggiorna?codice=003").exchange().block(); 
	}
}