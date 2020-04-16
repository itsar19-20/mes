package mes.scada;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import mes.scada.controller.LineaController;
import mes.scada.service.Client;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ScadaApplication {
	
	private static Logger log = LoggerFactory.getLogger(ScadaApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ScadaApplication.class, args);
	}
}
