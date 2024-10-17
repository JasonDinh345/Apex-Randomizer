package com.jason.apex_randomizer.legend;


import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jason.apex_randomizer.legendClass.LegendClass;
import com.jason.apex_randomizer.legendClass.LegendClassRepository;



@Service
public class LegendService {

	private LegendRepository legendRepository;
	private LegendClassRepository legendClassRepository;

	@Autowired
	public LegendService(LegendRepository theRepository, LegendClassRepository theRepo){
		legendRepository = theRepository;
		legendClassRepository = theRepo;
	}
	public ResponseEntity<?> getLegends(){
		return ResponseEntity.ok(legendRepository.findAll());
	}
	public ResponseEntity<?>  getLegendById(Long id){
		Legend theLegend = legendRepository.findAll().stream().filter(legend -> legend.getID().equals(id)).findFirst().orElse(null);
		if(theLegend == null){
			return new ResponseEntity<>("Legend with the given ID doesn't exist: " + id, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(theLegend);
	}
	public ResponseEntity<?> addNewLegend(Legend theLegend) {
		if(theLegend == null){
			return new ResponseEntity<>("Legend can't be null!", HttpStatus.FORBIDDEN);
		}
		Optional<Legend> legendOptional = legendRepository.findLegendByName(theLegend.getName());
		Optional<LegendClass> optionalClass = legendClassRepository.findLegendClassByName(theLegend.getClassName());
		if(legendOptional.isPresent()||!optionalClass.isPresent()){
			return new ResponseEntity<>("Legend already Exists: " + theLegend.getName(), HttpStatus.FORBIDDEN);
		}
		theLegend.setLegendClass(optionalClass.get());
		legendRepository.save(theLegend);
		return ResponseEntity.ok(theLegend);
	}

	public ResponseEntity<?> updateLegend(Long id, Legend updatedLegend){
		Legend existingLegend;
        try{
			existingLegend = legendRepository.findById(id).get();
		}catch(NoSuchElementException e){
			return new ResponseEntity<>("Legend with the given ID doesn't exist: " + id, HttpStatus.NOT_FOUND);
			
		}
        
        if (updatedLegend.getName() != null) {
            existingLegend.setName(updatedLegend.getName());
        }
        if (updatedLegend.getClassName() != null) {
			Optional<LegendClass> optionalClass = legendClassRepository.findLegendClassByName(updatedLegend.getClassName());
			if(!optionalClass.isPresent()){
				return new ResponseEntity<>("The given classname doesn't exist: " + updatedLegend.getClassName(), HttpStatus.NOT_FOUND);
			}

            existingLegend.setLegendClass(optionalClass.get());
        }
        if (updatedLegend.getImageURL() != null) {
            existingLegend.setImageURL(updatedLegend.getImageURL());
        }


		legendRepository.save(existingLegend);
		return ResponseEntity.ok(existingLegend);
	}
    public ResponseEntity<?> deleteLegend(Long id) {
		if (legendRepository.existsById(id)) {
            legendRepository.deleteById(id);
			return ResponseEntity.noContent().build();
        }
		return new ResponseEntity<>("Legend with the given ID doesn't exist: " + id, HttpStatus.NOT_FOUND);
    }
}
