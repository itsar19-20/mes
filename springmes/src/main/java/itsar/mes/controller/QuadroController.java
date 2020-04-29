package itsar.mes.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import itsar.mes.model.StatoLinea;
import itsar.mes.model.StatoStazione;

@Controller
public class QuadroController {

	private final WebClient webClient; 
	
	private static final String NOME_LINEA_UNO = "Linea Uno"; 
	private static final String NOME_LINEA_DUE = "Linea Due";
	private static final String NOME_LINEA_TRE = "Linea Tre"; 
	
	
	public QuadroController( WebClient webClient) {
		
		this.webClient = webClient; 
	}
	

	@GetMapping("/quadro")
	public String quadro(@RequestParam(name = "codice", required = true, defaultValue = "000") String codice, Model model) {
	
		switch( codice ) {
		
		case "001":
			model.addAttribute("nomeLinea", NOME_LINEA_UNO);
			model.addAttribute("codice",codice); 
			model.addAttribute("statoStazioni", getCurrentState(codice));
			break; 
		case "002":
			model.addAttribute("nomeLinea", NOME_LINEA_DUE);
			model.addAttribute("codice",codice); 
			model.addAttribute("statoStazioni", getCurrentState(codice));
			break; 
		case "003":
			model.addAttribute("nomeLinea", NOME_LINEA_TRE);
			model.addAttribute("codice",codice); 
			model.addAttribute("statoStazioni", getCurrentState(codice));
			break;
			
		default:
		}
		
		return "quadro"; 
	}
	
	private List<StatoStazione> getCurrentState(String codice){
		
		List<StatoStazione> result;
		
		result = this.webClient.get().uri("/linea?codice=" + codice)
				.accept(MediaType.APPLICATION_JSON)
       			.retrieve()
       			.bodyToFlux(StatoStazione.class)
       			.collectList()
       			.block();
		
		return result; 
	}
}
