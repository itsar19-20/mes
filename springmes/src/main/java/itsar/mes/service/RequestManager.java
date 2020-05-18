package itsar.mes.service;

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

import itsar.mes.model.StatoStazione;
import reactor.core.publisher.Mono;

@Service
public class RequestManager {

	/*
	private static Logger log = LoggerFactory.getLogger(RequestManager.class);
		
	private final WebClient webClient;
	
	public RequestManager( WebClient webClient) {
		
		this.webClient = webClient; 
	}
	
	@Async
	@Scheduled(fixedRate = 10000)
	public void lineaUno() {
		
		this.webClient.post().uri("/aggiorna?codice=001").exchange().block(); 
	}
	
	@Async
	@Scheduled(fixedRate = 10000)
	public void lineaDue() {
		
		this.webClient.post().uri("/aggiorna?codice=002").exchange().block(); 
	}
	
	@Async
	@Scheduled(fixedRate = 10000)
	public void lineaTre() {
		
		this.webClient.post().uri("/aggiorna?codice=003").exchange().block(); 
	}*/
}