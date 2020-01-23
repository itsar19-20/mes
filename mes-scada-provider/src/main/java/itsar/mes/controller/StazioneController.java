package itsar.mes.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import itsar.mes.business.StatusManager;
import itsar.mes.business.StazioniManager;
import itsar.mes.model.Stazione;

@RestController
public class StazioneController {

	
	@RequestMapping(path = {"/stazioni", "/stazioni/{tagLinea}", "/stazioni/{tagLinea}/{tagStazione}"}, method = RequestMethod.GET )
	public @ResponseBody List<Stazione> stazioni(
			@PathVariable(required = false) String tagLinea,
			@PathVariable(required = false) String tagStazione) {
		List<Stazione> stazioni = StazioniManager.listStazioni();
		if (tagLinea != null) {
			stazioni = stazioni
					.stream()
					.filter(s -> s.getLinea().getTag().contentEquals(tagLinea))
					.collect(Collectors.toList());
		}
		if (tagStazione != null) {
			stazioni = stazioni
					.stream()
					.filter(s -> s.getTagStazione().contentEquals(tagStazione))
					.collect(Collectors.toList());
		}		
		return stazioni;
	}
	
	@RequestMapping(path = {"/stato-stazioni", "/stato-stazioni/{tagLinea}", "/stato-stazioni/{tagLinea}/{tagStazione}"}, method = RequestMethod.GET )
	public @ResponseBody List<Stazione> statoStazioni(
			@PathVariable(required = false) String tagLinea,
			@PathVariable(required = false) String tagStazione) {
		List<Stazione> stazioni = StazioniManager.listStazioni();
		if (tagLinea != null) {
			stazioni = stazioni
					.stream()
					.filter(s -> s.getLinea().getTag().contentEquals(tagLinea))
					.collect(Collectors.toList());
		}
		if (tagStazione != null) {
			stazioni = stazioni
					.stream()
					.filter(s -> s.getTagStazione().contentEquals(tagStazione))
					.collect(Collectors.toList());
		}
		stazioni.forEach(s -> {
			s.setStato(StatusManager.nextStatus(s.getStato()));
		});
		return stazioni;
	}
	
}
