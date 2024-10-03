package com.jason.apex_randomizer.legend;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class LegendService {

	private LegendRepository myRepository;

	@Autowired
	public LegendService(LegendRepository theRepository){
		myRepository = theRepository;
	}
	public List<Legend> getLegends(){
		return myRepository.findAll();
	}
	public void addNewLegend(Legend theLegend) {
		Optional<Legend> legendOptional = myRepository.findLegendByName(theLegend.getName());
		if(legendOptional.isPresent()){
			throw new IllegalStateException("Legend Already Exists");
		}
		myRepository.save(theLegend);
	}

	public Legend getLegendById(Long id){
		return myRepository.findAll().stream().filter(legend -> legend.getID().equals(id)).findFirst().orElse(null);
	}

	public ResponseEntity<Legend> updateLegend(Long id, Legend updatedLegend){
		Legend existingLegend;
        try{
			existingLegend = myRepository.findById(id).get();
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


		myRepository.save(existingLegend);
		return ResponseEntity.ok(existingLegend);
	}
    public ResponseEntity<Legend> deleteLegend(Long id) {
		if (myRepository.existsById(id)) {
            myRepository.deleteById(id);
			return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
