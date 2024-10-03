package com.jason.apex_randomizer.legend;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class LegendService {

	private LegendRepository legendRepository;

	@Autowired
	public LegendService(LegendRepository theRepository){
		legendRepository = theRepository;
	}
	public List<Legend> getLegends(){
		return legendRepository.findAll();
	}
	public ResponseEntity<Legend> addNewLegend(Legend theLegend) {
		Optional<Legend> legendOptional = legendRepository.findLegendByName(theLegend.getName());
		if(legendOptional.isPresent()){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		legendRepository.save(theLegend);
		return ResponseEntity.ok(theLegend);
	}

	public Legend getLegendById(Long id){
		return legendRepository.findAll().stream().filter(legend -> legend.getID().equals(id)).findFirst().orElse(null);
	}

	public ResponseEntity<Legend> updateLegend(Long id, Legend updatedLegend){
		Legend existingLegend;
        try{
			existingLegend = legendRepository.findById(id).get();
		}catch(NoSuchElementException e){
			return ResponseEntity.notFound().build();
			
		}
        
        if (updatedLegend.getName() != null) {
            existingLegend.setName(updatedLegend.getName());
        }
        if (updatedLegend.getClassName() != null) {
            existingLegend.setClassName(updatedLegend.getClassName());
        }
        if (updatedLegend.getImageURL() != null) {
            existingLegend.setImageURL(updatedLegend.getImageURL());
        }


		legendRepository.save(existingLegend);
		return ResponseEntity.ok(existingLegend);
	}
    public ResponseEntity<Legend> deleteLegend(Long id) {
		if (legendRepository.existsById(id)) {
            legendRepository.deleteById(id);
			return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
