package com.jason.apex_randomizer.legendClass;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/legendClasses")
public class LegendClassController {
    
    private LegendClassService legendClassService;

    @Autowired
    public LegendClassController(final LegendClassService theService){
        legendClassService = theService;
    }
    
    @GetMapping
    public List<LegendClass> getLegendClasses(){
        return legendClassService.getLegendClasses();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getLegendClass(@PathVariable Long id){
        return legendClassService.getLegendClass(id);
    }
    @PostMapping
    public ResponseEntity<?> addNewLegendClass(@RequestBody LegendClass theClass){
        return legendClassService.addNewLegendClass(theClass);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateLegendClass(@PathVariable Long id, @RequestBody LegendClass theClass){
        return legendClassService.updateLegendClass(id, theClass);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLegendClass(@PathVariable Long id){
        return legendClassService.deleteLegendClass(id);
    }
}
