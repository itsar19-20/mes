package itsar.mes.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import itsar.mes.model.StatoLinea;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

	private final WebClient webClient = WebClient.create("http://localhost:9090");
	
	@GetMapping("/")
	public String homepage( Model model) {
		
		List<StatoLinea> stati = this.webClient.get().uri("/statolinee")
														.accept(MediaType.APPLICATION_JSON)
									           			.retrieve()
									           			.bodyToFlux(StatoLinea.class)
									           			.collectList()
									           			.block(); 
		
		//background colors 
		for( int i=0; i < stati.size(); i++ ) {
			String color = bgcolor(stati.get(i).getStato()); 
			stati.get(i).setBgcolor(color);			
		}
		
		model.addAttribute("stati", stati);
						
		return "homepage"; 
	}
	
	private String bgcolor( String stato) {
		
		String color = ""; 

        if( stato.equals("avviata")){

            color = "lightgreen"; 

        }else if( stato.equals("ferma")){

            color = "white"; 

        }else if( stato.equals("errore")){

            color = "lightpink"; 

        }else if( stato.equals("pausa")){

            color = "lightyellow";             
        }

        return color;
	}
}
