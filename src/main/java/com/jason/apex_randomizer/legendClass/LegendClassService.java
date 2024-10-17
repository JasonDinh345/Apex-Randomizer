package com.jason.apex_randomizer.legendClass;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;


@Service
public class LegendClassService {
    
    private LegendClassRepository legendClassRepository;

    @Autowired
    public LegendClassService(final LegendClassRepository theRepo){
        legendClassRepository = theRepo;
    }

    public List<LegendClass> getLegendClasses() {
       return legendClassRepository.findAll();
    }

    public ResponseEntity<?> getLegendClass(Long id) {
        LegendClass theLegendClass  = getLegendClasses().stream().filter(legendClass -> legendClass.getID().equals(id)).findFirst().orElse(null);
        if(theLegendClass == null){
            return new ResponseEntity<>("Legend Class with the given ID doesn't exist: " + id  , HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(theLegendClass);
    }

    public ResponseEntity<?> addNewLegendClass(LegendClass theClass) {
        Optional<LegendClass> optionalLegend = legendClassRepository.findLegendClassByName(theClass.getName());
        if(optionalLegend.isPresent()){
           return new ResponseEntity<>("Legend Class with the given name already exists!: " + theClass.getName(), HttpStatus.FORBIDDEN);
        }
        legendClassRepository.save(theClass);
        return ResponseEntity.ok(theClass);
        
    }

    public ResponseEntity<?> updateLegendClass(Long id, LegendClass theClass) {
        LegendClass existingClass;
        try{
            existingClass = legendClassRepository.findById(id).get();
        }catch(NoSuchElementException e){
            return new ResponseEntity<>("Legend Class with the given ID doesn't exist: " + id  , HttpStatus.NOT_FOUND);
        }
        if(theClass.getName() != null){
            existingClass.setName(theClass.getName());
        }
        if(theClass.getImageURL() != null){
            existingClass.setImageURL(theClass.getImageURL());
        }
        legendClassRepository.save(existingClass);
        return ResponseEntity.ok(existingClass);
    }

    public ResponseEntity<?> deleteLegendClass(Long id) {
        if(legendClassRepository.existsById(id)){
            legendClassRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>("Legend Class with the given ID doesn't exist: " + id, HttpStatus.NOT_FOUND);
    }

}
