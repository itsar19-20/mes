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


/*
 * 	WORK IN PROGRESS...
 */

@Controller
public class DatabaseController {

	
	@GetMapping("/database")
	public String db(@RequestParam(name = "codice", required = true, defaultValue = "000") String codice, Model model ){
		
		model.addAttribute("codice", codice); 
		
		return "database"; 
	}
}
