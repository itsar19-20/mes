package itsar.mes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*; 

import itsar.mes.controller.AuthController;
import itsar.mes.controller.GreetingController;
import itsar.mes.controller.HomeController;
import itsar.mes.controller.LoginController;
import itsar.mes.controller.QuadroController;
import itsar.mes.controller.ScadaController;
import itsar.mes.service.LineaManager;
import itsar.mes.service.UpdateManager;

@SpringBootTest
class MesApplicationTests {

	@Autowired
	private AuthController auth; 
	
	@Autowired
	private GreetingController greet; 
	
	@Autowired
	private HomeController home; 
	
	@Autowired
	private LoginController login; 
	
	@Autowired
	private QuadroController quadro; 
	
	@Autowired 
	private ScadaController scada; 
	
	@Autowired
	private UpdateManager updateManager; 
	
	@Autowired 
	private LineaManager lineaManager; 
	
	
	@Test
	void contextLoads() {}

	@Test
	void controllers() throws Exception {
		
		assertThat(auth).isNotNull(); 
		assertThat(greet).isNotNull(); 
		assertThat(home).isNotNull(); 
		assertThat(login).isNotNull(); 
		assertThat(quadro).isNotNull(); 
		assertThat(scada).isNotNull(); 
	}
	
	@Test
	void services() throws Exception {
		
		assertThat(updateManager).isNotNull(); 
		assertThat(lineaManager).isNotNull(); 
	}
	
	
}
