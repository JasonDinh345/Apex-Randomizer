package com.jason.apex_randomizer.legendClass;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



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

    public LegendClass getLegendClass(Long id) {
        return getLegendClasses().stream().filter(legendClass -> legendClass.getID().equals(id)).findFirst().orElse(null);
    }

    public ResponseEntity<LegendClass> addNewLegendClass(LegendClass theClass) {
        Optional<LegendClass> optionalLegend = legendClassRepository.findLegendClassByName(theClass.getName());
        if(optionalLegend.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        legendClassRepository.save(theClass);
        return ResponseEntity.ok(theClass);
        
    }

    public ResponseEntity<LegendClass> updateLegendClass(Long id, LegendClass theClass) {
        LegendClass existingClass = getLegendClass(id);
        if(existingClass == null){
            return ResponseEntity.badRequest().build();
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

    public ResponseEntity<LegendClass> deleteLegendClass(Long id) {
        if(legendClassRepository.existsById(id)){
            legendClassRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
