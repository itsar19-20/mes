package itsar.mes.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DatabaseController {

	
	@GetMapping("/database")
	public String db(@RequestParam(name = "codice", required = true, defaultValue = "000") String codice, Model model ){
		
		model.addAttribute("codice", codice); 
		
		return "database"; 
	}
	
	private void img( String codice) {
		
		BufferedImage img = new BufferedImage(540, 720, BufferedImage.TYPE_INT_ARGB);

		Color test = new Color(255,0,0);
		
		for( int x = 0; x < img.getWidth(); x++ ) {
			
			for( int y = 0; y < img.getHeight(); y++ ) {
					
				img.setRGB(x, y, test.getRGB());
			}
		}
		
		File output = new File("C:\\Users\\user\\eclipse-workspace\\springmes\\src\\main\\resources\\static\\images\\linea003.png");
		try {
			ImageIO.write( img, "png", output);
		} catch (IOException e) {
			
			System.out.println("debug: error"); 
		}
	}
}
