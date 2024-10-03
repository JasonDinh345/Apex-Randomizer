package com.jason.apex_randomizer.legend;


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

@RequestMapping(path = "api/v1/legends")
public class LegendController {
    private LegendService myService;

    @Autowired
    public LegendController(LegendService theService){
        myService = theService;
    }
	
    @GetMapping
    public List<Legend> getLegends(){
        return myService.getLegends();
    }
    @GetMapping("/{id}")
    public Legend getLegends(@PathVariable Long id){
        return myService.getLegendById(id);
    }

    @PostMapping
    public void addNewLegend(@RequestBody Legend theLegend){
        myService.addNewLegend(theLegend);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Legend> updateLegend(@PathVariable Long id, @RequestBody Legend updatedLegend) {
        return myService.updateLegend(id, updatedLegend);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Legend> deleteLegend(@PathVariable Long id){
        return myService.deleteLegend(id);
    }
}
